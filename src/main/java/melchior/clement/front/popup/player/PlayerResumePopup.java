package melchior.clement.front.popup.player;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import melchior.clement.PlayerResumeController;
import melchior.clement.back.entity.player.Player;
import melchior.clement.front.popup.IPopup;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerResumePopup implements IPopup {
    private Pane root;
    private PlayerResumeController controller;
    private ArrayList<Player> players;

    public PlayerResumePopup(ArrayList <Player> players) throws IOException {
        FXMLLoader loader = new FXMLLoader(PlayerResumePopup.class.getResource("playerResumeWindow.fxml"));
        this.root = loader.load();
        this.controller = loader.getController();
        this.players = players;
        //this.objectList.add(0, new Main());
    }

    public void init () {
        this.controller.initialize(this.players);
    }

    public Pane getPopup() {
        return root;
    }

    public void disableNextAction() {
        this.controller.disableNextAction();
    }

    public void disablePreviousAction() {
        this.controller.disablePreviousAction();
    }
}