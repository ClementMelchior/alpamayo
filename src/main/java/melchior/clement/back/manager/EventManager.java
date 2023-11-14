package melchior.clement.back.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.list.bonus.BeauTempsEvent;
import melchior.clement.back.entity.event.list.bonus.DahuEvent;
import melchior.clement.back.entity.event.list.end.alpiniste.Alpiniste1Event;
import melchior.clement.back.entity.event.list.end.helicoptere.HelicoptereEvent;
import melchior.clement.back.entity.event.list.end.hommeCaverne.HommeCaverne1Event;
import melchior.clement.back.entity.event.list.end.yeti.Yeti1Event;
import melchior.clement.back.entity.event.list.malus.ArraigneeEvent;
import melchior.clement.back.entity.event.list.malus.EffondrementEvent;
import melchior.clement.back.entity.event.list.malus.EnfantEvent;
import melchior.clement.back.entity.event.list.malus.LoupEvent;
import melchior.clement.back.entity.event.list.malus.MarmottesEvent;
import melchior.clement.back.entity.event.list.malus.TempeteEvent;
import melchior.clement.back.entity.event.list.none.FantomeEvent;
import melchior.clement.back.entity.event.list.none.NomadeEvent;
import melchior.clement.back.entity.event.list.none.Survivant;
import melchior.clement.back.entity.expedition.ExpeditionStateEnum;
import melchior.clement.utilities.Mathematiques;

public class EventManager {

    private static Logger LOGGER = Logger.getLogger(EventManager.class);

    private static EventManager instance;
    public ArrayList <Event> availableEvents;
    public ArrayList <Event> lockEvents;
    
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    private EventManager () {
        availableEvents = new ArrayList<Event>();
        lockEvents = new ArrayList<Event>();
        populateEvent();
    }

    public void reset () {
        LOGGER.info("Resetting event");
        availableEvents = new ArrayList<Event>();
        lockEvents = new ArrayList<Event>();
        populateEvent();
    }

    private void populateEvent() {
        LOGGER.info("Populate event");
        this.lockEvents.add(new BeauTempsEvent());
        this.lockEvents.add(new DahuEvent());
        this.lockEvents.add(new ArraigneeEvent());
        this.lockEvents.add(new EffondrementEvent());
        this.lockEvents.add(new TempeteEvent());
        this.lockEvents.add(new Survivant());
        this.lockEvents.add(new EnfantEvent());
        this.lockEvents.add(new MarmottesEvent());
        this.lockEvents.add(new FantomeEvent());
        this.lockEvents.add(new NomadeEvent());
        this.lockEvents.add(new LoupEvent());
        // ------ Ending event ------
        this.lockEvents.add(new Alpiniste1Event());
        this.lockEvents.add(new Yeti1Event());
        this.lockEvents.add(new HommeCaverne1Event());
        this.lockEvents.add(new HelicoptereEvent());
    }

    public Event getNewEvent () {
        Event event = null;
        int alea = Mathematiques.alea(5);

        if (alea < 3) {
            if (ExpeditionManager.getInstance().getState().equals(ExpeditionStateEnum.AVAILABLE)) {
                LOGGER.info("Expedition found");
                event = ExpeditionManager.getInstance().getExpeditionProposal();
            }
        } else {
            LOGGER.info("Event found");
            event = getRandomEvent();
        }
        return event;
    }

    public Event getRandomEvent () {
        Event event = null;
        int alea = Mathematiques.alea(this.availableEvents.size() - 1);
        event = availableEvents.get(alea);
        availableEvents.remove(alea);
        return event;
    }

    public Event nextDay() {
        for (Event e : lockEvents) {
            e.setLock(e.getLock() - 1);
            if (e.getLock() <= 0) {
                availableEvents.add(e);
                lockEvents.remove(e);
            }
        }
        return getNewEvent();
    }

    public void addEvent (Event e) {
        if (e.getLock() > 0) {
            lockEvents.add(e);
        } else {
            availableEvents.add(e);
        }
    }
}
