package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import exceptions.DukeException;

public class TaskList {
    public ArrayList<Task> myTaskList = new ArrayList<>();

    /**
     * TaskList empty constructor
     */
    public TaskList() {
        myTaskList = new ArrayList<>();
    }


    /**
     * TaskList constructor
     *
     * @param f File that contains list of tasks
     */
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
            case "T":
                if (inputList.length == 4) {
                    String toDoTask = inputList[2];
                    ToDo toDo = new ToDo(toDoTask);
                    toDo.setPriority(inputList[3]);
                    if (Integer.parseInt(inputList[1]) == 1) {
                        toDo.markDone();
                    } else {
                        toDo.unMarkDone();
                    }
                    myTaskList.add(toDo);
                    break;

                } else {
                    break;
                }

            case "E":
                if (inputList.length == 5 && inputList[1].matches("-?\\d+(\\.\\d+)?")) {
                    String eventTask = inputList[2];
                    String byDate = inputList[3];
                    Event event = new Event(eventTask, byDate);
                    event.setPriority(inputList[4]);
                    if (Integer.parseInt(inputList[1]) == 1) {
                        event.markDone();
                    } else {
                        event.unMarkDone();
                    }
                    myTaskList.add(event);
                    break;
                } else {
                    break;
                }
            case "D":
                if (inputList.length == 5 && inputList[1].matches("-?\\d+(\\.\\d+)?")) {
                    String deadLineTask = inputList[2];
                    String atDate = inputList[3];
                    Deadline deadline = new Deadline(deadLineTask, atDate);
                    deadline.setPriority(inputList[4]);
                    if (Integer.parseInt(inputList[1]) == 1) {
                        deadline.markDone();
                    } else {
                        deadline.unMarkDone();
                    }
                    myTaskList.add(deadline);
                    break;
                } else {
                    break;
                }
            case "DWP":
                if (inputList.length == 6 && inputList[1].matches("-?\\d+(\\.\\d+)?")) {
                    String doWithinPeriodTask = inputList[2];
                    String startPeriodDate = inputList[3];
                    String endPeriodDate = inputList[4];
                    DoWithinPeriod doWithinPeriod = new DoWithinPeriod(doWithinPeriodTask, startPeriodDate, endPeriodDate);
                    doWithinPeriod.setPriority(inputList[5]);
                    if (Integer.parseInt(inputList[1]) == 1) {
                        doWithinPeriod.markDone();
                    } else {
                        doWithinPeriod.unMarkDone();
                    }
                    myTaskList.add(doWithinPeriod);
                    break;
                } else {
                    break;
                }
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