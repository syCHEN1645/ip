package Rook.Task;

public class Task {
    protected String description;
    protected final String FINISH = "[X]";
    protected final String N_FINISH = "[ ]";
    protected final String INITIAL = "[ ]";
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return INITIAL + " " + getDescription();
    }
}
