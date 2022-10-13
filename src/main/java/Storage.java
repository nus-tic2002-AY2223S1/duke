import java.io.File;
import java.io.IOException;

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

    public static File load() {
        boolean result;
        try {
            result = f.createNewFile();
            if(result){
                System.out.println("file created " + f.getCanonicalPath());
            }
            else {
                System.out.println("File already exist at location: "+f.getCanonicalPath());
            }
        } catch (IOException e) { //print exception if any
            e.printStackTrace();
        }

        return f;
    }
}
