import Command.*;

import exception.CommandInvalidException;
import exception.LackDetailsException;
import exception.UnknownException;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String markUnmarkPattern = "(mark|unmark)\\s*(\\d+)";
    private String todoPattern = "(todo) ([\\S\\s]*)";
    private String deadlinePattern = "(deadline) ([\\S\\s]*)(\\/by) ([\\S\\s]*)";
    private String eventPattern = "(event) ([\\S\\s]*)(\\/at )(\\d{4}-\\d{2}-\\d{2}) ([\\S\\s]*)";
    private String deletePattern = "(delete)\\s*(\\d+)";


    public Command parse(String input){
        String[] arrayOfInput = input.trim().split(" ",2);
        return parseCommand(arrayOfInput[0].toLowerCase(),input);
    }

    private Command parseCommand(String commandName,String fullInput) {
        if (commandName.equalsIgnoreCase("bye")) {
            return new byebyeCommand();
        }

        switch (commandName) {
            case "list":
                return new listCommand();

            case "mark":
                Pattern r = Pattern.compile(markUnmarkPattern);
                Matcher m = r.matcher(fullInput);
                if (m.find()) {
                    int inputTaskNumber = Integer.parseInt(m.group(2));
                    return new markCommand(inputTaskNumber);
                    } else {
                        throw new CommandInvalidException("Please key in a valid task number");
                    }

            case "unmark":
                r = Pattern.compile(markUnmarkPattern);
                m = r.matcher(fullInput);
                if (m.find()) {
                    int taskNumber = Integer.parseInt(m.group(2));
                    return new unmarkCommand(taskNumber);
                } else {
                    throw new CommandInvalidException("Please key in a valid task number");
                }


            case "todo":
                r = Pattern.compile(todoPattern);
                m = r.matcher(fullInput);
                if (m.find()) {
                    return new todoCommand(m.group(2));
                }else{
                    throw new LackDetailsException(fullInput);
                }

            case "deadline":
                r = Pattern.compile(deadlinePattern);
                m = r.matcher(fullInput);
                if (m.find()) {
                    String descrp = m.group(2);
                    String by = m.group(4);
                    return new deadlineCommand(descrp, LocalDate.parse(by));
                }
                throw new LackDetailsException(fullInput);

            case "event":
                r = Pattern.compile(eventPattern);
                m = r.matcher(fullInput);
                if (m.find()) {
                    String descrp = m.group(2);
                    String dateString = m.group(4);
                    String at = m.group(5);
                    return new eventCommand(descrp,LocalDate.parse(dateString),at);
                }
                throw new LackDetailsException(fullInput);

            case "delete":
                r = Pattern.compile(deletePattern);
                m = r.matcher(fullInput);
                if (m.find()) {
                    return new deleteTaskCommand(Integer.valueOf(m.group(2)));
                }
                throw new CommandInvalidException("Please key in a valid task number");

            case "help":
                return new helpCommand();
//              throw new UnknownException("I dont understand that, if you need help");

            default:
                throw new CommandInvalidException("Invalid Command! Please enter again, you may key \"help\" to see a command example");
        }

    }
}
