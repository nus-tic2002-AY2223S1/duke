import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        assistant Duke = new assistant();
        Duke.greet();

        String line = "";
        Scanner in = new Scanner(System.in);

        while(!line.equals("bye") ){
            line = in.nextLine();

            if(line.equals("list")){
                Duke.listTasks();
            }
            else if (line.equals("bye")) {
                Duke.exit();
            }
            else {
                Duke.addTasks(line);
                System.out.println("added: " + line);
            }
        }
    }
}
