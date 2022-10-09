package model;

import static common.constants.SymbolConstant.CLOSE_SQ_BRACKET;
import static common.constants.SymbolConstant.OPEN_SQ_BRACKET;
import static common.constants.ModelConstant.TODO_ICON;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return OPEN_SQ_BRACKET + TODO_ICON + CLOSE_SQ_BRACKET + super.toString();
    }
}
