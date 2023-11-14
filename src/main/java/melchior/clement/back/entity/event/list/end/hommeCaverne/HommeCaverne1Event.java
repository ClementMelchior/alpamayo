package melchior.clement.back.entity.event.list.end.hommeCaverne;

import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.manager.GameManager;
import melchior.clement.utilities.Mathematiques;

public class HommeCaverne1Event extends EventYesNo {
    
    public HommeCaverne1Event () {
        super("Rencontre préhistorique", EventCategoryEnum.NONE, EventTypeEnum.YESNO, 
            "Depuis plusieurs jours, nous nous sentions observé. Nous ne savions pas dire s'il s'agissait d’un petit animal, d’un fantôme, ou d’autres choses, mais aujourd’hui nous sommes fixés. Au fond de la grotte, tapis dans l’ombre, nous avons aperçu un vieux monsieur aux allures préhistoriques, qui semble très interrogé sur notre présence. Devrions-nous aller à sa rencontre ?",
            "running.mp3", 25);
    }

    @Override
    public void passEvent (boolean yesno) {
        EventResume eventResume;
        if (yesno == true) {
            eventResume = passEventYes();
        } else {
            eventResume = passEventNo();
        }
        GameManager.getInstance().getNextDay().setEventResume(eventResume);
        GameManager.getInstance().addEvent(new HommeCaverne2Event(), 10 + Mathematiques.alea(5));;
    }

    private EventResume passEventYes() {
        int alea = Mathematiques.alea(1);
        String resume = "";     
        switch (alea) {
            case 0:
                resume = "La rencontre fut brève, nous avons essayé de nous approcher, mais il a pris peur, et c’est enfui dans les traits fond de la grotte. Nous ne voulions pas risquer de nous y perdre donc nous l’avons laissé retourner et là d’où il venait.";
                break;
            default:
                resume = "Nous avons essayé de nous approcher mais l’homme préhistorique s’est braqué et mis sur ses gardes. Il a sorti un vieux silex de son slip préhistorique et nous à menacer. De peur nous nous sommes reculés et l’avons laissé repartir d’où il venait.";
                break;
        }
        return new EventResume(this.getName(), resume, null, null, null);
    }

    private EventResume passEventNo() {
        String resume = "L’homme des cavernes a continué à nous observer pendant de longues minute puis à disparu dans la pénombre. Bizarre.";     
        return new EventResume(this.getName(), resume, null, null, null);
    }
}
