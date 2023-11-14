package melchior.clement.back.entity.event.list.end.yeti;

import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.manager.GameManager;
import melchior.clement.utilities.Mathematiques;

public class Yeti1Event extends EventYesNo {
    
    public Yeti1Event () {
        super("Trâces de pas suspectes", EventCategoryEnum.MALUS, EventTypeEnum.YESNO, 
            "Alors que bous partions nous vider la vessie dans l’après-midi, non loin de notre refuge, nous avons aperçu dans la neige des empreintes de pas très imposantes et étranges. Devrions nous enquêter ? ", 
            "pas.mp3", 15);
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
        int alea = Mathematiques.alea(2);
        String resume = "";     
        switch (alea) {
            case 0:
                resume = "Nous avons remonté les traces de pas sur plusieurs centaines de mètres avant d’être surpris par un tempête de niege, nous avons dû rapidement rebrousser chemin pour nous réfugiés dans notre.";
                break;
            case 1:
                resume = "Les traces nous ont mené, sur plusieurs kilomètres jusqu’à l’entrée d’une autre grotte. Nous nous sommes approchés mais nous avons très vite entendu d’intense grognement qui semblait venir de l’intérieur de la montagne. Nous avons aussitôt fait demi-tour. Nous n’en savons pas plus mais ce qui est sûr c’est que nous ne ferons pas de la bête un animal de compagnie.";
                break;
            default:
                resume = "En remontant la piste, des traces de pas, nous sommes arrivés dans une plaine, lorsque notre regard s’est posé sur une immense silhouette blanche au loin. Il s’agit assurément d’un YEEEETI. Nous avons aussitôt rebrousser chemin et nous sommes réfugié dans notre grotte. Espérons ne jamais la recroiser…";
                break;
        }
        return new EventResume(this.getName(), resume, null, null, null);
    }

    private EventResume passEventNo() {
        String resume = "Il est évident qu’une bête qui laisse des traces de pas aussi gigantesque et forcément dangereuse. Nous risquons pas notre vie par curiosité.";     
        return new EventResume(this.getName(), resume, null, null, null);
    }
}
