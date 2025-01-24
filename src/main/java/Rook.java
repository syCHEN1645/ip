import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    static List<String> activities = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(partition);
        ChatGreeting();
        // ChatEcho();
        ChatAddList();
    }

    static void ChatGreeting() {
        System.out.println("Greetings, my Lord, I am " + botName + "!");
        System.out.println(logo);
        System.out.println("How may I serve you?");
        System.out.println(partition);
    }

    static void ChatEcho() {
        String exitCommand = "bye";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals(exitCommand)) {
                System.out.println(partition);
                System.out.println("Farewell, my Lord!");
                System.out.println(partition);
                break;
            } else {
                System.out.println(partition);
                System.out.println(command);
                System.out.println(partition);
            }
        }
    }

    static void ChatAddList() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            if (message.equals(exitCommand)) {
                System.out.println(partition);
                System.out.println("Farewell, my Lord!");
                System.out.println(partition);
                break;
            } else if (message.equals(listCommand)) {
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
            } else {
                System.out.println(partition);
                activities.add(message);
                System.out.println(message + " is added.");
                System.out.println(partition);
            }
        }
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
