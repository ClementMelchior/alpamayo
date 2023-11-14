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

public class NomadeEvent extends EventYesNo {
    
    public NomadeEvent () {
        super ("Rencontre avec des nomades", EventCategoryEnum.NONE, EventTypeEnum.YESNO, 
            "Après des jours de lutte contre les éléments, nous sommes surpris de croiser un groupe de nomades, vêtus modestement mais habitués au froid. Nous nous regardons, hésitant entre l'envie de nouer des liens et la prudence qui nous a permis de survivre jusqu'à présent. Les nomades pourraient-ils devenir nos alliés dans cette lutte pour la survie ? Peut-être est-il temps de sortir de notre grotte et d'embrasser l'inconnu ?", 
            null, 25);
    }
    
    @Override
    public void passEvent (boolean yesno) {
        if (yesno) {
            EventResume eventResume = passEventYes();
            GameManager.getInstance().getNextDay().setEventResume(eventResume);
        } else {
            EventResume eventResume = passEventNo();
            GameManager.getInstance().getNextDay().setEventResume(eventResume);
        }
        setLock(40);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventYes () {
        EventResume eventResume;
        String resume; 
        ArrayList <MontainObject> objectsLost;
        ArrayList <MontainObject> objectsWon;
        ArrayList <Player> playerLost;        
        int alea = Mathematiques.alea(2);
        switch (alea) {
            case 0:
                resume = "Ces nomades sont vraiment des crêmes, enfin non des nomades, mais ils ont eu pitié de nous et nous ont fait des supers cadeaux, Beni soient-ils et god save the nomade comme on dit !";
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

    private EventResume passEventNo () {
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
