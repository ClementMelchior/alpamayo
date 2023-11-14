package melchior.clement.front;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import melchior.clement.GameController;
import melchior.clement.back.entity.day.Day;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;

public class FrontManager {

    private static FrontManager instance;
    private FXMLLoader loader = new FXMLLoader(FrontManager.class.getResource("game.fxml"));
    private GameController gameController;
    private Pane root;

    public static FrontManager getInstance() throws IOException {
        if (instance == null) {
            instance = new FrontManager();
        }
        return instance;
    }

    private FrontManager () throws IOException {
        this.root = loader.load();
        this.gameController = this.loader.getController();
        
    }

    public void endDay () throws IOException, InterruptedException {
        GameManager.getInstance().endDay();
        Day day = GameManager.getInstance().getCurrentDay();
        ArrayList <Player> players = PlayerManager.getInstance().getPlayers();
        ArrayList <MontainObject> inventory = InventoryManager.getInstance().getObjects();

        this.gameController.update(day, players, inventory);
    }

    public void startDay () throws IOException, InterruptedException {
        this.gameController.startDay();
    }

    public void init () throws IOException, InterruptedException {
        Day day = GameManager.getInstance().getCurrentDay();
        ArrayList <Player> players = PlayerManager.getInstance().getPlayers();
        ArrayList <MontainObject> inventory = InventoryManager.getInstance().getObjects();

        this.gameController.initialize(day, players, inventory);
    }

    public void nextWindow() throws IOException {
        this.gameController.displayNextWindow();
    }

    public void previousWindow() throws IOException {
        this.gameController.displayPreviousWindow();
    }

    public Pane getPopup() {
        return root;
    }

    public void okActionFeedPlayer (boolean eatDominique, boolean eatPaul, boolean eatEliot, boolean eatGaspard, boolean heat, boolean entertain) throws IOException, InterruptedException {
        //GameManager.getInstance().feedPlayer();
        this.gameController.okActionFeedPlayer();
    }

}
