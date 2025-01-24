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

    public static void main(String[] args) {
        System.out.println(partition);
        ChatGreeting();
        ChatEcho();
    }

    static void ChatGreeting() {
        System.out.println("Greetings, my Lord, I am " + botName + "!");
        System.out.println(logo);
        System.out.println("How may I serve you?");
        System.out.println(partition);
    }

    static void ChatEcho() {
        String exitMessage = "bye";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals(exitMessage)) {
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
