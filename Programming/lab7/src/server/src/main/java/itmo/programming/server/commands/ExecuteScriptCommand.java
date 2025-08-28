package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.user.User;
import itmo.programming.server.manager.CommandManager;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


/**
 * Команда для выполнения скрипта из файла.
 * Читает и выполняет команды из указанного файла.
 */
public class ExecuteScriptCommand implements Command {
    private final CommandManager commandManager;

    /**
     * Выполняет скрипт из полотна строк.
     *
     * @param commandManager менеджер команд для выполнения команд из скрипта
     */
    public ExecuteScriptCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) throws SQLException, NoSuchAlgorithmException {
        if (request.getData() == null) {
            return Response.error(
                "Данные скрипта отсутствуют",
                "NullScriptData", null,
                request.getClientId()
            );
        } else {
            final User user = request.getUser();
            final String[] output = (String[]) request.getData();
            final StringBuilder executionResults = new StringBuilder();
            boolean allCommandsSuccessful = true;

            for (String commandLine : output) {
                final String[] commandParts = commandLine.trim().split("\\s+", 2);
                if (commandParts.length == 0) {
                    executionResults.append("Пустая команда пропущена.\n");
                    allCommandsSuccessful = false;
                    continue;
                }
                final String commandName = commandParts[0];
                final String[] commandArgs = commandParts.length > 1 ? commandParts[1].split(
                        "\\s+", 2) : new String[0];
                final Response response = commandManager.executeCommand(
                        new Request(commandName, commandArgs, null, user, request.getClientId())
                );

                final Object responseData = response.getData();
                if (responseData == null) {
                    executionResults.append(response.getMessage()).append("\n");
                } else {
                    executionResults.append("Исполнение команды"
                           + " (").append(commandName).append(
                                   "):").append("\n").append(response.getMessage()).append(
                                           "\n").append(responseData).append("\n");

                }
                if (!response.isSuccess()) {
                    allCommandsSuccessful = false;
                }


            }
            if (allCommandsSuccessful) {
                return Response.ok(
                        "Все команды скрипта выполнены успешно: \n"
                                + executionResults.toString(), null, null, request.getClientId());

            } else {
                return Response.error(
                        "Некоторые команды в скрипте не были выполнены:\n "
                                + executionResults.toString(),
                        "PartOfCommands", null,  request.getClientId());
            }





        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла";
    }
}
