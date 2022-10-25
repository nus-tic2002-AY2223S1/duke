package nus.duke.parser;

import nus.duke.frontend.*;
import nus.duke.tasklist.*;
import nus.duke.exceptions.*;
import java.util.Scanner;
import java.util.regex.Matcher; // check date using regex
import java.util.regex.Pattern; // check date using regex

public class Parser {
    private static Ui ui;

    public static String getCommand(String userInput){
        if (userInput.length() == 4){
            return userInput.substring(0, 4);
        } else {
            int idx = userInput.indexOf(" ");
            String command = userInput.substring(0, idx);
            return command;
        }
    }

    public static boolean isValidCommand(String command){
        if ((command.equals(LegalCommandEnumerations.MARK.toString())) || (command.equals(LegalCommandEnumerations.UNMARK.toString())) ||
            (command.equals(LegalCommandEnumerations.DELETE.toString())) || (command.equals(LegalCommandEnumerations.VIEW.toString())) || (command.equals(LegalCommandEnumerations.REMINDERS.toString())) ||
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

    public static boolean hasDate(String userInput){
        int start = userInput.indexOf("/by") + 3;
        int end = userInput.indexOf("[");
        String dateInString = userInput.substring(start, userInput.length());

        if ((end == -1) && dateInString.isBlank()){
            return false;
        } else if ((end == -1) && dateInString.isBlank() == false){
            dateInString = userInput.substring(start + 1, userInput.length());
        } else if (end != -1){
            dateInString = userInput.substring(start, end);
        }

        //String regex = "^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$";
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(dateInString);
        return matcher.matches();
    }

    public static boolean hasInputErrors(String userInput) throws MissingKeywordException, EmptyTaskException, InvalidCommandException, MissingDateException {
        if (userInput.indexOf(" ") == -1){
            if (userInput.equals("VIEW") || userInput.equals("EXIT") || userInput.trim().equals("REMINDERS")){
                return false;
            } else if (userInput.equals("TODO") || userInput.equals("DEADLINE") || userInput.equals("EVENT") || userInput.equals("MARK") || userInput.equals("UNMARK")){
                throw new EmptyTaskException();
            } else {
                throw new InvalidCommandException();
            }
        }

        String command = getCommand(userInput);
        if (isValidCommand(command) == false){
            throw new InvalidCommandException();
        }

        if (isValidCommand(command) == false && isEmptyTask(userInput) == true){
            throw new InvalidCommandException();
        }

        if ((isValidCommand(command) == true) && (isEmptyTask(userInput) == true)){
            throw new EmptyTaskException();
        }

        if (command.equals("DEADLINE") && (userInput.contains("/by") == false)){
            throw new MissingKeywordException();
        }

        if (command.equals("EVENT") && (userInput.contains("/at") == false)){
            throw new MissingKeywordException();
        }

        if (command.equals("DEADLINE") && (userInput.contains("/by") == true) && hasDate(userInput) == false){
            throw new MissingDateException();
        }
        return false;
    }

    public static String parse(String userInput) {
        boolean hasError;
        Scanner s;

        try {
            hasError = hasInputErrors(userInput);
        } catch (EmptyTaskException ete){
            System.out.println("OOPS!!! Task cannot be empty.");
            hasError = true;
        } catch (InvalidCommandException ice){
            System.out.println("OOPS!!! This is not a valid command. Please see command menu.");
            ui.printCommandMenu();
            hasError = true;
        } catch (MissingKeywordException mke){
            System.out.println("OOPS!!! Please indicate //by <<date>> for deadlines and //at <<venue>> for events");
            hasError = true;
        } catch (MissingDateException mde){
            System.out.println("OOPS!!! Date needed. Please also key in the correct format: dd/mm/yyyy i.e dd-mm-yyyy is wrong");
            hasError = true;
        }

        if (hasError){
            Ui ui = new Ui();
            s = new Scanner(System.in);
            System.out.println("Please proceed to key in the correct command");
            String newUserInput = ui.getUserInput(s);
            return newUserInput;
        } else {
            return getCommand(userInput);
        }
    }
}
