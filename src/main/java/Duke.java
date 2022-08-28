import java.util.Scanner;

public class Duke {
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
     * echo prints item entered by user
     *
     * @param {String} item entered by user
     * @return {void}
     */
    public static void echo(String input) {
        printLine();
        System.out.println(input);
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
                default:
                    echo(input);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Constant.LOGO);

        greet();
        chat();
    }
}
