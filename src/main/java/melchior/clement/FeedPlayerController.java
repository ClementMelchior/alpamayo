package melchior.clement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import melchior.clement.back.entity.player.Player;
import melchior.clement.front.FrontManager;

public class FeedPlayerController {

    private int numberEat = 0;
    private int numberHeat = 0;
    private int numberEntertain = 0;
    private ArrayList <Player> players;
    private Player paul;
    private Player dominique;
    private Player gaspard;
    private Player eliot;


    @FXML
    private ImageView jeuImage;

    @FXML
    private CheckBox checkBoxJeu;

    @FXML
    private CheckBox checkBoxGaz;

    @FXML
    private CheckBox checkBoxViandeDominique;

    @FXML
    private CheckBox checkBoxViandeEliot;

    @FXML
    private CheckBox checkBoxViandeGaspard;

    @FXML
    private CheckBox checkBoxViandePaul;

    @FXML
    private Circle circleDominiquePane2;

    @FXML
    private Circle circleEliotPane2;

    @FXML
    private Circle circleGaspardPane2;

    @FXML
    private Circle circlePaulPane2;

    @FXML
    private ImageView gazImage;

    @FXML
    private Label numberJeuLabel;

    @FXML
    private Label numberGazLabel;

    @FXML
    private Label numberViandeLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button previousWindowButton;

    @FXML
    private ImageView viandeImage;

    public void initialize (ArrayList <Player> players, int numberEat, int numberHeat, int numberEntertain) {
        String filename = new File("src/main/resources/melchior/clement/picture/object/Jeu.png").toURI().toString();
        this.jeuImage.setImage(new Image(filename));
        filename = new File("src/main/resources/melchior/clement/picture/object/Viande.png").toURI().toString();
        this.viandeImage.setImage(new Image(filename));
        filename = new File("src/main/resources/melchior/clement/picture/object/Gaz.png").toURI().toString();
        this.gazImage.setImage(new Image(filename));

        this.players = players;
        this.paul = getPlayerById(0);
        this.dominique = getPlayerById(1);
        this.gaspard = getPlayerById(2);
        this.eliot = getPlayerById(3);

        this.numberEat = numberEat;
        this.numberHeat = numberHeat;
        this.numberEntertain = numberEntertain;

        updateNumberLabel();
        disableEatCheckbox();
        enableEatCheckbox();
        enableDisableHeatCheckbox();
        enableDisableEntertainCheckbox();

        initializePlayer(players);
    }

    private void initializePlayer (ArrayList <Player> players) {
        for (Player p : players) {
            String str = "ko";
            if (p.isAlive()) {
                str = "ok";
            }
            String filename = new File("src/main/resources/melchior/clement/picture/player/id/" + str + "/" + p.getName() + ".jpg").toURI().toString();
            Image img = new Image(filename);
            switch (p.getName()) {
                case "Dominique": 
                    circleDominiquePane2.setFill(new ImagePattern(img));
                    break;
                case "Eliot": 
                    circleEliotPane2.setFill(new ImagePattern(img));
                    break;
                case "Gaspard": 
                    circleGaspardPane2.setFill(new ImagePattern(img));
                    break;
                case "Paul": 
                    circlePaulPane2.setFill(new ImagePattern(img));
                    break;
            }
        }
    }

    @FXML
    void checkBoxDominique(ActionEvent event) {
        if (this.checkBoxViandeDominique.isSelected()) {
            this.numberEat = this.numberEat - 1;
        } else {
            this.numberEat = this.numberEat + 1;
        }
        updateNumberLabel();
        enableDisableEatCheckbox();
    }

    @FXML
    void checkBoxEliot(ActionEvent event) {
        if (this.checkBoxViandeEliot.isSelected()) {
            this.numberEat = this.numberEat - 1;
        } else {
            this.numberEat = this.numberEat + 1;
        }
        updateNumberLabel();
        enableDisableEatCheckbox();
    }

    @FXML
    void checkBoxGaspard(ActionEvent event) {
        if (this.checkBoxViandeGaspard.isSelected()) {
            this.numberEat = this.numberEat - 1;
        } else {
            this.numberEat = this.numberEat + 1;
        }
        updateNumberLabel();
        enableDisableEatCheckbox();
    }

    @FXML
    void checkBoxGaz(ActionEvent event) {
        if (this.checkBoxGaz.isSelected()) {
            this.numberHeat = this.numberHeat - 1;
        } else {
            this.numberHeat = this.numberHeat + 1;
        }
        updateNumberLabel();
        enableDisableHeatCheckbox();
    }

    @FXML
    void checkBoxPaul(ActionEvent event) {
        if (this.checkBoxViandePaul.isSelected()) {
            this.numberEat = this.numberEat - 1;
        } else {
            this.numberEat = this.numberEat + 1;
        }
        updateNumberLabel();
        enableDisableEatCheckbox();
    }

    @FXML
    void checkBoxJeu(ActionEvent event) {
        if (this.checkBoxJeu.isSelected()) {
            this.numberEntertain = this.numberEntertain - 1;
        } else {
            this.numberEntertain = this.numberEntertain + 1;
        }
        updateNumberLabel();
        enableDisableEntertainCheckbox();
    }

    private void updateNumberLabel() {
        this.numberJeuLabel.setText(String.valueOf(this.numberEntertain));
        this.numberGazLabel.setText(String.valueOf(this.numberHeat));
        this.numberViandeLabel.setText(String.valueOf(this.numberEat));

    }

    private void enableDisableEatCheckbox () {
        if (this.numberEat == 0) {
            disableEatCheckbox();
        } else {
            enableEatCheckbox();
        }
    }

    private void enableDisableHeatCheckbox () {
        if (this.numberHeat == 0) {
            if (!this.checkBoxGaz.isSelected()) {
                this.checkBoxGaz.setDisable(true);
            }
        } else {
            this.checkBoxGaz.setDisable(false);
        }
    }

    private void enableDisableEntertainCheckbox () {
        if (this.numberEntertain == 0) {
            if (!this.checkBoxJeu.isSelected()) {
                this.checkBoxJeu.setDisable(true);
            }
        } else {
            this.checkBoxJeu.setDisable(false);
        }
    }

    private void disableEatCheckbox () {
        if (!checkBoxViandeDominique.isSelected()) {
            this.checkBoxViandeDominique.setDisable(true);
        }
        if (!checkBoxViandeEliot.isSelected()) {
            this.checkBoxViandeEliot.setDisable(true);
        }        
        if (!checkBoxViandeGaspard.isSelected()) {
            this.checkBoxViandeGaspard.setDisable(true);
        }        
        if (!checkBoxViandePaul.isSelected()) {
            this.checkBoxViandePaul.setDisable(true);
        }
    }

    private void enableEatCheckbox () {
        if (this.dominique.isAlive()) {
            this.checkBoxViandeDominique.setDisable(false);
        }
        if (this.eliot.isAlive()) {
            this.checkBoxViandeEliot.setDisable(false);
        }
        if (this.gaspard.isAlive()) {
            this.checkBoxViandeGaspard.setDisable(false);
        }
        if (this.paul.isAlive()) {
            this.checkBoxViandePaul.setDisable(false);
        }
    }

    private Player getPlayerById (int id) {
        for (Player p : this.players) {
            if (p.getId()==id) {
                return p;
            }
        }
        return null;
    }

    @FXML
    void previousAction(ActionEvent event) throws IOException {
        FrontManager.getInstance().previousWindow();
    }

    public void disablePreviousAction() {
        this.previousWindowButton.setDisable(true);
    }

    public void disableNextAction() {
        //nothing to do
    }

    @FXML
    void okAction(ActionEvent event) throws IOException, InterruptedException {
        FrontManager.getInstance().okActionFeedPlayer(this.checkBoxViandeDominique.isSelected(),
            this.checkBoxViandePaul.isSelected(),
            this.checkBoxViandeEliot.isSelected(),
            this.checkBoxViandeGaspard.isSelected(),
            this.checkBoxGaz.isSelected(), 
            this.checkBoxJeu.isSelected());
    }
    
}