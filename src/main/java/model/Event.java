package model;

import static common.constants.SymbolConstant.CLOSE_BRACKET;
import static common.constants.SymbolConstant.CLOSE_SQ_BRACKET;
import static common.constants.SymbolConstant.OPEN_BRACKET;
import static common.constants.SymbolConstant.OPEN_SQ_BRACKET;
import static common.constants.CommonConstant.EVENT_AT;
import static common.constants.CommonConstant.SPACE;
import static common.constants.ModelConstant.EVENT_ICON;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + EVENT_ICON + CLOSE_SQ_BRACKET + super.toString() +
                SPACE + OPEN_BRACKET + EVENT_AT + at + CLOSE_BRACKET;
    }
}
