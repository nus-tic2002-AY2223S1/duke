import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskList {
    public int task_count;
    public Task[] myTaskList;

    //default constructor
    TaskList(){
        task_count = 0;
        myTaskList = new Task[100];
    }
    //constructor reading a existing file
    TaskList(String f) throws FileNotFoundException, DukeException { //string f is a file
        Scanner s = new Scanner(f); //create a Scanner using the File as the source
        while (s.hasNext()){ //add task to the list as long there is next line
            addTasks(s.nextLine());
        }
    }
    public void addTasks(String input) throws DukeException {
        String[] words = input.split(" ");
        String description = "";

        int dateDivider = 0;

        for(int i = 1; i < words.length; i++){
            if(words[i].startsWith("/")){
                dateDivider = i;
                break;
            }
            description = description + words[i] + " ";
        }

        String date = "";

        for(int i = dateDivider + 1; i < words.length; i++){
            date = date + words[i] + " ";
        }

        if (description.equals("")){
            throw new DukeException();
        }

        switch (words[0]) {
            case "deadline":
                myTaskList[task_count] = new Deadline(description, "deadline", date);
                break;
            case "event":
                myTaskList[task_count] = new Event(description, "event", date);
                break;
            case "todo":
                myTaskList[task_count] = new ToDo(description, "todo");
                break;
            default:
                throw new DukeException();
        }
        ++task_count;
    }

    public void removeTasks(int taskID) throws DukeException{
        for(int i = taskID; i < task_count; i++){
            myTaskList[i] = myTaskList[i+1];
        }
        myTaskList[task_count] = null;
        --task_count;
    }


    public void listTasks(){
        for(int i = 0; i < task_count; ++i){
            System.out.println((i+1) + ". " + myTaskList[i].toString());
        }
    }
}
