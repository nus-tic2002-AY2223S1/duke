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
        String[] list = new String[100];
        int listCount = 0; //to count number of items added to checklist
        System.out.println(logo);
        printbox("Hello! It's Tomo here.\nHow can I help you?");

        while(true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            if(input.equals("bye")) {
                break;
            }
            else if(input.equals("list")) {
                String combineList = "Checklist: \n";
                int i = 0;

                //loop used to combine array into a single string for printing
                while(i!=listCount) {
                    i++;
                    combineList = combineList + String.valueOf(i) + ") " + list[i-1] + "\n";
                }
                combineList = combineList + "-end of list-";
                printbox(combineList);
            }
            else {
                list[listCount] = input;
                listCount++;
                printbox("Added '" + input + "' into the list." ); //echo user input
            }
        }

        printbox("Bye! See you and take care :)");
    }
}
