package melchior.clement.back.entity.event.list.none;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.Livre;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.utilities.Mathematiques;

public class FantomeEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;

    public FantomeEvent () {
        super("Le fantôme de la grotte", EventCategoryEnum.NONE, 
            "Depuis plusieurs jours, des phénomènes étranges ont attiré notre attention. Nous avons plusieurs entendu dans le fond de la grotte des murmures, d’autres fois des bruit pas et même une fois une lueur. Nous avons d’abord cru que c’était des petits animaux mais cette fois ci c’est certain, il y un fantôme. Nous l’avons même vu passé au dessus de nous alors que nous étions allongé pour détendre. C’est vraiment pas rassurant que devrions nous faire ?", 
            "fantome.mp3", 15);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Nous pourrions réciter une incantation magique pour le faire fuir ?", new Livre()));
        this.choices.add(new ChoiceObject(1, false, "Nous pourrions lui tirer dessus pour lui faire peur ?", new Fusil()));
        this.choices.add(new ChoiceObject(2, false, "Peut-être pouvons nous simplement lui demander de partir ?", null));
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
                // Livre
                case 0:
                    eventResume = passEventLivre(choice);
                    GameManager.getInstance().getNextDay().setEventResume(eventResume);
                    break;
                // Fusil
                case 1:
                    eventResume = passEventFusil(choice);
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
    }

    private EventResume passEventNull() {
        EventResume eventResume;
        // One possibility
        String resume = "Le fantôme s’est arrêté puis nous a regardé quelques instants avant de disparaître. C’était si simple... On sous-estime souvent le pouvoir des mots.";
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        return eventResume;
    }

    private EventResume passEventLivre(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "";
        // One possibility
        resume = "Notre incantation n’a pas fonctionné comme prévu. Des que le fantôme est revenu, nous avons récité notre sortilège, le fantome s’est d’abord mis à tournoyer dans toute la grotte en criant, puis avec une voix grave et sourde nous a dit : ce tombeau sera votre tombeau, avant de disparaitre. Nous n’avons pas bien compris.";     
        
        // Broke object
        if (Mathematiques.alea(10) > object.getSolidity()) {
            InventoryManager.getInstance().remove(object.getId());
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
        return eventResume;
    }

    private EventResume passEventFusil(ChoiceObject choice) {
                EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        // One possibility
        String resume = "Mais qui a eu cette idée ? C’est un fantôme, il ne sent rien. Le coup de feu ne lui a même pas fait peur, il a continué a se promener sans rien faire avant de disparaître. Visiblement il ne semble pas agressif.";     
        
        // Broke object
        if (Mathematiques.alea(10) > object.getSolidity()) {
            InventoryManager.getInstance().remove(object.getId());
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
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
