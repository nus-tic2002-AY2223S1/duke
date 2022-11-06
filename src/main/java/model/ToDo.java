package model;

import common.enums.TypeEnum;

import static common.constants.CommonConstant.CLOSE_SQ_BRACKET;
import static common.constants.CommonConstant.OPEN_SQ_BRACKET;
import static common.enums.TypeEnum.T;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public TypeEnum getType() {
        return T;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + T + CLOSE_SQ_BRACKET + super.toString();
    }
}
