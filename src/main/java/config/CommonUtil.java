package config;

public class CommonUtil {

    public static String underScoreLine = "____________________________________________________________";
    public static String dashLine = "------------------------------------------------------------";
    public static String singleTab = "\t";
    public static final String byeConstant = "bye";
    public static final String addedConstant = "added: ";
    public static final String listConstant = "list";
    public static final String markConstant = "mark";
    public static final String unmarkConstant = "unmark";
    public static String byeGreeting = "Bye. Hope to see you again soon!";
    public static String markedMessage = "Nice! I've marked this task as done:";
    public static String unmarkedMessage = "OK, I've marked this task as not done yet:";

    public static void showLine(String object) {
        System.out.println(singleTab + object);
    }


}
