import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class ChaBot extends Todo{

    private String separator;
    public ArrayList<Todo> TodoList = new ArrayList<Todo>();

    public ChaBot(String taskName, boolean markAsDone, String separator, ArrayList<Todo> todoList) {
        super(taskName, markAsDone);
        this.separator = separator;
        TodoList = todoList;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
        System.out.println("_____________________________________" + "\n");
    }

    public void addNewItem(String incomingTaskName){
        TodoList.add(new Todo(incomingTaskName,false));
    }

    public void showTodoList(){
        //WIP
        for(int i = 0; i < TodoList.size(); i++) {
            System.out.println(TodoList.get(i));
        }
    }


}

