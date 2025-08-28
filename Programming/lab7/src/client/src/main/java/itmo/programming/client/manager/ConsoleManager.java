package itmo.programming.client.manager;

import static itmo.programming.client.manager.AuthManager.authorize;

import itmo.programming.client.parser.CommandParser;
import itmo.programming.client.parser.ParsedCommand;
import itmo.programming.client.request.RequestDisplay;
import itmo.programming.client.request.RequestFactory;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.user.User;
import java.io.IOException;
import java.util.Objects;


/**
 * Класс, который реализует интерактивный режим.
 */
public class ConsoleManager {
    private final ConsoleInputManager inputManager;
    private final CommandParser parser;
    private final RequestFactory requestFactory;
    private final RequestSender sender;
    private boolean running = true;

    /**
     * Конструктор.
     *
     * @param requestFactory фабрика запросов
     * @param sender отправитель запросов
     */
    public ConsoleManager(RequestFactory requestFactory,
                          RequestSender sender) {

        this.inputManager = new ConsoleInputManager();
        this.parser = new CommandParser();
        this.requestFactory = requestFactory;
        this.sender = sender;
    }


    /**
     * Запуск интерактивного режима клиента.
     */
    public void start() {
        System.out.println("Клиент запущен. Сначала нужно войти или зарегистрироваться");
        System.out.println("Введите 'login' или 'register'");
        while (running) {
            try {
                final String input = inputManager.readLine();
                if (input == null || input.equalsIgnoreCase("exit")) {
                    running = false;
                    continue;
                }

                final ParsedCommand parsed = parser.parse(input);
                final String commandName = parsed.getCommand();
                final String[] args = parsed.getArguments();
                final Request request = requestFactory.createRequest(commandName, args);
                if (request != null) {
                    try {
                        final Response response = sender.send(request);
                        if (response.isSuccess()) {
                            if (Objects.equals(response.getMessage(),
                                    "Успешный вход") || Objects.equals(response.getMessage(),
                                    "Успешная регистрация")) {
                                final User currentUser = request.getUser();
                                authorize(currentUser);
                                System.out.println("Введите 'help', чтобы показать список команд");
                            }
                            RequestDisplay.displayResponse(response);
                        } else {
                            System.out.println("Ошибка: " + response.getMessage());
                            if (
                                    "AuthenticationRequired".equals(response.getErrorDetails())
                                            || "InvalidCredentials".equals(
                                                    response.getErrorDetails())) {
                                System.out.println("Необходимо войти в систему");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ошибка при отправке запроса: " + e.getMessage());
                        System.out.println("Проверьте подключение к серверу");
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении команды: " + e.getMessage());
            }
        }

        try {
            inputManager.close();
        } catch (IOException e) {
            System.out.println("Ошибка при закрытии ввода");
        }
    }
}
