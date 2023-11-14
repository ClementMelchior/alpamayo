package melchior.clement.back.entity.day;

public class LastDay extends Day {

    private boolean success;
    private String resume;

    public LastDay (LastDayReasonEnum reason, String resume) {
        super();
        switch (reason) {
            case TIME:
                this.success = false;
                this.resume = resume;
                break;
            case DEAD:
                this.success = false;
                this.resume = resume;
                break;
            case YETI:
                this.success = false;
                this.resume = resume;
                break;
            case SAFETY:
                this.success = true;
                this.resume = resume;
                break;
            default:
                break;
        }
    }


    public String getResume() {
        return resume;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
