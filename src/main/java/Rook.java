import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Rook.Command.Command;
import Rook.Exception.InvalidInfoException;
import Rook.Exception.MissingInfoException;
import Rook.Exception.MissingKeywordException;
import Rook.Exception.RookException;
import Rook.Task.Deadline;
import Rook.Task.Event;
import Rook.Task.Task;
import Rook.Task.Todo;
import Rook.FileManager;

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


    static List<Task> tasks;
    static Scanner scanner = new Scanner(System.in);
    static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        loadSavedData();

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

    private static void loadSavedData() {
        tasks = fileManager.readTask();
//        if (tasks == null) {
//            tasks = new ArrayList<>();
//        }
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
        try {
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
            case DELETE_COMMAND:
                chatDeleteTask(message);
                break;
            default:
                chatBadCommand();
            }
        } catch (RookException e) {
            e.printErrorMessage();
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

    private static void chatMarkDone(String message) throws RookException {
        // Given there is at least a 1st word "mark"
        String[] words = message.split(" ");

        if (1 > words.length - 1) {
            throw new MissingInfoException();
        } else if (1 < words.length - 1) {
            throw new InvalidInfoException();
        }

        int index = convertStringToInt(words[1]);
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidInfoException();
        }

        tasks.get(index - 1).setDone(true);
        System.out.println(PARTITION);
        System.out.println(tasks.get(index - 1));
        System.out.println("My lord, Activity " + index + " has been marked under your command!");
        System.out.println(PARTITION);
    }

    private static void chatUnmarkDone(String message) throws RookException {
        // Given there is at least a 1st word "unmark"
        String[] words = message.split(" ");

        if (words.length < 2) {
            throw new MissingInfoException();
        } else if (words.length > 2) {
            throw new InvalidInfoException();
        }

        int index = convertStringToInt(words[1]);
        if (index <= 0) {
            throw new InvalidInfoException();
        }

        tasks.get(index - 1).setDone(false);
        System.out.println(PARTITION);
        System.out.println(tasks.get(index - 1));
        System.out.println("My lord, Rook.Task.Task " + index + " has been unmarked under your command!");
        System.out.println(PARTITION);
    }

    private static void chatAddTodo(String message) throws RookException {
        // Given there is at least a 1st word "todo"
        String[] words = message.split(" ");
        if (words.length < 2) {
            throw new MissingInfoException();
        }

        System.out.println(PARTITION);

        String description = message.replaceFirst(Command.ADD_TODO_COMMAND.getCmd(), "").strip();
        Todo task = new Todo(description);
        tasks.add(task);
        try {
            fileManager.writeTask(task);
        } catch (IOException e) {
            System.out.println("Failed to add.");
        }

        System.out.println(task + " is added.");
        System.out.println(PARTITION);
    }

    private static Deadline convertMessageToDeadline(String[] words, int indexByTime) {
        StringBuilder description = new StringBuilder();
        StringBuilder byTime = new StringBuilder();
        for (int i = 1; i < indexByTime; i++) {
            description.append(words[i]);
            description.append(" ");
        }
        for (int i = indexByTime + 1; i < words.length; i++) {
            byTime.append(words[i]);
            byTime.append(" ");
        }
        return new Deadline(description.toString().strip(), byTime.toString().strip());
    }

    private static void chatAddDeadline(String message) throws RookException {
        // Given there is at least a 1st word "deadline"
        String[] words = message.split(" ");
        int indexByTime = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                indexByTime = i;
                break;
            }
        }
        // Case no description
        if (indexByTime == 1) {
            throw new MissingInfoException();
        }
        // Case no "/by"
        if (indexByTime == -1) {
            throw new MissingKeywordException();
        }
        // Case no byTime
        if (indexByTime >= words.length - 1) {
            throw new MissingInfoException();
        }

        Deadline task = convertMessageToDeadline(words, indexByTime);
        tasks.add(task);
        try {
            fileManager.writeTask(task);
        } catch (IOException e) {
            System.out.println("Failed to add.");
        }
        System.out.println(PARTITION);
        System.out.println(task + " is added.");
        System.out.println(PARTITION);
    }

    private static Event convertMessageToEvent(String[] words, int indexFromTime, int indexToTime) {
        StringBuilder description = new StringBuilder();
        StringBuilder fromTime = new StringBuilder();
        StringBuilder toTime = new StringBuilder();
        for (int i = 1; i < indexFromTime; i++) {
            description.append(words[i]);
            description.append(" ");
        }
        for (int i = indexFromTime + 1; i < indexToTime; i++) {
            fromTime.append(words[i]);
            fromTime.append(" ");
        }
        for (int i = indexToTime + 1; i < words.length; i++) {
            toTime.append(words[i]);
            toTime.append(" ");
        }
        return new Event(
                description.toString().strip(),
                fromTime.toString().strip(),
                toTime.toString().strip()
        );
    }

    private static void chatAddEvent(String message) throws RookException {
        String[] words = message.split(" ");
        int indexFromTime = -1;
        int indexToTime = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/from")) {
                indexFromTime = i;
                break;
            }
        }
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/to")) {
                indexToTime = i;
                break;
            }
        }
        // Case no description
        if (indexFromTime == 1) {
            throw new MissingInfoException();
        }
        // Case no "/from" or "/to"
        if (indexFromTime == -1 || indexToTime == -1) {
            throw new MissingKeywordException();
        }
        // Case "/from" is after "/to"
        if (indexFromTime > indexToTime) {
            throw new InvalidInfoException();
        }
        // Case no fromTime
        if (indexFromTime + 1 == indexToTime) {
            throw new MissingInfoException();
        }
        // Case no toTime
        if (indexToTime == words.length - 1) {
            throw new MissingInfoException();
        }

        Event task = convertMessageToEvent(words, indexFromTime, indexToTime);
        tasks.add(task);
        try {
            fileManager.writeTask(task);
        } catch (IOException e) {
            System.out.println("Failed to add.");
        }
        System.out.println(PARTITION);
        System.out.println(task + " is added.");
        System.out.println(PARTITION);
    }

    private static void chatDeleteTask(String message) throws RookException {
        // Given there is at least a 1st word "delete"
        String[] words = message.split(" ");

        if (1 > words.length - 1) {
            throw new MissingInfoException();
        } else if (1 < words.length - 1) {
            throw new InvalidInfoException();
        }

        int index = convertStringToInt(words[1]);
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidInfoException();
        }

        System.out.println(PARTITION);
        System.out.println(tasks.get(index - 1));
        System.out.println("My lord, Activity " + index + " has ceased to exist!");
        System.out.println(PARTITION);
        fileManager.deleteTask(index);
        tasks.remove(index - 1);
    }

    private static void chatShowList(String message) throws RookException {
        if (!message.equals(Command.LIST_COMMAND.getCmd())) {
            throw new InvalidInfoException();
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
