package melchior.clement.back.entity.event.list.end.alpiniste;

import melchior.clement.back.entity.day.LastDay;
import melchior.clement.back.entity.day.LastDayReasonEnum;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.manager.GameManager;
import melchior.clement.utilities.Mathematiques;

public class Alpiniste1Event extends EventYesNo {
    
    public Alpiniste1Event () {
        super("Bruit dehors", EventCategoryEnum.NONE, EventTypeEnum.YESNO, 
            "Tandis que, nous étions en train de compter les gouttes d’eau qui tombaient du plafond, nous avons entendu des voix humaines à l’extérieur de la grotte. Nous ne savons pas encore si il s’agit de notre imagination, devrions-nous aller jeter un coup d’œil ?",
            null, 40);
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
    }

    private EventResume passEventYes() {
        String resume = "Dieu soit loué, il s’agissait d’alpiniste qui était en route pour gravir les sommiers de l’alpamayo ! Nous leur avons raconter une autre histoire, ils ont décidé de me venir en aide. Ils vont redescendre la montagne et prévenir les secours pour qu’ils viennent nous chercher. Nous n’avons plus qu’à attendre une quinzaine de jours.";
        int alea = Mathematiques.alea(5);
        String lastDayResume = "A l'aube d'une nouvelle journée de survit nous avons été réveillé par les secours. Ils ont été prévenu par les alpiniste recontrés quelques jours plus tôt. Hourra, nous allons enfin rentrer chez nous.";
        GameManager.getInstance().setLastDay(new LastDay(LastDayReasonEnum.SAFETY, lastDayResume), 15 + alea);
        return new EventResume(this.getName(), resume, null, null, null);
    }

    private EventResume passEventNo() {
        String resume = "Il s’agit sûrement d’un piège, est-ce qu’on paye notre vie pour rien et restons camoufler dans notre grotte. Les secours ne devraient pas tarder.";     
        return new EventResume(this.getName(), resume, null, null, null);
    }
}
