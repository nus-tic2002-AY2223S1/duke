import java.io.*;

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

    public static BufferedReader load() throws IOException {
        String file ="data/tasks.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        reader.close();

        return reader;
    }
}
