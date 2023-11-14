package melchior.clement.back.entity.event;

import java.util.ArrayList;

public abstract class EventChoiceObject extends Event{
    public EventChoiceObject (String name, EventCategoryEnum eventCategory, String question, String soundName, int lock) {
        super (name, eventCategory, EventTypeEnum.OBJECT, question, soundName, lock);
    }

    public abstract void passEvent (ChoiceObject choice);

    public abstract void update ();

    public abstract ArrayList <ChoiceObject> getChoices();

}
