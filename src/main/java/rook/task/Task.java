package rook.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected final String FINISH = "[X]";
    protected final String N_FINISH = "[ ]";
    protected static final String INITIAL = "[ ]";
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

    public static String getINITIAL() {
        return INITIAL;
    }

    @Override
    public String toString() {
        return INITIAL + " " + getDescription();
    }


    public String convertTimeToString(String timeString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeString);
            return dateTimeToPrintString(dateTime);
        } catch (DateTimeParseException e) {
            // if time is given in non-standard form, just return original string
            return timeString;
        }
    }

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
