package melchior.clement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.front.FrontManager;

public class EventChoiceObjectController implements IEventController {

    private EventChoiceObject eventChoiceObject;
    private ArrayList <ChoiceObject> choices;
    private ChoiceObject currentChoice;
    private int currentPosition;

    @FXML
    private ImageView choiceObjectImage;

    @FXML
    private Text choiceResumeText;

    @FXML
    private Button nextButton;

    @FXML
    private Button okButton;

    @FXML
    private Label playerChoseLabel;

    @FXML
    private Button previousButton;

    @FXML
    private Text resume;

    @FXML
    private Label titleLabel;

    @Override
    public void initialize(Event event) {
        this.titleLabel.setText(event.getName());
        this.resume.setText(event.getQuestion());
        if (event instanceof EventChoiceObject) {
            this.eventChoiceObject = (EventChoiceObject) event;
            this.choices = (ArrayList<ChoiceObject>) eventChoiceObject.getChoices().stream().filter(c -> c.isPossible()).collect(Collectors.toList());
            this.currentChoice = this.choices.get(0);
 
            updateCurrentChoiceCard();
        }
    }

    private void updateCurrentChoiceCard() {
        if (this.currentChoice != null) {
            String filename = "";
            try {
                filename = new File("src/main/resources/melchior/clement/picture/object/" + this.currentChoice.getObject().getName() + ".png").toURI().toString();
            } catch (Exception e) {
                filename = new File("src/main/resources/melchior/clement/picture/object/Main.png").toURI().toString();
            }
            this.choiceObjectImage.setImage(new Image(filename));
            this.choiceResumeText.setText(this.currentChoice.getResume());
            isButtonAvailable();
        }
    }

    @FXML
    void nextAction(ActionEvent event) {
        if (this.currentPosition + 1 <= this.choices.size() - 1) {
            this.currentPosition = this.currentPosition + 1;
            this.currentChoice = this.choices.get(this.currentPosition);
            updateCurrentChoiceCard();
            isButtonAvailable();
        } 
    }

    @FXML
    void previousAction(ActionEvent event) {
        if (this.currentPosition - 1 >= 0) {
            this.currentPosition = this.currentPosition - 1;
            this.currentChoice = this.choices.get(this.currentPosition);
            updateCurrentChoiceCard();
            isButtonAvailable();
        }     
    }

    private void isButtonAvailable () {
        if (this.currentPosition - 1 < 0) {
            this.previousButton.setDisable(true);
        } else {
            this.previousButton.setDisable(false);
        }
        if (this.currentPosition + 1 > this.choices.size()-1) {
            this.nextButton.setDisable(true);
        } else {
            this.nextButton.setDisable(false);
        }
    }

    @FXML
    void okAction(ActionEvent e) throws IOException, InterruptedException {
        this.eventChoiceObject.passEvent(currentChoice);
        FrontManager.getInstance().endDay();
    }
}