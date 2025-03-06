package rook.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Todo {
    protected String startTime;
    protected LocalDateTime startDateTime;
    protected String endTime;
    protected LocalDateTime endDateTime;
    protected static final String INITIAL = "[E]";

    public Event(String description, String startTime, String endTime) {
        super(description);
        setEndTime(endTime);
        setStartTime(startTime);
        try {
            startDateTime = LocalDateTime.parse(startTime);
        } catch (DateTimeParseException e) {
            startDateTime = null;
        }
        try {
            endDateTime = LocalDateTime.parse(endTime);
        } catch (DateTimeParseException e) {
            endDateTime = null;
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
            res += " " + time;
        }
        return res;
    }

    public String startTimePrintString() {
        if (dateTimeToPrintString(startDateTime) == null) {
            return startTime;
        }
        return dateTimeToPrintString(startDateTime);
    }

    public String endTimePrintString() {
        if (dateTimeToPrintString(endDateTime) == null) {
            return endTime;
        }
        return dateTimeToPrintString(endDateTime);
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
                    + " (from: " + startTimePrintString() +
                    " to: " + endTimePrintString() + ")";
        }
        return INITIAL + N_FINISH + " " + getDescription()
                + " (from: " + startTimePrintString() +
                " to: " + endTimePrintString() + ")";
    }
}
