package melchior.clement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Carte;
import melchior.clement.back.entity.object.list.Couteau;
import melchior.clement.back.entity.object.list.Gaz;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.back.manager.GameManager;
import melchior.clement.front.FrontManager;

/**
 * JavaFX App
 */
public class App extends Application {
/*
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Event event = new Yeti1Event();
        //scene = new Scene(loadFXML("game"));
        //stage.setScene(scene);
        //stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    */
    public void start(final Stage primaryStage) throws IOException, InterruptedException {
/*
        ExpeditionProposalEvent exp = new ExpeditionProposalEvent();
        ArrayList <Player> players = new ArrayList<>();
        players.add(new Player(0, "Paul"));
        players.add(new Player(1, "Dominique"));
        players.add(new Player(2, "Gaspard"));
        players.add(new Player(3, "Eliot"));
        exp.setPossiblePlayers(players);

        Button button = new Button();
        button.setText("Open a New Window");
        
        EventPopup eventPopup = new EventPopup(exp);
        eventPopup.init();
        Stage newWindow = eventPopup.getPopup(primaryStage);
*/

        /*
        FeedPlayerPopup
        FeedPlayerPopup feedPopup = new FeedPlayerPopup(players, new ArrayList<MontainObject>());
        feedPopup.init();
        Stage newWindow = feedPopup.getPopup(primaryStage);
        */

        ArrayList <MontainObject> objects = new ArrayList<>();
        objects.add(new Viande());
        objects.add(new Viande());
        objects.add(new Gaz());
        objects.add(new Carte());
        objects.add(new Couteau());

        /*
        FeedPlayerPopup
        ExpeditionResumeSuccess expSuccess = new ExpeditionResumeSuccess("Hourra, enfin une bonne nouvelle, Paul est de retour ! Il semble frais comme un gardon, ou du moins juste frais.", objects, new Fusil());
        ExpeditionResumePopup expeditionResumePopup = new ExpeditionResumePopup(expSuccess);
        expeditionResumePopup.init();
        Stage newWindow = expeditionResumePopup.getPopup(primaryStage);
        */

        /*
        EventChoiceObject event = new TempeteEvent();
        EventPopup eventPopup = new EventPopup(event);
        eventPopup.init();
        Stage newWindow = eventPopup.getPopup(primaryStage);
        */


        /*
        EventYesNo event = new EnfantEvent();
        EventPopup eventPopup = new EventPopup(event);
        eventPopup.init();
        Stage newWindow = eventPopup.getPopup(primaryStage);
        */
        
        String file = new File("src/main/resources/melchior/clement/sound/AmbianceSong.mp3").toURI().toString();
        Media sound = new Media(file);
        MediaPlayer mp = new MediaPlayer(sound);
        mp.play();

        GameManager.getInstance().startGame();
        FrontManager frontManager = FrontManager.getInstance();
        
        Scene root = new Scene(frontManager.getPopup());
        frontManager.init();
        primaryStage.setTitle("Alpamyo");
        primaryStage.setScene(root);
        primaryStage.show();


        /*
        button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            newWindow.show();
        }
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 450, 250);
        //Scene scene = new Scene(loadFXML("game"));

        primaryStage.setTitle("Alpamyo");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        launch(args);
    }
}