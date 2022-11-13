package model;

import common.enums.CommandTypeEnum;
import common.enums.PeriodicalEnum;

import static common.constants.CommonConstant.CLOSE_SQ_BRACKET;
import static common.constants.CommonConstant.OPEN_SQ_BRACKET;
import static common.enums.CommandTypeEnum.T;

public class ToDo extends Task {
    public ToDo(PeriodicalEnum periodical, String description) {
        super(periodical, description);
    }

    @Override
    public CommandTypeEnum getType() {
        return T;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + T + CLOSE_SQ_BRACKET + super.toString();
    }
}
