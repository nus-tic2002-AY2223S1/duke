import java.util.Scanner;

public class Duke {
    public static String line = "\t____________________________________________________________\n";

    public static void main(String[] args) {

        String greeting = line + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                +line;

        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        int count = 0;
        String[] list = new String[100];

        while(input!=null){
            String output = "";
            if(input.equalsIgnoreCase("bye")){
                output = String.format("%s\t%s\n%s",line,"Bye. Hope to see you again soon!",line);
                System.out.println(output);
                break;
            }

            if(input.equalsIgnoreCase("list")){
                for(int i = 0; i < count;i++){
                    output += String.format("\t%d. %s\n",i+1,list[i]);
                }
                System.out.println(line + output+line);
            }
            else{
                list[count] = input;
                count++;
                output = String.format("added: %s",input);
                System.out.println(line + "\t"+output+"\n"+line);
            }
            input = in.nextLine();

        }

    }
}


