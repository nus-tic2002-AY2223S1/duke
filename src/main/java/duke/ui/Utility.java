package duke.ui;

public class Utility {
    public void startup() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("System booting up...\n" + logo);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void help() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("List of commands");
        System.out.println("  - help                  | open the help page");
        System.out.println("  - syntax <keyword>      | display an example of a correct keyword syntax");
        System.out.println("  - list                  | list all task stored");
        System.out.println("  - bye                   | ends the application");
        System.out.println("  - delete <task No.>     | delete a specific task by task number");
        System.out.println("  - mark   <task No.>     | mark a specific task by task number");
        System.out.println("  - unmark <task No.>     | unmark a specific task by task number");
        System.out.println();
        System.out.println("List of keywords and syntax");
        System.out.println("  - todo                  | <keyword> <description>");
        System.out.println("  - deadline              | <keyword> <description> /by <yyyy-mm-dd hh:mm:ss>");
        System.out.println("  - event                 | <keyword> <description> /at <yyyy-mm-dd hh:mm:ss>");
        System.out.println("  - find                  | <keyword> <word>");
        System.out.println();
        System.out.println("List of keywords alias and syntax");
        System.out.println("  - /t                    | <keyword alias> <description>");
        System.out.println("  - /d                    | <keyword alias> /by <yyyy-mm-dd hh:mm:ss>");
        System.out.println("  - /e                    | <keyword alias> /at <yyyy-mm-dd hh:mm:ss>");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public void invalidDescription(String keyword) {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t The description of the " + keyword +  " keyword cannot be empty. Kindly re-enter the command.");
        System.out.println("\t Use the 'help' command to open the help page to read instructions");
        System.out.println("\t Use the 'syntax <keyword>' command to view examples of valid keyword syntax, e.g syntax deadline");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void invalidSearchTermWord(String keyword) {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t The search term word of the " + keyword +  " keyword cannot be empty.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void invalidCommand() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Invalid command or keyword syntax. Kindly re-enter the command.");
        System.out.println("\t Use the 'help' command to open the help page to read instructions");
        System.out.println("\t Use the 'syntax <keyword>' command to view examples of valid keyword syntax, e.g syntax deadline");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void syntaxHelp(String task) {
        if (task.equalsIgnoreCase("todo")) {
            System.out.println("\t-----------------------------------------------------------------");
            System.out.println("\t Syntax  : <keyword> <description>");
            System.out.println("\t Example : todo buy bread on the way home");
            System.out.println("\t Syntax  : <keyword alias> <description>");
            System.out.println("\t Example : /t buy bread on the way home");
            System.out.println("\t-----------------------------------------------------------------");
        }
        if (task.equalsIgnoreCase("deadline")) {
            System.out.println("\t-----------------------------------------------------------------");
            System.out.println("\t Syntax  : <keyword> <description> /by <yyyy-mm-dd hh:mm:ss>");
            System.out.println("\t Example : deadline complete homework /by 2022-10-19 23:59:59");
            System.out.println("\t Syntax  : <keyword alias> <description> /by <yyyy-mm-dd hh:mm:ss>");
            System.out.println("\t Example : /d complete homework /by 2022-10-19 23:59:59");
            System.out.println("\t-----------------------------------------------------------------");
        }
        if (task.equalsIgnoreCase("event")) {
            System.out.println("\t-----------------------------------------------------------------");
            System.out.println("\t Syntax  : <keyword> <description> /at <yyyy-mm-dd hh:mm:ss>");
            System.out.println("\t Example : event attend wedding /at 2022-10-20 19:00:00");
            System.out.println("\t Syntax  : <keyword alias> <description> /at <yyyy-mm-dd hh:mm:ss>");
            System.out.println("\t Example : /e attend wedding /at 2022-10-20 19:00:00");
            System.out.println("\t-----------------------------------------------------------------");
        }
        if (task.equalsIgnoreCase("find")) {
            System.out.println("\t-----------------------------------------------------------------");
            System.out.println("\t Syntax  : <keyword> <search_term>");
            System.out.println("\t Example : find book");
            System.out.println("\t-----------------------------------------------------------------");
        }
    }

    public void deadlineDateNotPresent() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Date not present or missing '/by' command, please re-enter deadline task with the date and time in the following format:");
        System.out.println("\t Syntax  : <keyword> <description> /by <yyyy-mm-dd hh:mm:ss>");
        System.out.println("\t Example : deadline complete homework /by 2022-10-19 23:59:59");
        System.out.println("\t Example : /d complete homework /by 2022-10-19 23:59:59");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void deadlineTimeNotPresent() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Time not present, please re-enter deadline task with the date and time in the following format:");
        System.out.println("\t Syntax  : <keyword> <description> /by <yyyy-mm-dd hh:mm:ss>");
        System.out.println("\t Example : deadline complete homework /by 2022-10-19 23:59:59");
        System.out.println("\t Example : /d complete homework /by 2022-10-19 23:59:59");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void eventDateNotPresent() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Date and time not present or missing '/at' command, please re-enter event task with the date and time in the following format:");
        System.out.println("\t Syntax  : <keyword alias> /at <yyyy-mm-dd hh:mm:ss>");
        System.out.println("\t Example : event attend wedding /at 2022-10-20 19:00:00");
        System.out.println("\t Example : /e attend wedding /at 2022-10-20 19:00:00");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void eventTimeNotPresent() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Time not present, please re-enter event task with the date and time in the following format:");
        System.out.println("\t Syntax  : <keyword alias> /at <yyyy-mm-dd hh:mm:ss>");
        System.out.println("\t Example : event attend wedding /at 2022-10-20 19:00:00");
        System.out.println("\t Example : /e attend wedding /at 2022-10-20 19:00:00");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void invalidDeadlineDateTime() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Invalid date or time, please re-enter deadline task with the date and time in the following format:");
        System.out.println("\t Syntax  : <keyword> <description> /by <yyyy-mm-dd hh:mm:ss>");
        System.out.println("\t Example : deadline complete homework /by 2022-10-19 23:59:59");
        System.out.println("\t Example : /d complete homework /by 2022-10-19 23:59:59");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void invalidEventDateTime() {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Invalid date or time, please re-enter event task with the date and time in the following format:");
        System.out.println("\t Syntax  : <keyword alias> /at <yyyy-mm-dd hh:mm:ss>");
        System.out.println("\t Example : event attend wedding /at 2022-10-20 19:00:00");
        System.out.println("\t Example : /e attend wedding /at 2022-10-20 19:00:00");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void indexOutOfBound(Integer upperbound) {
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Oops! Invalid task number,");
        System.out.println("\t please enter a valid task number from 1 to " + upperbound + ".");
        System.out.println("\t-----------------------------------------------------------------");
    }

}
