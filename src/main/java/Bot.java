import java.util.Arrays;
public class Bot {
    private String name;
    private String seperator;
    private String[] storage;
    private int index;

    public Bot(String name, String seperator) {
        this.name = name;
        this.seperator = seperator;
        this.storage = new String[100];
        this.index = 0;
    }

    public void welcome() {
        seperator();
        System.out.println("\tHello! I'm LaMDA");
        System.out.println("\tWhat can I do for you?");
        seperator();
    }

    public void show(String value) {
        store(value);
        seperator();
        System.out.println("\tadded: " + value);
        seperator();
    }

    public void showList() {

        int i = 1;
        seperator();
        String[] stored = Arrays.copyOf(storage, index);
        for(String each : stored) {
            System.out.println("\t"+ i+". " + each);
            i++;
        }
        seperator();
    }

    public void goodbye() {
        seperator();
        System.out.println("\tBye. Hope to see you again soon!");
        seperator();
    }

    private void seperator() {
        System.out.println("\t" + seperator);
    }
    private void store(String value) {
        storage[index] = value;
        index++;
    }
}
