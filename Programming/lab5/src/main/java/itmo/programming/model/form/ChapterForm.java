package itmo.programming.model.form;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Chapter;

/**
 * Класс для запрашивания и заполнения значений полей Chapter.
 */
public class ChapterForm extends Form<Chapter> {
    private final ConsoleManager console;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */
    public ChapterForm(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    /**
     * Запрос в консоль и заполнение полей Chapter.
     */
    @Override
    public Chapter build() {
        return new Chapter(askString("Chapter.name",
                "(Поле не может быть null, Строка не может быть пустой)",
                s -> !s.isEmpty()),
                askString("Chapter.parentlegion", "", s -> true));
    }
}
