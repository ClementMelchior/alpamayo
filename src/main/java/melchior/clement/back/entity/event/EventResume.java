package melchior.clement.back.entity.event;

import java.util.ArrayList;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;

public class EventResume {
    private String name;
    private String resume; 
    private ArrayList <MontainObject> objectsLost;
    private ArrayList <MontainObject> objectsWon;
    private ArrayList <Player> playerLost;

    public EventResume(String name, String resume, ArrayList<MontainObject> objectsLost,
            ArrayList<MontainObject> objectsWon, ArrayList<Player> playersLost) {
        this.name = name;
        this.resume = resume;
        if (objectsLost == null) {
            this.objectsLost = new ArrayList<MontainObject>();
        } else {
            this.objectsLost = objectsLost;
        }
        if (objectsWon == null) {
            this.objectsWon = new ArrayList<MontainObject>();
        } else {
            this.objectsWon = objectsWon;
        }
        this.playerLost = playersLost;
    }

    // getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public ArrayList<MontainObject> getObjectsLost() {
        return objectsLost;
    }
    public void setObjectsLost(ArrayList<MontainObject> objectsLost) {
        this.objectsLost = objectsLost;
    }
    public ArrayList<MontainObject> getObjectsWon() {
        return objectsWon;
    }
    public void setObjectsWon(ArrayList<MontainObject> objectsWon) {
        this.objectsWon = objectsWon;
    }
    public ArrayList<Player> getPlayerLost() {
        return playerLost;
    }
    public void setPlayerLost(ArrayList<Player> playerLost) {
        this.playerLost = playerLost;
    }
}
