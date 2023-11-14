package melchior.clement.back.entity.event.list.end.yeti;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Couteau;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class Yeti3Event extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public Yeti3Event () {
        super("Le yéti de retour !", EventCategoryEnum.MALUS, 
            "Des grognements. DES GROGNEMENT !!! Cette fois-ci, pas de doute, le yéti-nous à trouver, il se tient devant nous, à l’entrée de la grotte. Cette fois-ci pas de doute, nous allons devoir le combattre. Que devrions-nous utiliser ?"            , 
            "grognement3.mp3", 0);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "A L'ATTAAAAQUE !", new Couteau()));
        this.choices.add(new ChoiceObject(1, false, "Mettons-lui une énorme bastos dans le front !", new Fusil()));
        this.choices.add(new ChoiceObject(2, false, "Prouvons lui que nous sommes des hommes dans un combat à main nu", null));
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
            case 0:
                eventResume = passEventCouteau();
                break;
            case 1:
                eventResume = passEventFusil();
                break;
            default:
                eventResume = passEventNull();
                break;
        }
        GameManager.getInstance().getNextDay().setEventResume(eventResume);
    }

    private EventResume passEventFusil() {
        EventResume eventResume;
        MontainObject object = choices.get(1).getObject();
        int alea = Mathematiques.alea(10);
        String resume = "";
        // one player get sick and yeti come back in futurs days     
        if (alea < 6) {
            resume = "Faut toujours que ça arrive au mauvais moment, c’est chose là : notre fusil a décidé de s’enrayer au moment où le coup devait partir. Heureusement, la balle a fini par partir, mais le yeti a eu le temps de mettre un coup de griffe et et a blessé l’un d’entre nous. La blessure semble s’infecter, il faut le soigner.";
            PlayerManager.getInstance().randomSick();
            GameManager.getInstance().addEvent(new Yeti3Event(), 10 + Mathematiques.alea(4));
        } else {
            resume = "Après une lutte acharnée, et sans, merci, nous sommes enfin arrivés à bout de cette créature des ténèbres. Une balle entre les deux yeux, elle est tombée, raide sur le côté. Hasta la Vista baby.";
        }
        if (!(object.use())) {
            InventoryManager.getInstance().remove(object.getId());
            ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
            objects.add(object);
            eventResume = new EventResume("Le yéti de retour !", resume, objects, null, null);
        } else {
            eventResume = new EventResume("Le yéti de retour !", resume, null, null, null);
        }
        return eventResume;
    }

    private EventResume passEventCouteau() {
        EventResume eventResume;
        MontainObject object = choices.get(1).getObject();
        int alea = Mathematiques.alea(10);
        String resume = "";     
        if (alea < 2) {
            resume = "C’était un combat, presque impossible. Nous nous sommes battus comme des chiens et nous l’avons même blessé, mais c’est avec une profonde tristesse que nous déplorons la perte d’un ami proche. Va-t-il enfin nous laisser tranquille cette fois-ci ?";
            GameManager.getInstance().addEvent(new Yeti3Event(), 10 + Mathematiques.alea(4));
        } else if (alea < 7) {
            resume = "Nous avons bataillé dur, très dur pour repousser, c’est énorme créature. Heureusement elle a fini par nous laisser tranquille, mais elle a laissé des blessures profondes sur l’un d’entre nous qui se sont infectés. Nous devrions peut-être le soigner maintenant ?";
            GameManager.getInstance().addEvent(new Yeti3Event(), 10 + Mathematiques.alea(4));
        } else {
            resume = "Après une lutte acharnée, et sans, merci, nous sommes enfin arrivés à bout de cette créature des ténèbres.";
        }
        if (!(choices.get(1).getObject().use())) {
            InventoryManager.getInstance().remove(object.getId());
            ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
            objects.add(object);
            eventResume = new EventResume("Le yéti de retour !", resume, objects, null, null);
        } else {
            eventResume = new EventResume("Le yéti de retour !", resume, null, null, null);
        }
        return eventResume;
    }

    private EventResume passEventNull() {
       EventResume eventResume;
        int alea = Mathematiques.alea(1);
        String resume = "";     
        if (alea == 0) {
            resume = "Après quelques minutes, le yéti est entré dans la grotte. Il a renifler nos affaires fait le tour de notre refuge. Mais heureusement il ne nous a pas trouvé. Espérons qu’ils ne reviennent plus.";
            GameManager.getInstance().addEvent(new Yeti3Event(), 10 + Mathematiques.alea(4));
        } else {
            resume = "Après plusieurs heures d’attente, nous avons décidé de sortir du fond de la grotte, le yéti n’était plus là. Visiblement il ne nous a pas trouvé.";
            GameManager.getInstance().addEvent(new Yeti3Event(), 10 + Mathematiques.alea(4));
        }
        eventResume = new EventResume("Grognement", resume, null, null, null);
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
