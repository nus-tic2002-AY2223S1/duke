package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import exceptions.DukeException;

public class TaskList {
    public ArrayList<Task> myTaskList = new ArrayList<>();

    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    // When we are loading a file
    public TaskList(File f) throws FileNotFoundException, DukeException {
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) { //add task to the list as long there is next line
            loadTasks(s.nextLine());
        }
    }

    /**
     * Method to read and process each line in task.txt to add them as task into myTaskList
     *
     * @param input each line in task.txt
     */
    public void loadTasks(String input) {
        String[] inputList = input.split(" \\| ");
        switch (inputList[0]) {
            // Todo
            case "T":
                String toDoTask = inputList[2];
                myTaskList.add(new ToDo(toDoTask));
                if (Integer.parseInt(inputList[1]) == 1) {
                    myTaskList.get(myTaskList.size() - 1).markDone();
                } else {
                    myTaskList.get(myTaskList.size() - 1).unMarkDone();
                }
                break;
            case "E":
                String EventTask = inputList[2];
                String ByDate = inputList[3];
                myTaskList.add(new Event(EventTask, ByDate));
                if (Integer.parseInt(inputList[1]) == 1) {
                    myTaskList.get(myTaskList.size() - 1).markDone();
                } else {
                    myTaskList.get(myTaskList.size() - 1).unMarkDone();
                }
                break;

            case "D":
                String DeadLineTask = inputList[2];
                String AtDate = inputList[3];
                myTaskList.add(new Deadline(DeadLineTask, AtDate));
                if (Integer.parseInt(inputList[1]) == 1) {
                    myTaskList.get(myTaskList.size() - 1).markDone();
                } else {
                    myTaskList.get(myTaskList.size() - 1).unMarkDone();
                }
                break;
        }
    }

    /**
     * Method to add task into myTaskList
     *
     * @param taskToAdd Task to add into the arraylist
     */
    public void addTask(Task taskToAdd) {
        myTaskList.add(taskToAdd);
    }
}