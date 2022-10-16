package nus.duke.parser;

import nus.duke.frontend.*;
import nus.duke.tasklist.*;
import nus.duke.exceptions.*;

public class Parser {
    private static Ui ui;
    private static TaskList tasks;

    public static String getCommand(String userInput){
        int idx = userInput.indexOf(" ");
        String cmd = userInput.substring(0, idx);
        return cmd;
    }

    public static int getItemNumber(String userInput){
        int idx = userInput.indexOf(" ");
        String itemNumber = userInput.substring(idx+1, userInput.length());
        idx = Integer.parseInt(itemNumber);
        return idx;
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
        if (userInput.indexOf(" ") == -1){ // e.g. "TODO" or "VIEW" or "EXIT"
            if (userInput.equals("VIEW") || userInput.equals("EXIT")){
                return false;
            } else {
                throw new EmptyTaskException(); // E.G. "TODO"
            }
        } else {
            String command = getCommand(userInput); // e.g. "TODO buy lunch" --> "TODO" or "TODO     " --> "TODO"
            if (isValidCommand(command) == true) {
                if (isEmptyTask(userInput) == true) { // e.g. "TODO     " is an empty task with alot of blank space
                    throw new EmptyTaskException();
                } else {
                    if (command.equals("DEADLINE") && (userInput.contains("/by") == false)){
                        throw new WrongInputSyntaxException();
                    } else if (command.equals("EVENT") && (userInput.contains("/at") == false)){
                        throw new WrongInputSyntaxException();
                    } else {
                        return false;
                    }
                }
            } else {
                throw new WrongInputSyntaxException(); // user does not enter a valid command
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
            if (userInput.equals(LegalCommandEnumerations.VIEW.toString())){
                tasks.viewTasks();
            } else if (userInput.equals(LegalCommandEnumerations.EXIT.toString())) {
                ui.exit();
            } else {
                String command = getCommand(userInput);
                if (command.equals(LegalCommandEnumerations.MARK.toString())){
                    tasks.markTask(getItemNumber(userInput)); // e.g. "1. TODO buy lunch" --> 1
                } else if (command.equals(LegalCommandEnumerations.UNMARK.toString())){
                    tasks.unmarkTask(getItemNumber(userInput));
                } else if (command.equals(LegalCommandEnumerations.DELETE.toString())){
                    tasks.deleteTask(getItemNumber(userInput));
                } else {
                    tasks.addTask(userInput);
                }
            }
        }
        return false; // i.e. do not terminate program
    }

}
