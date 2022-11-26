package storage;

import exceptions.DukeException;
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
    public Storage(String filePath){
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Load existing tasklist if prompted by user
     * Reads all lines from existing text file and retrieve the details required for creation of each task
     *
     * @throws IOException If format is not recognised
     * @throws NoSuchElementException If file is empty
     */

    public static Tasklist load () throws IOException {
        lines = Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
        Tasklist tasks = new Tasklist();
        boolean printed = false;

        try {
            if(lines.isEmpty()) {
                throw new NoSuchElementException();
            }

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
        } catch (NoSuchElementException e) {
            System.out.println(Ui.ERROR_LOAD_NOMATCH);
        }

        Tasklist.setListCount(Tasklist.getListcount());
        System.out.println(Ui.UI_DIVIDER);
        assert Tasklist.getListcount() != 0 : "Tasklist count is 0, updated count after file load has not been updated";
        return tasks;
    }

    /**
     * Keeps a copy of all existing task by saving into a text file
     * Retrieve tasks details from tasklist and save the details according to defined format
     *
     * @throws IOException If required details are not available
     */

    public static void saveFile() throws IOException {
        assert !filePath.isBlank() : "filePath cannot be blank to ensure that file is saved";
        FileWriter fw = new FileWriter(filePath);
        int listCount = Tasklist.getListcount();
        ArrayList<Task> tasklist = Tasklist.getTasklist();

        for(int i = 0; i < listCount; i++){
            fw.write(tasklist.get(i).getTask() + "\n");
        }

        fw.close();
    }

}