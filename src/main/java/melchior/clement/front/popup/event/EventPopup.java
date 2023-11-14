package melchior.clement.front.popup.event;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import melchior.clement.IEventController;
import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventYesNo;
import melchior.clement.back.entity.event.list.expeditionProposal.ExpeditionProposalEvent;
import java.io.IOException;

public class EventPopup {
    private Pane root;
    private IEventController controller;
    private Event event;

    public EventPopup(Event event) throws IOException {
        FXMLLoader loader;
        if (event instanceof EventChoiceObject) {
            loader = new FXMLLoader(EventPopup.class.getResource("eventChoiceObjectWindow.fxml"));
        } else if (event instanceof EventYesNo) {
            loader = new FXMLLoader(EventPopup.class.getResource("eventYesNoWindow.fxml"));
        } else if (event instanceof ExpeditionProposalEvent) {
            loader = new FXMLLoader(EventPopup.class.getResource("expeditionProposalEventWindow.fxml"));
        } else {
            loader = new FXMLLoader(EventPopup.class.getResource("eventChoiceObjectWindow.fxml"));
        }
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
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}