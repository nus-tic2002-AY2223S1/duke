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
 * Represents the skeleton of the Duke chatbot
 */
public class Duke {
	private Storage storage;
	private TaskList tasks;
	private Ui ui;
	private Parser parser;
	Scanner s;

	public Duke(String filePath) {
		try {
			storage = new Storage(filePath);
			ArrayList<Task> hardDiskTaskList = storage.load();
			tasks = new TaskList(hardDiskTaskList);
		} catch (IOException ioe) {
			System.out.println(FILE_WRITER_IOEXCEPTION_ERROR_MESSAGE);
		}
	}

	public void run() {
		ui = new Ui();
		parser = new Parser();
		s = new Scanner(System.in);
		ui.startProgram();
		boolean terminateProgram = false;
		String command;
		do {
			String userInput = ui.getUserInput(s);
			command = parser.parse(userInput);
			terminateProgram = tasks.processTasks(command, userInput);
		} while (terminateProgram != true);
		storage.saveTasks(tasks);
		ui.exit();
	}

	public static void main(String[] args) {
		new Duke("/Users/rebecca/Desktop/Duke/data/DukeTasks.txt").run();
	}

}
