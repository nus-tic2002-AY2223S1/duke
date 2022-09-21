import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Bot bot = new Bot("lamBDA", "--------------------------");
        bot.welcome();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(Keyword.getKeyword(line) != Keyword.Bye) {
            Keyword key = Keyword.getKeyword(line);
            try {
                if(key == Keyword.List) {
                    bot.showList();
                } else if (key == Keyword.Mark) {
                    String part = line.split(" ")[1];
                    bot.mark(part);
                } else if (key == Keyword.Unmark) {
                    String part = line.split(" ")[1];
                    bot.unmark(part);
                } else {
                    bot.show(line);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(key.toString() + " on invalid task");
            } catch (NullPointerException e) {
                System.out.println(key.toString() + " on invalid task");
            } catch (NumberFormatException e) {
                System.out.println("invalid value, expect a number for " + key.toString());
            }
            line = in.nextLine();

        }
        bot.goodbye();
    }

    public enum Keyword {
        Bye, List, Mark, Unmark, None;

        public static Keyword getKeyword(String key) {
            if(key.equalsIgnoreCase("bye")) {
                return Bye;
            } else if (key.equalsIgnoreCase("list")) {
                return List;
            } else if (key.toLowerCase().startsWith("mark")) {
                return Mark;
            } else if (key.toLowerCase().startsWith("unmark")) {
                return Unmark;
            } else {
                return None;
            }
        }
    }
}