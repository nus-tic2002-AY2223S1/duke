package util;

import entity.Task;

public class PrintUtil {
    public static String breaker = "\t____________________________________________________________\n";

    public static void printWithIndentation(String content) {
        System.out.printf("%s\t %s\n%s", breaker, content, breaker);

    }
    
    public static void printErrorMessage(Throwable e) {
        printWithIndentation(e.getMessage());
    }
    
}


