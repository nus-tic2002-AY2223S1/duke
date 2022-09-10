import java.util.Scanner;
import java.util.Arrays;


public class Duke {

    //public static String taskname(int num) {

    //}


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat's on your mind today?");
        //String[] com= new String[100];
        Task[] t= new Task[10];
        String temp;
        int i=1;
        int j=0;
        while(i==1){
            Scanner in = new Scanner(System.in);
            temp = in.nextLine();

            if(temp.equals("list")){
                System.out.println("Content of your List\n");

                for(int k=0;k<j;k++){
                    System.out.println(k+1 + ". ["+t[k].getStatusIcon()+"] " + t[k].getDescription() +"\n");
                }

            }
            else if(temp.equals("bye")){
                i=0;
            }

            else if (temp.substring(0,3).equals("unm")) {
                //unmark
                int num= Integer.parseInt(temp.substring(7));
                t[num-1].markAsUndone();

            }

            else if (temp.substring(0,3).equals("mar")) {
                //mark
                int num= Integer.parseInt(temp.substring(5));
                t[num-1].markAsDone();

            }
            else{

                 t[j]= new Task(temp);
                //com[j]= temp;
                j++;
            }

        }
        System.out.println("Byeee!! See you Again!!");
    }
}
