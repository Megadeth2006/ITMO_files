package itmo.programming.server.manager;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.auth.LoginCommand;
import itmo.programming.server.auth.RegisterCommand;
import itmo.programming.server.commands.AddCommand;
import itmo.programming.server.commands.AddIfMaxCommand;
import itmo.programming.server.commands.AddIfMinCommand;
import itmo.programming.server.commands.ClearCommand;
import itmo.programming.server.commands.Command;
import itmo.programming.server.commands.ExecuteScriptCommand;
import itmo.programming.server.commands.FilterByAchievementsCommand;
import itmo.programming.server.commands.HelpCommand;
import itmo.programming.server.commands.InfoCommand;
import itmo.programming.server.commands.PrintAscendingCommand;
import itmo.programming.server.commands.RemoveByIdCommand;
import itmo.programming.server.commands.ShowCommand;
import itmo.programming.server.commands.SumOfHealthCommand;
import itmo.programming.server.commands.UpdateCommand;
import itmo.programming.server.database.CollectionDaO;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер команд сервера.
 */
public class CommandManager {
    private final Map<String, Command> commands;
    private final CollectionManager collectionManager;
    private final AuthManager authManager;
    private final CollectionDaO collectiondao;

    /**
     * Создает новый менеджер команд.
     *
     * @param collectionManager менеджер коллекции для работы с данными
     */
    public CommandManager(CollectionManager collectionManager,
                          AuthManager authManager, CollectionDaO collectiondao) {
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>();
        this.authManager = authManager;
        this.collectiondao = collectiondao;
        initCommands();
    }

    /**
     * Возвращает карту доступных команд.
     *
     * @return карта, где ключ - имя команды, значение - объект команды
     */
    public Map<String, Command> getCommands() {
        return this.commands;
    }

    /**
     * Инициализирует команды.
     */
    private void initCommands() {
        commands.put("help", new HelpCommand(this));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, collectiondao));
        commands.put("update", new UpdateCommand(collectionManager, collectiondao));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, collectiondao));
        commands.put("clear", new ClearCommand(collectionManager, collectiondao));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager, collectiondao));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, collectiondao));
        commands.put("sum_of_health", new SumOfHealthCommand(collectionManager));
        commands.put("filter_by_achievements", new FilterByAchievementsCommand(collectionManager));
        commands.put("print_ascending", new PrintAscendingCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("login", new LoginCommand(authManager));
        commands.put("register", new RegisterCommand(authManager));

    }

    /**
     * Выполняет команду.
     *
     * @param request запрос от клиента
     * @return ответ клиенту
     */
    public Response executeCommand(Request request) throws SQLException, NoSuchAlgorithmException {
        final Command command = commands.get(request.getCommandName());
        if (command == null) {
            return Response.error(
                "Неизвестная команда: " + request.getCommandName(),
                "CommandNotFound",
                null,
                request.getClientId()
            );
        }


        if (command instanceof LoginCommand || command instanceof RegisterCommand) {
            return command.execute(request);
        }


        if (request.getUser() == null) {
            return Response.error(
                "Необходима авторизация для выполнения команды",
                "AuthenticationRequired",
                null,
                request.getClientId()
            );
        }

        try {
            if (!authManager.login(request.getUser())) {
                return Response.error(
                    "Неверные учетные данные",
                    "InvalidCredentials",
                    null,
                    request.getClientId()
                );
            }
        } catch (NoSuchAlgorithmException e) {
            return Response.error(
                "Ошибка при проверке авторизации: " + e.getMessage(),
                "AuthenticationError",
                null,
                request.getClientId()
            );
        }

        return command.execute(request);
    }

    /**
     * Возвращает описания всех команд.
     *
     * @return строка с описаниями команд
     */
    public String getCommandsDescription() {
        final StringBuilder sb = new StringBuilder();
        if (commands != null) {
            commands.forEach((name, command) -> {
                final String description = command != null ? command.getDescription() : null;
                if (description != null) {
                    sb.append(description).append("\n");
                }
            });
        }

        return sb.toString();
    }
}
