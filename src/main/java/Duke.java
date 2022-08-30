import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList <String> wishList = new ArrayList<String>();

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
        wishList.add(wish);
        System.out.print("Dobby has taken note of your wish: " + wish + ". ");
    }

    public static void removeWish(String wish){
        int idx = wishList.indexOf(wish);
        wishList.remove(idx); // Remove wish that is currently stored at this index
        System.out.print("Dobby has striked off " + wish + " from your wish list. ");
    }

    public static void printWishList(){
        System.out.println("Here's your wish list that Dobby kept as a secret:");
        for (int i = 0; i < wishList.size(); i++) {
            System.out.println(wishList.get(i));
        }
    }

    public static Boolean echo(){
        String wish = input();
        if (isBye(wish)){
            bye();
            return true;
        } else if (wish.contains("remove")){ // e.g. remove wish
            int idx = wish.indexOf(" ") + 1;
            String w = wish.substring(idx, wish.length());
            removeWish(w);
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

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
    }
}
