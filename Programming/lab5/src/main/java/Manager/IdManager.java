package Manager;

// TODO: дописать логику

public class IdManager {
    private static int id = 1;
    private static CollectionManager collection;

    private IdManager(){

    }
    public static void setCollectionManager(CollectionManager collection){
        IdManager.collection = collection;
    }
    public static int generateId(){
        if (collection == null){
            throw new NullPointerException("CollectionManager не инициализован");
        }
        while (collection.findById(id) != null){
            id++;

        }
        return id;
    }
}
