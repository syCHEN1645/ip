package rook.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A type of <code>Task</code> that contains <code>isDone, description, startTime, endTime</code>.
 * It is a task that starts at a time and ends at a later time.
 */
public class Event extends Todo {
    protected String startTime;
    protected String endTime;
    protected static final String INITIAL = "[E]";

    /**
     * Constructs a <code>Todo</code> object with <code>isDone</code> set to false.
     * Standard time format is yyyy-mm-ddThh:mm.
     *
     * @param description Text description of this <code>Todo</code> object.
     * @param startTime Start-time of this object.
     * @param endTime End-time of this object.
     */
    public Event(String description, String startTime, String endTime, boolean isReminder) {
        super(description);
        setEndTime(endTime);
        setStartTime(startTime);
        try {
            LocalDateTime.parse(startTime);
            LocalDateTime.parse(endTime);
        } catch (DateTimeParseException e) {
            if (isReminder) {
                System.out.println("* Emm, my Lord, non-standard time is given, but I will keep it for now.");
            }
        }
    }

    /**
     * @return Start-time of this task in original <code>String</code> form.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime Set the start-time of this task.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return End-time of this task in original <code>String</code> form.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime Set the end-time of this task.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return Initial identifier of this task, "[E]".
     */
    public static String getINITIAL() {
        return INITIAL;
    }

    /**
     * @return String format of this task, e.g. [E][ ] Homework (from: 2024 Feb 2, 23:59 to: to be confirmed).
     */
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
