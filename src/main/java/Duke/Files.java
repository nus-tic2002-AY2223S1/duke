package Duke;

import Duke.Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Files {

    public static ArrayList<Task> readDukeFile(File fileDuke){
        ArrayList<Task> list = new ArrayList<>();

        if (!fileDuke.exists()){
            return list;
        }

        //update tasks list from duke.txt

        return list;
    }


    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void checkDataDirectory(){
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()){
            dataDirectory.mkdir();
        }
    }

    public static void writeToFile(File fileDuke, ArrayList<Task> list) throws IOException {
        try {
            FileWriter fw = new FileWriter(fileDuke.getPath());
            for(Task t: list) {
                fw.write(t.getDescription() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed writing to duke file: " + e.getMessage());
        }

    }
}


