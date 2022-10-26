import java.util.ArrayList;


public class TaskList {
    //Consists of operations involving the modification of the task list
    //stores the list of tasks
    //Performs add/remove task operations for all task types.
    //Shows to do list

    private static ArrayList<Task> TaskList;
    private static TaskList instance = null;

    //constructor
    private TaskList() {
        TaskList = new ArrayList<>();
    }


    public static TaskList getInstance(){
        if (instance == null){
            instance = new TaskList();
        }
        return instance;
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

        Helper.separator();
        System.out.println("added: " + TaskList.size() + "." + incomingTaskName + "\nYou have " + TaskList.size()+ " tasks in the list!");
        Helper.separator();
    }

    public void addNewTask(String incomingTaskName){

        TaskList.add(new Todo(incomingTaskName));

        Helper.separator();
        System.out.println("added: " + TaskList.size() + "." + incomingTaskName + "\nYou have " + TaskList.size()+ " tasks in the list!");
        Helper.separator();
    }

    public void deleteTask(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            Helper.separator();
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(. Task " + TaskIndex + " is NOT deleted.");
            showTodoList();
            Helper.separator();
        }
        else{
            String toBeDeleted = TaskList.get(i).toString();
            TaskList.remove(i);
            Helper.separator();
            System.out.println("Done! " +TaskIndex+". "+toBeDeleted+" has been deleted. The task list has been updated: ");
            showTodoList();
        }
    }

    public void showTodoList(){
        Helper.separator();
        for (int i = 0; i< TaskList.size(); i++){

            System.out.println(i+1+"."+TaskList.get(i).toString());

        }
        System.out.println("There are " + TaskList.size() + " tasks in the list!");
        Helper.separator();
    }

    public void changeToMarkAsDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            Helper.separator();
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            showTodoList();
            Helper.separator();
        }
        else{
            if (TaskList.get(i).isMarkAsDone().equals(" ")){
                TaskList.get(i).setMarkAsDone();
                Helper.separator();
                System.out.println("Nice! I've marked this task as done: ");
            }
            else {
                Helper.separator();
                System.out.println("This task has already been marked as done!");
            }
            System.out.println(TaskIndex+". "+TaskList.get(i).toString());
            Helper.separator();
            Helper.newline();
        }

    }

    public void changeToMarkNotDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TaskList.size()){
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            showTodoList();
        }
        else{
            if (TaskList.get(i).isMarkAsDone().equals("X")){
                TaskList.get(i).setMarkNotDone();
                Helper.separator();
                System.out.println("Meow, I've marked this task as not done yet: ");
            }
            else {
                Helper.separator();
                System.out.println("This task has already been marked as not done!");
            }
            System.out.println(TaskIndex+". "+TaskList.get(i).toString());
            Helper.separator();
            Helper.newline();
        }

    }


}
