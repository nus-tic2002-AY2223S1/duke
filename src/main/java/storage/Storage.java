package storage;

import tasklist.*;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.File;


public class Storage {
    public static String filePath;
    public static List<String> lines;
    private static File file;
    private static Scanner in;
    public Storage(String filePath){
        this.filePath = filePath;
        file = new File(filePath);
    }

    public static Tasklist load () throws IOException {
        lines = Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
        Tasklist tasks = new Tasklist();
        boolean printed = false;

        for (String line: lines){
            String[] arr = line.split("\\Q|\\E");

            if(!printed){
                System.out.println(Ui.REPLY_LOADED_TASK);
                printed = true;
            }

            try {
                switch (arr[0].toUpperCase().trim()) {
                    case "T":
                        Tasklist.addTodo(tasks, arr[2].trim());
                        break;
                    case "E":
                        Tasklist.addEvent(tasks, arr[2].trim(), arr[3].trim());
                        break;
                    case "D":
                        Tasklist.addDeadline(tasks, arr[2].trim(), arr[3].trim());
                        break;
                    default:
                        throw new IOException();
                }
            } catch (IOException e) {
                System.out.println(Ui.UI_DIVIDER + "\n" + Ui.ERROR_LOAD);
                break;
            }

            if (arr[1].trim().equals("1")){
                Tasklist.markDone(tasks, Tasklist.getListcount()-1);
            }

            Tasklist.printLoaded(Tasklist.getListcount()-1);
        }

        Tasklist.setListCount(Tasklist.getListcount());
        System.out.println(Ui.UI_DIVIDER);
        return tasks;
    }

    public static void saveFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int listCount = Tasklist.getListcount();
        ArrayList<Task> tasklist = Tasklist.getTasklist();

        for(int i = 0; i < listCount; i++){
            fw.write(tasklist.get(i).getTask() + "\n");
        }

        fw.close();
    }

}