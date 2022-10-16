package logic.parsers;

import common.exceptions.*;
import logic.commands.*;
import model.Chat;

import static common.constants.CommandConstant.*;
import static common.utils.PrintUtil.printBye;

public class Parser {
    public Parser() {}
    /**
     * parseCommand returns parse command
     *
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @throws MarkedTaskException
     * @throws UnmarkedTaskException
     * @param {String} input
     * @param {Chat} chat
     * @return {void}
     */
    public static void parseCommand(Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException, UnmarkedTaskException {
        Command addCom = new AddCommand(chat);
        Command deleteCom = new DeleteCommand(chat);
        Command helpCom = new HelpCommand(chat);
        Command listCom = new ListCommand(chat);
        Command markCom = new MarkCommand(chat);
        Command unmarkCom = new UnmarkCommand(chat);

        switch (chat.getCommand()) {
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
            case DELETE_COMMAND:
                deleteCom.execute();
                break;
            case HELP_COMMAND:
                helpCom.execute();
                break;
            default:
                addCom.execute();
        }
    }

    /**
     * parseChat returns parse chat
     *
     * @throws EmptyTaskListException
     * @throws InvalidTaskCommandException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @throws MarkedTaskException
     * @throws UnmarkedTaskException
     * @return {void}
     */
    public static void parseChat(Chat chat) throws EmptyTaskListException, InvalidTaskCommandException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException, UnmarkedTaskException {
        String command = chat.getCommand();

        if (!command.equals(BYE_COMMAND) &&
                !command.equals(DELETE_COMMAND) &&
                !command.equals(HELP_COMMAND) &&
                !command.equals(LIST_COMMAND) &&
                !command.equals(MARK_COMMAND) &&
                !command.equals(UNMARK_COMMAND) &&
                !command.equals(ADD_TODO_COMMAND) &&
                !command.equals(ADD_EVENT_COMMAND) &&
                !command.equals(ADD_DEADLINE_COMMAND)) {
            throw new InvalidTaskCommandException();
        } else {
            parseCommand(chat);
        }
    }
}