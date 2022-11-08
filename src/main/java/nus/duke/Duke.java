package nus.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nus.duke.frontend.*;
import nus.duke.parser.*;
import nus.duke.storage.*;
import nus.duke.tasklist.*;
import nus.duke.task.*;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * The Duke program is a virtual personal assistant that keeps track of a user's tasks.
 * Users interact with the Duke program by chatting with Dobby, a chatbot named after an elf in Harry Potter.
 * Dobby is assisted by his friend Pooh Bear.
 *
 * @author ANG Poh Ye
 * @version 1.0
 * @since Oct 2022
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    Scanner s;

    public Duke(String filePath) {
        boolean isEmpty = filePath.isEmpty();
        assert (isEmpty == false);
        try {
            storage = new Storage(filePath);
            ArrayList<Task> hardDiskTaskList = storage.load();
            tasks = new TaskList(hardDiskTaskList);
        } catch (IOException ioe) {
            System.out.println(FILE_WRITER_IOEXCEPTION_ERROR_MESSAGE);
        }
    }

    /**
     * Starts the Duke program and keeps the program running until it is terminated.
     *
     * @return nothing. This is a void function.
     */
    public void run() {
        ui = new Ui();
        parser = new Parser();
        s = new Scanner(System.in);
        ui.startProgram();
        boolean terminateProgram = false;
        boolean noInputErrors = false;
        String command;
        do {
            String userInput = ui.getUserInput(s);
            noInputErrors = parser.parse(userInput);
            if (noInputErrors) {
                command = parser.getCommand(userInput);
                terminateProgram = tasks.processTasks(command, userInput);
            }
        } while (terminateProgram != true);
        storage.saveTasks(tasks);
        ui.exit();
    }

    /**
     * Entry point to the Duke program.
     * The filepath was intentionally fixed for ease of use i.e. just run the program.
     */
    public static void main(String[] args) {
        new Duke("/Users/rebecca/Desktop/Duke/data/DukeTasks.txt").run();
    }
}
