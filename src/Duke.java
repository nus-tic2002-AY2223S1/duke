import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList <Task> wishList = new ArrayList<Task>();
    private static int totalTasks = 0;

    public static String input(Scanner s){
        String wish = s.nextLine();
        return wish;
    }

    public static void awakeDobby(){
        System.out.println("My name is Dobby and I am a free elf!");
        System.out.println("____________________Command Menu____________________");
        System.out.println("mark | unmark | remove | view | bye | <<to do task>> | <<deadline>>/by (deadline)| <<event>>/at");
        System.out.println("Automatic Testing: /bin/bash runtest.sh");
        System.out.println("_____________________________________________________");
    }

    public static void bye(){
        System.out.print("Goodbye, my friend. Dobby must now hurry to Harry Potter.\n");
    }

    public static void storeWish(String wish){
        Task t;
        if (wish.contains("/by")){
            t = new Deadline(wish);
        } else if (wish.contains("/at")){
            t = new Event(wish);
        } else {
            t = new Todo(wish);
        }
        wishList.add(t);
        totalTasks = totalTasks + 1;
        System.out.println("wish added: " + wish + ". ");
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public static void markWish(String wish){ //mark as done
        int wNumber = Integer.parseInt(wish);
        (wishList.get(wNumber-1)).markAsDone();
        System.out.println("Dobby has marked your wish as done");
        System.out.println();
    }

    public static void unmarkWish(String wish){ //mark as done
        int wNumber = Integer.parseInt(wish);
        (wishList.get(wNumber-1)).markAsNotDone();
        System.out.println("Dobby has unmarked your wish");
        System.out.println();
    }

    public static void removeWish(String wish){
        int wNumber = Integer.parseInt(wish);
        wishList.remove(wNumber-1);
        totalTasks = totalTasks - 1;
        System.out.println("wish removed: " + wish);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println();
    }

    public static void printWishList(){
        System.out.println(" ");
        for (int i = 0; i < wishList.size(); i++) {
            System.out.println( "[" + wishList.get(i).getTaskType() + "][" + wishList.get(i).getStatusIcon() + "] " + wishList.get(i).getTask());
        }
    }

    public static String substringHelper(String wish){
        int idx = wish.indexOf(" ") + 1;
        String w = wish.substring(idx, wish.length());
        return w;
    }

    public static boolean echo(Scanner s){
        String wish = input(s);
        if (wish.equals("bye")){
            bye();
            return true;
        } else if (wish.contains("remove") || wish.contains("Remove")){
            String w = substringHelper(wish);
            removeWish(w);
            return false;
        } else if (wish.contains("unmark") || wish.contains("Unmark")) {
            String w = substringHelper(wish);
            unmarkWish(w);
            return false;
        }else if (wish.contains("mark") || wish.contains("Mark")){
            String w = substringHelper(wish);
            markWish(w);
            return false;
        }else if(wish.equals("wishlist")){
            printWishList();
            return false;
        }else{
            storeWish(wish);
            return false;
        }
    }

    public static void main(String[] args) {

        awakeDobby();

        Scanner s = new Scanner(System.in);
        boolean terminateDobby = false;
        do{
            terminateDobby = echo(s);
            if (terminateDobby == false) {
                //System.out.println("Is there anything else?");
                System.out.println("____________________");
            }
        }while(terminateDobby != true);

    }
}
