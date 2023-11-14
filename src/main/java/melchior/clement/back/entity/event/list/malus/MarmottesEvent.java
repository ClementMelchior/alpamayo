package melchior.clement.back.entity.event.list.malus;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Couteau;
import melchior.clement.back.entity.object.list.FuseeEclairante;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.utilities.Mathematiques;

public class MarmottesEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public MarmottesEvent () {
        super("Des petites galeries ?", EventCategoryEnum.MALUS, 
            "Depuis plusieurs jours, nous entendons chaque nuit, des petits bruits de pas, comme si quelqu'un essayait de gratter quelque chose. Mais aujourd'hui, nous avons decouvert sous un rocher, une petite galerie. A coup sûr un animal vit dedans et veut nous piquer des vivres. Nous devons nous en débarrasser ! Que devrions nous utiliser ?", 
            "marmotte.mp3", 5);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Reglons ça à l'américaine", new Fusil()));
        this.choices.add(new ChoiceObject(1, false, "On va les hacher menu ça va leur faire tout drôle", new Couteau()));
        this.choices.add(new ChoiceObject(2, false, "Allumons les avec une fusée éclairante !", new FuseeEclairante()));
        this.choices.add(new ChoiceObject(3, false, "Prouvons lui que nous sommes des hommes dans un combat à main nu", null));
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
            // Fusil
            case 0:
                eventResume = passEventFusil(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            // Couteau
            case 1:
                eventResume = passEventCouteau(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            // Fusée eclairante
            case 2:
                eventResume = passEventFusee(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            default:
                eventResume = passEventNull();
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
        }
        setLock(50);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventNull() {
        EventResume eventResume;
        String resume = "Après consultation et brainstorming de nos meilleurs cerveaux, nous avons decider de casser la gueule à main nue de cette marmotte. A peine sortie de son trou, elle n'a pas eu le temps de voir la lumiere jour, nous en avons fait de la bouillie.";     
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEventFusil(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "Nous avons attendu, toute la nuit, comme des buses et tout d'un coup : BAM ! En plein dans le mille. Nous avons tout de même versé une petite larme en voyant qu'il s'agissait d'un petite marmotte, toute mignonne... Ca ne fait pas de nous des psychopathes, n'est ce pas ?";     
        if (Mathematiques.alea(10) > object.getSolidity()) {
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEventCouteau(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        ArrayList <MontainObject> objectsWon = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "";
        // Two possibility
        if (Mathematiques.alea(1)==0 && InventoryManager.getInstance().isInIventory(new Viande())) {
            resume = "Notre plan etait parfait, nous avons mis un bout de viande à la sortie du trou puis nous avons attendu toute la nuit que l'animal vienne nous voler mais il a été tellement rapide que nous avons juste eu le temps de voir que c'etait une marmotte. Elle est reparti avec le bout de viande... Elle a quand même eu peur de nous donc elle ne devrait pas revenir.";     
            objectsLost.add(new Viande());
            InventoryManager.getInstance().remove(17);

        } else {
            resume = "Comme prevu, en pleine nuit, une marmotte est sorti du trou mais elle a fait face à notre lame MOUHAHAHAHA ! C'est ça de nous voler, on s'en tire pas comme ça ! En plus ca nous fera à manger pour les prochains jours. ";     
            objectsWon.add(new Viande());
            InventoryManager.getInstance().add(objectsWon);
        }
        
        // Broke object
        if (Mathematiques.alea(10) > object.getSolidity()) {
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, objectsWon, null);
        }
        return eventResume;
    }

    private EventResume passEventFusee(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
            MontainObject object = choice.getObject();
        String resume = "Nous avons attendu, toute la nuit, comme des buses et tout d'un coup : BAM ! En plein dans le mille. Nous avons tout de même versé une petite larme en voyant cette petite marmotte, toute mignonne, completement explosée... Ca ne fait pas de nous des psychopathes, n'est ce pas ?";     
        if (Mathematiques.alea(10) > object.getSolidity()) {
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
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
