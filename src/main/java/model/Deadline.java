package model;

import static common.constant.SymbolConstant.CLOSE_BRACKET;
import static common.constant.SymbolConstant.CLOSE_SQ_BRACKET;
import static common.constant.SymbolConstant.OPEN_BRACKET;
import static common.constant.SymbolConstant.OPEN_SQ_BRACKET;
import static common.constant.CommonConstant.DEADLINE_BY;
import static common.constant.CommonConstant.SPACE;
import static common.constant.ModelConstant.DEADLINE_ICON;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + DEADLINE_ICON + CLOSE_SQ_BRACKET + super.toString() +
                SPACE + OPEN_BRACKET + DEADLINE_BY + by + CLOSE_BRACKET;
    }
}