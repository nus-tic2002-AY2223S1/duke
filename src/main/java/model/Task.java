package model;

import common.enums.CommandTypeEnum;

import static common.constants.CommonConstant.CLOSE_SQ_BRACKET;
import static common.constants.CommonConstant.OPEN_SQ_BRACKET;
import static common.constants.CommonConstant.SPACE;
import static common.constants.CommonConstant.MARKED_ICON;
import static common.enums.CommandTypeEnum.UNDEFINED;
import static common.utils.DateUtil.dateConverter;

public abstract class Task {
    protected String description;

    protected boolean isDone;

    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = dateConverter(time);
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

    public CommandTypeEnum getType() {
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
