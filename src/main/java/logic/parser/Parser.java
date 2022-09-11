package logic.parser;

import logic.commands.Command;
import logic.commands.AddCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.UnmarkCommand;
import model.Chat;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static common.constant.CommandConstant.BYE_COMMAND;
import static common.constant.CommandConstant.LIST_COMMAND;
import static common.constant.CommandConstant.MARK_COMMAND;
import static common.constant.CommandConstant.UNMARK_COMMAND;
import static common.constant.CommonConstant.PROMPT;
import static common.constant.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.util.PrintUtil.printBye;
import static common.util.PrintUtil.printEmptyTaskList;
import static common.util.StringUtil.getFirstWord;

public class Parser {
    public Parser() {};

    /**
     * parseCommand returns parse command
     *
     * @param {String} input
     * @param {Chat} chat
     * @return {void}
     */
    public static void parseCommand(String input, Chat chat) {
        Command addCom = new AddCommand(chat);
        Command listCom = new ListCommand(chat);
        Command markCom = new MarkCommand(chat);
        Command unmarkCom = new UnmarkCommand(chat);

        switch (input){
            case BYE_COMMAND:
                printBye();
                System.exit(0);
            case LIST_COMMAND:
                listCom.execute();
                break;
            case MARK_COMMAND:
                markCom.execute();
                break;
            case UNMARK_COMMAND:
                unmarkCom.execute();
                break;
            default:
                addCom.execute();
        }
    }

    /**
     * parseChat returns parse chat
     *
     * @return {void}
     */
    public static void parseChat() {
        System.out.print(PROMPT);
        Scanner userInput = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        try {
            while (userInput.hasNext()) {
                String input = (userInput.nextLine()).toLowerCase();
                Chat chat = new Chat(input, taskList);

                if (chat.getTaskList().isEmpty() && (
                        getFirstWord(input).equals(LIST_COMMAND) ||
                        getFirstWord(input).equals(MARK_COMMAND) ||
                        getFirstWord(input).equals(UNMARK_COMMAND)
                )) {
                    printEmptyTaskList();
                } else {
                    parseCommand(getFirstWord(input), chat);
                }

                System.out.print(PROMPT);
            }
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }
    }
}
