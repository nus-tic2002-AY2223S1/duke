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
            Duke.echo(line);
        }

        Duke.exit();
    }
}
