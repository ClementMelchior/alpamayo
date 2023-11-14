package melchior.clement.back.entity.expedition;

import java.util.ArrayList;

import melchior.clement.back.entity.object.MontainObject;

public class ExpeditionResumeSuccess extends ExpeditionResume {
    private MontainObject objectLost;
    private ArrayList <MontainObject> objectsWon;

    public ExpeditionResumeSuccess(String resume, ArrayList<MontainObject> objectsWon, MontainObject objectLost) {
        super("Fin d'expedition", resume);
        this.objectsWon = objectsWon;
        this.objectLost = objectLost;
    }

        public MontainObject getObjectsLost() {
        return objectLost;
    }
    public void setObjectsLost(MontainObject objectLost) {
        this.objectLost = objectLost;
    }
    public ArrayList<MontainObject> getObjectsWon() {
        return objectsWon;
    }
    public void setObjectsWon(ArrayList<MontainObject> objectsWon) {
        this.objectsWon = objectsWon;
    }
}
