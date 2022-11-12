package cat;

import java.util.Random;

/**
 * Nala is a class, and at the same time, a cat. Click the link to find out more about her (not malware).{@link <a href="https://share.icloud.com/photos/0a8PnKhH3NEpDH3ANZpjpF8yA">...</a>}
 */
public class Nala {
    public static void nalaConfused() {
        Random rand = new Random();
        int nalaMood = rand.nextInt(9);
        if (nalaMood < 3) {
            System.out.println("Nala hisses at you (there is an error in your text). Try typing that again (with the correct syntax :))");
        } else {
            System.out.println("Meow :\\ There's an error in your text. Could you repeat that again (with the correct syntax)?");
        }
    }

    public static void nalaBehaviour() {
        Random rand = new Random();
        int nalaMood = rand.nextInt(8);
        if (nalaMood < 3) {
            System.out.println("(Nala meows at you)");
        } else if (nalaMood < 6){
            System.out.println("(Nala purrs)");
        } else {
            System.out.println("(Nala takes a nap)");
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
                System.out.println("Meow! I can't create an Deadline for you without a \"/by\". Please create the task again!");
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
