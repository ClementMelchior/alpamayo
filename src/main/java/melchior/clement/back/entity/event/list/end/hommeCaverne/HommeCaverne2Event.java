package melchior.clement.back.entity.event.list.end.hommeCaverne;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.Pinot;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class HommeCaverne2Event extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public HommeCaverne2Event () {
        super("Le retour préhistorique", EventCategoryEnum.NONE, 
            "L’homme des cavernes que nous avions rencontré il y a plusieurs jours a décidé de refaire surface. Cette fois ci il semble peut-être vouloir faire connaissance. Devrions nous lui faire une offrande ?", 
            "hmmhmm.mp3", 0);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Nous pourrions lui donner notre fusil ?", new Fusil()));
        this.choices.add(new ChoiceObject(1, false, "Invitons le a deguster une côte de boeuf ?", new Viande()));
        this.choices.add(new ChoiceObject(2, false, "Quoi de mieux qu’une bonne cuite pour faire connaissance ?", new Pinot()));
        this.choices.add(new ChoiceObject(3, false, "On a pas assez de ressource pour partager avec n’importe qui.", null));
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
                eventResume = passEventFusil();
                break;
            case 1:
                eventResume = passEventViande();
                break;
            case 2:
                eventResume = passEventPinot();
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
        int alea = Mathematiques.alea(1);
        ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
        String resume = "";     
        if (alea == 0) {
            resume = "L’homme des cavernes, timide s’est saisi du fusil puis en l’inspectant a appuyer sur la gâchette. Surpris par la puissance de l’arme il l’a laissé tombé au sol puis s’est enfuit.";
            if (!(choices.get(1).getObject().use())) {
                InventoryManager.getInstance().remove(object.getId());
                objects.add(object);
                eventResume = new EventResume(this.getName(), resume, objects, null, null);
            } else {
                eventResume = new EventResume(this.getName(), resume, null, null, null);
            }
            GameManager.getInstance().addEvent(new HommeCaverne3Event(), 10 + Mathematiques.alea(6));
        } else {
            resume = "L’homme des cavernes, timide s’est saisi du fusil puis en l’inspectant a appuyer sur la gâchette. Subjugué par la puissance de l’arme il s’est enfuis en sautant, dansant comme un enfant découvrant le nutella.";
            InventoryManager.getInstance().remove(object.getId());
            objects.add(object);
            eventResume = new EventResume(this.getName(), resume, objects, null, null);
        }
        return eventResume;
    }

    private EventResume passEventViande() {
        EventResume eventResume;
        MontainObject object = choices.get(1).getObject();
        String resume = "L’homme s’est saisi du bifteck, l’a reniflé puis nous l’a lancé dans le fond de la grotte en poussant un cris de dégoût. Il est aussitôt reparti chez lui. Peut être est-il végétarien ?";     
        ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
        objects.add(object);
        InventoryManager.getInstance().remove(object.getId());
        eventResume = new EventResume(this.getName(), resume, objects, null, null);
        GameManager.getInstance().addEvent(new HommeCaverne3Event(), 10 + Mathematiques.alea(6));
        return eventResume;
    }

    private EventResume passEventNull() {
        EventResume eventResume;
        String resume = "Nous avons continué de nouveau a nous fixer longuement, puis nous avons décidé d’établir un contact. Dans une langue approximative avec les mains à base de 'nous, gentil', 'nous vouloir ami'. Nous avons réussi à dialoguer quelques minutes avec lui avant qu’il ne reparte.";     
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        GameManager.getInstance().addEvent(new HommeCaverne3Event(), 10 + Mathematiques.alea(6));
        return eventResume; 
    }

    private EventResume passEventPinot() {
        EventResume eventResume;
        MontainObject object = choices.get(1).getObject();
        String resume = "L’homme des cavernes a d’abord hésité puis a déposé ses lèvres sur le goulot. Après quelques secondes de réflexions il s’est enfilé la moitié de la bouteille avant de nous la rendre. Pour l’accompagner nous avons fait de même. Nous avons ensuite chanté et dansé pendant des heures avant qu’il ne retourne chez lui et disparaisse de nouveau.";     
        ArrayList <MontainObject> objects = new ArrayList<MontainObject>();
        objects.add(object);
        InventoryManager.getInstance().remove(object.getId());
        PlayerManager.getInstance().entertain(object.getEntertainment());
        eventResume = new EventResume(this.getName(), resume, objects, null, null);
        GameManager.getInstance().addEvent(new HommeCaverne3Event(), 8 + Mathematiques.alea(6));
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
