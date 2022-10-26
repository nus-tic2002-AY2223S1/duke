package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import nus.duke.enumerations.*;

import static nus.duke.frontend.CommonPrintStatements.*;

public class Deadline extends Task {
    protected LocalDate localDate;

    public Deadline(String userInput) {
        super(userInput);
        String description = this.getDescription(userInput);
        super.setDescription(description);
        this.localDate = processDate(userInput);
    }

    public LocalDate getDate(){
        return this.localDate;
    }

    @Override
    public String getTaskType(){
        return LegalTaskEnumerations.D.toString();
    }


    @Override
    public String getTaskDetails() {
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = "(by: " + date + ")";
        return date;
    }

    @Override
    public String getDescription(String userInput) {
        int end = userInput.indexOf(BY);
        return userInput.substring(0, end);
    }

    @Override
    public String getDateInStr(String userInput) {
        int start = userInput.indexOf(BY) + 4;
        if (userInput.indexOf(MARKED_AS_TRUE) == -1 || userInput.indexOf(MARKED_AS_FALSE) == -1) {
            return userInput.substring(start, userInput.length()).trim();
        } else {
            return userInput.substring(start, userInput.length()-3).trim();
        }
    }
}