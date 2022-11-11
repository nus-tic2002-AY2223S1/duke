package entities;

import exception.DukeException;
import utils.DataUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private static List<Task> myTaskList = new ArrayList<>();
    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    public TaskList(File f) throws FileNotFoundException, DukeException {
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String input = s.nextLine();
            if(input.isEmpty()){
                continue;
            }
            String[] args = input.split("\\|");
            createTask(args);
        }
    }
    public static List<Task> getMyTaskList(){
        return myTaskList;
    }

    public static Task getTask(int index){
        Task t = myTaskList.get(index);
        return t;
    }
    private void createTask(String[] args){
        String type = args[0].trim();
        boolean done = DataUtils.convertToboolean(args[1].trim());
        String description = args[2].trim();

        switch (type){
            case"T":
               Todo todo = new Todo(description);
               todo.updateMark(done);
               myTaskList.add(todo);
               break;

            case"D":
                String by = args[3].trim();
                Deadline deadline = new Deadline(description, by);
                deadline.updateMark(done);
                myTaskList.add(deadline);
                break;

            case"E":
                String at = args[3].trim();
                Event event = new Event(description, at);
                event.updateMark(done);
                myTaskList.add(event);
                break;
        }
    }
    public void printTaskList(){
        for (Task task : myTaskList) {
            System.out.println(task.toCommandString());
        }
    }

}
