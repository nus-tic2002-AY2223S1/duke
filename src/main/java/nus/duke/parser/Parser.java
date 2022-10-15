package nus.duke.parser;

import nus.duke.frontend.*;
import nus.duke.commands.*;
import nus.duke.exceptions.*;

public class Parser {
    private static Ui ui;
    private static Command c;

    public static String getCommand(String userInput){
        int idx = userInput.indexOf(" ");
        String cmd = userInput.substring(0, idx);
        return cmd;
    }

    public static String getTask(String userInput){
        int idx = userInput.indexOf(" ") + 1;
        String task = userInput.substring(idx, userInput.length());
        return task;
    }

    public static boolean isValidCommand(String command){
        if ((command.equals(LegalCommandEnumerations.MARK.toString())) || (command.equals(LegalCommandEnumerations.UNMARK.toString())) ||
            (command.equals(LegalCommandEnumerations.DELETE.toString())) || (command.equals(LegalCommandEnumerations.VIEW.toString())) ||
            (command.equals(LegalCommandEnumerations.EXIT.toString())) || (command.equals(LegalCommandEnumerations.TODO.toString())) ||
            (command.equals(LegalCommandEnumerations.DEADLINE.toString())) || (command.equals(LegalCommandEnumerations.EVENT.toString()))){
                return true;
        } else {
                return false;
        }

    }

    public static boolean isEmptyTask(String userInput){
        int idx = userInput.indexOf(" ");
        String str = userInput.substring(idx, userInput.length());
        if (str.isBlank()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasInputErrors(String userInput) throws WrongInputSyntaxException, EmptyTaskException {
        if (userInput.indexOf(" ") == -1){ // e.g. "TODO"
            throw new EmptyTaskException();
        } else {
            String command = getCommand(userInput); // e.g. "TODO buy lunch" --> "TODO" or "TODO     " --> "TODO"
            if (isValidCommand(command) == true) {
                if (isEmptyTask(userInput) == true) { // e.g. "TODO     " is an empty task with alot of blank space
                    throw new EmptyTaskException();
                } else {
                    return false;
                }
            } else {
                throw new WrongInputSyntaxException();
            }
        }
    }

    public static boolean parse(String userInput) throws WrongInputSyntaxException, EmptyTaskException {
        boolean hasInputErrors;
        try {
            hasInputErrors = hasInputErrors(userInput);
        } catch (EmptyTaskException ete){
            throw new EmptyTaskException();
        } catch (WrongInputSyntaxException wise){
            throw new WrongInputSyntaxException();
        }

        if (hasInputErrors == false) {
            String command = getCommand(userInput);
            String task = getTask(userInput);
            if (command.equals(LegalCommandEnumerations.MARK.toString())){
                c.markTask(task);
            } else if (command.equals(LegalCommandEnumerations.UNMARK.toString())){
                c.unmarkTask(task);
            } else if (command.equals(LegalCommandEnumerations.DELETE.toString())){
                c.deleteTask(task);
            } else if (command.equals(LegalCommandEnumerations.VIEW.toString())){
                c.viewTasks();
            } else if (command.equals(LegalCommandEnumerations.EXIT.toString())){
                ui.exit();
            } else {
                c.addTask(task);
            }
        }
        return false; // i.e. do not terminate program
    }

}
