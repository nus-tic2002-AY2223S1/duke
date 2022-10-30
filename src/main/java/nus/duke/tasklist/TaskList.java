package nus.duke.tasklist;

import nus.duke.enumerations.*;
import nus.duke.task.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static nus.duke.frontend.CommonPrintStatements.*;

public class TaskList {
	protected static ArrayList<Task> taskList = new ArrayList<Task>();
	private static int totalTasks = 0;

	public TaskList(ArrayList<Task> loadedTaskList) {
		Collections.copy(loadedTaskList, this.taskList);
	}

	public TaskList() {
	}

	;

	public ArrayList<Task> getTaskList() {
		return this.taskList;
	}

	public void incrementTotalTasks() {
		this.totalTasks = this.totalTasks + 1;
	}

	public void decrementTotalTasks() {
		this.totalTasks = this.totalTasks - 1;
	}

	public int getTotalTasks() {
		return this.totalTasks;
	}

	public static int getItemNumber(String userInput) {
		int idx = userInput.indexOf(SPACE);
		String itemNumber = userInput.substring(idx + 1, userInput.length());
		idx = Integer.parseInt(itemNumber);
		return idx;
	}

	public static void printTask(Task t) {
		System.out.println("[" + t.getTaskType() + "][" + t.getStatusIcon() + "] " + t.getTask() + t.getTaskDetails());
	}

	public static void viewTaskList() {
		for (int i = 0; i < taskList.size(); i++) {
			printTask(taskList.get(i));
		}
	}

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

	public void deleteTask(int idx) {
		taskList.remove(idx - 1);
		decrementTotalTasks();
		System.out.println(REMOVED);
	}

	public static void markTask(int idx) {
		(taskList.get(idx - 1)).markAsDone();
		System.out.println(MARKED);
	}

	public static void unmarkTask(int idx) {
		(taskList.get(idx - 1)).markAsNotDone();
		System.out.println(UNMARKED);
	}

	public void getReminders() {
		ArrayList<Deadline> reminders = new ArrayList<Deadline>();
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getTaskType().equals(LegalTaskEnumerations.D.toString())) {
				Deadline dl = (Deadline) taskList.get(i);
				reminders.add(dl);
			}
		}

		Collections.sort(reminders, (d1, d2) -> d1.getDate().compareTo(d2.getDate()));
		for (int i = 0; i < reminders.size(); i++) {
			printTask(reminders.get(i));
		}
	}

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

		if (command.equals(LegalCommandEnumerations.REMINDERS.toString())) {
			this.getReminders();
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

	public void processIsDone(int idx, String userInput) {
		int start = userInput.indexOf("[") + 1;
		String isDone = userInput.substring(start, userInput.length() - 1);
		if (isDone.equals(TRUE)) {
			this.markTask(idx);
		}
	}

}
