import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String[] txtLst = new String[100];
        Scanner input = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n"+"What can I do for you?:\n");
        int counter = 0;
        while (true) {
            String line = input.nextLine();
            if ("bye".equals(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if ("list".equals(line)) {
                for(int count=0; count<txtLst.length; count++){
                    if(txtLst[count]!= null){
                        System.out.println((count+1)+": "+ txtLst[count]);
                    }
                }
                System.out.println("\n");
            }
            else{
                txtLst[counter] = line;
                counter++;
                System.out.println(line+"\n");
            }
        }
    }
}