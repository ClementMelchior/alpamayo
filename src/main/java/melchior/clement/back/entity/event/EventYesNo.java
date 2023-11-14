package melchior.clement.back.entity.event;

public abstract class EventYesNo extends Event {
    
    public EventYesNo (String name, EventCategoryEnum eventCategory, EventTypeEnum eventType, String question, String soundName, int lock) {
        super (name, eventCategory, eventType, question, soundName, lock);
    }

    public abstract void passEvent (boolean yesno);
}
