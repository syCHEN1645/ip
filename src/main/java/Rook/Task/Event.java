package Rook.Task;

public class Event extends Todo {
    protected String startTime;
    protected String endTime;
    protected final String INITIAL = "[E]";

    public Event(String description, String startTime, String endTime) {
        super(description);
        setEndTime(endTime);
        setStartTime(startTime);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (isDone) {
            return INITIAL + FINISH + " " + getDescription()
                    + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
        }
        return INITIAL + N_FINISH + " " + getDescription()
                + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }
}
