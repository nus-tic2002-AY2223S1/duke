package logic.commands;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import common.exceptions.DuplicatedTaskException;
import model.Chat;
import ui.ConsoleUi;

public abstract class Command {
    ConsoleUi ui;
    Chat chat;

    public Command(ConsoleUi ui, Chat chat) {
        this.ui = ui;
        this.chat = chat;
    }

    public abstract void execute() throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException, UnmarkedTaskException, DuplicatedTaskException;
}