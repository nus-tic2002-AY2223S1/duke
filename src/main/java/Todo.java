import java.util.ArrayList;

public class Todo {

    private ArrayList<Task> TodoList;

    public Todo() {
        TodoList = new ArrayList<>();
    }

    public void separator() {
        System.out.println("____________________________________");
    }
    public void newline(){
        System.out.println("\n");
    }

    public void addNewTodo(String incomingTaskName){
        TodoList.add(new Task(incomingTaskName));
        separator();
        System.out.println("added: " + incomingTaskName);
        separator();
    }

    public void showTodoList(){
        for (int i = 0; i< TodoList.size(); i++){
            String Tick;
            boolean check = TodoList.get(i).isMarkAsDone();
            if (check){
                Tick = "x";
            }
            else {
                Tick = " ";
            }
            System.out.println(i+1 + ".[" + Tick + "] " + TodoList.get(i).getTaskName());

        }
    }

    public void changeToMarkAsDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TodoList.size()){
            separator();
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
            separator();
        }
        else{
            if (!TodoList.get(i).isMarkAsDone()){
                TodoList.get(i).setMarkAsDone();
                separator();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(TaskIndex + ".[x] " + TodoList.get(i).getTaskName());
                separator();
                newline();
            }
            else {
                separator();
                System.out.println("This task has already been marked as done!");
                System.out.println(TaskIndex + ".[x] " + TodoList.get(i).getTaskName());
                separator();
                newline();
            }
        }

    }

    public void changeToMarkNotDone(int TaskIndex){
        int i = TaskIndex-1;
        if (i> TodoList.size()){
            System.out.println("Uh oh! Task " + TaskIndex + " is not found :(");
        }
        else{
            if (TodoList.get(i).isMarkAsDone()){
                TodoList.get(i).setMarkNotDone();
                separator();
                System.out.println("Meow, I've marked this task as not done yet: ");
                System.out.println(TaskIndex + ".[ ] " + TodoList.get(i).getTaskName());
                separator();
                newline();
            }
            else {
                separator();
                System.out.println("This task has already been marked as not done!");
                System.out.println(TaskIndex + ".[ ] " + TodoList.get(i).getTaskName());
                separator();
                newline();
            }
        }

    }

}
