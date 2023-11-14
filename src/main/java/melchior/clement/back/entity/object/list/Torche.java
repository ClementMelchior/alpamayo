package melchior.clement.back.entity.object.list;

import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.MontainObjectCategoryEnum;

public class Torche extends MontainObject {

    public Torche () {
        super (15, "Lampe torche", 8, 0, 0, 0, MontainObjectCategoryEnum.UTILITY, 1);
    }
    
}
