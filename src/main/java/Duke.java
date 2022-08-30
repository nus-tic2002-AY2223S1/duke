public class Duke {
    public static void main(String[] args) {
        greeting();
        goodbye();
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        // add"what can i do for you"
        System.out.println("What can i do for you?");
    }
    //add"good bye "
    public static void goodbye(){
        System.out.println("Bye.Hope to see you again soon!");

    }
}
