package catbot;

import catbot.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    static private File f;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        String directoryName = "data/";
        File directory = new File(directoryName);

        if(! directory.exists()){
            directory.mkdir();
        }

        f = new File(filePath);

        try{
            if(!f.exists()){
                f.createNewFile();
            }else{
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Task> load() throws FileCorruptedException, IOException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] arr = currentLine.split(" \\| ");

            for(String column : arr){
                if (column.equals("null")) {
                    throw new FileCorruptedException("tasks.txt is corrupted");
                }
            }

            boolean isDone;
            if(arr[1].equals("1")){
                isDone = true;
            } else {
                isDone = false;
            }
            if(arr[0].equals("T")){
                listOfTasks.add(new ToDo(isDone, arr[2]));
            } else if (arr[0].equals("D")) {
                listOfTasks.add(new Deadline(isDone, arr[2], LocalDateTime.parse(arr[3])));
            } else if (arr[0].equals("E")) {
                listOfTasks.add(new Event(isDone, arr[2], LocalDateTime.parse(arr[3]), LocalDateTime.parse(arr[4])));
            }
        }
        return listOfTasks;
    }

    public void save(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int size = tl.getSize();
        for(int i = 0; i < size; i++){
            Task t = tl.getTask(i);
            int isDone;
            if(t.getIsDone()){
                isDone = 1;
            } else {
                isDone = 0;
            }

            if(t.getTaskType() == TaskType.TODO){
                ToDo td = (ToDo) t;
                fw.write("T | " + isDone + " | " + td.getTaskName());
            } else if (t.getTaskType() == TaskType.DEADLINE) {
                Deadline d = (Deadline)t;
                assert d.getBy() != null;

                fw.write("D | " + isDone + " | " + d.getTaskName() + " | " + d.getBy());
            } else if (t.getTaskType() == TaskType.EVENT) {
                Event e = (Event)t;
                assert e.getFromDateTime() != null;
                assert e.getToDateTime() != null;

                fw.write("E | " + isDone + " | " + e.getTaskName() + " | " + e.getFromDateTime() + " | " + e.getToDateTime());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
