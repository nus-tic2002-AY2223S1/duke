import java.util.Scanner;

public class Duke {
    public static void printbox(String words) {
        System.out.println("_____________________________________________");
        System.out.println(words);
        System.out.println("_____________________________________________");
    }

    public static void main(String[] args) {
        String logo = " _______ ______ ___  ___ ______\n"
                    + "|_______|  __  |   \\/   |  __  |\n"
                    + "   | |  | |  | |  |\\/|  | |  | |\n"
                    + "   | |  | |__| |  |  |  | |__| |\n"
                    + "   |_|  |______|__|  |__|______|\n";
        String line = "_____________________________________________";
        System.out.println(logo);
        printbox("Hello! It's Tomo here.\nHow can I help you?");

        while(true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if(input.equals("bye")) {
                break;
            }
            else {
                printbox(input); //echo user input
            }
        }
        printbox("Bye! See you and take care :)");
    }
}
