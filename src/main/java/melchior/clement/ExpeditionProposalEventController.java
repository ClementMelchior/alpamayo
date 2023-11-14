package melchior.clement;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.list.expeditionProposal.ExpeditionProposalEvent;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.player.Player;

public class ExpeditionProposalEventController implements IEventController{

    private ExpeditionProposalEvent expeditionProposal;
    private ArrayList <String> playersName;
    private ArrayList <String> objectsName;

    @FXML
    private Button noButton;

    @FXML
    private ImageView objectImage;

    @FXML
    private ListView<String> objectListView;

    @FXML
    private Button okButton;

    @FXML
    private Label playerChose1;

    @FXML
    private Label playerChoseLabel;

    @FXML
    private ImageView playerImage;

    @FXML
    private ListView<String> playerListView;

    @FXML
    private Text textDescription;

    @FXML
    private Label titleLabel; 

    public void initialize(Event event) {
        if (event instanceof ExpeditionProposalEvent) {
            this.expeditionProposal = (ExpeditionProposalEvent) event;
            ArrayList <Player> players = this.expeditionProposal.getPossiblePlayers();
            ArrayList <MontainObject> objects = this.expeditionProposal.getPossibleObjects();
            if (!players.isEmpty() || players != null) {
                this.playersName = (ArrayList<String>) players.stream().map(p -> p.getName()).collect(Collectors.toList());
                initializePlayer();
            }
            if (!objects.isEmpty() || objects != null) {
                this.objectsName = (ArrayList<String>) objects.stream().map(o -> o.getName()).collect(Collectors.toList());
                initializeObject();
            }
        }
    }

    private void initializePlayer () {
        ObservableList<String> items =FXCollections.observableArrayList (
                this.playersName);
        playerListView.setItems(items);
        playerListView.getSelectionModel().selectedItemProperty().addListener(this::selectionPlayerChanged);
    }

    private void initializeObject () {
        ObservableList<String> items =FXCollections.observableArrayList (
                this.objectsName);
        objectListView.setItems(items);
        objectListView.getSelectionModel().selectedItemProperty().addListener(this::selectionObjectChanged);
    }

    private void selectionPlayerChanged (ObservableValue <? extends String> observable, String oldValue, String newValue) {
        ObservableList<String> selectedItems = playerListView.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {
            playerImage.setImage(new Image(getClass().getResourceAsStream("/melchior/clement/picture/player/id/ok/"+selectedItems.get(0)+".jpg")));
        }
    }

    private void selectionObjectChanged (ObservableValue <? extends String> observable, String oldValue, String newValue) {
        ObservableList<String> selectedItems = playerListView.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {
            playerImage.setImage(new Image(getClass().getResourceAsStream("/melchior/clement/picture/object/"+selectedItems.get(0)+".png")));
        }
    }

}