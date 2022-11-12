package duke.ui;

import duke.repository.Repository;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulate the properties, constructors and methods required to run the program.
 * Contains the program logic.
 * Contains the parser code to parse user inputs.
 */
public class Parser {
    private final Repository parserRepo;
    private final TaskList parserTaskList = new TaskList();
    private final Utility parserUtility = new Utility();

    public Parser(Repository repo) {
        this.parserRepo = repo;
    }

    /**
     * Encapsulates the program logic and parsing of user input.
     * @throws IOException If an I/O error occurred.
     */
    public void start() throws IOException {
        parserRepo.loadDirectory();
        parserRepo.loadFile();
        parserTaskList.existingTaskList(parserRepo.readFile());
        parserUtility.help();
        parserUtility.startup();
        String inputLine;
        String commandLine;
        Scanner input = new Scanner(System.in);
        while (true) {
            inputLine = input.nextLine();
            String[] lineArray = inputLine.split(" ");
            commandLine = lineArray[0];

            if (inputLine.equalsIgnoreCase("bye")) {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------------------------");
                break;
            } else if (commandLine.equalsIgnoreCase("delete")) {
                parserTaskList.deleteTask(lineArray);
                parserTaskList.saveTaskList(parserRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("mark")) {
                parserTaskList.markTask(lineArray);
                parserTaskList.saveTaskList(parserRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("unmark")) {
               parserTaskList.unmarkTask(lineArray);
                parserTaskList.saveTaskList(parserRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("list")) {
                parserTaskList.listTask();
            } else if (commandLine.equalsIgnoreCase("help")) {
                parserUtility.help();
            } else if (commandLine.equalsIgnoreCase("syntax")) {
                if (lineArray.length == 2) {
                    if (lineArray[1].equalsIgnoreCase("todo") || lineArray[1].equalsIgnoreCase("deadline") || lineArray[1].equalsIgnoreCase("event") || lineArray[1].equalsIgnoreCase("find")) {
                        parserUtility.syntaxHelp(lineArray[1]);
                    } else {
                        parserUtility.invalidCommand();
                    }
                } else {
                    parserUtility.invalidCommand();
                }
            } else if (commandLine.equalsIgnoreCase("todo") || commandLine.equalsIgnoreCase("/t")) {
                if (lineArray.length < 2) {
                    parserUtility.invalidDescription(commandLine);
                    continue;
                }
                parserTaskList.addToDo(lineArray);
                parserTaskList.saveTaskList(parserRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("deadline") || commandLine.equalsIgnoreCase("/d")) {
                if (lineArray.length < 2) {
                    parserUtility.invalidDescription(commandLine);
                    continue;
                }
                parserTaskList.addDeadLine(lineArray);
                parserTaskList.saveTaskList(parserRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("event") || commandLine.equalsIgnoreCase("/e")) {
                if (lineArray.length < 2) {
                    parserUtility.invalidDescription(commandLine);
                    continue;
                }
                parserTaskList.addEvent(lineArray);
                parserTaskList.saveTaskList(parserRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("find")) {
                if (lineArray.length < 2) {
                    parserUtility.invalidSearchTermWord(commandLine);
                    continue;
                }
                if (lineArray.length > 2) {
                    parserUtility.invalidCommand();
                    continue;
                }
                parserTaskList.findTask(lineArray[1]);
            } else {
                parserUtility.invalidCommand();
            }
        }
        input.close();
    }
}
