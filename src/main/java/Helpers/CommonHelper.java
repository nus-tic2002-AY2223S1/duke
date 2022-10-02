package Helpers;

import java.util.Arrays;

public class CommonHelper {
    public static String[] formatPassedName(String n){
        String[] formatted = new String[2];
        Integer idx = n.indexOf("/");
        formatted[0] = n.substring(0, idx-1).trim();
        String x = n.substring(idx+1,idx+3);
        if(x.equals("at") || x.equals("by")){
            formatted[1] = n.substring(idx+3).trim();
        } else {
            formatted[1] = n.substring(idx+1).trim();
        }
        return formatted;
    }

    public static boolean isOpening(String text){
        String[] opening = {"Hello","Hi"};
        return Arrays.stream(opening).anyMatch(text::equalsIgnoreCase);
    }

    public static boolean isClosing(String text){
        String[] closing = {"Bye","See ya"};
        return Arrays.stream(closing).anyMatch(text::equalsIgnoreCase);
    }

    public static boolean isGet(String text){
        String[] closing = {"list","show"};
        return Arrays.stream(closing).anyMatch(text::equalsIgnoreCase);
    }

    public static boolean isMark(String text){
        String[] closing = {"mark","done"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    public static boolean isUnmark(String text){
        String[] closing = {"unmark","not done"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    public static boolean isTodo(String text){
        String[] closing = {"todo"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    public static boolean isDeadline(String text){
        String[] closing = {"deadline"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    public static boolean isEvent(String text){
        String[] closing = {"event"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
