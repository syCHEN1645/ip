package rook.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Todo {
    protected String byTime;
    protected static final String INITIAL = "[D]";

    public Deadline(String description, String byTime) {
        super(description);
        setByTime(byTime);
        try {
            LocalDateTime.parse(byTime);
        } catch (DateTimeParseException e) {
            System.out.println("* Emm, my Lord, non-standard time is given, but I will keep it for now.");
        }
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
            return INITIAL + FINISH + " " + getDescription() + " (by: " + convertTimeToString(byTime) + ")";
        }
        return INITIAL + N_FINISH + " " + getDescription() + " (by: " + convertTimeToString(byTime) + ")";
    }
}