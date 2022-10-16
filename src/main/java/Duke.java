import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static common.constants.CommonConstant.LOGO;
import static common.constants.CommonConstant.PROMPT;
import static common.constants.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.utils.PrintUtil.printGreet;
import static logic.parsers.Parser.parseChat;

public class Duke {
    public static void main(String[] args) {
        System.out.println(LOGO);
        printGreet();
        System.out.print(PROMPT);

        Scanner userInput = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        try {
            parseChat(userInput, taskList);
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }
    }
}