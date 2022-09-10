import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList <Task> wishList = new ArrayList<Task>();

    public static String input(){
        Scanner s = new Scanner(System.in);
        String wish = s.nextLine();
        return wish;
    }

    public static void bye(){
        System.out.print("Goodbye, my friend. Dobby must now hurry to Harry Potter.\n");
    }

    public static Boolean isBye(String word){
        if (word.equals("bye") || word.equals("Bye") ||
                word.equals("goodbye") || word.equals("Goodbye") ||
                word.equals("see you") || word.equals("See you") ||
                word.equals("adios") || word.equals("Adios") ||
                word.equals("ciao") || word.equals("Ciao") ||
                word.equals("cya") || word.equals("Cya")){
            return true;
        } else {
            return false;
        }
    }

    public static void storeWish(String wish){
        Task t;
        if (wish.contains("/by")){
            t = new Deadline(wish); //Task t = new Task("read book");
        } else if (wish.contains("/at")){
            t = new Event(wish); //Task t = new Task("read book");
        } else {
            t = new Todo(wish); //Task t = new Task("read book");
        }
        wishList.add(t);
        System.out.print("Dobby has taken note of your wish: " + wish + ". ");
        printWishList();
    }

    public static void markWish(String wish){ //mark as done
        int wNumber = Integer.parseInt(wish);
        (wishList.get(wNumber-1)).markAsDone();
        System.out.print("Dobby has marked your wish as done");
        printWishList();
    }

    public static void unmarkWish(String wish){ //mark as done
        int wNumber = Integer.parseInt(wish);
        (wishList.get(wNumber-1)).markAsNotDone();
        System.out.print("Dobby has marked your wish as done");
        printWishList();
    }

    public static void removeWish(String wish){
        //int idx = wishList.indexOf(wish);
        //wishList.remove(idx); // Remove wish that is currently stored at this index
        int wNumber = Integer.parseInt(wish);
        wishList.remove(wNumber-1);
        System.out.print("Dobby has struck off " + wish + " from your wish list. ");
        printWishList();
    }

    public static void printWishList(){
        System.out.println("Here's your wish list that Dobby kept as a secret:");
        for (int i = 0; i < wishList.size(); i++) {
            System.out.println( "[" + wishList.get(i).getTaskType() + "][" + wishList.get(i).getStatusIcon() + "] " + wishList.get(i).getTask());
        }
    }

    public static String substringHelper(String wish){
        int idx = wish.indexOf(" ") + 1;
        String w = wish.substring(idx, wish.length());
        return w;
    }

    public static Boolean echo(){
        String wish = input();
        if (isBye(wish)){
            bye();
            return true;
        } else if (wish.contains("remove")){ // e.g. remove wish
            String w = substringHelper(wish);
            removeWish(w);
            return false;
        } else if (wish.contains("unmark")) {
            String w = substringHelper(wish);
            unmarkWish(w);
            return false;
        }else if (wish.contains("mark")){ // e.g. i am done returning the book
            String w = substringHelper(wish);
            markWish(w);
            return false;
        }else if(wish.equals("secret wish list")){ // What's my secret wish list?
            printWishList();
            return false;
        }else{
            storeWish(wish);
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.print("My name is Dobby and I am a free elf! Now, what is it that you wish for?");

        Boolean terminateDobby = false;
        do{
            terminateDobby = echo();
            if (terminateDobby == false) {
                System.out.print("Is there anything else?");
            }
        }while(terminateDobby != true);

    }
}
