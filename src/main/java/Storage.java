import java.io.*;
import java.util.Scanner;

public class Storage {
    private static File f;
    //constructor
    public Storage(String filePath){
        // create a data folder to hold the file
        File f1 = new File("data");
        boolean bool = f1.mkdir();
        if(bool){
            System.out.println("Folder is created successfully");
        }

        //create the task file
        f = new File(filePath);
    }

    public static String load() throws IOException {
        Scanner scan = new Scanner(f);

        String fileContent = "";
        while(scan.hasNextLine()){ //addTask to myTaskList for every line
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
        return fileContent;
    }
}
