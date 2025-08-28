package itmo.programming.client.commands;

import itmo.programming.common.network.Request;


/**
 * Неизвестные команды – формирует запрос без данных.
 */
public class Default implements Base {

    @Override
    public Request execute(String[] args)  {
        System.out.println("Введена неизвестная команда");
        return null;
    }
}
