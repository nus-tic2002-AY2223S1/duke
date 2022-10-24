import java.util.ArrayList;

public class TaskList {
    //Consists of operations involving the modification of the task list
    //stores the list of tasks
    //Performs add/remove task operations for all task types.
    //Shows to do list
    Helper h = new Helper();
    private static ArrayList<Task> TaskList;

    //constructor
    public TaskList() {
        TaskList = new ArrayList<>();
    }

    public void addNewTask(String incomingTaskName, String incomingType, String incomingDate){
        switch (incomingType){

            case ("event"):
                TaskList.add(new Event(incomingTaskName, incomingDate));
                break;

            case ("deadline"):
                TaskList.add(new Deadline(incomingTaskName, incomingDate));
                break;
        }

        h.separator();
        System.out.println("added: " + TaskList.size() + ". " + incomingTaskName + "\nYou have " + TaskList.size()+ " tasks in the list!");
        h.separator();
    }

    public void addNewTask(String incomingTaskName, String incomingType){

        TaskList.add(new Todo(incomingTaskName));

        h.separator();
        System.out.println("added: " + TaskList.size() + ". " + incomingTaskName + "\nYou have " + TaskList.size()+ " tasks in the list!");
        h.separator();
    }

    public void showTodoList(){
        h.separator();
        for (int i = 0; i< TaskList.size(); i++){

            System.out.println(TaskList.get(i).toString());

        }
        System.out.println("There are " + TaskList.size() + " tasks in the list!");
        h.separator();
    }

    public void changeToMarkAsDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            h.separator();
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            showTodoList();
            h.separator();
        }
        else{
            if (TaskList.get(i).isMarkAsDone() == " "){
                TaskList.get(i).setMarkAsDone();
                h.separator();
                System.out.println("Nice! I've marked this task as done: ");
            }
            else {
                h.separator();
                System.out.println("This task has already been marked as done!");
            }
            System.out.println(i+". "+TaskList.get(i).toString());
            h.separator();
            h.newline();
        }

    }

    public void changeToMarkNotDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            showTodoList();
        }
        else{
            if (TaskList.get(i).isMarkAsDone() == "X"){
                TaskList.get(i).setMarkNotDone();
                h.separator();
                System.out.println("Meow, I've marked this task as not done yet: ");
            }
            else {
                h.separator();
                System.out.println("This task has already been marked as not done!");
            }
            System.out.println(i+". "+TaskList.get(i).toString());
            h.separator();
            h.newline();
        }

    }

    public static boolean taskListIsEmpty(){
        return TaskList.isEmpty();
    }


}
