package Manager;

import Model.SpaceMarine;

import java.util.*;



public class CollectionManager {

    public static Date initTime = new Date();
    private HashMap<Integer, SpaceMarine> models = new HashMap<>();
    private TreeSet<SpaceMarine> collection = new TreeSet<>();


    public boolean add(SpaceMarine object){
        if (isExist(object)) return false;
        models.put(object.getId(), object);
        collection.add(object);
        return true;

    }
    public boolean updateById(int id, SpaceMarine object){
        if (!isExist(object)) return false;
        collection.remove(findById(id));
        models.put(id, object);
        collection.add(object);
        return true;
    }

    public boolean removeById(int id){
        var object = findById(id);
        if (object == null) return false;
        models.remove(object.getId());
        collection.remove(object);
 
        return true;
    }

    public boolean clear(){
        initTime = new Date();
        collection.clear();
        return true;
    }
    public boolean addIfMax(SpaceMarine object){
        if (collection.isEmpty() || collection.first().compareTo(object) < 0){
            collection.add(object);
            return true;
        }
        return false;
    }
    public boolean addIfMin(SpaceMarine object){
        if (collection.isEmpty() || collection.first().compareTo(object) > 0){
            collection.add(object);
            return true;
        }
        return false;
    }
    public long sumOfHealth(){
        long healthSum = 0;
        for (SpaceMarine values: collection ){
            healthSum += values.getHealth();
        }
        return healthSum;
    }
    public List<SpaceMarine> filterByAchievements(String achievement){
        return collection.stream().filter(object -> object.getAchievements().contains(achievement)).toList();
    }
    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }
    public SpaceMarine findById(int id){
        return models.get(id);
    }
    public boolean isExist(SpaceMarine object){
        return (object != null && findById(object.getId()) == null);
    }

    public List<SpaceMarine> printAscending(){
        ArrayList<SpaceMarine> list = null;
        list.addAll(collection);
        return list;
    }

}
