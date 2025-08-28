package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CommandManager;

/**
 * Команда вывода справки по доступным командам.
 */
public class HelpCommand implements Command {
    private final CommandManager commandManager;

    /**
     * Создает новую команду справки.
     *
     * @param commandManager менеджер команд
     */
    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду вывода справки.
     *
     * @param request запрос на выполнение команды
     * @return ответ со списком доступных команд и их описанием
     */
    @Override
    public Response execute(Request request) {
        final StringBuilder helpText = new StringBuilder();
        helpText.append("Доступные команды:").append("\n");
        helpText.append(commandManager.getCommandsDescription());
        helpText.append("logout: Выйти из аккаунта");
        return Response.ok(
            helpText.toString(),
            null, null,
            request.getClientId()
        );
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
} 
