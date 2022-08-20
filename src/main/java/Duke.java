import java.util.Scanner;


public class Duke {

    public static void seperator() {
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        Bot bot = new Bot("lamBDA", "--------------------------");
        bot.welcome();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.equals("Bye") == false) {
            bot.show(line);
            line = in.nextLine();
        }
        bot.goodbye();

    }
}
