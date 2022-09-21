import java.util.Arrays;
public class Bot {
    private String name;
    private String seperator;
    private Task[] storage;
    private int index;

    public Bot(String name, String seperator) {
        this.name = name;
        this.seperator = seperator;
        this.storage = new Task[100];
        this.index = 0;
    }

    public void welcome() {
        seperator();
        System.out.println(appendTab("Hello! I'm "  + name));
        System.out.println(appendTab("What can I do for you?"));
        seperator();
    }

    public void show(String value) {
        store(value);
        seperator();
        System.out.println(appendTab("added: " + value));
        seperator();
    }

    public void showList() {
        int i = 1;
        seperator();
        System.out.println(appendTab("Here are the tasks in your list:"));
        Task[] stored = Arrays.copyOf(storage, index);
        for(Task each : stored) {
            System.out.println(appendTab(i+". " + each));
            i++;
        }
        seperator();
    }

    public void mark(String i){
        int index = Integer.parseInt(i);
        seperator();
        if(storage[index - 1].isDone() == false) {
            storage[index - 1].markTask(true);
            System.out.println(appendTab("Nice! I've marked this task as done:"));
            System.out.println(appendTab(storage[index - 1].toString()));
        } else {
            System.out.println(appendTab("This task is already marked as done"));
        }
        seperator();

    }

    public void unmark(String i){
        int index = Integer.parseInt(i);
        seperator();
        if(storage[index - 1].isDone() == true) {
            storage[index - 1].markTask(false);
            System.out.println(appendTab("OK! I've marked this task as not done yet:"));
            System.out.println(appendTab(storage[index - 1].toString()));
        } else {
            System.out.println(appendTab("This task is already marked as not done"));
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
    private void store(String value) {
        Task newTask = new Task(value);
        storage[index] = newTask;
        index++;
    }

    private String appendTab(String text) {
        return "\t" + text;
    }
}
