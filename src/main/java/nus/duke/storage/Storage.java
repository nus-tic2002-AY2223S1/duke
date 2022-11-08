package nus.duke.storage;

import nus.duke.frontend.Ui;
import nus.duke.task.*;
import nus.duke.tasklist.*;
import nus.duke.parser.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;  // Needed to handle IOException thrown by FileWriter
import java.io.BufferedWriter;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a storage system.
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
	private static String filePath;
	private static TaskList hardDiskTaskList;
	private static Parser parser;
	private static Ui ui;

	/**
	 * Constructor.
	 *
	 * @return nothing. This is a constructor.
	 */
	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Returns the filepath that was stored.
	 *
	 * @return The filepath that was stored.
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Returns true if a file (i.e. the hard disk) has been created.
	 *
	 * @param filePath The filepath where the file will be created.
	 * @return true (i.e. file has been created) or false (i.e. file not created)
	 * @throws IOException when filewriter catches an exception
	 */
	public static boolean createHardDiskFile(String filePath) {
		File f = new File(filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println(FILE_WRITER_IOEXCEPTION_ERROR_MESSAGE);
		}
		return true;
	}

	/**
	 * Loads data from the hard disk.
	 *
	 * @return an ArrayList of Task objects which will be copied to the task list when a task list is constructed.
	 * @throws IOException when filewriter catches an exception
	 */
	public ArrayList<Task> load() throws IOException {
		hardDiskTaskList = new TaskList();
		try {
			String filePath = getFilePath();
			java.nio.file.Path path = java.nio.file.Paths.get(filePath);
			boolean directoryExists = java.nio.file.Files.exists(path);
			if (directoryExists) {
				ui.showHardDiskLoadingMessage();
				File file = new File(filePath);
				Scanner s = new Scanner(file);
				String line;
				String command = EMPTY_STRING;
				for (int count = 1; s.hasNext(); count++) {
					line = s.nextLine();
					if (parser.parse(line)){
						command = parser.getCommand(line);
					}
					hardDiskTaskList.processTasks(command, line);
					hardDiskTaskList.processIsDone(count, line);
				}
				ui.showHardDiskLoadedMessage();
			} else {
				boolean successfullyCreated = createHardDiskFile(filePath);
				assert(successfullyCreated == true);
				ui.showHardDiskCreationMessage();
			}
		} catch (IOException ioe) {
			System.out.println(FILE_WRITER_IOEXCEPTION_ERROR_MESSAGE);
		}
		return hardDiskTaskList.getTaskList();
	}

	/**
	 * Saves task list into a file when a user wants to exit the Duke program.
	 *
	 * @param taskList The most recent task list that stored all tasks.
	 * @return nothing. This is a void function.
	 */
	public void saveTasks(TaskList taskList) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()));
			for (int i = 0; i < taskList.getTotalTasks(); i++) {
				String markedStatus = taskList.getTaskList().get(i).getIsDone();
				if (markedStatus.equals("true")) {
					markedStatus = MARKED_AS_TRUE;
				} else {
					markedStatus = MARKED_AS_FALSE;
				}
				String taskToBeSaved = taskList.getTaskList().get(i).getTask() + markedStatus + "\n";
				writer.write(taskToBeSaved);
			}
			writer.close();
			System.out.println(TASKS_ARE_SAVED_MESSAGE);
		} catch (IOException e) {
			System.out.println(FILE_WRITER_IOEXCEPTION_ERROR_MESSAGE);
		}
	}
}