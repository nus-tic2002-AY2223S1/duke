public class PrintUtil {
    public static String breaker = "\t____________________________________________________________\n";

    public static void printWithIndentation(String format, String content) {
        System.out.printf(format, breaker, content, breaker);

    }

}
