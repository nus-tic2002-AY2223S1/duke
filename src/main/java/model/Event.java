package model;

import common.enums.CommandTypeEnum;

import static common.constants.CommonConstant.CLOSE_BRACKET;
import static common.constants.CommonConstant.CLOSE_SQ_BRACKET;
import static common.constants.CommonConstant.OPEN_BRACKET;
import static common.constants.CommonConstant.OPEN_SQ_BRACKET;
import static common.constants.CommonConstant.EVENT_AT;
import static common.constants.CommonConstant.SPACE;
import static common.enums.CommandTypeEnum.E;
import static common.utils.DateUtil.dateTimeConverter;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = dateTimeConverter(at);
    }

    @Override
    public void setTime(String at) {
         this.at = dateTimeConverter(at);
    }

    @Override
    public CommandTypeEnum getType() {
        return E;
    }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + E + CLOSE_SQ_BRACKET + super.toString() +
                SPACE + OPEN_BRACKET + EVENT_AT + at + CLOSE_BRACKET;
    }
}
