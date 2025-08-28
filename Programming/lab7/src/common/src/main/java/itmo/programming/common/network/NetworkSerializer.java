package itmo.programming.common.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Утилитарный класс для сериализации и десериализации объектов по сети.
 */
public class NetworkSerializer {
    private static final Logger LOGGER = Logger.getLogger(NetworkSerializer.class.getName());
    private static final int MAX_OBJECT_SIZE = 10 * 1024 * 1024; // 10 MB

    /**
     * Сериализует объект в массив байтов.
     *
     * @param obj объект для сериализации
     * @return массив байтов
     * @throws IOException при ошибке сериализации
     */
    public static byte[] serialize(Object obj) throws IOException {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot serialize null object");
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            
            LOGGER.fine("Serializing object of type: " + obj.getClass().getName());
            oos.writeObject(obj);
            
            final byte[] data = baos.toByteArray();
            if (data.length > MAX_OBJECT_SIZE) {
                throw new IOException("Serialized object size exceeds maximum allowed size");
            }
            
            LOGGER.fine("Successfully serialized object. Size: " + data.length + " bytes");
            return data;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during serialization", e);
            throw new IOException("Failed to serialize object: " + e.getMessage(), e);
        }
    }

    /**
     * Десериализует массив байтов в объект.
     *
     * @param data массив байтов для десериализации
     * @return десериализованный объект
     * @throws IOException при ошибке десериализации
     * @throws ClassNotFoundException если класс объекта не найден
     */
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Cannot deserialize null or empty data");
        }
        
        if (data.length > MAX_OBJECT_SIZE) {
            throw new IOException("Data size exceeds maximum allowed size");
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            
            LOGGER.fine("Deserializing data of size: " + data.length + " bytes");
            final Object obj = ois.readObject();
            LOGGER.fine("Successfully deserialized object of type: " + obj.getClass().getName());
            return obj;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during deserialization", e);
            throw new IOException("Failed to deserialize data: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Class not found during deserialization", e);
            throw new ClassNotFoundException("Failed to deserialize data: " + e.getMessage(), e);
        }
    }
}
