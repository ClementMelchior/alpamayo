package melchior.clement.back.entity.object.list;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.MontainObjectCategoryEnum;

public class Gaz extends MontainObject {

    public Gaz () {
        super (7, "Gaz", 0, 0, 4, 1, MontainObjectCategoryEnum.HEAT, 1000);
    }
    
}
