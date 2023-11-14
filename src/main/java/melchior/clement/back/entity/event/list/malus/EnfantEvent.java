package melchior.clement.back.entity.event.list.malus;

import java.util.ArrayList;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.entity.player.PlayerStateEnum;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class EnfantEvent extends EventYesNo {
    
    public EnfantEvent () {
        super ("Petit enfant perdu", EventCategoryEnum.NONE, EventTypeEnum.YESNO, 
            "Alors que nous dormions paisiblement et confortablement nous avons entendu une petite voix dans la pénombre. Nous avons allumé une torche et trouvé, au beau milieu de notre grotte, un enfant complètement déboussolé. On ne peut pas le laisser comme ça il faut l’aider et lui proposer de rester pour se réchauffer non ?", 
            null, 8);
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
                resume = "L’enfant nous a fait un signe de remerciement de la tête puis s’est allongé à coté de nous. Mais stupeur, ce n’est qu’au matin que nous avons compris sont plan démoniaque. Il a utilisé sa bonne bouille pour nous piller ! Ahhh sale gosse !";
                objectsLost = InventoryManager.getInstance().getRandomObjectAndDelete(1);;
                objectsWon = null;
                playerLost = null;
                eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
                break;
            case 1:
                Player p = PlayerManager.getInstance().getRandomPlayer(PlayerStateEnum.ALIVE, PlayerStateEnum.CRY);
                p.fallSick();
                resume = "L’enfant nous a remercié puis est venu s’installer près de nous. C’est seulement au réveil que nous avons remarqué son absence. Espérons qu’il s’en sorte maintenant. Par contre, " + p.getName() + " tousse beaucoup, peut être que le mioche lui a refilé un rhume ?";
                objectsLost = null;
                objectsWon = null;
                playerLost = null;
                eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
                break;
            default:
                resume = "Nous avons longuement discuté avec lui toute la nuit. Il nous a raconté son histoire tragique puis nous a confié qu’il cherchait ses parents. Au petit matin, il etait deja reparti, souhaitons lui bonne chance.";
                objectsLost = null;
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
        resume = "Malheureusement, c’est la survie, nous ne sommes deja même pas sûr de rester en vie et d’avoir suffisamment de provision, ne prenons aucun risque, c’est chacun pour soi. Houste la vermine.";
        objectsLost = null;
        objectsWon = null;
        playerLost = null;
        eventResume = new EventResume(this.getName(), resume, objectsLost, objectsWon, playerLost);
        return eventResume;
    }
}
