package Data;
import Common.TaskType;
import CustomException.UnsupportedTaskType;
import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File storage to store it in a file by implementing DataInterface
 */
public class DataFileFactory implements DataInterface {
    private String folder;
    private String path;
    private File file;

    public DataFileFactory(String path, String folder) {
        this.folder = folder;
        this.path = folder +'/'+ path;
        setupFile();
    }

    private void setupFile() {
        try {
            //check if folder exists
            File dir = new File(folder);
            if(dir.exists() == false) {
                dir.mkdir();
            }
            file = new File(path);
            if(file.exists() == false) {
                file.createNewFile();
            }
        } catch (IOException e) {

        }

    }
    @Override
    public ArrayList<TaskInterface> loadData() throws FileNotFoundException, UnsupportedTaskType {
        ArrayList<TaskInterface> arr = new ArrayList<>();
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            TaskInterface task = convertStringToTask(s.nextLine());
            arr.add(task);
        }
        return arr;
    }

    @Override
    public void update(TaskInterface task) throws IOException, UnsupportedTaskType, IndexOutOfBoundsException {
        replaceByTask(convertTaskToString(task), task.getID());
    }

    @Override
    public void delete(TaskInterface task) throws IOException, IndexOutOfBoundsException {
        String t = convertTaskToString(task);
        List<String> lines = Files.readAllLines(Path.of(file.getAbsolutePath()), StandardCharsets.UTF_8);
        int index = lines.indexOf(t);
        lines.remove(index);
        Files.write(Path.of(file.getAbsolutePath()), lines, StandardCharsets.UTF_8);
    }

    @Override
    public void add(TaskInterface task) throws IOException {
        String text = convertTaskToString(task);
        text += "\n";
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    private void replaceByTask(String newString, long id) throws IOException, UnsupportedTaskType {
        List<String> lines = Files.readAllLines(Path.of(file.getAbsolutePath()), StandardCharsets.UTF_8);
        int i = 0;
        while(i < lines.size()) {
            TaskInterface current = convertStringToTask(lines.get(i));
            if(current.getID() == id) {
                break;
            }
            i++;
        }
        lines.set(i, newString);
        Files.write(Path.of(file.getAbsolutePath()), lines, StandardCharsets.UTF_8);
    }

    private String convertTaskToString(TaskInterface task) {
        String text = task.getID() + "|";
        if(task instanceof Deadline) {
            text += TaskType.DEADLINE.getKey()+"|";
        } else if(task instanceof Todo) {
            text += TaskType.TODO.getKey() + "|";
        } else {
            text += TaskType.EVENT.getKey() +"|";
        }
        text += (task.isDone() ? "1" : 0) + "|";
        text += task.getWork() + "|";
        text += task.getEndDate();
        return text;
    }

    private TaskInterface convertStringToTask(String text) throws UnsupportedTaskType, IndexOutOfBoundsException {
        String[] texts = text.split("\\|");
        if(texts.length == 0) {
            return null;
        }
        TaskType type = TaskType.getTypeByKey(texts[1]);
        switch (type) {
            case TODO:
                return new Todo(Integer.parseInt(texts[2]) == 1, texts[3], Long.parseLong(texts[0]));
            case DEADLINE:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                return new Deadline(Integer.parseInt(texts[2]) == 1, texts[3], Long.parseLong(texts[0]), LocalDateTime.parse(texts[4], formatter));
            case EVENT:
                return new Event(Integer.parseInt(texts[2]) == 1, texts[3], Long.parseLong(texts[0]), texts[4]);
            default:
                throw new UnsupportedTaskType();
        }
    }
}
