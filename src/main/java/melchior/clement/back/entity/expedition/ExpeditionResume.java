package melchior.clement.back.entity.expedition;

public class ExpeditionResume {
    private String name;
    private String resume; 


    public ExpeditionResume(String name, String resume) {
        this.name = name;
        this.resume = resume;
    }
    
    // getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
}
