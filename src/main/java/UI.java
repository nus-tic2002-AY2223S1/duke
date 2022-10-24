
import java.io.File;
import java.util.Scanner;


/**
 * greeting
 * since first time run Duke no task,so will print "there is no tasks at this moment"
 * next time when start Duke, will automatically read file and print those outstanding tasks only
 */
public class UI {
    public static void Greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("Here is your outstanding tasks:");
        File file = new File("C:\\data\\duke.txt");

       try{
           Scanner s = new Scanner(file);
           while (s.hasNextLine()) {
               String line = s.nextLine();
               if(line.charAt(2)=='0'){
                   System.out.println(line);
               }
           }
       } catch(Exception e){
           System.out.println("There is no outstanding tasks at this moment");
       }
        System.out.println("What can I do for you?");
    }
}
