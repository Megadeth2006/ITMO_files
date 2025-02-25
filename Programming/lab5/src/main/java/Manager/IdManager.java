package Manager;

// TODO: дописать логику

public class IdManager {
    private static int id = 1;
    private static CollectionManager collectionManager;

    private IdManager(){

    }
    public static void setCollectionManager(CollectionManager collectionManager){
        IdManager.collectionManager = collectionManager;
    }
    public static int generateId(){
        if (collectionManager == null){
            throw new NullPointerException("CollectionManager не инициализован");
        }
        while (collectionManager.findById(id) != null){
            id++;

        }
        return id;
    }
}
