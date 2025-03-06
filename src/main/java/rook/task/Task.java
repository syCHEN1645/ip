package rook.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Serves as a parent class of more sophisticated child classes.
 * A <code>Task</code> object contains only generic features, e.g. <code>description, toString</code>.
 */
public class Task {
    protected String description;
    protected final String FINISH = "[X]";
    protected final String N_FINISH = "[ ]";
    protected static final String INITIAL = "[ ]";
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> object with <code>isDone</code> set to false.
     *
     * @param description Text description of this task.
     */
    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    /**
     * @return Text description of this object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Text description of this task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return <code>True</code> if this task is finished.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @param done If this task is finished.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * @return Initial identifier of this task, "[ ]".
     */
    public static String getINITIAL() {
        return INITIAL;
    }

    /**
     * @return String format of this task.
     */
    @Override
    public String toString() {
        return INITIAL + " " + getDescription();
    }

    /**
     * Converts a <code>String</code> into a standard string format of time.
     *
     * @param timeString A time in <code>String</code> form.
     * @return The time in formatted string form. If it cannot be converted, return the original input string.
     */
    public String convertTimeToString(String timeString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeString);
            return dateTimeToPrintString(dateTime);
        } catch (DateTimeParseException e) {
            // if time is given in non-standard form, just return original string
            return timeString;
        }
    }

    /**
     * Converts a <code>LocalDateTime</code> into a standard string format of time.
     *
     * @param dateTime A time in <code>LocalDateTime</code> form.
     * @return The time in formatted string form. If it cannot be converted, return <code>null</code>.
     */
    public String dateTimeToPrintString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        if (date == null) {
            return null;
        }

        String res = date.getYear() + " " +
                date.getMonth() + " " +
                date.getDayOfMonth();
        if (time != null) {
            res += ", " + time;
        }
        return res;
    }
}
