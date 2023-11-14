package melchior.clement.back.entity.event.list.malus;

import java.util.ArrayList;
import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.utilities.Mathematiques;

public class EffondrementEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public EffondrementEvent () {
        super("Tremblement de terre !", EventCategoryEnum.MALUS, 
            "Le soleil etait couché et comme à notre habitude nous avons décidé d’aller dormir mais nous avons rapidement été réveillé en sursaut par un vacarme horrible. La montagne s’est mise à trembler de manière apocalyptique. Deux pierre semble être prête à s’écraser sur nos ressources, la quelle devrions nous sauver ?", 
            "eboulement.mp3", 10);
        this.choices = new ArrayList<>();

        MontainObject obj1 = InventoryManager.getInstance().getRandomObject(1).get(0);
        MontainObject obj2 = InventoryManager.getInstance().getRandomObject(1).get(0);
        int i = 0;
        while (obj1.getId() == obj2.getId() || i < 15) {
            obj2 = InventoryManager.getInstance().getRandomObject(1).get(0);
            i = i + 1;
        }
        
        this.choices.add(new ChoiceObject(0, false, "Sauvons celle ci", obj1));
        this.choices.add(new ChoiceObject(1, false, "Protegeons celle là", obj2));
    }

    @Override
    public void update () {
        for (ChoiceObject c : choices) {
            if (c.getObject() != null && InventoryManager.getInstance().isInIventory(c.getObject())) {
                c.setPossible(true);
            } else {
                c.setPossible(false);
            }
        }
    }

    @Override
    public void passEvent (ChoiceObject choice) {
        EventResume eventResume;
        switch (choice.getId()) {
            // 0
            case 0:
                eventResume = passEvent0(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            // 1
            case 1:
                eventResume = passEvent1(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            default:
                eventResume = passEventNull();
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
        }
        setLock(35);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventNull() {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        objectsLost.add(choices.get(0).getObject());
        objectsLost.add(choices.get(1).getObject());
        String resume = "Nous avons fait au plus vite malheureusement, nous sommes arrivé trop tard pour sauver ce qui pouvait l'être...";     
        eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEvent0(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choices.get(1).getObject();
        objectsLost.add(object);
        InventoryManager.getInstance().remove(object.getId());
        String resume = "Nous avons bondi pour sauver ce que nous pouvions, malheureusement nous avons perdu " + object.getName() + " dans l'effondrement de la grotte.";     
        eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEvent1(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choices.get(0).getObject();
        objectsLost.add(object);
        InventoryManager.getInstance().remove(object.getId());
        String resume = "Nous avons bondi pour sauver ce que nous pouvions, malheureusement nous avons perdu " + object.getName() + " dans l'effondrement de la grotte.";     
        eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    @Override
    public ArrayList<ChoiceObject> getChoices() {
        if (choices != null) {
            return choices;
        } else {
            return new ArrayList<ChoiceObject>();
        }
    }
}
