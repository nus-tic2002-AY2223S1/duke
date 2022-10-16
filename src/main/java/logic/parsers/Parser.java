package logic.parsers;

import logic.commands.Command;
import logic.commands.AddCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.UnmarkCommand;
import model.Chat;
import model.Task;

import java.util.List;
import java.util.Scanner;

import static common.constants.CommandConstant.BYE_COMMAND;
import static common.constants.CommandConstant.LIST_COMMAND;
import static common.constants.CommandConstant.MARK_COMMAND;
import static common.constants.CommandConstant.UNMARK_COMMAND;
import static common.constants.CommandConstant.ADD_TODO_COMMAND;
import static common.constants.CommandConstant.ADD_DEADLINE_COMMAND;
import static common.constants.CommandConstant.ADD_EVENT_COMMAND;
import static common.constants.CommonConstant.PROMPT;
import static common.utils.PrintUtil.printBye;
import static common.utils.PrintUtil.printLine;
import static common.utils.StringUtil.getDescriptionFromString;
import static common.utils.StringUtil.getFirstWord;
import static logic.parsers.TaskValidationParser.validateList;
import static logic.parsers.TaskValidationParser.validateMark;
import static logic.parsers.TaskValidationParser.validateUnmark;
import static common.utils.TaskValidationUtil.invalidTaskCommandValidation;

public class Parser {
    public Parser() {}
    /**
     * parseCommand returns parse command
     *
     * @param {String} input
     * @param {Chat} chat
     * @return {void}
     */
    public static void parseCommand(String description, Chat chat) {
        Command addCom = new AddCommand(chat);
        Command listCom = new ListCommand(chat);
        Command markCom = new MarkCommand(chat);
        Command unmarkCom = new UnmarkCommand(chat);

        switch (chat.getCommand()){
            case BYE_COMMAND:
                printBye();
                System.exit(0);
            case LIST_COMMAND:
                validateList(chat);
                listCom.execute();
                break;
            case MARK_COMMAND:
                validateMark(description, chat);
                markCom.execute();
                break;
            case UNMARK_COMMAND:
                validateUnmark(description, chat);
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
    public static void parseChat(Scanner userInput, List<Task> taskList) {
        while (userInput.hasNext()) {
            String input = (userInput.nextLine()).toLowerCase();
            String command = getFirstWord(input);
            Chat chat = new Chat(userInput, command, input, taskList);
            String description = getDescriptionFromString(command, input);

            printLine();

            if (!command.equals(BYE_COMMAND) && !command.equals(LIST_COMMAND) && !command.equals(MARK_COMMAND) && !command.equals(UNMARK_COMMAND) && !command.equals(ADD_TODO_COMMAND) && !command.equals(ADD_EVENT_COMMAND) && !command.equals(ADD_DEADLINE_COMMAND)) {
                invalidTaskCommandValidation();
            } else {
                parseCommand(description, chat);
            }

            printLine();
            System.out.print(PROMPT);
        }
    }
}