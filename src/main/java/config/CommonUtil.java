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
    public static final String todoConstant = "todo";
    public static final String deadlineConstant = "deadline";
    public static final String eventConstant = "event";
    public static final String byIndicator = "/";
    public static String byeGreeting = "Bye. Hope to see you again soon!";
    public static String markedMessage = "Nice! I've marked this task as done:";
    public static String unmarkedMessage = "OK, I've marked this task as not done yet:";

    public static void showLine(String object) {
        System.out.println(singleTab + object);
    }

    public static int findDelimiter(String[] splitString) {
        int index = -1;
        for (int i = 0; i < splitString.length; i++){
            String word = splitString[i];
            if (word.contains(byIndicator)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String getTask(String[] splitString) {
        String task = "";
        for (String word: splitString) {
            if (!word.contains(byIndicator)) {
                task += word + " ";
            }
            else {
                break;
            }
        }
        return task;
    }

    public static String getBy(String[] splitString) {
        int startInd = findDelimiter(splitString);
        if (startInd == -1){
            startInd = 0;
        }
        String by = "";
        for (int i = startInd; i < splitString.length; i++) {
            by += splitString[i];
        }
        return by;
    }

}
