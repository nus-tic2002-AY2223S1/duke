import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> task=new ArrayList<Task>();
    public static final String byeMsg="Bye. Hope to see you again soon!\n";
    public static String print(String msg) {
        return "________________________________\n" + msg + "________________________________\n";
    }


    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\nWhat can I do for you?\n";

        System.out.println(print(greet));
        Scanner in = new Scanner(System.in);






        while (true) {
            String userInput = in.nextLine();
            String[] inputSplit = userInput.split(" ");
            int itemNo;


            switch (inputSplit[0]) {
                case "bye":
                    System.out.println(print(byeMsg));
                    return;

                case "list":
                    Task.list();
                    break;

                case "mark":
                case "unmark":
                case "delete":
                    try{
                        if(inputSplit[1]==null){
                            throw new DukeException();
                        }
                    }catch(Exception e){
                        System.out.println(print("☹ OOPS!!! Task ID cannot be empty, please check!\n"));
                        break;
                    }
                    itemNo = Integer.parseInt(inputSplit[1]) - 1;

                    try{
                        if(itemNo>=task.size() || itemNo<0){
                            throw new DukeException();
                        }
                    }catch(DukeException e){
                        System.out.println(print("☹ OOPS!!! Task ID not found, please check!\n"));
                        break;
                    }
                    if(inputSplit[0].equals("mark")){
                        task.get(itemNo).mark();
                    }else if(inputSplit[0].equals("unmark")) {
                        task.get(itemNo).unmark();
                    }else{
                        Task.deleteTask(itemNo);

                    }
                    break;


                case "deadline":
                case "todo":
                case "event":
                    try{
                        if(inputSplit.length==1){
                            throw new DukeException();
                        }
                    }catch(DukeException e){

                        System.out.println(print("☹ OOPS!!! The description of a "+inputSplit[0] +" cannot be empty.\n"));
                        break;
                    }

                    try {
                        Task.addTask(inputSplit[0], userInput);
                    }catch(DukeException e){
                        System.out.println(print("☹ OOPS!!! Invalid input, please check!\n"));
                    }

                    break;

                default:
                    try{
                        throw new DukeException();
                    }catch(DukeException e){
                        System.out.println(print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"));

                    }
                    break;


            }
        }
    }
}