package melchior.clement.back.entity.event.list.expeditionProposal;

import java.util.ArrayList;

import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Couteau;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.Grapin;
import melchior.clement.back.entity.object.list.Torche;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.entity.player.PlayerStateEnum;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;

public class ExpeditionProposalEvent extends Event {
    private ArrayList <Player> possiblePlayers;
    private ArrayList <MontainObject> possibleObjects;
    private ArrayList <MontainObject> objects;


    public ExpeditionProposalEvent() {
        super("Expedition", EventCategoryEnum.NONE, EventTypeEnum.EXPEDITION, 
            "Le temps est clair aujourd'hui, nous devrions peut etre envisager une expedition ?", 
            null, 0);
            this.objects = new ArrayList<>();
            this.objects.add(new Couteau());
            this.objects.add(new Fusil());
            this.objects.add(new Grapin());
            this.objects.add(new Torche());
            this.possibleObjects = new ArrayList<MontainObject>();
            this.possiblePlayers = new ArrayList<Player>();
    }

    public void update () {
        this.possibleObjects = getPossibleObjectsFromInventory();
        this.possiblePlayers = getPossiblePlayersAvailable();
    }

    private ArrayList <MontainObject> getPossibleObjectsFromInventory() {
        ArrayList <MontainObject> possibleObjectsTemp = new ArrayList<MontainObject>();
        for (MontainObject o : this.objects) {
            if (InventoryManager.getInstance().isInIventory(o)) {
                possibleObjectsTemp.add(o);
            }
        }
        return possibleObjectsTemp;
    }

    private ArrayList <Player> getPossiblePlayersAvailable() {
        ArrayList <Player> possiblePlayersTemp = new ArrayList<Player>();
        possiblePlayersTemp = PlayerManager.getInstance().getPlayers(PlayerStateEnum.ALIVE, PlayerStateEnum.CRY, PlayerStateEnum.SICK);
        return possiblePlayersTemp;
    }

    // getter and setter
    public ArrayList<Player> getPossiblePlayers() {
        return possiblePlayers;
    }
    public void setPossiblePlayers(ArrayList<Player> players) {
        this.possiblePlayers = players;
    }
    public ArrayList<MontainObject> getPossibleObjects() {
        return possibleObjects;
    }
    public void setPossibleObjects(ArrayList<MontainObject> possibleObjects) {
        this.possibleObjects = possibleObjects;
    }
}
