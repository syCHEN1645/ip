package rook.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A type of <code>Task</code> that contains <code>isDone, description, byTime</code>.
 * It is a task that need to be done before a time.
 */
public class Deadline extends Todo {
    protected String byTime;
    protected static final String INITIAL = "[D]";

    /**
     * Constructs a <code>Deadline</code> task with <code>isDone</code> set to false.

     * Standard time format is yyyy-mm-ddThh:mm.
     *
     * @param description Text description of this <code>Deadline</code> object.
     * @param byTime Deadline time of this task.
     */
    public Deadline(String description, String byTime, boolean isReminder) {
        super(description);
        setByTime(byTime);
        try {
            LocalDateTime.parse(byTime);
        } catch (DateTimeParseException e) {
            if (isReminder) {
                System.out.println("* Emm, my Lord, non-standard time is given, but I will keep it for now.");
            }
        }
    }

    /**
     * @return Deadline of this object.
     */
    public String getByTime() {
        return byTime;
    }

    public void setByTime(String byTime) {
        this.byTime = byTime;
    }

    /**
     * @return Initial identifier of this task, "[D]".
     */
    public static String getINITIAL() {
        return INITIAL;
    }

    /**
     * @return String format of this task, e.g. [D][ ] Homework (by: 2024 Feb 2, 23:59).
     */
    @Override
    public String toString() {
        if (isDone) {
            return INITIAL + FINISH + " " + getDescription() + " (by: " + convertTimeToString(byTime) + ")";
        }
        return INITIAL + N_FINISH + " " + getDescription() + " (by: " + convertTimeToString(byTime) + ")";
    }
}