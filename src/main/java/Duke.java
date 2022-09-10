import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat's on your mind today?");
        String[] com= new String[100];
        String temp;
        int i=1;
        int j=0;
        while(i==1){
            Scanner in = new Scanner(System.in);
            temp = in.nextLine();
            if(temp.equals("list")){
                System.out.println("Content of your List\n");

                for(int k=0;k<j;k++){
                    System.out.println(k+1 + ". " + com[k] +"\n");
                }

            }
            else if(temp.equals("bye")){
                i=0;
            }
            else{
                com[j]= temp;
                j++;
            }

        }
        System.out.println("Byeee!! See you Again!!");
    }
}
