package melchior.clement;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import melchior.clement.back.entity.expedition.ExpeditionResume;
import melchior.clement.back.entity.expedition.ExpeditionResumeFail;
import melchior.clement.front.FrontManager;

public class ExpeditionResumeFailController implements IExpeditionResumeController {

    private ExpeditionResumeFail expeditionResumeFail;
    
    @FXML
    private Button nextWindowButton;

    @FXML
    private ImageView objectLostImage;

    @FXML
    private Label playerChose1;

    @FXML
    private Label playerChoseLabel;

    @FXML
    private ImageView playerLostImage;

    @FXML
    private Button previousWindowButton;

    @FXML
    private Text resume;

    @FXML
    private Label titleLabel;

    @Override
    public void initialize(ExpeditionResume expeditionResume) {
        this.titleLabel.setText(expeditionResume.getName());
        this.resume.setText(expeditionResume.getResume());
        if (expeditionResume instanceof ExpeditionResumeFail) {
            this.expeditionResumeFail = (ExpeditionResumeFail) expeditionResume;
            String filenameObjectLost = new File("src/main/resources/melchior/clement/picture/object/" + expeditionResumeFail.getObjectsLost().getName() + ".png").toURI().toString();
            this.objectLostImage.setImage(new Image(filenameObjectLost));

            String filenamePlayerLost = new File("src/main/resources/melchior/clement/picture/player/id/ko/" + expeditionResumeFail.getPlayerLost().getName() + ".jpg").toURI().toString();
            this.playerLostImage.setImage(new Image(filenamePlayerLost));
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
