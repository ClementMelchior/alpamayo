package melchior.clement.front.popup.expedition;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import melchior.clement.IExpeditionResumeController;
import melchior.clement.back.entity.expedition.ExpeditionResume;
import melchior.clement.back.entity.expedition.ExpeditionResumeSuccess;
import melchior.clement.front.popup.IPopup;

import java.io.IOException;

public class ExpeditionResumePopup implements IPopup {
    private Pane root;
    private IExpeditionResumeController controller;
    private ExpeditionResume expeditionResume;


    public ExpeditionResumePopup(ExpeditionResume expeditionResume) throws IOException {
        FXMLLoader loader;
        if (expeditionResume instanceof ExpeditionResumeSuccess) {
            loader = new FXMLLoader(ExpeditionResumePopup.class.getResource("expeditionResumeSuccessWindow.fxml"));
        } else {
            loader = new FXMLLoader(ExpeditionResumePopup.class.getResource("expeditionResumeFailWindow.fxml"));
        }
        this.root = loader.load();
        this.controller = loader.getController();
        this.expeditionResume = expeditionResume;
    }

    @Override
    public void init () {
        this.controller.initialize(expeditionResume);
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