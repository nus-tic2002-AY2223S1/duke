import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Bot bot = new Bot("lamBDA", "--------------------------");
        bot.welcome();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.equalsIgnoreCase("bye") == false) {
            if(line.equalsIgnoreCase("list")) {
                bot.showList();
            } else {
                bot.show(line);
            }
            line = in.nextLine();
        }
        bot.goodbye();

    }
}
