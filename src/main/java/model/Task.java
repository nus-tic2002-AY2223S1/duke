package model;

import static common.constant.CommonConstant.MARKED_ICON;
import static common.constant.CommonConstant.SPACE;
import static common.constant.CommonConstant.CLOSE_SQ_BRACKET;
import static common.constant.CommonConstant.OPEN_SQ_BRACKET;

public class Task {
    protected String description;
    protected boolean isDone;

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

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + getStatusIcon() + CLOSE_SQ_BRACKET + description;
    }
}
