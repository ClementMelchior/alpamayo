package melchior.clement.back.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.MontainObjectCategoryEnum;
import melchior.clement.back.entity.object.list.*;
import melchior.clement.utilities.Mathematiques;

public class ObjectManager {
    private static Logger LOGGER = Logger.getLogger(ObjectManager.class);

    private static ObjectManager instance;
    public ArrayList <MontainObject> foodObjects;
    public ArrayList <MontainObject> heatObjects;
    public ArrayList <MontainObject> entertainObjects;
    public ArrayList <MontainObject> utilityObjects;
    public ArrayList <MontainObject> healthObjects;
    public ArrayList <MontainObject> specialObjects;

    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
        }
        return instance;
    }

    private ObjectManager () {
        foodObjects();
        heatObjects();
        entertainObjects();
        utilityObjects();
        healthObjects();
        specialObjects();
    }

    public void reset () {
        LOGGER.info("Resetting object");
        foodObjects();
        heatObjects();
        entertainObjects();
        utilityObjects();
        healthObjects();
        specialObjects();
    }

    private void foodObjects() {
        this.foodObjects = new ArrayList<> ();
        this.foodObjects.add(new Viande());
    }

    private void heatObjects() {
        this.heatObjects = new ArrayList<> ();
        this.heatObjects.add(new Gaz());
    }

    private void entertainObjects() {
        this.entertainObjects = new ArrayList<> ();
        this.entertainObjects.add(new LSD());
        this.entertainObjects.add(new Pinot());
        this.entertainObjects.add(new Carte());
        this.entertainObjects.add(new Flute());
        this.entertainObjects.add(new Cigarettes());
    }

    private void healthObjects() {
        this.heatObjects = new ArrayList<> ();
        this.heatObjects.add(new Soin());
    }

    private void utilityObjects() {
        this.utilityObjects = new ArrayList<> ();
        this.utilityObjects.add(new Fusil());
        this.utilityObjects.add(new Grapin());
        this.utilityObjects.add(new Livre());
        this.utilityObjects.add(new Torche());
        this.utilityObjects.add(new Couteau());
    }

    private void specialObjects() {
        this.specialObjects = new ArrayList<> ();
        this.specialObjects.add(new Viande());
        this.specialObjects.add(new Peau());
        this.specialObjects.add(new Bois());
        this.specialObjects.add(new Scootch());
        this.specialObjects.add(new FuseeEclairante());
    }

    public ArrayList <MontainObject> getRandomObjectAndDelete (int number) {
        MontainObject object;
        ArrayList <MontainObject> objects = new ArrayList<>();
        for (int i=0; i<number; i++) {
            int alea = Mathematiques.alea(11);

            // FOOD
            if (alea < 4) {
                objects.add(getObject(this.foodObjects));
            } else 
            // HEAT
            if (alea < 6) {
                objects.add(getObject(this.heatObjects));
            } else 
            // ENTERTAINMENT
            if (alea < 8) {
                object = getObject(this.entertainObjects);
                if (object != null) {
                    objects.add(object);
                    this.entertainObjects.remove(object);                    
                }
            } else 
            // HEALTH
            if (alea < 10) {
                object = getObject(this.healthObjects);
                if (object != null) {
                    objects.add(object);
                    this.entertainObjects.remove(object);
                }     
            } else 
            // UTILITY
            if (alea < 11) {
                object = getObject(this.utilityObjects);
                if (object != null) {
                    objects.add(object);
                    this.entertainObjects.remove(object);  
                }
            } else 
            // SPECIAL
            {
                object = getObject(this.specialObjects);
                if (object != null) {
                    objects.add(object);
                    this.entertainObjects.remove(object);
                }
            }
        }
        return objects;
    }

    public ArrayList <MontainObject> getRandomObjectAndDelete (int number, MontainObjectCategoryEnum category) {
        MontainObject object;
        ArrayList <MontainObject> objects = new ArrayList<>();
        for (int i=0; i<number; i++) {
            switch (category) {
                case FOOD:
                    objects.add(getObject(this.foodObjects));
                    break;
                case HEAT:
                    objects.add(getObject(this.heatObjects));
                    break;
                case ENTERTAINMENT:
                    object = getObject(this.entertainObjects);
                    if (object != null) {
                        objects.add(object);
                        this.entertainObjects.remove(object);                    
                    }
                    break;
                case HEALTH:
                    object = getObject(this.healthObjects);
                    if (object != null) {
                        objects.add(object);
                        this.entertainObjects.remove(object);
                    }                    
                    break;
                case UTILITY:
                    object = getObject(this.utilityObjects);
                    if (object != null) {
                        objects.add(object);
                        this.entertainObjects.remove(object);  
                    }
                    break;
                case SPECIAL:
                    object = getObject(this.specialObjects);
                    if (object != null) {
                        objects.add(object);
                        this.entertainObjects.remove(object);
                    }
                    break;
                default:
                    break;
            }
        }
        return objects;
    }

    private MontainObject getObject(ArrayList <MontainObject> montainObjects) {
        if (montainObjects.size()>0) {
            int alea = Mathematiques.alea(montainObjects.size() - 1);
            return montainObjects.get(alea);
        } else {
            return null;
        }
    }

    public void add(ArrayList<MontainObject> objects) {
        for (MontainObject o : objects) {
            add(o);
        }
    }

    public void remove(ArrayList<MontainObject> objects) {
        for (MontainObject o : objects) {
            remove(o);
        }
    }

    public void remove(MontainObject o) {
        switch(o.getCategory()) {
            case ENTERTAINMENT:
                this.entertainObjects.remove(o);
                break;
            case UTILITY:
                this.utilityObjects.remove(o);
                break;
            case SPECIAL:
                this.specialObjects.remove(o);
                break;
            case HEALTH:
                this.specialObjects.remove(o);
                break;
            default:
                break;
        }
    }

    public void add(MontainObject o) {
        switch(o.getCategory()) {
            case ENTERTAINMENT:
                this.entertainObjects.add(o);
                break;
            case UTILITY:
                this.utilityObjects.add(o);
                break;
            case SPECIAL:
                this.specialObjects.add(o);
                break;
            case HEALTH:
                this.specialObjects.add(o);
                break;
            default:
                break;
        }
    }
}
