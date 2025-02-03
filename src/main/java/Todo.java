// This is equivalent to Todo class
public class Activity {
    protected String description;
    protected boolean isDone;

    public Activity(String description) {
        setDescription(description);
        setDone(false);
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }
}
