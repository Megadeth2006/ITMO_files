package itmo.programming.client.manager;

import itmo.programming.client.parser.CommandParser;
import itmo.programming.client.parser.ParsedCommand;
import itmo.programming.client.request.RequestDisplay;
import itmo.programming.client.request.RequestFactory;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import java.io.IOException;

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
    public ConsoleManager(RequestFactory requestFactory, RequestSender sender) {
        this.inputManager = new ConsoleInputManager();
        this.parser = new CommandParser();
        this.requestFactory = requestFactory;
        this.sender = sender;
    }

    /**
     * Запуск интерактивного режима клиента.
     */
    public void start() {
        System.out.println("Клиент запущен. Введите 'help' для получения списка команд.");

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
                    final Response response = sender.send(request);
                    RequestDisplay.displayResponse(response);
                }

            } catch (IOException e) {
                System.out.println("Ошибка при обработке команды: " + e.getMessage());
            }
        }

        try {
            inputManager.close();
        } catch (IOException e) {
            System.out.println("Ошибка при закрытии ввода");
        }
    }
}
