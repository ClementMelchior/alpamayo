package melchior.clement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import melchior.clement.back.entity.event.Event;

public class EventYesNoController implements IEventController {

    @FXML
    private Button noButton;

    @FXML
    private Text resume;

    @FXML
    private Label titleLabel;

    @FXML
    private Button yesButton;

    @FXML
    void noAction(ActionEvent event) {

    }

    @FXML
    void yesAction(ActionEvent event) {

    }

    @Override
    public void initialize(Event event) {
        this.titleLabel.setText(event.getName());
        this.resume.setText(event.getQuestion());
    }

}