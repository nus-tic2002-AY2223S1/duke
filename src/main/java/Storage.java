import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// load tasks from file and save tasks in file
public class Storage {
    private static File f;
    private static String filePath;
    private static FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
    }

    public static void checkDir(File f) {
        if(!f.exists()) {
            File dir = new File("data");
            dir.mkdir();
        }
    }

    public static void saveFile(ArrayList<Task> list) throws IOException {
        fw = new FileWriter(filePath);
        String toPrint = "";
        for (int i = 0; i < list.size(); i++) {
            if(i!=0) {
                toPrint = toPrint + "\n";
            }
            toPrint = toPrint + list.get(i).getTask();
        }
        fw.write(toPrint);
        fw.close();
    }

    public static ArrayList<Task> loadFile() throws FileNotFoundException {
        checkDir(f);
        int count = 0;
        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine(); // taking string line by line
            String[] arr = s.split("\\Q|\\E"); // splitting string into arrays (using | as delimiter)
            if(arr[0].equals("T")) {
                list.add(count, new Todo(arr[2]));
                if(arr[1].equals("1")){
                    list.get(count).setStatus(true);
                }
                count++;
            } else if (arr[0].equals("D")) {
                list.add(count, new Deadline(arr[2], arr[3]));
                if(arr[1].equals("1")){
                    list.get(count).setStatus(true);
                }
                count++;
            } else if (arr[0].equals("E")) {
                list.add(count, new Event(arr[2], arr[3]));
                if(arr[1].equals("1")){
                    list.get(count).setStatus(true);
                }
                count++;
            }
        }
        return list;
    }
}
