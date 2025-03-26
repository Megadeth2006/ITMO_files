package itmo.programming.manager;

import itmo.programming.model.SpaceMarine;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Класс для взаимодействия с коллекцией.
 */
public class CollectionManager {
    /**
     * Время инициализации коллекции.
     */
    public Date initTime = new Date();
    /**
     * Хеш-словарь для хранения объектов модели с быстрым обращением по ключу - id.
     */
    private final HashMap<Integer, SpaceMarine> models = new HashMap<>();
    /**
     * Коллекция.
     */

    private final TreeSet<SpaceMarine> collection = new TreeSet<>();

    /**
     * Добавить объект модели в коллекцию.
     *
     * @param object объект.
     */
    public void add(SpaceMarine object) {
        if (isExist(object)) {
            return;
        }
        models.put(object.getId(), object);
        collection.add(object);
    }

    /**
     * Обновить элемент коллекции по id.
     *
     * @param id id.
     * @param object объект.
     */
    public boolean updateById(Integer id, SpaceMarine object) {
        if (!isExist(object)) {
            return false;
        }
        collection.remove(findById(id));
        models.remove(id);
        models.put(id, object);
        collection.add(object);
        return true;
    }

    /**
     * Удалить элемент коллекции по id.
     *
     * @param id id.
     */
    public boolean removeById(int id) {
        final var object = findById(id);
        if (object == null) {
            return false;
        }
        models.remove(object.getId());
        collection.remove(object);

        return true;
    }

    /**
     * Очистить коллекцию.
     */
    public boolean clear() {
        initTime = new Date();
        collection.clear();
        return true;
    }


    /**
     * Добавить элемент в коллекцию,
     * если его значение превышает значение наибольшего элемента этой коллекции.
     *
     * @param object объект.
     */
    public boolean addIfMax(SpaceMarine object) {
        if (collection.isEmpty() || collection.first().compareTo(object) < 0) {
            collection.add(object);
            return true;
        }
        return false;
    }

    /**
     * Добавить элемент в коллекцию,
     * если его значение меньше, чем у наименьшего элемента этой коллекции.
     *
     * @param object объект.
     */
    public boolean addIfMin(SpaceMarine object) {
        if (collection.isEmpty() || collection.first().compareTo(object) > 0) {
            collection.add(object);
            return true;
        }
        return false;
    }

    /**
     * Вычислить сумму значений health у всех элементов коллекции.
     */
    public long sumOfHealth() {
        long healthSum = 0;
        for (SpaceMarine values : collection) {
            healthSum += values.getHealth();
        }
        return healthSum;
    }

    /**
     * Возвращает элементы, значение поля achievements которых равно заданному.
     *
     * @param achievement achievement.
     */
    public List<SpaceMarine> filterByAchievements(String achievement) {
        if (!Objects.equals(achievement, "")) {
            return collection.stream().filter(object ->
                    (object.getAchievements().contains(achievement))).toList();
        } else {
            return collection.stream().filter(object ->
                    (Objects.equals(object.getAchievements(), ""))).toList();
        }
    }

    /**
     * Получить коллекцию.
     */
    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Метод для получения элементов коллекции в формате строки.
     */
    public String getCollectionAsString() {
        String collectionString = "";
        if (!collection.isEmpty()) {
            for (SpaceMarine object : collection) {
                collectionString += object;
                collectionString += "\n";
            }
        } else {
            collectionString = "Нечего выводить...\n";
        }
        return collectionString;
    }

    /**
     * Получить размер коллекции.
     */
    public long getCollectionSize() {
        return collection.size();
    }

    /**
     * Получить тип коллекции.
     */

    public String getCollectionType() {
        return collection.getClass().getTypeName();
    }

    /**
     * Получить коллекцию по id.
     *
     * @param id id.
     */
    public SpaceMarine findById(int id) {
        return models.get(id);
    }

    /**
     * Проверка существования элемента в коллекции.
     *
     * @param object object.
     */
    public boolean isExist(SpaceMarine object) {
        return ((object != null) && (findById(object.getId()) != null));
    }

    /**
     * Получить элементы коллекции в порядке возрастания.
     */
    public ArrayList<SpaceMarine> getAscending() {
        return new ArrayList<>(collection);
    }

}
