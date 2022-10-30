package model;

import common.enums.TypeEnum;

import static common.constants.SymbolConstant.CLOSE_SQ_BRACKET;
import static common.constants.SymbolConstant.OPEN_SQ_BRACKET;
import static common.constants.CommonConstant.SPACE;
import static common.constants.CommonConstant.MARKED_ICON;
import static common.enums.TypeEnum.UNDEFINED;

public class Task {
    protected String description;
    protected boolean isDone;

    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? MARKED_ICON : SPACE);
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public TypeEnum getType() {
        return UNDEFINED;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + getStatusIcon() + CLOSE_SQ_BRACKET + SPACE + description;
    }
}
