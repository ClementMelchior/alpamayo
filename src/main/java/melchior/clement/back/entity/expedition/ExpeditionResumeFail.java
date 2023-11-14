package melchior.clement.back.entity.expedition;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;

public class ExpeditionResumeFail extends ExpeditionResume {
    private MontainObject objectLost;
    private Player playerLost;

    public ExpeditionResumeFail(String resume, MontainObject objectLost, Player playerLost) {
        super("Fin d'expedition", resume);
        this.objectLost = objectLost;
        this.playerLost = playerLost;
    }

        public MontainObject getObjectsLost() {
        return objectLost;
    }
    public void setObjectsLost(MontainObject objectLost) {
        this.objectLost = objectLost;
    }
    public Player getPlayerLost() {
        return playerLost;
    }
    public void setPlayerLost(Player playerLost) {
        this.playerLost = playerLost;
    }
}
