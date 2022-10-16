package logic.commands;

import common.exceptions.*;
import model.Chat;

public abstract class Command {
    Chat chat;

    public Command(Chat chat) {
        this.chat = chat;
    }

    public abstract void execute() throws EmptyTaskListException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException, UnmarkedTaskException;
}