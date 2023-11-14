package melchior.clement.back.entity.event.list.none;

import java.util.ArrayList;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.ObjectManager;
import melchior.clement.utilities.Mathematiques;

public class Survivant extends EventYesNo {
    
    public Survivant () {
        super ("Des survivants", EventCategoryEnum.NONE, EventTypeEnum.YESNO, 
            "Visiblement, nous ne sommes pas les seuls à avoir survécu du crash de l’avion. Un survivant est venu nous rendre visite. La discussion était pourtant amicale au début mais il a commencé à nous menacer avec un couteau et nous a demandé à manger et nos bouteilles de gaz. Nous ne pouvons pas nous laisser faire ainsi, nous aussi nous avons faim et froid. Que devrions-nous utiliser pour le faire fuir ?", 
            null, 5);
    }
    
    @Override
    public void passEvent (boolean yesno) {
        if (yesno) {
            EventResume eventResume = success();
            GameManager.getInstance().getNextDay().setEventResume(eventResume);;
        } else {
            EventResume eventResume = fail();
            GameManager.getInstance().getNextDay().setEventResume(eventResume);;
        }
        setLock(50);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume success () {
        EventResume eventResume;
        String resume; 
        ArrayList <MontainObject> objectsLost;
        ArrayList <MontainObject> objectsWon;
        ArrayList <Player> playerLost;        
        int alea = Mathematiques.alea(2);
        switch (alea) {
            case 0:
                resume = "Ces nomades sont vraiment des crême, enfin non des nomades, mais ils ont eu pitié de nous et nous ont fait des supers cadeaux, Beni soient-ils et god save the nomade comme on dit !";
                objectsLost = null;
                objectsWon = ObjectManager.getInstance().getRandomObjectAndDelete(3);
                playerLost = null;
                eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
                break;
            case 1:
                resume = "Bon, la communication avec ces nomades n'etait pas simple mais ils ont compris que nous etions en mauvaise posture. Ou du moins, ils l'ont vu. Ils sont reparti en nous laissant un petit cadeau. Ou alors, ils l'ont oublié, mais ça nous sera bien utile !";
                objectsLost = null;
                objectsWon = ObjectManager.getInstance().getRandomObjectAndDelete(1);
                playerLost = null;
                eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
                break;
            case 2:
                resume = "HORRIBLE, ces nomades ne comprennent rien, pas un ne parle français c'est dingue quand même ? Même pas anglais, il pourait faire un efforts. Ils ont gesticulé dans tous les sens puis sont repartis, vivement qu'on rentre chez nous quand même...";
                objectsLost = null;
                objectsWon = null;
                playerLost = null;
                eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
                break;
            default: 
                resume = "Nous sommes sur le choc ! Ce ne sont pas des nomades, ce sont des voleurs, ils ont vu notre abris et ont decider de ce servir dedans. Mais quel honte vous imaginez ?";
                objectsLost = InventoryManager.getInstance().getRandomObjectAndDelete(1);
                objectsWon = null;
                playerLost = null;
                eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
                break;
        }
        return eventResume;
    }

    private EventResume fail () {
        EventResume eventResume;
        String resume; 
        ArrayList <MontainObject> objectsLost;
        ArrayList <MontainObject> objectsWon;
        ArrayList <Player> playerLost;        
        resume = "Vous n'entrerez pas, chez nous ! Après tout, on ne les connais pas, il vaut mieux ne pas prendre de rique.";
        objectsLost = null;
        objectsWon = null;
        playerLost = null;
        eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
        return eventResume;
    }
}
