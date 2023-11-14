package melchior.clement;

import melchior.clement.back.entity.expedition.ExpeditionResume;

public interface IExpeditionResumeController {

    void initialize (ExpeditionResume expeditionResume);

    void disablePreviousAction();

    void disableNextAction();
    
}
