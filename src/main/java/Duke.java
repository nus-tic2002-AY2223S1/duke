import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
       String line;
         String[] book_list=new String[100];
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        //
       /*
        while(true){
            line=in.nextLine();
            Echo t = new Echo(line);
            if(line.equalsIgnoreCase("bye")){
                t.get();
                break;
            }
            t.get();
        }
        */
        while(true){
            line=in.nextLine();
            AddList t = new AddList(line);
            if(line.equalsIgnoreCase("bye")){
                t.get();
                break;
            }
            t.get();
        }
    }
}


