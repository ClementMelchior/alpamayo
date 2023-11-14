package melchior.clement.back.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.entity.player.PlayerStateEnum;
import melchior.clement.utilities.Mathematiques;

public class PlayerManager {
    private static Logger LOGGER = Logger.getLogger(PlayerManager.class);
    private static PlayerManager instance;
    private ArrayList <Player> players;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    private PlayerManager () {
        this.players = new ArrayList<Player>();
        this.players.add(new Player(0, "Dominique"));
        this.players.add(new Player(1, "Paul"));
        this.players.add(new Player(2, "Eliot"));
        this.players.add(new Player(3, "Gaspard"));
    }

    public void reset () {
        LOGGER.info("Resetting player");
        this.players = new ArrayList<Player>();
        this.players.add(new Player(0, "Dominique"));
        this.players.add(new Player(1, "Paul"));
        this.players.add(new Player(2, "Eliot"));
        this.players.add(new Player(3, "Gaspard"));
    }

    public void nextDay () {
        for (Player p : players) {
            p.nextDay();
        }
    }

    // utility

    /*
     *  add heat for all player alive, sick or cry
     */
    public void heat(int i) {
        for (Player p : players) {
            if (p.getState() == PlayerStateEnum.ALIVE || p.getState() == PlayerStateEnum.CRY || p.getState() == PlayerStateEnum.SICK) {
                p.heat(i);
            }
        }
    }

    public void heat() {
        heat(Player.MAX_HEAT);
    }

    /*
     *  add eat for all player alive, sick or cry
     */
    public void eat(int i) {
        for (Player p : players) {
            if (p.getState() == PlayerStateEnum.ALIVE || p.getState() == PlayerStateEnum.CRY || p.getState() == PlayerStateEnum.SICK) {
                p.eat(i);
            }
        }
    }

    public void eat(Player p, int i) {
        if (p.getState() == PlayerStateEnum.ALIVE || p.getState() == PlayerStateEnum.CRY || p.getState() == PlayerStateEnum.SICK) {
            p.eat(i);
        }
    }

    public void eat(Player p) {
        eat(p, Player.MAX_FOOD);
    }

    /*
     *  add entertainment for all player alive, sick or cry
     */
    public void entertain(int i) {
        for (Player p : players) {
            if (p.getState() == PlayerStateEnum.ALIVE || p.getState() == PlayerStateEnum.CRY || p.getState() == PlayerStateEnum.SICK) {
                p.entertain(i);
            }
        }
    }

    public void entertain() {
        entertain(Player.MAX_ENTERTAINMENT);
    }

    public void entertain(MontainObject obj) {
        if (obj != null) {
            if (!(obj.use())) {
                InventoryManager.getInstance().remove(obj.getId());
            }
            entertain(obj.getEntertainment());
        }
    }

    /*
    * Set sick a random player if there is alive or cry players 
    */
    public void randomSick () {
        ArrayList <Player> ps = getPlayers(PlayerStateEnum.ALIVE, PlayerStateEnum.CRY);
        Player p = ps.get(Mathematiques.alea(ps.size()-1));
        p.fallSick();
    }

    public ArrayList <Player> getPlayers () {
        return players;
    }

    public ArrayList <Player> getPlayers (PlayerStateEnum state) {
        return getPlayers (state, state);
    }

    public ArrayList <Player> getPlayers (PlayerStateEnum state1, PlayerStateEnum state2) {
        return getPlayers (state1, state2, state1);
    }

    public ArrayList <Player> getPlayers (PlayerStateEnum state1, PlayerStateEnum state2, PlayerStateEnum state3) {
        ArrayList <Player> playersTemp = new ArrayList<>();
        for (Player p : players) {
            if (p.getState() == state1 || p.getState() == state2 || p.getState() == state3) {
                playersTemp.add(p);
            }
        }
        if (playersTemp.size() > 0) {
            return playersTemp;
        } else {
            return null;
        }
    }

    // get random

    public Player getRandomPlayer (PlayerStateEnum state) {
        return getRandomPlayer (state, state);
    }

    public Player getRandomPlayer (PlayerStateEnum state1, PlayerStateEnum state2) {
        ArrayList <Player> ps = getPlayers (state1, state2);
        int alea = Mathematiques.alea(ps.size() - 1);
        return ps.get(alea);
    }

    
}
