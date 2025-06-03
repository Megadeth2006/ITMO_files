package itmo.programming.client.request;

import itmo.programming.client.commands.Add;
import itmo.programming.client.commands.AddIfMax;
import itmo.programming.client.commands.AddIfMin;
import itmo.programming.client.commands.Base;
import itmo.programming.client.commands.Clear;
import itmo.programming.client.commands.Default;
import itmo.programming.client.commands.ExecuteScript;
import itmo.programming.client.commands.FilterByAchievements;
import itmo.programming.client.commands.Help;
import itmo.programming.client.commands.Info;
import itmo.programming.client.commands.PrintAscending;
import itmo.programming.client.commands.RemoveById;
import itmo.programming.client.commands.Show;
import itmo.programming.client.commands.SumOfHealth;
import itmo.programming.client.commands.Update;
import itmo.programming.client.manager.AskManager;
import itmo.programming.client.manager.RequestSender;
import itmo.programming.common.network.Request;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, формирующий запросы на основе введенных пользователем команд.
 * RequestFactory хранит словарь всех доступных команд
 * и позволяет создать объект Request, который будет отправлен на сервер.
 */
public class RequestFactory {
    private final Map<String, Base> commands;

    /**
     * Метод, инициализирующий генераторы запросов.
     *
     * @param askManager askManager.
     */
    public RequestFactory(AskManager askManager, RequestSender requestSender) {
        commands = new HashMap<>();
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("add", new Add(askManager));
        commands.put("update", new Update(askManager, requestSender));
        commands.put("remove_by_id", new RemoveById());
        commands.put("clear", new Clear());
        commands.put("add_if_max", new AddIfMax(askManager));
        commands.put("add_if_min", new AddIfMin(askManager));
        commands.put("sum_of_health", new SumOfHealth());
        commands.put("filter_by_achievements", new FilterByAchievements());
        commands.put("print_ascending", new PrintAscending());
        commands.put("execute_script", new ExecuteScript());
    }

    /**
     * Метод, создающий запрос серверу.
     *
     * @param commandName имя команды
     * @param args аргументы команды
     */
    public Request createRequest(String commandName, String[] args) throws IOException {
        final Base command = commands.getOrDefault(commandName.toLowerCase(), new Default());
        return command.execute(args);
    }
}
