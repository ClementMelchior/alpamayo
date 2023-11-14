package melchior.clement.front.popup.event;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import melchior.clement.EventResumeController;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.front.popup.IPopup;

import java.io.IOException;

public class EventResumePopup implements IPopup {
    private Pane root;
    private EventResumeController controller;
    private EventResume event;

    public EventResumePopup(EventResume event) throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(EventPopup.class.getResource("eventResumeWindow.fxml"));
        this.event = event;
        this.root = loader.load();
        this.controller = loader.getController();
    }

    public void init () {
        this.controller.initialize(this.event);
    }

    public Pane getPopup() {
        return root;
    }
    public EventResume getEvent() {
        return event;
    }

    public void setEvent(EventResume event) {
        this.event = event;
    }

    public void disableNextAction() {
        this.controller.disableNextAction();
    }

    public void disablePreviousAction() {
        this.controller.disablePreviousAction();
    }
}