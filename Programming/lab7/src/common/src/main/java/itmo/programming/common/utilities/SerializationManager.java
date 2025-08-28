package itmo.programming.common.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Менеджер сериализации для преобразования объектов в байты и обратно.
 */
public class SerializationManager {
    private static final int BUFFER_SIZE = 65536; // 64KB buffer

    /**
     * Сериализует объект в массив байтов.
     *
     * @param obj объект для сериализации
     * @return массив байтов
     * @throws IOException при ошибке сериализации
     */
    public static byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
            objectStream.writeObject(obj);
            return byteStream.toByteArray();
        }
    }

    /**
     * Десериализует объект из массива байтов.
     *
     * @param bytes массив байтов
     * @return десериализованный объект
     * @throws IOException при ошибке десериализации
     * @throws ClassNotFoundException если класс объекта не найден
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectStream = new ObjectInputStream(byteStream)) {
            return objectStream.readObject();
        }
    }

    /**
     * Создает буфер для сериализации.
     *
     * @return буфер заданного размера
     */
    public static ByteBuffer createBuffer() {
        return ByteBuffer.allocate(BUFFER_SIZE);
    }

    /**
     * Преобразует массив байтов в ByteBuffer.
     *
     * @param bytes массив байтов
     * @return ByteBuffer
     */
    public static ByteBuffer wrapData(byte[] bytes) {
        return ByteBuffer.wrap(bytes);
    }
}
