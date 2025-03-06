package rook.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Todo {
    protected String startTime;
    protected String endTime;
    protected static final String INITIAL = "[E]";

    public Event(String description, String startTime, String endTime) {
        super(description);
        setEndTime(endTime);
        setStartTime(startTime);
        try {
            LocalDateTime.parse(startTime);
            LocalDateTime.parse(endTime);
        } catch (DateTimeParseException e) {
            System.out.println("* Emm, my Lord, non-standard time is given, but I will keep it for now.");
        }
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

    public static String getINITIAL() {
        return INITIAL;
    }

    @Override
    public String toString() {
        if (isDone) {
            return INITIAL + FINISH + " " + getDescription()
                    + " (from: " + convertTimeToString(startTime) +
                    " to: " + convertTimeToString(endTime) + ")";
        }
        return INITIAL + N_FINISH + " " + getDescription()
                + " (from: " + convertTimeToString(startTime) +
                " to: " + convertTimeToString(endTime) + ")";
    }
}
