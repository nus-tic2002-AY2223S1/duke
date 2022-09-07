import java.util.Scanner;

public class Duke {
    public static int filterTaskID(String line) {
        String[] words = line.split(" ");
        int ID_num = Integer.parseInt(words[1]);
        return ID_num - 1;
    }
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
        int taskID;
        Scanner in = new Scanner(System.in);

        while(!line.equals("bye") ){
            line = in.nextLine();

            if(line.equals("list")){
                Duke.listTasks();
            }
            else if (line.equals("bye")) {
                Duke.exit();
            }
            else if (line.startsWith("mark")) {
                taskID = filterTaskID(line);
                Duke.taskList[taskID].markAsDone();
            }
            else if (line.startsWith("unmark")) {
                taskID = filterTaskID(line);
                Duke.taskList[taskID].markAsNotDone();
            }
            else {
                Duke.addTasks(line);
                System.out.println("added: " + line);
            }
        }
    }
}
