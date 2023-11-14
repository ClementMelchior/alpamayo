package melchior.clement.back.entity.event.list.bonus;

import java.util.ArrayList;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.MontainObjectCategoryEnum;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.ObjectManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class DahuEvent extends EventYesNo {
    
    public DahuEvent () {
        super("Rencontre étrange", EventCategoryEnum.BONUS, EventTypeEnum.YESNO, 
            "Au détour d'un rayon de soleil, nos yeux se sont posés sur une bête étrange, peut-être magique, se tenant là, majestueuse et mystèrieuse. Est-ce une licorne aux pouvoirs enchanteurs ou simplement un animal exotique égaré dans notre coin reculé ? Devrions nous tenter une approche amicale ?",
            null, 0);
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
        setLock(100);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventNo() {
        EventResume eventResume;
        String resume = "Oui bon on est perdu dans la montagne, on est pas là pour jouer les détéctive et faire des documentaires animaliers non plus. On va rester chez nous et faire comme si nous n'avions rien vu.";     
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        return eventResume;
    }

    private EventResume passEventYes() {
        EventResume eventResume;
        int alea = Mathematiques.alea(2);
        ArrayList <MontainObject> objectsWon = new ArrayList<>();
        String resume = "";
        if (alea == 0) {
            resume = "Le Dahu !! Enfin le voila, ce n'étais donc pas un mythe il existe vraiment. C'etait comme une biche, mais avec deux pattes plus courte. C'est dingue ce que la nature peut créer quand même non ? En tout cas ça nous a bien occupé la journée.";     
            PlayerManager.getInstance().entertain(5);
            eventResume = new EventResume(this.getName(), resume, null, objectsWon, null);
        } else if (alea == 1) {
            resume = "Quel malheur ! La pauvre bête a eu peur de nous. Elle a decidé de s'enfuir en courant mais elle a glissé sur une plaque de verglas et s'est ratatiné 100 metres plus bas. Elle est morte sur le coup. On en a quand même profité pour aller decouper son cadavre et reconstituer nos ressources de nourriture. Le malheur des uns fait le bonheur des autres comme on dit !";     
            objectsWon.addAll(ObjectManager.getInstance().getRandomObjectAndDelete(2, MontainObjectCategoryEnum.FOOD));
            eventResume = new EventResume(this.getName(), resume, null, objectsWon, null);
            InventoryManager.getInstance().add(objectsWon);
        } else {
            resume = "Bon rien de bien folichon... On a essayer de s'approcher mais visiblement la bête n'a pas eu confiance en nous. On a à peine eu le temps de la voir avant qu'elle ne se sauve.";     
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }
        return eventResume;
    }
}
