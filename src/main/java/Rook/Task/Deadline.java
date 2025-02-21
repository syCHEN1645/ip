package Rook.Task;

public class Deadline extends Todo {
    protected String byTime;
    protected static final String INITIAL = "[D]";

    public Deadline(String description, String byTime) {
        super(description);
        setByTime(byTime);
    }

    public String getByTime() {
        return byTime;
    }

    public void setByTime(String byTime) {
        this.byTime = byTime;
    }

    public static String getINITIAL() {
        return INITIAL;
    }

    @Override
    public String toString() {
        if (isDone) {
            return INITIAL + FINISH + " " + getDescription() + " (by: " + byTime + ")";
        }
        return INITIAL + N_FINISH + " " + getDescription() + " (by: " + byTime + ")";
    }
}