package itmo.programming.client.request;

import itmo.programming.client.auth.Login;
import itmo.programming.client.auth.Logout;
import itmo.programming.client.auth.Register;
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
import itmo.programming.client.manager.AuthManager;
import itmo.programming.client.manager.ConsoleInputManager;
import itmo.programming.client.manager.RequestSender;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
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
    private User currentUser;

    /**
     * Метод, инициализирующий генераторы запросов.
     *
     * @param askManager askManager.
     */
    public RequestFactory(AskManager askManager,
                          RequestSender requestSender, AuthManager authManager) {
        commands = new HashMap<>();
        commands.put("help", new Help(authManager));
        commands.put("info", new Info(authManager));
        commands.put("show", new Show(authManager));
        commands.put("add", new Add(askManager, authManager));
        commands.put("update", new Update(askManager, requestSender, authManager));
        commands.put("remove_by_id", new RemoveById(authManager));
        commands.put("clear", new Clear(authManager));
        commands.put("add_if_max", new AddIfMax(askManager, authManager));
        commands.put("add_if_min", new AddIfMin(askManager, authManager));
        commands.put("sum_of_health", new SumOfHealth(authManager));
        commands.put("filter_by_achievements", new FilterByAchievements(authManager));
        commands.put("print_ascending", new PrintAscending(authManager));
        commands.put("execute_script", new ExecuteScript(authManager));
        commands.put("login", new Login(new ConsoleInputManager(), authManager));
        commands.put("register", new Register(new ConsoleInputManager(), authManager));
        commands.put("logout", new Logout(authManager));
    }

    /**
     * Поставить текущего юзера.
     *
     * @param currentUser текущий пользователь.
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Устанавливает текущего пользователя (после логина/регистрации).
     */
    public void setUser(User user) {
        this.currentUser = user;
    }

    /**
     * Получить пользователя.
     *
     * @return user.
     */
    public User getUser() {
        return currentUser;
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
