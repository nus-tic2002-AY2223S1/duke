import Tasks.Task;
import Tasks.UnsupportedTaskType;

public class Bot {
    private String name;
    private String seperator;
    private TaskManager taskManager;

    public Bot(String name, String seperator) {
        this.name = name;
        this.seperator = seperator;
        taskManager = new TaskManager();
    }

    public void welcome() {
        seperator();
        System.out.println(appendTab("Hello! I'm "  + name));
        System.out.println(appendTab("What can I do for you?"));
        seperator();
    }

    public void add(String text) {
        try {
            String firstWord = text.substring(0, text.indexOf(' '));
            String theRest = text.substring(text.indexOf(" "));
            seperator();
            taskManager.addNewTask(TaskManager.TaskType.getType(firstWord), theRest);
            int size = taskManager.getList().size();
            System.out.println(appendTab("Got it. I've added this task:"));
            System.out.println(appendTab(taskManager.getString()));
            System.out.println(appendTab("Now you have "+size+" tasks in the list."));
        } catch(UnsupportedTaskType e) {
            System.out.println(appendTab("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        } catch(IndexOutOfBoundsException e) {
            System.out.println(appendTab("☹ OOPS!!! The description of a "+text+" cannot be empty."));
        }
        seperator();
    }

    public void showList() {
        seperator();
        System.out.println(appendTab("Here are the tasks in your list:"));
        int i = 1;
        for(String each : taskManager.getList()) {
            System.out.println(appendTab(i+ ". " + each));
            i++;
        }
        seperator();
    }

    public void mark(String i,  boolean value){
        int index = Integer.parseInt(i);
        seperator();
        boolean success = taskManager.markTask(index - 1, value);
        if(success) {
            if(value == true) {
                System.out.println(appendTab("Nice! I've marked this task as done:"));
            } else {
                System.out.println(appendTab("OK, I've marked this task as not done yet:"));
            }
            System.out.println(appendTab(taskManager.getTask(index - 1)));
        } else {
            if(value == true) {
                System.out.println(appendTab("This task is already marked as done"));
            } else {
                System.out.println(appendTab("This task is already marked as not done"));
            }
        }
        seperator();
    }

    public void goodbye() {
        seperator();
        System.out.println(appendTab("Bye. Hope to see you again soon!"));
        seperator();
    }

    private void seperator() {
        System.out.println("\t" + seperator);
    }

    private String appendTab(String text) {
        return "\t" + text;
    }
}
