package melchior.clement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import melchior.clement.back.entity.day.Day;
import melchior.clement.back.entity.event.Event;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.event.list.malus.TempeteEvent;
import melchior.clement.back.entity.expedition.ExpeditionResumeSuccess;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Carte;
import melchior.clement.back.entity.object.list.Couteau;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.Gaz;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.manager.GameManager;
import melchior.clement.front.FrontManager;
import melchior.clement.front.popup.IPopup;
import melchior.clement.front.popup.event.EventPopup;
import melchior.clement.front.popup.event.EventResumePopup;
import melchior.clement.front.popup.expedition.ExpeditionResumePopup;
import melchior.clement.front.popup.feed.FeedPlayerPopup;
import melchior.clement.front.popup.player.PlayerResumePopup;

public class GameController {

    private Day day;
    private ArrayList <Player> players;
    private ArrayList <MontainObject> inventory;
    private int currentWindow;
    private ArrayList <IPopup> windows;
    private Event event;
    private EventPopup eventPopup;

    @FXML
    private Button dayButton;

    @FXML
    private Pane backgroundGame;

    @FXML
    private Text dayText;

    @FXML
    private ImageView backgroundPlayer1;

    @FXML
    private ImageView backgroundPlayer2;

    @FXML
    private ImageView backgroundPlayer3;

    @FXML
    private ImageView backgroundPlayer4;

    @FXML
    private Button startDayButton;

    @FXML
    void startButton(ActionEvent e) throws IOException {
        System.out.println("Staring the day...");
        this.startDayButton.setDisable(true);
        this.currentWindow = 0;
        if (this.windows != null && this.windows.size() > 0) {
            IPopup popup = this.windows.get(this.currentWindow);
            popup.disablePreviousAction();
            show(popup);
        }

    }

    public void update(Day pDay, ArrayList <Player> pPlayers, ArrayList <MontainObject> pInventory) throws IOException, InterruptedException {
        this.dayButton.setDisable(false);
        this.initialize(pDay, pPlayers, pInventory);
    }

    public void initialize(Day pDay, ArrayList <Player> pPlayers, ArrayList <MontainObject> pInventory) throws IOException, InterruptedException {
        this.day = pDay;
        this.players = pPlayers;
        this.inventory = pInventory;

        this.windows = new ArrayList<IPopup>();
        this.currentWindow = 0;
        this.startDayButton.setVisible(false);

        setBlackBackground();
        parseDay();
    }

    private void parseDay() throws IOException {
        initializeEventResume();
        initializeExpeditionResume();
        initializePlayerResume();
        initializeFeedPlayer();
        initializeEvent(new TempeteEvent());
    }

    @FXML
    public void startDay() throws InterruptedException {
        this.dayButton.setVisible(false);
        this.startDayButton.setDisable(false);
        this.startDayButton.setVisible(true);
        setBackgroundPicture();
    }

    private void setBlackBackground() throws InterruptedException {
        this.dayButton.setVisible(false);
        this.startDayButton.setVisible(false);
        this.backgroundPlayer1.setImage(new Image(new File("src/main/resources/melchior/clement/picture/player/black/player1.png").toURI().toString()));
        this.backgroundPlayer2.setImage(new Image(new File("src/main/resources/melchior/clement/picture/player/black/player2.png").toURI().toString()));
        this.backgroundPlayer3.setImage(new Image(new File("src/main/resources/melchior/clement/picture/player/black/player3.png").toURI().toString()));
        this.backgroundPlayer4.setImage(new Image(new File("src/main/resources/melchior/clement/picture/player/black/player4.png").toURI().toString()));
        this.dayButton.setText("Jour " + GameManager.getInstance().getCurrentDay().getDayNumber());
        this.dayButton.setVisible(true);
    }

    private void setBackgroundPicture() {
        for (Player p : players) {
            String str = "alive";
            switch (p.getState()) {
                case ALIVE: 
                    str = "alive";
                    break;
                case CRY: 
                    str = "cry";
                    break;
                case SICK: 
                    str = "sick";
                    break;
                case ICED: 
                    str = "iced";
                    break;
                case GONE: 
                    str = "gone";
                    break;
                case DEAD: 
                    str = "dead";
                    break;
            }
            determineBackgroundImageForPlayer(p, str); 
        }
    }

    private void determineBackgroundImageForPlayer (Player p, String state) {
        String filename = "src/main/resources/melchior/clement/picture/player/" + state;
        String file = "";
        switch (p.getName()) {
                case "Dominique": 
                    filename = filename + "/player1.png";
                    file = new File(filename).toURI().toString();
                    backgroundPlayer1.setImage(new Image(file));
                    break;
                case "Paul": 
                    filename = filename + "/player2.png";
                    file = new File(filename).toURI().toString();
                    backgroundPlayer2.setImage(new Image(file));
                    break;
                case "Eliot": 
                    filename = filename + "/player3.png";
                    file = new File(filename).toURI().toString();
                    backgroundPlayer3.setImage(new Image(file));
                    break;
                case "Gaspard": 
                    filename = filename + "/player4.png";
                    file = new File(filename).toURI().toString();
                    backgroundPlayer4.setImage(new Image(file));
                    break;
        }
    }

    public void initializeEventResume() throws IOException {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        ArrayList <MontainObject> objectsWon = new ArrayList<>();
        objectsLost.add(new Viande());
        objectsWon.add(new Carte());
        objectsWon.add(new Gaz());
        String resume = "Nous avons bondi pour sauver ce que nous pouvions, malheureusement nous avons perdu un viande dans l'effondrement de la grotte.";     
        eventResume = new EventResume("Effondrement", resume, objectsLost, objectsWon, null);
        this.day.setEventResume(eventResume);
        
        if (this.day.getEventResume() != null) {
            EventResumePopup eventResumePopup = new EventResumePopup(this.day.getEventResume());
            eventResumePopup.init();
            this.windows.add(eventResumePopup);
            initWindow(eventResumePopup.getPopup());
        }
    }

    public void initializeExpeditionResume() throws IOException {
        ArrayList <MontainObject> objects = new ArrayList<>();
        objects.add(new Viande());
        objects.add(new Gaz());
        objects.add(new Couteau());
        ExpeditionResumeSuccess expSuccess = new ExpeditionResumeSuccess("Hourra, enfin une bonne nouvelle, Paul est de retour ! Il semble frais comme un gardon, ou du moins juste frais.", objects, new Fusil());
        this.day.setExpeditionResume(expSuccess);

        if (this.day.getExpeditionResume() != null) {
            ExpeditionResumePopup expeditionResumePopup = new ExpeditionResumePopup(this.day.getExpeditionResume());
            expeditionResumePopup.init();
            this.windows.add(expeditionResumePopup);
            initWindow(expeditionResumePopup.getPopup());        
        }
    }

    public void initializePlayerResume() throws IOException {
        PlayerResumePopup playerResumePopup = new PlayerResumePopup(this.players);
        playerResumePopup.init();
        this.windows.add(playerResumePopup);
        initWindow(playerResumePopup.getPopup());
    }

    public void initializeFeedPlayer() throws IOException {
        FeedPlayerPopup feedPopup = new FeedPlayerPopup(this.players, this.inventory);
        feedPopup.init();
        this.windows.add(feedPopup);
        initWindow(feedPopup.getPopup());
    }

    public void initializeEvent(Event event) throws IOException {
        if (event != null) {
            this.eventPopup = new EventPopup(event);
            this.eventPopup.init();
        }
    }

    public void initWindow(Pane panePopup) throws IOException {
        panePopup.setLayoutX(475.0);
        panePopup.setLayoutY(100.0);
        panePopup.setVisible(false);
        this.backgroundGame.getChildren().add(panePopup);    
    }

    public void show(IPopup popup) throws IOException {
        popup.getPopup().setVisible(true);
    }

    public void hide(IPopup popup) throws IOException {
        popup.getPopup().setVisible(false);
    }

    public void displayNextWindow () throws IOException {
        if (this.currentWindow+1 < this.windows.size()) {
            this.currentWindow = this.currentWindow + 1;
            if (this.currentWindow == this.windows.size() - 1) {
                this.windows.get(currentWindow).disableNextAction();
            }
            hide(this.windows.get(this.currentWindow - 1));
            show(this.windows.get(this.currentWindow));
        }
    }

    public void displayPreviousWindow () throws IOException {
        if (this.currentWindow > 0) {
            this.currentWindow = this.currentWindow - 1;
            if (this.currentWindow == 0) {
                this.windows.get(currentWindow).disablePreviousAction();
            }
            hide(this.windows.get(this.currentWindow + 1));
            show(this.windows.get(this.currentWindow));
        }
    }

    public void okActionFeedPlayer() throws IOException, InterruptedException {
        System.out.println("Resume of the day ending");
        hide(this.windows.get(currentWindow));
        // feedPlayer();
        if (this.event != null) {
            System.out.println("Passing event");
            initWindow(this.eventPopup.getPopup());            
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), event -> {
                this.eventPopup.getPopup().setVisible(true);
                this.eventPopup.getEvent().playSound();
            }));
            timeline.play();
        } else {
            System.out.println("No event, skip to new day");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), event -> {
                try {
                    FrontManager.getInstance().endDay();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }));
            timeline.play();
        }
    }

    public void okActionEvent() throws IOException, InterruptedException {
        this.eventPopup.getPopup().setVisible(false);
               
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), event -> {
            //FrontManager.getInstance().nextDay();
        }));
        timeline.play();
    }
}