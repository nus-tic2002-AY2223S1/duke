public class Bot {
    private String name;
    private String seperator;

    public Bot(String name, String seperator) {
        this.name = name;
        this.seperator = seperator;
    }

    public void welcome() {
        seperator();
        System.out.println("\tHello! I'm LaMDA");
        System.out.println("\tWhat can I do for you?");
        seperator();
    }

    public void show(String value) {
        seperator();
        System.out.println("\t" + value);
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
}
