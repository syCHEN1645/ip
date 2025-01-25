import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rook {
    static String botName = "Rook";
    static String logo = """
                \t___ ___ ___
                \t| |_| |_| |
                \t \\       /
                \t  |     |
                \t  |     |
                \t  |_____|
                \t /-------\\
                \t|_________|""";
    static String partition = "------------------------------------------";
    static String exitCommand = "bye";
    static String listCommand = "list";
    static String unmarkCommand = "unmark";
    static String markCommand = "mark";
    static List<Activity> activities = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(partition);
        chatGreeting();
        while (true) {
            String message = scanner.nextLine();

            if (message.equals(exitCommand)) {
                chatBye();
                break;
            } else if (message.equals(listCommand)) {
                chatShowList();
            } else if (isUnmarkCommand(message)) {
                int index = convertStringToInt(message.split(" ")[1]);
                chatUnmarkDone(index);
            } else if (isMarkCommand(message)) {
                int index = convertStringToInt(message.split(" ")[1]);
                chatMarkDone(index);
            } else {
                chatAddList(message);
            }
        }
    }

    private static boolean isMarkCommand(String message) {
        String[] words = message.split(" ");
        return words.length == 2 && words[0].equals(markCommand)
                && convertStringToInt(words[1]) > 0
                && convertStringToInt(words[1]) <= activities.size();
    }

    private static boolean isUnmarkCommand(String message) {
        String[] words = message.split(" ");
        return words.length == 2 && words[0].equals(unmarkCommand)
                && convertStringToInt(words[1]) > 0
                && convertStringToInt(words[1]) <= activities.size();
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

    private static void chatMarkDone(int index) {
        activities.get(index - 1).setDone(true);
        System.out.println(partition);
        System.out.println(activities.get(index - 1));
        System.out.println("My lord, Activity " + index + " has been marked under your command!");
        System.out.println(partition);
    }

    private static void chatUnmarkDone(int index) {
        activities.get(index - 1).setDone(false);
        System.out.println(partition);
        System.out.println(activities.get(index - 1));
        System.out.println("My lord, Activity " + index + " has been unmarked under your command!");
        System.out.println(partition);
    }

    private static void chatAddList(String message) {
        System.out.println(partition);
        activities.add(new Activity(message));
        System.out.println(message + " is added.");
        System.out.println(partition);
    }

    private static void chatShowList() {
        System.out.println(partition);
        if (activities.isEmpty()) {
            System.out.println("You have no activities, my Lord.");
        } else {
            System.out.println("Here are your activities, my Lord.");
            for (int i = 0; i < activities.size(); i++) {
                System.out.println(i + 1 + ". " + activities.get(i));
            }
        }
        System.out.println(partition);
    }

    private static void chatGreeting() {
        System.out.println("Greetings, my Lord, " + botName + " at your service!");
        System.out.println(logo);
        System.out.println("How may I assist you?");
        System.out.println(partition);
    }

//    private static void chatEcho() {
//        while (true) {
//            String message = scanner.nextLine();
//            if (message.equals(exitCommand)) {
//                System.out.println(partition);
//                System.out.println("Farewell, my Lord!");
//                System.out.println(partition);
//                break;
//            } else {
//                System.out.println(partition);
//                System.out.println(message);
//                System.out.println(partition);
//            }
//        }
//    }

//    private static void chatAddList() {
//        while (true) {
//            String message = scanner.nextLine();
//            if (message.equals(exitCommand)) {
//                System.out.println(partition);
//                System.out.println("Farewell, my Lord!");
//                System.out.println(partition);
//                break;
//            } else if (message.equals(listCommand)) {
//                System.out.println(partition);
//                if (activities.isEmpty()) {
//                    System.out.println("You have no activities, my Lord.");
//                } else {
//                    System.out.println("Here are your activities, my Lord.");
//                    for (int i = 0; i < activities.size(); i++) {
//                        System.out.println(i + 1 + ". " + activities.get(i));
//                    }
//                }
//                System.out.println(partition);
//            } else {
//                System.out.println(partition);
//                activities.add(new Activity(message));
//                System.out.println(message + " is added.");
//                System.out.println(partition);
//            }
//        }
//    }

    private static void chatBye() {
        System.out.println(partition);
        System.out.println("Farewell, my Lord!");
        System.out.println(partition);
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
