package formatting;

import java.time.format.DateTimeFormatter;
public class Helper {
    //helper class for formatting

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("d/MM/yyyy HHmm");

    public static void newline(){
        System.out.println("\n");
    }
    public static void separator() {
        System.out.println("____________________________________");
    }



}
