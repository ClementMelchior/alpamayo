package melchior.clement.back.entity.event.list.malus;

import java.util.ArrayList;
import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Gaz;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.utilities.Mathematiques;

public class ArraigneeEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public ArraigneeEvent () {
        super("Attaque d'arraignée", EventCategoryEnum.MALUS, 
            "Cette grotte, notre abri, notre refuge, nous semblait si accueillante réconfortante, du moins, avant de nous rendre compte qu'elle abritait une vraie colonie d'arraignée. Nous ne pouvons pas rester comme les bras croisés, il faut absolument les exterminer. C'est elles où nous, mais nous ne cohabiterons pas.  Que devrions-nous utiliser pour s'en débarrasser ?", 
            "araingnee.mp3", 3);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Utilisons nos pieds, rien de tel pour écraser ces sales bêtes !", null));
        this.choices.add(new ChoiceObject(1, false, "Nous pourrions peut-être faire exploser une bouteille de gaz pour les réduire en miettes ?", new Gaz()));
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
                eventResume = passEventNull();
                break;
            default:
                eventResume = passEventGaz(choice);
                break;
        }
        GameManager.getInstance().getNextDay().setEventResume(eventResume);
    }

    private EventResume passEventGaz(ChoiceObject choice) {
        EventResume eventResume;
        MontainObject object = choices.get(1).getObject();
        int alea = Mathematiques.alea(1);
        String resume = "";
        if (alea == 0) {
            resume = "Bon, la bouteille de gaz a bien fonctionné. Plus aucune araignée en vue. Mais c’était peut-être un peu disproportionné non ?";
            InventoryManager.getInstance().remove(object.getId());
            ArrayList <MontainObject> objects = new ArrayList<>();
            objects.add(object);
            eventResume = new EventResume(this.getName(), resume, objects, null, null);
        } else {   
            resume = "Nous n’avons pas réussi à faire exploser la bouteille de gaz, il faut dire qu’il y a des sacrés sécurité. Mais bon, nous l’avons fait rouler sur toute la colonie est avec son poids, elle les a réduit bouillie. Peut-être que nos simples pieds auraient suffi…";
            eventResume = new EventResume(this.getName(), resume, null, null, null);        
        }
        return eventResume;
    }

    private EventResume passEventNull() {
        EventResume eventResume;
        String resume = "En effet, nous avons toujours trouvé que chausser du 46 n’était pas élégant, mais finalement ça s’est avéré vraiment utile et nous avons pu réduire cette colonie en bouillie en un rien de temps.";     
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
