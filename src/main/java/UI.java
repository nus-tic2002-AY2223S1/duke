
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {

    public static void Greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("Here is your outstanding tasks");
        File file = new File("C:\\data\\duke.txt");
//        String text =" ";
//        try {
//            text = Files.readString(filename);
//            //System.out.println("read file");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String[] textArray = text.split(" \n");

       try{
           Scanner s = new Scanner(file);
           while (s.hasNextLine()) {
               String line = s.nextLine();
               if(line.charAt(2)=='0'){
                   System.out.println(line);
               }
           }
       } catch(Exception e){
           System.out.println("read outstanding file error");
       }

        System.out.println("What can I do for you?");
    }
}
