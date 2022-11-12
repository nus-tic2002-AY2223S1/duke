package formatting;

import engine.Parser;
import storage.Storage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Helper {
    //helper class for formatting

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern ("d/MM/yyyy HHmm");
    public static String DUKEFILEPATH = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "dukeFile";

    public static Path DUKEDIRECTORY() {
        Storage s = Storage.getInstance();
        String fileNameTxt = s.getFileName().concat(".txt");
        Path directory = Paths.get(System.getProperty("user.home"), "Desktop", "dukeFile",fileNameTxt);

        return directory;
    }


    public static void newline(){
        System.out.println("\n");
    }
    public static void separator() {
        System.out.println("____________________________________");
    }

    public static String userInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
