import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello there! I'm Duke\nWhat can I do for you?");

        String list[] = new String[100];
        Task tasks[]= new Task[100];

        int count = 0;

        String line;
        Scanner in = new Scanner(System.in);

        while (true){
            line = in.nextLine();

            // enter bye to end chat
            if (line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // to list all items
            else if (line.equals("list"))  {
                int listCount = 0;
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                while (listCount < count){
                    if (list[listCount]!=null) {
                        listCount++;
                        System.out.println((listCount) + ": " + list[listCount-1]);
                    }
                }
                System.out.println("____________________________________________________________");
            }

            // mark items
            else if (line.startsWith("mark")){
                String markString = line;

                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done:");
                int markIndex = Integer.parseInt(markString.substring(5));
                System.out.println(markIndex);
                tasks[markIndex-1].markAsDone();
                System.out.println(markIndex + ": " + tasks[markIndex-1].getStatusIcon()+ " " + tasks[markIndex-1].getDescription());
            }

            // unmarked items
            else if (line.startsWith("unmark")){
                String markString = line;
                int markIndex = Integer.parseInt(markString.substring(5));
                System.out.println(markIndex);
            }

            // add to list
            else {
                list[count]=line;
                count++;
                System.out.println("added: " + line);
            }

        }
    }
}
// print hello
// prompt command, store in list
// return what user types
// when bye , terminate with message
// push at every level
