import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Duke {
    public static ArrayList<String> listItems = new ArrayList<>();

    /**
     * printLine prints line
     *
     * @return {void}
     */
    public static void printLine() {
        System.out.println(Constant.DASHES);
    }

    /**
     * greet prints greeting text
     *
     * @return {void}
     */
    public static void greet() {
        printLine();
        System.out.println(Constant.HELLO_GREETING);
        System.out.println(Constant.INPUT_OPTIONS);
        System.out.println(Constant.PROMPT_INPUT);
        printLine();
    }

    /**
     * add adds and prints item added to listItems array
     *
     * @param {String} item entered by user
     * @return {void}
     */
    public static void add(String input) {
        printLine();

        listItems.add(input);

        System.out.println(Constant.ADDED + input);
        printLine();
    }

    /**
     * list prints list of item in listItems array
     *
     * @return {void}
     */
    public static void list() {
        printLine();
        System.out.println(Constant.TASK_LIST);

        AtomicInteger counter = new AtomicInteger(Constant.INIT_INT_VAL);
        listItems.forEach(item -> {
            System.out.println(counter.getAndIncrement() + Constant.PERIOD + item);
        });

        printLine();
    }

    /**
     * exit prints bye statement
     *
     * @return {void}
     */
    public static void exit() {
        printLine();
        System.out.println(Constant.BYE_GREETING);
        printLine();
    }

    /**
     * chat returns the flow of the chat
     *
     * @return {void}
     */
    public static void chat() {
        Scanner userInput = new Scanner(System.in);

        while (userInput.hasNext()) {
            String input = (userInput.nextLine()).toLowerCase();
            switch (input) {
                case Constant.BYE:
                    exit();
                    return;
                case Constant.LIST:
                    list();
                    break;
                default:
                    add(input);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Constant.LOGO);

        greet();
        chat();
    }
}
