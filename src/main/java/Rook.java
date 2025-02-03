import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Rook {
    static final String BOT_NAME = "Rook";
    static final String LOGO = """
                \t___ ___ ___
                \t| |_| |_| |
                \t \\       /
                \t  |     |
                \t  |     |
                \t  |_____|
                \t /-------\\
                \t|_________|""";
    static final String PARTITION = "------------------------------------------";


    static List<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(PARTITION);
        chatGreeting();

        while (true) {
            String message = scanner.nextLine();
            if (message.equals(Command.EXIT_COMMAND.getCmd())) {
                chatBye();
                break;
            }
            executeCommand(message);
        }
    }

    private static Command identifyCommand(String word) {
        for (Command cmd: Command.values()) {
            if (word.equals(cmd.getCmd())) {
                return cmd;
            }
        }
        return null;
    }

    private static void executeCommand(String message) {
        Command cmd = identifyCommand(message.split(" ")[0]);
        if (cmd == null) {
            chatBadCommand();
            return;
        }
        switch (cmd) {
        case LIST_COMMAND:
            chatShowList(message);
            break;
        case UNMARK_COMMAND:
            chatUnmarkDone(message);
            break;
        case MARK_COMMAND:
            chatMarkDone(message);
            break;
        case ADD_TODO_COMMAND:
            chatAddTodo(message);
            break;
        case ADD_DEADLINE_COMMAND:
            chatAddDeadline(message);
            break;
        case ADD_EVENT_COMMAND:
            chatAddEvent(message);
            break;
        default:
            chatBadCommand();
        }
    }

    private static void chatBadCommand() {
        System.out.println("I beg you pardon, my Lord?");
    }

    private static int convertStringToInt(String str) {
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

    private static boolean isValidMarkCommand(String message) {
        // given this is a mark/unmark command
        String[] words = message.split(" ");
        return words.length == 2 && convertStringToInt(words[1]) > 0
                && convertStringToInt(words[1]) <= tasks.size();
    }

    private static void chatMarkDone(String message) {
        if (!isValidMarkCommand(message)) {
            chatBadCommand();
            return;
        }
        int index = convertStringToInt(message.split(" ")[1]);
        tasks.get(index - 1).setDone(true);
        System.out.println(PARTITION);
        System.out.println(tasks.get(index - 1));
        System.out.println("My lord, Activity " + index + " has been marked under your command!");
        System.out.println(PARTITION);
    }

    private static void chatUnmarkDone(String message) {
        if (!isValidMarkCommand(message)) {
            chatBadCommand();
            return;
        }
        int index = convertStringToInt(message.split(" ")[1]);
        tasks.get(index - 1).setDone(false);
        System.out.println(PARTITION);
        System.out.println(tasks.get(index - 1));
        System.out.println("My lord, Task " + index + " has been unmarked under your command!");
        System.out.println(PARTITION);
    }

    private static boolean isValidTodoCommand(String message) {
        // given this is a todo command
        // format: todo description
        return message.split(" ").length > 1;
    }

    private static void chatAddTodo(String message) {
        if (!isValidTodoCommand(message)) {
            chatBadCommand();
            return;
        }
        System.out.println(PARTITION);

        String description = message.replaceFirst(Command.ADD_TODO_COMMAND.getCmd(), "").strip();
        Todo task = new Todo(description);
        tasks.add(task);

        System.out.println(task + " is added.");
        System.out.println(PARTITION);
    }

    private static boolean isValidDeadlineCommand(String message) {
        // given this is a deadline command
        // format: deadline description /by time
        String[] words = message.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by") && i >= 2) {
                // byTime is now found, and there are at least 2 words before (cmd + description)
                return true;
            }
        }
        return false;
    }

    private static void chatAddDeadline(String message) {
        if (!isValidDeadlineCommand(message)) {
            chatBadCommand();
            return;
        }
        System.out.println(PARTITION);

        String[] parts = message.replaceFirst(Command.ADD_DEADLINE_COMMAND.getCmd(), "").split("/by");
        String description = parts[0].strip();
        String byTime = "";
        if (parts.length > 1) {
            byTime = parts[1].strip();
        }
        Deadline task = new Deadline(description, byTime);
        tasks.add(task);

        System.out.println(task + " is added.");
        System.out.println(PARTITION);
    }

    private static boolean isValidEventCommand(String message) {
        // given this is an event command
        // format: event description /from startTime /to endTime
        String[] words = message.split(" ");

        boolean flagFrom = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/from") && i >= 2) {
                // fromTime is now found, and there are at least 2 words before (cmd + description)
                flagFrom = true;
            } else if (flagFrom && words[i].equals("/to")) {
                // fromTime exists, and toTime is now found
                return true;
            }
        }
        return false;
    }

    private static void chatAddEvent(String message) {
        if (!isValidEventCommand(message)) {
            chatBadCommand();
            return;
        }
        System.out.println(PARTITION);

        String[] parts = message.replaceFirst(Command.ADD_DEADLINE_COMMAND.getCmd(), "").split("/from");
        String description = parts[0].strip();

        String[] times = parts[1].replaceFirst("/from", "").split("/to");
        String fromTime = times[0];
        String toTime = "";
        if (times.length > 1) {
            toTime = times[1].strip();
        }

        Event task = new Event(description, fromTime, toTime);
        tasks.add(task);

        System.out.println(task + " is added.");
        System.out.println(PARTITION);
    }

    private static boolean isValidListCommand(String message) {
        return message.equals(Command.LIST_COMMAND.getCmd());
    }

    private static void chatShowList(String message) {
        if (!isValidListCommand(message)) {
            chatBadCommand();
            return;
        }
        System.out.println(PARTITION);
        if (tasks.isEmpty()) {
            System.out.println("You have no task, my Lord.");
        } else {
            System.out.println("Here are your tasks, my Lord.");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        }
        System.out.println(PARTITION);
    }

    private static void chatGreeting() {
        System.out.println("Greetings, my Lord, " + BOT_NAME + " at your service!");
        System.out.println(LOGO);
        System.out.println("How may I assist you?");
        System.out.println(PARTITION);
    }

    private static void chatBye() {
        System.out.println(PARTITION);
        System.out.println("Farewell, my Lord!");
        System.out.println(PARTITION);
    }

}

/*
___ ___ ___
| |_| |_| |
 \       /
  |     |
  |     |
  |_____|
 /-------\
|_________|

 */
