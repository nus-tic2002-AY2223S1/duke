package storage;

import exceptions.DukeException;
import task.TaskList;
import task.Task;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Storage {
    private static  File file;
    /**
     * Storage constructor to store file
     */
    public Storage(String filePath){
        this.file= new File(filePath);
    }
    /**
     * Access file
     */
    public File load() {
        return file;
    }
    /**
     * Save task to text file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw=new FileWriter(file);
        String textToAppend="";
        for(Task task:tasks){
            textToAppend+=task.toOutput()+"\n";


        }

        fw.write(textToAppend);
        fw.close();

    }



}
