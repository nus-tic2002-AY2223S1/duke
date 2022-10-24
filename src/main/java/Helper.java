import java.util.Random;
public class Helper {
    //helper class for formatting

    public static void newline(){
        System.out.println("\n");
    }
    public static void separator() {
        System.out.println("____________________________________");
    }

    public static void nalaConfused() {
        Random rand = new Random();
        int nalaMood = rand.nextInt(9);
        if (nalaMood < 3) {
            System.out.println("Nala hisses at you (she's on a bad day). Try typing that again (with the correct syntax :))");
        } else {
            System.out.println("Meow! I don't get what you're trying to say human. Could you repeat that again (with the correct syntax)?");
        }
    }

    public static void nalaSyntaxError(String type){
        if (type == "Event"){
            System.out.println("Meow! I spotted an \"/at\" in your input. Did you mean to create an event? Please create the task again!");
        }
        else if (type == "Deadline"){
            System.out.println("Meow! I spotted an \"/by\" in your input. Did you mean to create an deadline? Please create the task again!");
        }

        else if (type == "EventNoAt"){
            System.out.println("Meow! I can't create an Event for you without an \"/at\". Please create the task again!");
        }
        else if (type == "DeadlineNoBy"){
            System.out.println("Meow! I can't create an Event for you without a \"/by\". Please create the task again!");
        }
        else if (type == "BlankEvent"){
            System.out.println("Meow! I can't create an Event for you without a description! Please create the task again!");
        }
        else if (type == "BlankDeadline"){
            System.out.println("Meow! I can't create an Deadline for you without a description! Please create the task again!");
        }
        else if (type == "BlankTodo"){
            System.out.println("Meow! I can't create an Todo for you without a description! Please create the task again!");
        }
        else if (type == "NoDate"){
            System.out.println("Meow! You did not specify a date. Please create the task again!");
        }
    }

}
