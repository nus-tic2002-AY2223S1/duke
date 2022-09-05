import java.util.Scanner;


public class Duke {

    public static String print(String msg){
        return "\t  ________________________________\n"+msg+"\t  ________________________________\n";


    }


    public static void main(String[] args) {
        String greet ="\t\t        Hello! I'm \n"
                +"\t\t   ____        _        \n"
                + "\t\t  |  _ \\ _   _| | _____ \n"
                + "\t\t  | | | | | | | |/ / _ \\\n"
                + "\t\t  | |_| | |_| |   <  __/\n"
                + "\t\t  |____/ \\__,_|_|\\_\\___|\n"
                +"\t\t   What can I do for you?\n";

        System.out.println(print(greet));
        Scanner in= new Scanner(System.in);
        String tempMsg;

        while(true){
            String userInput=in.nextLine();

            if(userInput.equals("bye")){
                tempMsg="\t  Bye. Hope to see you again soon!\n";
                System.out.println(print(tempMsg));
                break;
            }
            else{

                System.out.println(print("\t  "+userInput+"\n"));
            }



        }
    }
}
