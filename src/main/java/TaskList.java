import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> myTaskList;

    TaskList() {
    }

    // When we are loading a file
    TaskList(File f) throws FileNotFoundException, DukeException {
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) { //add task to the list as long there is next line
            loadTasks(s.nextLine());
        }
    }

    public void loadTasks(String input) {
//        System.out.print(  '\n' + input + '\n');
        String[] inputList = input.split("\\|");
        switch (inputList[0]) {
            // Todo
            case "T":
                String toDoTask = inputList[2];
                myTaskList.add(new ToDo(toDoTask));
                if (inputList[1] == "1") {
                    myTaskList.get(myTaskList.size() - 1).markDone();
                } else {
                    myTaskList.get(myTaskList.size() - 1).unMarkDone();
                }
            case "E":
                String EventTask = inputList[2];
                String ByDate = inputList[3];
                myTaskList.add(new Event(EventTask, ByDate));
                if (inputList[1] == "1") {
                    myTaskList.get(myTaskList.size() - 1).markDone();
                } else {
                    myTaskList.get(myTaskList.size() - 1).unMarkDone();
                }

            case "D":
                String DeadLineTask = inputList[2];
                String AtDate = inputList[3];
                myTaskList.add(new Deadline(DeadLineTask, AtDate));
                if (inputList[1] == "1") {
                    myTaskList.get(myTaskList.size() - 1).markDone();
                } else {
                    myTaskList.get(myTaskList.size() - 1).unMarkDone();
                }
        }
    }
}