package storage;

import tasklist.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    //    private static TaskList taskList;
    public static String path = homeDirectory() + "/duke/data/duke.txt";

    public static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }
    public static File load() throws FileNotFoundException {
        return file;
    }
    public static String homeDirectory() {
        String homeDirectory = System.getProperty("user.home");
        return homeDirectory;
    }

    public static void fileExist() {
        if (!file.exists()) {
            File f = new File(path);
            if (!f.exists())
                f.getParentFile().mkdirs();
        }
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            fileExist();
            assert path != null : "File path cannot be null";
            FileOutputStream writer = new FileOutputStream(path);

            for (Task task : taskList) {
                writer.write((task.toOutput() + '\n').getBytes());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//    private static void writeLineToFile(String filePath, Task task) throws IOException {
//        // write to file
//
//        try {
//            FileWriter fw = new FileWriter(filePath);
//            fw.write(task.toString() + System.lineSeparator());
//            fw.close();
//        }
//        catch (IOException | NullPointerException e) {
//            System.out.println("1. Something went wrong: " + e.getMessage());
//        }

//    }
//    public static void main(String[] args) throws InvalidStorageFilePathException, IOException {
//        // create file to file path assuming all users are storing the duke in the user home directory
//        assert homeDirectory() != null;
//        File file = new File(homeDirectory(), "/duke/data/duke.txt");
//        File home = new File(homeDirectory());
//        taskList = new TaskList();
//
//        // if file path does not exist, throw exception
//        if (!home.exists()) {
//            throw new InvalidStorageFilePathException();
//        }
//        else {
//            try {
//
//                for (int i = 0; i < taskList.size(); i++){
//                // write to file
//                    writeLineToFile(String.valueOf(file),taskList.getTaskList().get(i));
//                    UI.printMessage(String.valueOf(taskList.getTaskList().get(i)));}
////                    writeToFile(String.valueOf(file), taskList);
//            } catch (IOException | NullPointerException e) {
//                System.out.println("Something went wrong: " + e.getMessage());
//            }
//        }
//    }
//
//    public static void mainCaller() throws InvalidStorageFilePathException, IOException {
//        Storage.main(null);
//    }


