package melchior.clement.back.entity.event.list.bonus;

import java.util.ArrayList;

import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.ObjectManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class BeauTempsEvent extends EventYesNo {
    
    public BeauTempsEvent () {
        super("Un peu de beau temps", EventCategoryEnum.BONUS, EventTypeEnum.YESNO, 
            "Il semble que le beau temps soit de sorti aujourd'hui. C'est enfin une bonne nouvelle, devrions-nous en profiter pour sortir s'aéré un petit peu ?", 
            "beauTemps.mp3", 4);
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
        setLock(2);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventNo() {
        EventResume eventResume;
        String resume = "Le beau temps ? et quoi d'autre encore, on est perdu dans la montagne, on a pas que ça à faire. Restons bien au calme dans notre grotte si accueillante, n'est ce pas ?";     
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        GameManager.getInstance().getNextDay().setTemperature(10 + Mathematiques.alea(5));
        return eventResume;
    }

    private EventResume passEventYes() {
        EventResume eventResume;
        int alea = Mathematiques.alea(1);
        ArrayList <MontainObject> objectsWon = new ArrayList<>();
        String resume = "";
        if (alea == 0) {
            resume = "Super bonne idée, on a pu se balader autour de notre grotte, le soleil nous a bien réchauffé et nous avons meme trouvé de quoi compléter notre inventaire !";     
            PlayerManager.getInstance().heat(2);
            objectsWon.addAll(ObjectManager.getInstance().getRandomObjectAndDelete(1));
            eventResume = new EventResume(this.getName(), resume, null, objectsWon, null);
            InventoryManager.getInstance().add(objectsWon);
        } else {
            resume = "Qui a eu cette idée ? Le soleil a disparu au bout de 10 min puis il s'est mis à neiger ! Cette montagne n'est vraiment pas accueillante...";     
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
        GameManager.getInstance().getNextDay().setTemperature(10 + Mathematiques.alea(5));
        return eventResume;
    }
}
