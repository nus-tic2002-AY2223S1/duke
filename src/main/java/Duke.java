import java.util.Scanner;


public class Duke {

    public static String print(String msg){
        return "________________________________\n"+msg+"________________________________\n";


    }


    public static void main(String[] args) {
        String greet ="Hello! I'm Duke\nWhat can I do for you?\n";

        System.out.println(print(greet));
        Scanner in= new Scanner(System.in);
        String tempMsg;
        Task[] task=new Task[100];

        int countTask=0;
        String itemList="";
	int indexS;
	int index;
	String detailTask;
	String detail;	

        while(true) {
            String userInput = in.nextLine();
            String[] sentence=userInput.split(" ");
            int itemNo=999;
            

            switch (sentence[0]) {
                case "bye":
                    tempMsg = "Bye. Hope to see you again soon!\n";
                    System.out.println(print(tempMsg));
                    return;

            case "list":
                for (int i = 0; i < countTask; i++) {
                    //itemList += "(i + 1) + ".["+task[i].getStatusIcon()+"] " + task[i].getTask() + "\n";
		    itemList +=(i+1)+"."+task[i].toString();
                }
                System.out.println(print(itemList));
                itemList = "";
                break;

            case "mark":
		itemNo=Integer.parseInt(sentence[1])-1;
                task[itemNo].mark();
                System.out.println(print("Nice! I've marked this task as done:\n ["+task[itemNo].getStatusIcon()+"] "+task[itemNo].getTask()+"\n"));
                break;

            case "unmark":
		itemNo=Integer.parseInt(sentence[1])-1;
                task[itemNo].unmark();
                System.out.println(print("OK, I've marked this task as not done yet:\n ["+task[itemNo].getStatusIcon()+"] "+task[itemNo].getTask()+"\n"));
                break;

	    case "deadline":
		
		index=userInput.indexOf('/');
		indexS=userInput.indexOf(' ');
		detail=userInput.substring(index+4);
		detailTask=userInput.substring(indexS,index-1);
		task[countTask]=new Deadline(detailTask,detail);		
	
		System.out.println(print(task[countTask].encapTask(task[countTask].toString(),countTask+1)));
		countTask++;
		break;
	    
            case "todo":
		indexS=userInput.indexOf(' ');
		detailTask=userInput.substring(indexS);
		task[countTask]=new Todo(detailTask);
		
		System.out.println(print(task[countTask].encapTask(task[countTask].toString(),countTask+1)));
		countTask++;
		break;

	    case "event":
		
		index=userInput.indexOf('/');
		indexS=userInput.indexOf(' ');
		detail=userInput.substring(index+4);
		detailTask=userInput.substring(indexS,index-1);
		task[countTask]=new Event(detailTask,detail);		
	
		System.out.println(print(task[countTask].encapTask(task[countTask].toString(),countTask+1)));
		countTask++;
		break;	
	
            default:
                break;


            }
        }
    }
}