package nus.duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate localDate;
    public Deadline(String userInput) {
        super(userInput);
        String description = super.getDescription(userInput);
        super.setDescription(description);
        this.localDate = processDate(userInput);
    }
    @Override
    public String getTaskType(){
        return "D";
    }
}