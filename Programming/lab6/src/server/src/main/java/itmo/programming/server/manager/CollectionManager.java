package itmo.programming.server.manager;


import itmo.programming.common.model.SpaceMarine;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Менеджер коллекции на сервере.
 */
public class CollectionManager {
    private TreeSet<SpaceMarine> collection;
    private final Date initializationDate;
    private int nextId = 1;

    /**
     * Создает новый менеджер коллекции.
     * Инициализирует пустую коллекцию и настраивает сериализацию.
     */
    public CollectionManager() {
        this.collection = new TreeSet<>(Comparator.comparingDouble(SpaceMarine::getDistance));
        this.initializationDate = new Date();
    }

    /**
     * Устанавливает значение коллекции.
     *
     * @param collection коллекция.
     */
    public void setCollection(TreeSet<SpaceMarine> collection) {
        this.collection = collection;
    }

    /**
     * Возвращает коллекцию.
     *
     */
    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param spaceMarine элемент для добавления
     * @return true если элемент добавлен успешно
     */
    public boolean add(SpaceMarine spaceMarine) {
        if (spaceMarine == null) {
            return false;
        }

        // Генерируем и устанавливаем id до добавления в коллекцию
        if (spaceMarine.getId() == null) {
            // Находим следующий свободный id
            while (collection.stream().anyMatch(sm -> sm.getId() != null && sm.getId() == nextId)) {
                nextId++;
            }
            try {
                spaceMarine.setId(nextId++);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        return collection.add(spaceMarine);
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Удаляет элемент по id.
     *
     * @param id id элемента
     * @return true если элемент удален успешно
     */
    public boolean removeById(Integer id) {
        return collection.removeIf(marine -> marine.getId().equals(id));
    }

    /**
     * Обновляет элемент по id.
     *
     * @param id id элемента
     * @param newSpaceMarine новый элемент
     * @return true если элемент обновлен успешно
     */
    public boolean updateById(Integer id, SpaceMarine newSpaceMarine) {
        final Optional<SpaceMarine> marineToUpdate = collection.stream()
                .filter(marine -> marine.getId().equals(id))
                .findFirst();

        if (marineToUpdate.isPresent()) {
            collection.remove(marineToUpdate.get());
            newSpaceMarine.setId(id);
            collection.add(newSpaceMarine);
            return true;
        }
        return false;
    }

    /**
     * Добавляет элемент, если он больше максимального.
     *
     * @param spaceMarine элемент для добавления
     * @return true если элемент добавлен успешно
     */
    public boolean addIfMax(SpaceMarine spaceMarine) {
        if (collection.isEmpty()) {
            return add(spaceMarine);
        }

        final SpaceMarine max = collection.stream()
                .max(Comparator.naturalOrder())
                .orElse(null);

        if (spaceMarine.compareTo(max) > 0) {
            return add(spaceMarine);
        }
        return false;
    }

    /**
     * Добавляет элемент, если он меньше минимального.
     *
     * @param spaceMarine элемент для добавления
     * @return true если элемент добавлен успешно
     */
    public boolean addIfMin(SpaceMarine spaceMarine) {
        if (collection.isEmpty()) {
            return add(spaceMarine);
        }

        final SpaceMarine min = collection.stream()
                .min(Comparator.naturalOrder())
                .orElse(null);

        if (spaceMarine.compareTo(min) < 0) {
            return add(spaceMarine);
        }
        return false;
    }

    /**
     * Возвращает сумму значений поля health.
     *
     * @return сумма значений поля health
     */
    public double sumOfHealth() {
        return collection.stream()
                .mapToDouble(SpaceMarine::getHealth)
                .sum();
    }

    /**
     * Фильтрует коллекцию по полю achievements.
     *
     * @param achievements значение для фильтрации
     * @return отфильтрованный список
     */
    public List<SpaceMarine> filterByAchievements(String achievements) {
        return collection.stream()
                .filter(marine -> marine.getAchievements().equals(achievements))
                .collect(Collectors.toList());
    }

    /**
     * Получить отсортированный список элементов.
     */
    public List<SpaceMarine> getAscending() {
        return collection.stream()
                .sorted(Comparator.comparing(SpaceMarine::getName,
                                Comparator.nullsFirst(String::compareTo))
                        .thenComparing(SpaceMarine::getId,
                                Comparator.nullsFirst(Integer::compareTo)))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает информацию о коллекции.
     *
     * @return строка с информацией
     */
    public String getInfo() {
        return String.format(
            "Тип: %s%n"
            + "Дата инициализации: %s%n"
            + "Количество элементов: %d",
            collection.getClass().getSimpleName(),
            initializationDate,
            collection.size()
        );
    }

    /**
     * Возвращает все элементы коллекции.
     *
     * @return коллекция элементов
     */
    public Collection<SpaceMarine> getAll() {
        final List<SpaceMarine> all = collection.stream()
            .sorted()
            .collect(Collectors.toList());
        return all;
    }

    /**
     * Проверяет существование элемента по id.
     *
     * @param id идентификатор элемента
     * @return true если элемент существует
     */
    public boolean existsById(Integer id) {
        return collection.stream()
            .anyMatch(marine -> marine.getId().equals(id));
    }

    /**
     * Обновляет nextId на основе максимального id в коллекции.
     */
    public void updateNextId() {
        if (!collection.isEmpty()) {
            nextId = collection.stream()
                    .mapToInt(SpaceMarine::getId)
                    .max()
                    .getAsInt() + 1;
        } else {
            nextId = 1;
        }
    }

}
