package nusduke.tasklist;

import java.util.ArrayList;
import nusduke.tasklist.Task;
import nusduke.ui.Ui;

public class TaskList {
    /** Container for tasks.  */
    static private ArrayList<Task> list;
    /** Keep track of number of tasks in the list.  */
    static private int listCount;
    public TaskList(){
        list = new ArrayList<Task>();
        listCount = 0;
    }

    public TaskList(TaskList load) {
        this.list = load.list;
        this.listCount = load.listCount;
    }

    public static int getListCount(){
        return listCount;
    }

    public ArrayList getList(){
        return list;
    }

    static public void loadTasksFromFiles(String[] task){
        String[] descriptionTime;
        switch (task[0]){
            case "T":
                list.add(new Todo(task[2]));
                listCount ++;
                break;
            case"D":
                descriptionTime = splitDescriptionTime(task[2]);
                list.add(new Deadline(descriptionTime[0], descriptionTime[1]));
                listCount ++;
                break;
            case "E":
                descriptionTime = splitDescriptionTime(task[2]);
                list.add(new Event(descriptionTime[0], descriptionTime[1]));
                listCount ++;
                break;
        }
        if (task[1].equals("X")){
            list.get(listCount - 1).markAsDone();
        }
        Ui.echoTasksLoadingFromFile(list    .get(listCount - 1));
    }

    public static String[] splitDescriptionTime(String description){
        String[] split = description.split(" \\(", 2);
        split[1].replace("\\)","");
        return split;
    }

    public void changeListCount (String sign){
        switch (sign){
            case "+":
                this.listCount ++;
                break;
            case "-":
                this.listCount --;
                break;
            default:
                break;
        }
    }

}