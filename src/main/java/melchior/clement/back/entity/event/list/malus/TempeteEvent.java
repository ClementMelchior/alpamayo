package melchior.clement.back.entity.event.list.malus;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Gaz;
import melchior.clement.back.entity.object.list.Peau;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class TempeteEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;

    public TempeteEvent () {
        super("Tempête glacial", EventCategoryEnum.MALUS, 
            "Des nuages, noirs, qui veinnent droit sur nous ! C'est ce qu'on a vu cette apres-midi en jetant un oeil en dehors de la grotte. C'est vraiment pas rassurant, en plus il commence à faire tres froid, est ce qu'on devrait se proteger pour cette nuit ?", 
            "tempete.mp3", 5);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Nous pourrions se couvrir de cette peau de bête pour se rechauffer ?", new Peau()));
        this.choices.add(new ChoiceObject(1, true, "Rechauffons-nous comme d'habitude avec notre bouteil de gaz.", new Gaz()));
        this.choices.add(new ChoiceObject(2, true, "On va se blottir les un contre les autres en priant pour que ça passe !", null));
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
        if (choice != null) {
            switch (choice.getId()) {
                // Peau de bête
                case 0:
                    eventResume = passEventPeau(choice);
                    GameManager.getInstance().getNextDay().setEventResume(eventResume);
                    break;
                // Gaz
                case 1:
                    eventResume = passEventGaz(choice);
                    GameManager.getInstance().getNextDay().setEventResume(eventResume);
                    break;
                default:
                    eventResume = passEventNull();
                    GameManager.getInstance().getNextDay().setEventResume(eventResume);
                    break;
            }
        } else {
            eventResume = passEventNull();
            GameManager.getInstance().getNextDay().setEventResume(eventResume);
        }
        setLock(10);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventNull() {
        EventResume eventResume;
        String resume = "Vous avez raison, nous n'avons pas besoin de tous ça, c'est pas une petite tempête qui va nous faire peur, c'est juste... Il fait quand meme froid là non ?";     
        PlayerManager.getInstance().heat(-1);
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEventPeau(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "Super bonne idée, nous ne savions que faire de cette peau de Dahu et voila qu'elle s'est avéré super utile. God bless le dahu cette tempête est passée comme sur des roulettes !";     
        PlayerManager.getInstance().heat(2);
        if (!(object.use())) {
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
        GameManager.getInstance().getNextDay().setTemperature(-30 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEventGaz(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "Super bonne idée, nous ne savions que faire de cette peau de Dahu et voila qu'elle s'est avéré super utile. God bless le dahu cette tempête est passée comme sur des roulettes !";     
        PlayerManager.getInstance().heat(4);
        InventoryManager.getInstance().remove(object);
        objectsLost.add(object);
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
