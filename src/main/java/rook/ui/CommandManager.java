package rook.ui;

import rook.task.Event;

/**
 * Manages methods used to identify and convert information from a String message.
 * Contains static methods that can be called and used without an object instance.
 */
public class CommandManager {
    private static final int NEXT = 1;

    /**
     * Identifies any Command that matches the input String word.
     *
     * @param word A single word without space.
     * @return The <code>Command</code> that matches this word. If no match, return <code>null</code>.
     */
    public static Command identifyCommand(String word) {
        for (Command cmd: Command.values()) {
            if (word.equals(cmd.getCmd())) {
                return cmd;
            }
        }
        return null;
    }

    /**
     * Converts a <code>String</code> message to an <code>Event</code> object.
     *
     * @param words An array of String containing information about an <code>Event</code>.
     * @param indexFromTime The index of start-time in the array.
     * @param indexToTime The index of end-time in the array.
     * @return An <code>Event</code> object using the information in the String array.
     */
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
                toTime.toString().strip(),
                true
        );
    }

    /**
     * Converts a <code>String</code> word to an <code>int</code> by its literal content.
     *
     * @param str A String word with no white space.
     * @return A non-negative <code>int</code> corresponds to the input literal. If input literal
     * is not a non-negative integer, return <code>-1</code>;
     */
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
