package itmo.programming.server.manager;


import itmo.programming.common.model.SpaceMarine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Менеджер коллекции на сервере.
 */
public class CollectionManager {
    private TreeSet<SpaceMarine> collection;
    private final Date initializationDate;

    /**
     * Создает новый менеджер коллекции.
     * Инициализирует пустую коллекцию и настраивает сериализацию.
     */
    public CollectionManager() {
        this.collection = new TreeSet<>();
        this.initializationDate = new Date();
    }

    /**
     * Заменяет текущую коллекцию на новую.
     *
     * @param newCollection новая коллекция SpaceMarine
     */
    public void setCollection(TreeSet<SpaceMarine> newCollection) {
        collection.clear();
        collection.addAll(newCollection);
    }

    /**
     * Возвращает копию текущей коллекции.
     *
     * @return копия TreeSet с элементами коллекции
     */
    public synchronized TreeSet<SpaceMarine> getCollection() {
        return new TreeSet<>(collection);
    }

    /**
     * Добавляет нового SpaceMarine в коллекцию.
     *
     * @param spaceMarine добавляемый объект
     * @return true, если объект успешно добавлен
     */
    public synchronized boolean add(SpaceMarine spaceMarine, String username) {
        if (spaceMarine == null) {
            return false;
        }
        spaceMarine.setOwner(username);
        return collection.add(spaceMarine);
    }

    /**
     * Удаляет все элементы, принадлежащие указанному пользователю.
     *
     * @param owner имя владельца
     */
    public synchronized void clear(String owner) {
        collection.removeIf(marine -> owner.equals(marine.getOwner()));
    }

    /**
     * Удаляет объект по id, если он принадлежит указанному пользователю.
     *
     * @param id идентификатор объекта
     * @param owner имя владельца
     * @return true, если объект был удалён
     */
    public synchronized boolean removeById(Integer id, String owner) {
        return collection.removeIf(
                marine -> marine.getId().equals(id) && owner.equals(marine.getOwner()));
    }

    /**
     * Обновляет объект по id, если он принадлежит указанному пользователю.
     *
     * @param id идентификатор объекта
     * @param newSpaceMarine новый объект
     * @param owner имя владельца
     * @return true, если обновление успешно
     */
    public synchronized boolean updateById(Integer id, SpaceMarine newSpaceMarine, String owner) {
        final Optional<SpaceMarine> target = collection.stream()
                .filter(m -> m.getId().equals(id) && owner.equals(m.getOwner()))
                .findFirst();

        if (target.isPresent()) {
            collection.remove(target.get());
            newSpaceMarine.setId(id);
            newSpaceMarine.setOwner(owner);
            return collection.add(newSpaceMarine);
        }
        return false;
    }

    /**
     * Добавляет объект, если он больше текущего максимального в коллекции.
     *
     * @param spaceMarine объект для добавления
     * @return true, если добавление выполнено
     */
    public synchronized boolean addIfMax(SpaceMarine spaceMarine) {
        return collection.isEmpty() || spaceMarine.compareTo(
                collection.last()) > 0;
    }

    /**
     * Добавляет объект, если он меньше текущего минимального в коллекции.
     *
     * @param spaceMarine объект для добавления
     * @return true, если добавление выполнено
     */
    public synchronized boolean addIfMin(SpaceMarine spaceMarine) {
        return collection.isEmpty() || spaceMarine.compareTo(
                collection.first()) < 0 && collection.add(spaceMarine);
    }

    /**
     * Вычисляет сумму всех значений поля health в коллекции.
     *
     * @return сумма значений health
     */
    public synchronized double sumOfHealth() {
        return collection.stream().mapToDouble(SpaceMarine::getHealth).sum();
    }

    /**
     * Возвращает список элементов с указанным значением achievements.
     *
     * @param achievements значение поля achievements
     * @return список подходящих элементов
     */
    public synchronized List<SpaceMarine> filterByAchievements(String achievements) {
        return collection.stream()
                .filter(m -> Objects.equals(m.getAchievements(), achievements))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список элементов в порядке возрастания.
     *
     * @return отсортированный список элементов
     */
    public synchronized List<SpaceMarine> getAscending() {
        return collection.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Возвращает информацию о текущей коллекции.
     *
     * @return строка с описанием типа, даты и размера
     */
    public synchronized String getInfo() {
        return String.format("Тип: %s\nДата инициализации: %s\nКоличество элементов: %d",
                collection.getClass().getSimpleName(), initializationDate, collection.size());
    }

    /**
     * Получить всю коллекцию в строковом формате.
     *
     * @return string.
     */
    public synchronized String getAllString() {
        final ArrayList<SpaceMarine> spaceMarines = new ArrayList<>(collection);
        final StringBuilder stringBuilder = new StringBuilder();
        for (SpaceMarine spaceMarine : spaceMarines) {
            stringBuilder.append(spaceMarine).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Возвращает все элементы коллекции.
     *
     * @return список всех элементов
     */
    public synchronized Collection<SpaceMarine> getAll() {
        return new ArrayList<>(collection);
    }

    /**
     * Проверяет наличие элемента с заданным id.
     *
     * @param id идентификатор
     * @return true, если элемент существует
     */
    public synchronized boolean existsById(Integer id) {
        return collection.stream().anyMatch(m -> m.getId().equals(id));
    }

    /**
     * Проверяет наличие элемента с заданным id и owner.
     *
     * @param id id
     * @param owner владелец.
     * @return boolean
     */
    public synchronized boolean checkOwner(Integer id, String owner) {
        return collection.stream().anyMatch(
                m -> m.getId().equals(id) && m.getOwner().equals(owner));
    }


}
