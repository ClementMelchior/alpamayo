package melchior.clement.back.entity.event.list.end.yeti;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.utilities.Mathematiques;

public class Yeti2Event extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public Yeti2Event () {
        super("Grognement", EventCategoryEnum.MALUS, 
            "Grrrrrrr grrrrrrrr. Cette nuit nous avons été réveillé par des grognements incessants. Nous sommes parti à l’entrée de la grotte vérifier de quoi il s’agissait et visiblement, la sale bête aux énormes traces de pas est un yeti, et il est juste à côté de nous. Heureusement il ne nous a pas encore vu. Que devrions nous faire ?", 
            "grognement2.mp3", 0);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Nous pourrions lui lancer de la viande pour qu'il nous laisse tranquille ?", new Viande()));
        this.choices.add(new ChoiceObject(1, false, "Provoquons le en duel avec un fusil ?", new Fusil()));
        this.choices.add(new ChoiceObject(2, false, "Restons cachés, tapi dans le fond de la grotte.", null));
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
                eventResume = passEventViande();
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
        if (alea < 5) {
            resume = "Fusil à la main, enfin, sous le bras, le doigt sur la gâchette, nous nous sommes approcher lentement mais le yéti était déjà parti. Nous n’avons même pas eu besoin de nous battre.";
            GameManager.getInstance().addEvent(new Yeti3Event(), 12 + Mathematiques.alea(4));
        } else if (alea < 8) {
            resume = "Fusil à la main, enfin, sous le bras, le doigt sur la gâchette, nous nous sommes approcher lentement mais le yéti était déjà parti. Nous n’avons même pas eu besoin de nous battre.";
            GameManager.getInstance().addEvent(new Yeti3Event(), 12 + Mathematiques.alea(4));
        } else {
            resume = "Fusil à la main, enfin, sous le bras, le doigt sur la gâchette, nous nous sommes approcher du yéti. Nous l’avons pris par surprise. Tel un chasseur, essayant d’abattre une vache immobile à 5 m de distance, nous lui avons mis une énorme bastos entre les deux yeux. S’en est fini pour cette sale bête, hasta la Vista, baby.";
        }
        if (!(choices.get(1).getObject().use())) {
            InventoryManager.getInstance().remove(object.getId());
            ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
            objects.add(object);
            eventResume = new EventResume(this.getName(), resume, objects, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
        return eventResume;
    }

    private EventResume passEventViande() {
        EventResume eventResume;
        MontainObject object = choices.get(1).getObject();
        String resume = "Heureusement, le yéti n’avait pas très faim, il s’est contenté de manger notre ration avant de disparaître.";     
        ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
        objects.add(object);
        InventoryManager.getInstance().remove(choices.get(1).getObject().getId());
        eventResume = new EventResume(this.getName(), resume, objects, null, null);
        GameManager.getInstance().addEvent(new Yeti3Event(), 12 + Mathematiques.alea(4));
        return eventResume;
    }

    private EventResume passEventNull() {
       EventResume eventResume;
        int alea = Mathematiques.alea(1);
        String resume = "";     
        if (alea == 0) {
            resume = "Après quelques minutes, le yéti est entré dans la grotte. Il a renifler nos affaires fait le tour de notre refuge. Mais heureusement il ne nous a pas trouvé. Espérons qu’ils ne reviennent plus.";
            GameManager.getInstance().addEvent(new Yeti3Event(), 12 + Mathematiques.alea(4));
        } else {
            resume = "Après plusieurs heures d’attente, nous avons décidé de sortir du fond de la grotte, le yéti n’était plus là. Visiblement il ne nous a pas trouvé.";
            GameManager.getInstance().addEvent(new Yeti3Event(), 12 + Mathematiques.alea(4));
        }
        eventResume = new EventResume(this.getName(), resume, null, null, null);
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
