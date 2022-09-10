package logic.commands;

import model.Chat;

public abstract class Command {
    Chat chat;

    public Command(Chat chat) {
        this.chat = chat;
    }

    public abstract void execute();
}