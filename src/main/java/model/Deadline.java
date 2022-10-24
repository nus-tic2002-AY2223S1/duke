package model;

import common.enums.TypeEnum;

import static common.constants.SymbolConstant.CLOSE_BRACKET;
import static common.constants.SymbolConstant.CLOSE_SQ_BRACKET;
import static common.constants.SymbolConstant.OPEN_BRACKET;
import static common.constants.SymbolConstant.OPEN_SQ_BRACKET;
import static common.constants.CommonConstant.DEADLINE_BY;
import static common.constants.CommonConstant.SPACE;
import static common.enums.TypeEnum.D;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public TypeEnum getType() {
        return D;
    }

    @Override
    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + D + CLOSE_SQ_BRACKET + super.toString() +
                SPACE + OPEN_BRACKET + DEADLINE_BY + by + CLOSE_BRACKET;
    }
}