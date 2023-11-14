package melchior.clement.back.manager;

import java.util.ArrayList;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.MontainObjectCategoryEnum;
import melchior.clement.back.entity.object.list.Carte;
import melchior.clement.back.entity.object.list.Gaz;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.utilities.Mathematiques;

public class InventoryManager {
    private static InventoryManager instance;
    private ArrayList <MontainObject> objects;

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    private InventoryManager () {
        this.objects = new ArrayList<>();
    } 

    public void setStartInventory() {
        System.out.println("Setting inventory");
        for (int i = 0; i < 6 + Mathematiques.alea(4); i++) {
            this.objects.add(new Viande());
        }
        for (int i = 0; i < 3 + Mathematiques.alea(2); i++) {
            this.objects.add(new Gaz());
        }
        for (int i = 0; i < 3 + Mathematiques.alea(2); i++) {
            this.objects.add(new Carte());
        }
        this.objects.addAll(ObjectManager.getInstance().getRandomObjectAndDelete(2, MontainObjectCategoryEnum.ENTERTAINMENT));
    }

    public ArrayList <MontainObject> getRandomObjectAndDelete (int num) {
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        for (int i=0; i<=num; i++) {
            int alea = Mathematiques.alea(objects.size() - 1);
            objectsLost.add(objects.get(alea));
            objects.remove(alea);
        }
        return objectsLost;
    }

    public ArrayList <MontainObject> getRandomObject (int num) {
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        for (int i=0; i<=num; i++) {
            int alea = Mathematiques.alea(objects.size() - 1);
            objectsLost.add(objects.get(alea));
        }
        return objectsLost;
    }


    public void add (MontainObject object) {
        this.objects.add(object);
    }

    public void add (ArrayList <MontainObject> objects) {
        this.objects.addAll(objects);
    }

    public void remove (MontainObject object) {
        this.objects.remove(object);
        ObjectManager.getInstance().add(object);
    }

    public void remove (ArrayList <MontainObject> objects) {
        ObjectManager.getInstance().add(objects);
        this.objects.removeAll(objects);
    }

    public void remove (int idObject) {
        for (MontainObject o : objects) {
            if (o.getId() == idObject) {
                ObjectManager.getInstance().add(objects);
                this.objects.removeAll(objects);   
                break;     
            }
        }
    }

    /*
     * return true if object o is in inventory, false otherwise
     */
    public boolean isInIventory (MontainObject o) {
        for (MontainObject object : this.objects) {
            if (object.equals(o)) {
                return true;
            }
        }
        return false;
    }


    // getter and setter 
    public ArrayList<MontainObject> getObjects() {
        return objects;
    }

    public ArrayList<MontainObject> getObjects (MontainObjectCategoryEnum category) {
        ArrayList <MontainObject> objectsTemp = new ArrayList<MontainObject>();
        for (MontainObject o : objects) {
            if (o.getCategory() == category) {
                objectsTemp.add(o);
            }
        }
        return objects;
    }

    public void setObjects(ArrayList<MontainObject> objects) {
        this.objects = objects;
    }
}
