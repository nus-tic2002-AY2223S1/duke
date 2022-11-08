package model;

import common.enums.CommandTypeEnum;
import common.enums.PeriodicalEnum;

import static common.constants.CommonConstant.DASH;
import static common.constants.CommonConstant.CLOSE_SQ_BRACKET;
import static common.constants.CommonConstant.OPEN_SQ_BRACKET;
import static common.constants.CommonConstant.SPACE;
import static common.constants.CommonConstant.MARKED_ICON;
import static common.enums.CommandTypeEnum.UNDEFINED;
import static common.enums.PeriodicalEnum.undefined;
import static common.utils.DateUtil.dateTimeConverter;

public class Task {
    protected String description;

    protected boolean isDone;

    protected String time;

    protected PeriodicalEnum periodical;

    public Task(PeriodicalEnum periodical, String description) {
        this.periodical = periodical;
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = dateTimeConverter(time);
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

    public PeriodicalEnum getPeriodical() {
        return periodical;
    }

    @Override
    public String toString() {
        String periodicalValue = ((periodical != undefined) ? (SPACE + periodical + SPACE + DASH + SPACE) : SPACE);
        return OPEN_SQ_BRACKET + getStatusIcon() + CLOSE_SQ_BRACKET +
                periodicalValue + description;
    }
}
