package melchior.clement.front.popup.feed;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import melchior.clement.FeedPlayerController;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.front.popup.IPopup;

import java.io.IOException;
import java.util.ArrayList;

public class FeedPlayerPopup implements IPopup {
    private Pane root;
    private FeedPlayerController controller;
    private ArrayList<Player> players;
    private ArrayList<MontainObject> inventory;

    public FeedPlayerPopup(ArrayList <Player> players, ArrayList <MontainObject> inventory) throws IOException {
        FXMLLoader loader = new FXMLLoader(FeedPlayerPopup.class.getResource("feedPlayerWindow.fxml"));
        this.root = loader.load();
        this.controller = loader.getController();
        this.players = players;
        this.inventory = inventory;
    }

    public void init () {
        this.controller.initialize(this.players, 2, 3, 1);
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