package itmo.programming.model.make;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Chapter;

/**
 * Класс для запрашивания и заполнения значений полей Chapter.
 */
public class ChapterMake extends Make<Chapter> {

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */
    public ChapterMake(ConsoleManager console) {
        super(console);
    }

    /**
     * Запрос в консоль и заполнение полей Chapter.
     */
    @Override
    public Chapter build() {
        return new Chapter(askString("Chapter.name",
                "(String, Поле не может быть null, Строка не может быть пустой)",
                s -> !s.isEmpty()),
                askString("Chapter.parentlegion", "(String)", s -> true));
    }
}
