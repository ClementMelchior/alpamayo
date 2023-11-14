package melchior.clement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import melchior.clement.back.entity.player.Player;
import melchior.clement.front.FrontManager;

public class PlayerResumeController {

    @FXML
    private Circle circleDominiquePane1;

    @FXML
    private Circle circleEliotPane1;

    @FXML
    private Circle circleGaspardPane1;

    @FXML
    private Circle circlePaulPane1;

    @FXML
    private Button nextWindowButton;

    @FXML
    private Button previousWindowButton;

    @FXML
    private Text resumeDominique;

    @FXML
    private Text resumeEliot;

    @FXML
    private Text resumeGaspard;

    @FXML
    private Text resumePaul;

    public void initialize (ArrayList <Player> players) {
        for (Player p : players) {
            String str = "ko";
            if (p.isAlive()) {
                str = "ok";
            }
            String filename = new File("src/main/resources/melchior/clement/picture/player/id/" + str + "/" + p.getName() + ".jpg").toURI().toString();
            Image img = new Image(filename);
            switch (p.getName()) {
                case "Dominique": 
                    circleDominiquePane1.setFill(new ImagePattern(img));
                    if (str.equals("ok")) {
                        resumeDominique.setText(p.getReport());
                    }
                    break;
                case "Eliot": 
                    circleEliotPane1.setFill(new ImagePattern(img));
                    if (str.equals("ok")) {
                        resumeEliot.setText(p.getReport());
                    }
                    break;
                case "Gaspard": 
                    circleGaspardPane1.setFill(new ImagePattern(img));
                    if (str.equals("ok")) {
                        resumeGaspard.setText(p.getReport());
                    }
                    break;
                case "Paul": 
                    circlePaulPane1.setFill(new ImagePattern(img));
                    if (str.equals("ok")) {
                        resumePaul.setText(p.getReport());
                    }
                    break;
            }
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
