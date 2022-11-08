package nus.duke.tasklist;

import nus.duke.enumerations.*;
import nus.duke.task.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents a task list.
 * This class has operations to add/delete/mark/unmark etc. tasks in the list.
 */
public class TaskList {
	protected static ArrayList<Task> taskList = new ArrayList<Task>();
	private static int totalTasks = 0;

	/**
	 * Constructor.
	 *
	 * @param loadedTaskList the task list that was saved in the hard disk.
	 * @return nothing.
	 */
	public TaskList(ArrayList<Task> loadedTaskList) {
		Collections.copy(loadedTaskList, this.taskList);
	}

	/**
	 * Constructor that does not take in any inputs.
	 *
	 * @return nothing.
	 */
	public TaskList() {
	}

	/**
	 * Returns the task list of the calling object.
	 *
	 * @return an ArrayList of Task objects i.e. ArrayList<Task>
	 */
	public ArrayList<Task> getTaskList() {
		return this.taskList;
	}

	/**
	 * Increments the total number of tasks each time a task is added to the task list.
	 *
	 * @return nothing. This is a void function.
	 */
	public void incrementTotalTasks() {
		this.totalTasks = this.totalTasks + 1;
	}

	/**
	 * Decrements the total number of tasks each time a task is deleted from the task list.
	 *
	 * @return nothing. This is a void function.
	 */
	public void decrementTotalTasks() {
		this.totalTasks = this.totalTasks - 1;
	}

	/**
	 * Returns the total number of tasks in the task list.
	 *
	 * @return Total number of tasks.
	 */
	public int getTotalTasks() {
		return this.totalTasks;
	}

	/**
	 * Returns the task number.
	 * For example, if the command is "MARK 2", the function returns "2".
	 *
	 * @param userInput The task keyed in by the user.
	 * @return Task number specified by the user.
	 */
	public static int getItemNumber(String userInput) {
		int idx = userInput.indexOf(SPACE);
		String itemNumber = userInput.substring(idx + 1, userInput.length());
		idx = Integer.parseInt(itemNumber);
		return idx;
	}

	/**
	 * Prints the task type, task icon, task description and task details such as date/venue.
	 *
	 * @param task A Task object.
	 * @return nothing. This is a void function.
	 */
	public static void printTask(Task task) {
		System.out.println("[" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getTask() + task.getTaskDetails());
	}

	/**
	 * Prints all the tasks in the task list.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void viewTaskList() {
		for (int i = 0; i < taskList.size(); i++) {
			int idx = i + 1;
			System.out.print(idx + ". ");
			printTask(taskList.get(i));
		}
	}

	/**
	 * Adds a task to the task list.
	 *
	 * @param userInput The task keyed in by the user.
	 * @return nothing. This is a void function.
	 */
	public void addTask(String userInput) {
		Task t;
		if (userInput.contains(BY)) {
			t = new Deadline(userInput);
		} else if (userInput.contains(AT)) {
			t = new Event(userInput);
		} else {
			t = new Todo(userInput);
		}
		taskList.add(t);
		incrementTotalTasks();
		System.out.println("Added: " + userInput + ". ");
	}

	/**
	 * Removes a task from the task list.
	 *
	 * @param idx The task number specified by the user. For example, in "DELETE 1" (which means delete task 1), idx = 1.
	 * @return nothing. This is a void function.
	 */
	public void deleteTask(int idx) {
		taskList.remove(idx - 1);
		decrementTotalTasks();
		System.out.println(REMOVED);
	}

	/**
	 * Marks specified task as done.
	 *
	 * @param idx The task number specified by the user. For example, in "MARK 1" (which means mark task 1), idx = 1.
	 * @return nothing. This is a void function.
	 */
	public static void markTask(int idx) {
		(taskList.get(idx - 1)).markAsDone();
		System.out.println(MARKED);
	}

	/**
	 * Unmarks a specified task i.e. task is not done.
	 *
	 * @param idx The task number specified by the user. For example, in "UNMARK 1" (which means unmark task 1), idx = 1.
	 * @return nothing. This is a void function.
	 */
	public static void unmarkTask(int idx) {
		(taskList.get(idx - 1)).markAsNotDone();
		System.out.println(UNMARKED);
	}

	/**
	 * Sorts and returns all deadline and event tasks stored in the task list.
	 * Since there is no need for dates for Todos, Duke only reminds a user of his deadlines and events
	 *
	 * @return nothing. This is a void function.
	 */
	public void getReminders() {
		ArrayList<Task> reminders = new ArrayList<Task>();

		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			reminders.add(task);
		}

		Collections.sort(reminders, (t1, t2) -> t1.getDate().compareTo(t2.getDate()));

		int idx = 0;
		for (int i = 0; i < reminders.size(); i++) {
			Task task = reminders.get(i);
			if (task.getIsDone().equals("false")) {
				idx = idx + 1;
				System.out.print(idx + ".  ");
				printTask(task);
			}
		}
	}

	/**
	 * Filters for and displays tasks with a specified keyword.
	 *
	 * @param keyword The keyword specified by the user.
	 * @return nothing. This is a void function.
	 */
	public void filter(String keyword) {
		int count = 0;
		for (int i = 0; i < taskList.size(); i++) {
			String str = taskList.get(i).getTask();
			if (str.contains(keyword)) {
				printTask(taskList.get(i));
				count = count + 1;
			}
		}
		if (count == 0) {
			System.out.println("No task with the keyword " + keyword + " exists");
		}
	}

	/**
	 * Processes a task based on its command.
	 *
	 * @param command The command specified by the user. For example, in "TODO assignment", command = TODO.
	 * @param userInput The task keyed in by the user.
	 * @return true (i.e. terminate the Duke program) or false (i.e. do not terminate the Duke program)
	 */
	public boolean processTasks(String command, String userInput) {
		int end = userInput.indexOf("[");
		String parsedString = userInput;

		if (end != -1) {
			parsedString = userInput.substring(0, end);
		}

		if (command.equals(EXIT)) {
			return true;
		}

		if ((command.equals(LegalCommandEnumerations.VIEW.toString())) && ((this.getTotalTasks()) == 0)) {
			System.out.println(TASKLIST_IS_EMPTY_MESSAGE);
			return false;
		}
		if ((command.equals(LegalCommandEnumerations.VIEW.toString())) && ((this.getTotalTasks()) > 0)) {
			this.viewTaskList();
			return false;
		}

		if (command.equals(LegalCommandEnumerations.REMINDERS.toString())) {
			this.getReminders();
			return false;
		}

		if (command.equals(LegalCommandEnumerations.MARK.toString())) {
			this.markTask(getItemNumber(userInput));
			return false;
		}

		if (command.equals(LegalCommandEnumerations.UNMARK.toString())) {
			this.unmarkTask(getItemNumber(userInput));
			return false;
		}

		if (command.equals(LegalCommandEnumerations.DELETE.toString())) {
			this.deleteTask(getItemNumber(userInput));
			return false;
		}

		if (command.equals(LegalCommandEnumerations.FILTER.toString())) {
			int start = userInput.indexOf(SPACE) + 1;
			String keyword = userInput.substring(start, userInput.length());
			this.filter(keyword);
			return false;
		}

		this.addTask(parsedString);
		return false;
	}

	/**
	 * Marks a task as done when loading data from hard disk.
	 * This function is used to process tasks when loading data from hard disk.
	 *
	 * @param idx The task number.
	 * @param userInput The task that was saved in the hard disk. The original user input was saved.
	 * @return nothing. This is a void function.
	 */
	public void processIsDone(int idx, String userInput) {
		int start = userInput.indexOf("[") + 1;
		String isDone = userInput.substring(start, userInput.length() - 1);
		if (isDone.equals(TRUE)) {
			this.markTask(idx);
		}
	}
}
