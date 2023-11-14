package melchior.clement.back.entity.event;

import java.io.File;

import org.apache.log4j.Logger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class Event {
    private static Logger LOGGER = Logger.getLogger(Event.class);
    private String name;
    private String question;
    private EventCategoryEnum eventCategory;
    private EventTypeEnum eventType;
    private String soundName;
    protected int lock;
    
    public Event(String name, EventCategoryEnum eventCategory, EventTypeEnum eventType, String question, String soundName, int lock) {
        LOGGER.info("Creating event : " + name);
        this.name = name;
        this.eventCategory = eventCategory;
        this.eventType = eventType;
        this.question = question;
        this.soundName=soundName;
        this.lock = lock;
    }

    public void playSound() {
        if (this.soundName != null) {
            String file = new File("src/main/resources/melchior/clement/sound/" + this.soundName).toURI().toString();
            Media sound = new Media(file);
            MediaPlayer mp = new MediaPlayer(sound);
            mp.play();
        }
    }

    // getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EventCategoryEnum getEventCategory() {
        return eventCategory;
    }
    public void setEventCategory(EventCategoryEnum eventCategory) {
        this.eventCategory = eventCategory;
    }
    public EventTypeEnum getEventType() {
        return eventType;
    }
    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public int getLock() {
        return lock;
    }
    public void setLock(int lock) {
        this.lock = lock;
    }
    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }
}
