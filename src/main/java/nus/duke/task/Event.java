package nus.duke.task;

import nus.duke.enumerations.LegalTaskEnumerations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static nus.duke.frontend.CommonPrintStatements.*;

public class Event extends Task {
    protected String at; // /at a specific place
    protected LocalDate localDate; // /on a specific date

    public Event(String userInput) {
        super(userInput);
        String description = this.getDescription(userInput);
        super.setDescription(description);
        this.at = getVenue(userInput);
        this.localDate = this.processDate(userInput);
    }

    @Override
    public String getTaskType(){
        return LegalTaskEnumerations.E.toString();
    }

    @Override
    public String getTaskDetails() {
        String venue = "(Venue: " + this.at + ")";
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = "(Time: " + date + ")";
        return venue + date;
    }

    @Override
    public String getDescription(String userInput) {
        int end = userInput.indexOf(AT);
        return userInput.substring(0, end);
    }

    @Override
    public String getDateInStr(String userInput) {
        int start = userInput.indexOf(ON) + 4;
        if (userInput.indexOf(MARKED_AS_TRUE) == -1 || userInput.indexOf(MARKED_AS_FALSE) == -1) {
            return userInput.substring(start, userInput.length()).trim();
        } else {
            return userInput.substring(start, userInput.length()-3).trim();
        }
    }

    public String getVenue(String userInput) {
        int start = userInput.indexOf(AT) + 4;
        int end = userInput.indexOf(ON);
        return userInput.substring(start, end).trim();
    }
}
