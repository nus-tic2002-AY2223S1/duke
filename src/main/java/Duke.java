import static config.ConfigParser.*;
import static config.CommonUtil.*;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO_CONST);

        BotMaster myBot = new BotMaster("ZeRo", underScoreLine);
        myBot.initialization();

        Scanner inputText = new Scanner(System.in);
        while (inputText.hasNext()) {
            String parseInput = (inputText.nextLine()).toLowerCase();
            switch (parseInput) {
                case byeConstant:
                    myBot.termination();
                    return;
                default:
                    myBot.displayMessage(parseInput);
            }
        }

    }


}
