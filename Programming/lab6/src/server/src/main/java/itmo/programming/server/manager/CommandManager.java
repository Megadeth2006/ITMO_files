package itmo.programming.server.manager;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер команд сервера.
 */
public class CommandManager {
    private final Map<String, Command> commands;
    private final CollectionManager collectionManager;

    /**
     * Создает новый менеджер команд.
     *
     * @param collectionManager менеджер коллекции для работы с данными
     */
    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>();
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
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager));
        commands.put("sum_of_health", new SumOfHealthCommand(collectionManager));
        commands.put("filter_by_achievements", new FilterByAchievementsCommand(collectionManager));
        commands.put("print_ascending", new PrintAscendingCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(this));

    }

    /**
     * Выполняет команду.
     *
     * @param request запрос от клиента
     * @return ответ клиенту
     */
    public Response executeCommand(Request request) {
        final Command command = commands.get(request.getCommandName());
        if (command == null) {
            return Response.error(
                "Неизвестная команда: " + request.getCommandName(),
                "CommandNotFound",
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
        commands.forEach((name, command) -> 
            sb.append(command.getDescription()).append("\n")
        );
        return sb.toString();
    }
}
