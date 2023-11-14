package melchior.clement;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.front.FrontManager;

public class EventResumeController {

    @FXML
    private Button nextWindowButton;

    @FXML
    private ImageView objectLostImage;

    @FXML
    private AnchorPane objectLostPan;

    @FXML
    private AnchorPane objectWonPan;

    @FXML
    private Label playerChose1;

    @FXML
    private Label playerChoseLabel;

    @FXML
    private ImageView playerLostImage1;

    @FXML
    private Button previousWindowButton;

    @FXML
    private Text resume;

    @FXML
    private Label titleLabel;

    public void initialize(EventResume eventResume) {
        this.titleLabel.setText(eventResume.getName());
        this.resume.setText(eventResume.getResume());

        // Setting image for object won
        this.objectWonPan.setPrefWidth(120.0 * eventResume.getObjectsWon().size());
        Double layoutX = 10.0;
        Double layoutY = 0.0;
        for (MontainObject object : eventResume.getObjectsWon()) {
            // Setting image for objects won
            String filename = new File("src/main/resources/melchior/clement/picture/object/" + object.getName() + ".png").toURI().toString();
            ImageView imageView = new ImageView(new Image(filename));
            imageView.setLayoutX(layoutX);
            imageView.setLayoutY(layoutY);
            imageView.setFitHeight(100.0);
            imageView.setFitWidth(100.0);
            this.objectWonPan.getChildren().add(imageView);
            layoutX = layoutX + 110.0;
        }
        // Same for object lost
        this.objectLostPan.setPrefWidth(120.0 * eventResume.getObjectsLost().size());
        layoutX = 10.0;
        layoutY = 0.0;
        for (MontainObject object : eventResume.getObjectsLost()) {
            // Setting image for objects won
            String filename = new File("src/main/resources/melchior/clement/picture/object/" + object.getName() + ".png").toURI().toString();
            ImageView imageView = new ImageView(new Image(filename));
            imageView.setLayoutX(layoutX);
            imageView.setLayoutY(layoutY);
            imageView.setFitHeight(100.0);
            imageView.setFitWidth(100.0);
            this.objectWonPan.getChildren().add(imageView);
            layoutX = layoutX + 110.0;
        }
    }

    @FXML
    void nextAction(ActionEvent event) throws IOException {
        FrontManager.getInstance().nextWindow();
    }

    @FXML
    void previousAction(ActionEvent event) throws IOException {
        FrontManager.getInstance().previousWindow();
    }

    public void disablePreviousAction() {
        this.previousWindowButton.setDisable(true);
    }

    public void disableNextAction() {
        this.nextWindowButton.setDisable(true);
    }

}
