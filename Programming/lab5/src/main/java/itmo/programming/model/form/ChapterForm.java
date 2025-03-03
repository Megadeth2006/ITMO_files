package itmo.programming.model.form;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Chapter;

public class ChapterForm extends Form<Chapter> {
    private final ConsoleManager console;

    public ChapterForm(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    @Override
    public Chapter build() {
        return new Chapter(askString("Chapter.name", "(Поле не может быть null, Строка не может быть пустой)",
                s -> !s.isEmpty()),
                askString("Chapter.parentlegion", "", s -> true));
    }
}
