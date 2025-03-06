package rook.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import rook.exception.RookException;
import rook.ui.Command;
import rook.task.Event;

public class CommandManager {
    private static final int NEXT = 1;

    public static Command identifyCommand(String word) {
        for (Command cmd: Command.values()) {
            if (word.equals(cmd.getCmd())) {
                return cmd;
            }
        }
        return null;
    }


    public static Event convertMessageToEvent(String[] words, int indexFromTime, int indexToTime) {
        StringBuilder description = new StringBuilder();
        StringBuilder fromTime = new StringBuilder();
        StringBuilder toTime = new StringBuilder();
        for (int i = 1; i < indexFromTime; i++) {
            description.append(words[i]);
            description.append(" ");
        }
        for (int i = indexFromTime + NEXT; i < indexToTime; i++) {
            fromTime.append(words[i]);
            fromTime.append(" ");
        }
        for (int i = indexToTime + NEXT; i < words.length; i++) {
            toTime.append(words[i]);
            toTime.append(" ");
        }
        return new Event(
                description.toString().strip(),
                fromTime.toString().strip(),
                toTime.toString().strip()
        );
    }

    public static int convertStringToInt(String str) {
        int result = 0;
        char[] chars = str.toCharArray();
        int[] digits = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            digits[i] = Character.digit(chars[i], 10);
            if (digits[i] < 0) {
                // if this char is not a number, then return -1
                return -1;
            }
            result *= 10;
            result += digits[i];
        }
        return result;
    }
}
