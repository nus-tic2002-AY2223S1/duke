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
            if (parseInput.startsWith(markConstant) || parseInput.startsWith(unmarkConstant)) {
                // handle mark and unmark
                String command = parseInput.split(" ")[0];
                String position = parseInput.split(" ")[1];
                if (command.equalsIgnoreCase(markConstant)) {
                    myBot.markTask(position);
                }
                else {
                    myBot.unmarkTask(position);
                }
            }
            else {
                if (parseInput.equalsIgnoreCase(byeConstant)) {
                    myBot.termination();
                    return;
                }
                else if (parseInput.equalsIgnoreCase(listConstant)) {
                    myBot.displayPocket();
                }
                else {
                    myBot.displayMessage(parseInput);
                }
            }
        }

    }


}
