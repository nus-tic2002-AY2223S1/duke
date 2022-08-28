import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String line;
        String[] book_list=new String[100];
        Scanner in= new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");


        int count=0;

        while(true){
            line=in.nextLine();

            if("read book".equalsIgnoreCase(line) || "return book".equalsIgnoreCase(line)){
                    book_list[count]=line;
                    count++;
                System.out.println("added: "+line);
                }


            if("bye".equalsIgnoreCase(line)){
             break;
            }
            if("list".equalsIgnoreCase(line)){
               for(int i=1;i<=count;i++){
                   System.out.println(i+". "+book_list[i-1]);
               }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
