package nus.duke.tasklist;

import nus.duke.enumerations.LegalCommandEnumerations;
import nus.duke.enumerations.LegalTaskEnumerations;
import nus.duke.storage.Storage;
import nus.duke.task.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static nus.duke.enumerations.LegalCommandEnumerations.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
	private TaskList taskList = new TaskList();

	@Test
	void taskProcessorReturnsCorrectContinuationValue() {
		assertEquals(false, taskList.processTasks("VIEW", "VIEW"));
		assertEquals(false, taskList.processTasks("REMINDERS", "REMINDERS"));
		assertEquals(false, taskList.processTasks("TODO", "TODO send dogs to the groomers"));
		assertEquals(false, taskList.processTasks("DEADLINE", "DEADLINE return Harry Potter book /by 20/10/2022"));
		assertEquals(false, taskList.processTasks("EVENT", "EVENT dog park gathering /at Central Park /on 23/12/2022"));
		assertEquals(false, taskList.processTasks("MARK", "MARK 1"));
		assertEquals(false, taskList.processTasks("UNMARK", "UNMARK 1"));
		assertEquals(false, taskList.processTasks("DELETE", "DELETE 1"));
		assertEquals(false, taskList.processTasks("FILTER", "FILTER dog"));
	}

	@Test
	void taskProcessorReturnsCorrectTerminationValue() {
		assertEquals(true, taskList.processTasks("EXIT", "EXIT"));
	}
}