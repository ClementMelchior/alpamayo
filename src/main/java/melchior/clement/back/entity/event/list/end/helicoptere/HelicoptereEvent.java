package melchior.clement.back.entity.event.list.end.helicoptere;

import java.util.ArrayList;
import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.object.list.FuseeEclairante;
import melchior.clement.back.entity.object.list.Fusil;

public class HelicoptereEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public HelicoptereEvent () {
        super("Helicoptère", EventCategoryEnum.NONE, 
            "Nous nous sommes précipité dehors dès que nous avons entendu le bourdonnement caractéristique d'un helicoptère. A coup sûr, il s'agit d'un helicoptere de secrous, comment devrions nous essayer de nous faire remarquer ?",
            "helicoptere.mp3", 20);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "Utilisons la fusée de détresse !", new FuseeEclairante()));
        this.choices.add(new ChoiceObject(1, false, "On va tirer au fusil, ils nous entendront forcément !", new Fusil()));
        this.choices.add(new ChoiceObject(1, false, "Agitons les bras au sautant et en criant !", null));

    }

    @Override
    public void passEvent(ChoiceObject choice) {
        throw new UnsupportedOperationException("Unimplemented method 'passEvent'");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<ChoiceObject> getChoices() {
        if (choices != null) {
            return choices;
        } else {
            return new ArrayList<ChoiceObject>();
        }
    }
}
