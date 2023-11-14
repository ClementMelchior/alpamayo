package melchior.clement.back.manager;

import org.apache.log4j.Logger;
import melchior.clement.back.entity.day.Day;
import melchior.clement.back.entity.day.FirstDay;
import melchior.clement.back.entity.day.LastDay;
import melchior.clement.back.entity.day.LastDayReasonEnum;
import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.expedition.ExpeditionResume;

/*

GameManager getInstance ();
void startGame ();
Day getNextDay();

// ------------------------ Player ------------------------

PlayerResume getPlayerResume ();
void feedPlayer (boolean [] isEating, boolean isHeating, MontainObject object);

*/

public class GameManager {
    
    private static Logger LOGGER = Logger.getLogger(GameManager.class);

    private static GameManager instance;
    public final int MAXIMUM_DAY = 120;

    // other
    public static int currentDay;
    public static Day [] days;
    public static FirstDay firstDay;
    public static LastDay lastDay;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private GameManager() {
        currentDay = 0;
        days = new Day[MAXIMUM_DAY];
        firstDay = new FirstDay();
        lastDay = new LastDay(LastDayReasonEnum.TIME, "Il faut se rendre à l'évidence, personne ne viendra nous chercher. Dans un dernier espoir, nous avons decidé de quitter notre abris, et essayer de rejoindre la vie humaine. Souhaitons nous bonne chance !");    
    }

    public void startGame () {
        LOGGER.info("STARTING GAME");
        currentDay = 0;

        ObjectManager.getInstance().reset();
        InventoryManager.getInstance().setStartInventory();
        PlayerManager.getInstance().reset();
        EventManager.getInstance().reset();
        ExpeditionManager.getInstance().reset();

        days = new Day[MAXIMUM_DAY];
        for (int i=0; i < MAXIMUM_DAY; i++) {
            days[i] = new Day(i);
        }
        firstDay = new FirstDay();
        lastDay = new LastDay(LastDayReasonEnum.TIME, "Il faut se rendre à l'évidence, personne ne viendra nous chercher. Dans un dernier espoir, nous avons decidé de quitter notre abris, et essayer de rejoindre la vie humaine. Souhaitons nous bonne chance !");    
    }

    public void endDay () {
        LOGGER.info("Ending day");
        this.currentDay = this.currentDay + 1;
        if (this.days[this.currentDay].getEvent() == null) {
            LOGGER.info("Finding event");
            this.days[this.currentDay].setEvent(EventManager.getInstance().getNewEvent());
        }
    }

    /*
     * Set last day
     */
    public void setLastDay (LastDay lastDay, int minimumDay) {
        if (currentDay + minimumDay < MAXIMUM_DAY) {
            days[currentDay + minimumDay] = lastDay;
        }
    }

    // ------------------------ Event ------------------------

    /*
     * Add new event for futur day
     */
    public void addEvent (Event event, int minimumDay) {
        while (days[currentDay + minimumDay].getEvent() != null || (currentDay + minimumDay) < 118) {
            minimumDay = minimumDay + 1;
        }
        if (days[currentDay + minimumDay].getEvent() == null) {
            days[currentDay + minimumDay].setEvent(event);
        }
    }

    // ------------------------ Expedition ------------------------

    /*
     * Get the expedition resume for the current day
     */
    public ExpeditionResume getExpeditionResume () {
        return days[currentDay].getExpeditionResume();
    }

    // ------------------------ Player ------------------------

    public void startDay () {

    }

    public void nextDay () {

    }

    public Day getNextDay () {
        return days[currentDay + 1];
    }

    public Day getCurrentDay () {
        return days[currentDay];
    }
}
