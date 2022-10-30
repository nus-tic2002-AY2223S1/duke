package nus.duke.task;

import nus.duke.enumerations.LegalTaskEnumerations;

import static nus.duke.frontend.CommonPrintStatements.*;

public class Todo extends Task {

	public Todo(String userInput) {
		super(userInput);
	}

	@Override
	public String getTaskType() {
		return LegalTaskEnumerations.T.toString();
	}

	@Override
	public String getTaskDetails() {
		return EMPTY_STRING;
	}

	@Override
	public String getDescription(String userInput) {
		return EMPTY_STRING;
	}

	@Override
	public String getDateInStr(String userInput) {
		return EMPTY_STRING;
	}
}
