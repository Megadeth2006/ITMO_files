package itmo.programming.manager;

/**
 * Работа с выдачей уникального id для элементов коллекций.
 */
public class IdManager {

    private static int id = 1;
    private static CollectionManager collection;

    /**
     * Определить коллекцию в менеджере.
     *
     * @param collection коллекция.
     */
    public static void setCollectionManager(CollectionManager collection) {
        IdManager.collection = collection;
    }

    /**
     * Сгенерировать уникальный id.
     */
    public static int generateId() {
        if (collection == null) {
            throw new NullPointerException("CollectionManager не инициализирован");
        }
        while (collection.findById(id) != null) {
            id++;

        }
        return id;
    }
}
