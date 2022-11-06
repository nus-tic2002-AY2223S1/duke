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
import logic.commands.ByeCommand;
import logic.commands.DeleteCommand;
import logic.commands.FindCommand;
import logic.commands.HelpCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.UnmarkCommand;
import logic.commands.UpdateCommand;
import model.Chat;
import ui.ConsoleUi;

public class Parser {
    public Parser() {}
    /**
     * Return parse chat
     *
     * @param   ui
     * @param   chat
     * @throws  EmptyTaskListException
     * @throws  InvalidTaskDescriptionException
     * @throws  NotExistTaskException
     * @throws  MarkedTaskException
     * @throws  UnmarkedTaskException
     * @throws  DuplicatedTaskException
     */
    public static void parseChat(ConsoleUi ui, Chat chat) throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException, UnmarkedTaskException, DuplicatedTaskException {
        Command addDeadlineCom = new AddDeadlineCommand(ui, chat);
        Command addEventCom = new AddEventCommand(ui, chat);
        Command addTodoCom = new AddTodoCommand(ui, chat);
        Command byeCom = new ByeCommand(ui, chat);
        Command deleteCom = new DeleteCommand(ui, chat);
        Command findCom = new FindCommand(ui, chat);
        Command helpCom = new HelpCommand(ui, chat);
        Command listCom = new ListCommand(ui, chat);
        Command markCom = new MarkCommand(ui, chat);
        Command unmarkCom = new UnmarkCommand(ui, chat);
        Command updateCom = new UpdateCommand(ui, chat);

        switch (chat.getCommand()) {
            case bye:
                byeCom.execute();
                break;
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
            case find:
                findCom.execute();
                break;
            default:
                updateCom.execute();
        }
    }
}