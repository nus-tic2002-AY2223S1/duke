import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Storage {

    public Storage(String filePath) {
        File f = new File(filePath);
    }

    static void save(String filePath, List<Task> list) throws IOException {
        // write to file
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            try {       
                fw.write(list.get(i).getDescription() + System.lineSeparator());
                
            }
            catch (IOException | NullPointerException e) {
                System.out.println("1. Something went wrong: " + e.getMessage());
            }
        }
        fw.close();
    }
}