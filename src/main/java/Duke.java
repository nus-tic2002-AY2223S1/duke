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
        Task[] task=new Task[100];

        int countTask=0;
        String itemList="";

        while(true) {
            String userInput = in.nextLine();
            String[] sentence=userInput.split(" ");
            int itemNo=999;
            if(sentence.length>1){
                itemNo=Integer.parseInt(sentence[1])-1;
            }

            switch (sentence[0]) {
                case "bye":
                    tempMsg = "\t  Bye. Hope to see you again soon!\n";
                    System.out.println(print(tempMsg));
                    return;

            case "list":
                for (int i = 0; i < countTask; i++) {
                    itemList += "\t  " + (i + 1) + ".["+task[i].getStatusIcon()+"] " + task[i].getTask() + "\n";

                }
                System.out.println(print(itemList));
                itemList = "";
                break;

            case "mark":
                task[itemNo].mark();
                System.out.println(print("\t  Nice! I've marked this task as done:\n\t    ["+task[itemNo].getStatusIcon()+"] "+task[itemNo].getTask()+"\n"));
                break;

            case "unmark":
                task[itemNo].unmark();
                System.out.println(print("\t   OK, I've marked this task as not done yet:\n\t    ["+task[itemNo].getStatusIcon()+"] "+task[itemNo].getTask()+"\n"));
                    break;

            default:
                task[countTask]=new Task(userInput);
                countTask++;
                System.out.println(print("\t  added: " + userInput + "\n"));
                break;


            }
        }
    }
}
