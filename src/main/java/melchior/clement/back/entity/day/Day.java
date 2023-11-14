package melchior.clement.back.entity.day;

import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.expedition.ExpeditionResume;
import melchior.clement.utilities.Mathematiques;


public class Day {
    private int dayNumber;
    private int temperature;
    private EventResume eventResume;
    private ExpeditionResume expeditionResume;
    private Event event;

    public Day() {
        this.dayNumber = 0;
        this.temperature = (-9 + Mathematiques.alea(5));
        this.eventResume = null;
        this.expeditionResume = null;
        this.event = null;
    }

    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
        this.temperature = (-9 + Mathematiques.alea(5));
        this.eventResume = null;
        this.expeditionResume = null;
        this.event = null;
    }

    // getter andx setter

    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public EventResume getEventResume() {
        return eventResume;
    }
    public void setEventResume(EventResume eventResume) {
        this.eventResume = eventResume;
    }
    public ExpeditionResume getExpeditionResume() {
        return expeditionResume;
    }
    public void setExpeditionResume(ExpeditionResume expeditionResume) {
        this.expeditionResume = expeditionResume;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public int getDayNumber() {
        return dayNumber;
    }
    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }
    
}
