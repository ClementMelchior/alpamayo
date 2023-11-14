package melchior.clement.back.entity.player;

import java.util.ArrayList;

import melchior.clement.back.entity.object.MontainObject;

public class PlayerResume {
    private ArrayList <Player> playersAlive;
    private ArrayList <Player> playersLost;
    private int food;
    private int gaz;
    private ArrayList <MontainObject> entertainmentObjects;
    private ArrayList <MontainObject> lostObjects;

// revoir la methode de report des player et comment les nourrir
// revoir evntManager et passEvent, pas besoin de passer par le GameManager pour faire passEvent
    public PlayerResume(ArrayList<Player> playersAlive, ArrayList<Player> playersLost, int food, int gaz,
        ArrayList<MontainObject> entertainmentObjects) {
        this.playersAlive = playersAlive;
        this.playersLost = playersLost;
        this.food = food;
        this.gaz = gaz;
        this.entertainmentObjects = entertainmentObjects;
        this.lostObjects = new ArrayList <MontainObject> ();
    }

    // getter and setter
    public ArrayList<Player> getPlayersAlive() {
        return playersAlive;
    }
    public void setPlayersAlive(ArrayList<Player> playersAlive) {
        this.playersAlive = playersAlive;
    }
    public void removePlayerAlive (Player player) {
        playersAlive.remove(player);
    }
    public ArrayList<Player> getPlayersLost() {
        return playersLost;
    }
    public void setPlayersLost(ArrayList<Player> playersLost) {
        this.playersLost = playersLost;
    }
    public void removePlayerLost (Player player) {
        playersLost.remove(player);
    }
    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public int getGaz() {
        return gaz;
    }
    public void setGaz(int gaz) {
        this.gaz = gaz;
    }
    public ArrayList<MontainObject> getEntertainmentObjects() {
        return entertainmentObjects;
    }
    public void setEntertainmentObjects(ArrayList<MontainObject> entertainmentObjects) {
        this.entertainmentObjects = entertainmentObjects;
    }
    public void removeEntertainmentObject (MontainObject object) {
        entertainmentObjects.remove(object);
    }
    public ArrayList<MontainObject> getLostObjects() {
        return lostObjects;
    }
    public void setLostObjects(ArrayList<MontainObject> lostObjects) {
        this.lostObjects = lostObjects;
    }
    public void addLostObject (MontainObject object) {
        lostObjects.add(object);
    }
}
