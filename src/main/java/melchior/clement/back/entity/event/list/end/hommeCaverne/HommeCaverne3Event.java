package melchior.clement.back.entity.event.list.end.hommeCaverne;

import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventTypeEnum;
import melchior.clement.back.entity.event.EventYesNo;

public class HommeCaverne3Event extends EventYesNo {

    public HommeCaverne3Event () {
        super("", EventCategoryEnum.NONE, EventTypeEnum.CHOICE, 
            "", "hmmhmm.mp3",
            0);
    }

    @Override
    public void passEvent(boolean yesno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'passEvent'");
    }
    
}
