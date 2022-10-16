package logic.parsers;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import common.exceptions.DuplicatedTaskException;
import logic.commands.Command;
import logic.commands.AddDeadlineCommand;
import logic.commands.AddEventCommand;
import logic.commands.AddTodoCommand;
import logic.commands.DeleteCommand;
import logic.commands.HelpCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.UnmarkCommand;
import model.Chat;

import static common.utils.PrintUtil.printBye;

public class Parser {
    public Parser() {}
    /**
     * parseChat returns parse chat
     *
     * @throws EmptyTaskListException
     * @throws InvalidTaskDescriptionException
     * @throws NotExistTaskException
     * @throws MarkedTaskException
     * @throws UnmarkedTaskException
     * @throws DuplicatedTaskException
     * @return {void}
     */
    public static void parseChat(Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException, UnmarkedTaskException, DuplicatedTaskException {
        Command addDeadlineCom = new AddDeadlineCommand(chat);
        Command addEventCom = new AddEventCommand(chat);
        Command addTodoCom = new AddTodoCommand(chat);
        Command deleteCom = new DeleteCommand(chat);
        Command helpCom = new HelpCommand(chat);
        Command listCom = new ListCommand(chat);
        Command markCom = new MarkCommand(chat);
        Command unmarkCom = new UnmarkCommand(chat);

        switch (chat.getCommand()) {
            case bye:
                printBye();
                System.exit(0);
            case list:
                listCom.execute();
                break;
            case mark:
                markCom.execute();
                break;
            case unmark:
                unmarkCom.execute();
                break;
            case delete:
                deleteCom.execute();
                break;
            case help:
                helpCom.execute();
                break;
            case deadline:
                addDeadlineCom.execute();
                break;
            case event:
                addEventCom.execute();
                break;
            case todo:
                addTodoCom.execute();
                break;
            default:
                break;
        }
    }
}