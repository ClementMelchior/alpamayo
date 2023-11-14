package melchior.clement.front.popup;

import javafx.scene.layout.Pane;

public interface IPopup {

    void init();

    Pane getPopup();

    void disablePreviousAction();

    void disableNextAction();
    
}
