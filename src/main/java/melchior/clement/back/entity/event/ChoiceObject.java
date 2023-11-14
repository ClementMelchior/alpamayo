package melchior.clement.back.entity.event;

import melchior.clement.back.entity.object.MontainObject;

public class ChoiceObject {

    private int id;
    private boolean possible;
    private String resume;
    private MontainObject object;

    public ChoiceObject(int id, boolean possible, String resume, MontainObject object) {
        this.id = id;
        this.possible = possible;
        this.resume = resume;
        this.object = object;
    }

    // getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public MontainObject getObject() {
        return object;
    }
    public void setObject(MontainObject object) {
        this.object = object;
    }
    public boolean isPossible() {
        return possible;
    }
    public void setPossible(boolean possible) {
        this.possible = possible;
    }
}
