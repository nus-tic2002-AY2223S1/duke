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

    public static void nalaBehaviour() {
        Random rand = new Random();
        int nalaMood = rand.nextInt(8);
        if (nalaMood < 3) {
            System.out.println("Nala meows at you");
        } else if (nalaMood < 6){
            System.out.println("Nala purrs");
        } else {
            System.out.println("Nala ignores you");
        }
    }

    public static void nalaSyntaxError(String type){
        switch (type) {
            case "Event":
                System.out.println("Meow! I spotted an \"/at\" in your input. Did you mean to create an event? Please create the task again!");
                break;
            case "Deadline":
                System.out.println("Meow! I spotted an \"/by\" in your input. Did you mean to create an deadline? Please create the task again!");
                break;
            case "EventNoAt":
                System.out.println("Meow! I can't create an Event for you without an \"/at\". Please create the task again!");
                break;
            case "DeadlineNoBy":
                System.out.println("Meow! I can't create an Event for you without a \"/by\". Please create the task again!");
                break;
            case "BlankEvent":
                System.out.println("Meow! I can't create an Event for you without a description! Please create the task again!");
                break;
            case "BlankDeadline":
                System.out.println("Meow! I can't create an Deadline for you without a description! Please create the task again!");
                break;
            case "BlankTodo":
                System.out.println("Meow! I can't create an Todo for you without a description! Please create the task again!");
                break;
            case "NoDate":
                System.out.println("Meow! You did not specify a date. Please create the task again!");
                break;
        }
    }

}
