package melchior.clement.back.entity.expedition;

import java.util.ArrayList;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.entity.player.PlayerStateEnum;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.ObjectManager;
import melchior.clement.utilities.Mathematiques;

public class Expedition {
    // Constant
    private double deadProbability = 0.75;
    private int minimumObject = 2;
    private int minimumDayInExpedition = 4;

    // variable
    private int dayInExpedition;
    private MontainObject object;
    private Player player;
    private boolean sick;

    public Expedition(MontainObject object, Player player) {
        this.dayInExpedition = this.minimumDayInExpedition + Mathematiques.alea(3);
        if (object != null) {
            this.object = object;
            InventoryManager.getInstance().remove(object);
            // Grapin
            if (object.getId() == 8){
                this.dayInExpedition = this.dayInExpedition - 2;
            }
        }
        this.player = player;
        this.sick = (player.getState() == PlayerStateEnum.SICK);
        player.setState(PlayerStateEnum.EXPEDITION);
    }

    /*
     *  Method that return true if nextDay is the end of exposition, false otherwise.
     */
    public boolean nextDay() {
        this.dayInExpedition = this.dayInExpedition - 1;
        if (this.dayInExpedition == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     *  endExposition return the ExpeditionResume of the curent exposition that just end.
     */
    public ExpeditionResume endExpedition () {
        double dead = this.deadProbability;
        if (this.sick) {
            dead = dead - 0.65;
            player.setState(PlayerStateEnum.SICK);
        }
        int objectsFound = minimumObject + Mathematiques.alea(2);
        switch (this.object.getId()) {
            // Couteau
            case 3:
                dead = dead + 0.15;
                break;
            // Torche
            case 15:
                objectsFound = objectsFound + 1;
                break;
            default:
                break;
        }
        if (Math.random() > dead) {
            return deadResume();
        } else {
            return successResume(objectsFound);
        }
    }


    private ExpeditionResume successResume(int numberObjectFound) {
        ArrayList <MontainObject> objectsFound = ObjectManager.getInstance().getRandomObjectAndDelete(numberObjectFound);
        InventoryManager.getInstance().add(objectsFound);
        this.player.eat(2 + Mathematiques.alea(2));
        this.player.heat(2);
        this.player.entertain(4 + Mathematiques.alea(3));
        if (this.sick) {
            this.player.setState(PlayerStateEnum.SICK);
        } else {
            this.player.setState(PlayerStateEnum.ALIVE);
        }
        if (object.use()) {
            InventoryManager.getInstance().remove(this.object);
            return new ExpeditionResumeSuccess("Hourra, enfin une bonne nouvelle, " + this.player.getName() + " est de retour ! Il semble frais comme un gardon, ou du moins juste frais.",  
                objectsFound, object);
        } else {
            return new ExpeditionResumeSuccess("Hourra, enfin une bonne nouvelle, " + this.player.getName() + " est de retour ! Il semble frais comme un gardon, ou du moins juste frais.", 
                objectsFound, null);
        }
    }

    private ExpeditionResume deadResume() {
        player.setState(PlayerStateEnum.GONE);
        InventoryManager.getInstance().remove(this.object);
        return new ExpeditionResumeFail("Malheureusement, je crois que nous ne reverrons pas " + this.player.getName() + ". Il a probablement perdu son chemin. Esperons qu'il trouve de l'aide et s'en sorte ! Paix à son âme.", 
            object, player);
    }

    
    // getter and setter
    public int getDayInExpedition() {
        return dayInExpedition;
    }
    public void setDayInExpedition(int dayInExpedition) {
        this.dayInExpedition = dayInExpedition;
    }
    public MontainObject getObject() {
        return object;
    }
    public void setObject(MontainObject object) {
        this.object = object;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
