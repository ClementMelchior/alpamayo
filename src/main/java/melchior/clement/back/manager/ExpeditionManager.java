package melchior.clement.back.manager;

import org.apache.log4j.Logger;

import melchior.clement.back.entity.event.list.expeditionProposal.ExpeditionProposalEvent;
import melchior.clement.back.entity.expedition.Expedition;
import melchior.clement.back.entity.expedition.ExpeditionResume;
import melchior.clement.back.entity.expedition.ExpeditionStateEnum;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;

public class ExpeditionManager {
    private static Logger LOGGER = Logger.getLogger(ExpeditionManager.class);

    private static ExpeditionManager instance;
    private Expedition expedition;
    private ExpeditionStateEnum state;
    private int daysLock;

    public static ExpeditionManager getInstance() {
        if (instance == null) {
            instance = new ExpeditionManager();
        }
        return instance;
    }

    private ExpeditionManager () {
        this.expedition = null;
        this.state = ExpeditionStateEnum.LOCK;
        this.daysLock = 3;
    }

    public void reset () {
        LOGGER.info("Resetting expedition");
        this.expedition = null;
        this.state = ExpeditionStateEnum.LOCK;
        this.daysLock = 3;
    }

    public ExpeditionProposalEvent getExpeditionProposal () {
        if (this.state == ExpeditionStateEnum.AVAILABLE) {
            return new ExpeditionProposalEvent();
        } 
        return null;
    }

    public void startExpedition (Player p, MontainObject o) {
        if (this.expedition == null && p.isAlive()) {
            this.expedition = new Expedition(o, p);
            this.state = ExpeditionStateEnum.IN_PROGRESS;
        } 
    }

    public ExpeditionResume nextDay () {
        ExpeditionResume expeditionResume = null;
        switch (state) {
            case LOCK:
                daysLock = daysLock - 1;
                if (daysLock == 0) {
                    this.state = ExpeditionStateEnum.AVAILABLE;
                }
                break;
            case IN_PROGRESS:
                if (this.expedition.nextDay()) {
                    this.state = ExpeditionStateEnum.LOCK;
                    this.daysLock = 3;
                    expeditionResume = this.expedition.endExpedition();
                }
                break;
            case AVAILABLE:
                break;
            default:
                break;
        }
        return expeditionResume;
    }

    // getter and setter
    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public ExpeditionStateEnum getState() {
        return state;
    }

    public void setState(ExpeditionStateEnum state) {
        this.state = state;
    }

    public int getDaysLock() {
        return daysLock;
    }

    public void setDaysLock(int daysLock) {
        this.daysLock = daysLock;
    }
}
