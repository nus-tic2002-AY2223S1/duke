package nus.duke.frontend;

import java.util.Scanner;

import static nus.duke.frontend.CommonPrintStatements.*;

/**
 * Represents the user interface.
 * User Interface deals with interactions with the user.
 * All interactions that are user facing will be found in this class.
 */
public class Ui {

	/**
	 * Returns the input keyed in by the user.
	 *
	 * @param scanner A scanner object.
	 * @return The user input.
	 */
	public static String getUserInput(Scanner scanner) {
		String input = scanner.nextLine();
		return input;
	}

	/**
	 * Prints a line border of the command menu.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void showLine() {
		System.out.println("_______________ʕ　·ᴥ·ʔ COMMAND MENU  ʕ·ᴥ·　ʔ_______________");
	}

	/**
	 * Prints the command menu.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void printCommandMenu() {
		showLine();
		System.out.println("MARK | UNMARK | DELETE | VIEW | EXIT | REMINDERS");
		System.out.println("TODO <<task>> | FILTER <<keyword>>");
		System.out.println("DEADLINE <<task>> /by <<dd/MM/YYYY>>| EVENT <<TASK>> /at <<venue>> /on <<dd/MM/YYY>>");
		System.out.println("Please ensure commands are all capitalised.");
		showLine();
	}

	/**
	 * Prints an artwork of Dobby, the elf from Harry Potter.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void printDobby() {
		System.out.println(DOBBY_ARTWORK);
	}

	/**
	 * Standardise way to start the Duke program.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void startProgram() {
		printDobby();
		System.out.println(ASK_FOR_USER_INPUT_MESSAGE);
		printCommandMenu();
	}

	/**
	 * Prints the goodbye message when a user exits a program.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void exit() {
		System.out.println(EXIT_PROGRAM_MESSAGE);

	}

	/**
	 * Prints a message to inform user that hard disk data has been loaded.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void showHardDiskLoadedMessage() {
		System.out.println(HARDDISK_LOADED_MESSAGE);
	}

	/**
	 * Prints message to inform user that the program is working on loading their data.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void showHardDiskLoadingMessage() {
		System.out.println(LOADING_ARTWORK);
	}

	/**
	 * Prints a message to inform user that a hard disk has been created for them.
	 *
	 * @return nothing. This is a void function.
	 */
	public static void showHardDiskCreationMessage() {
		System.out.println("\n");
		System.out.println(HARDDISK_CREATED_MESSAGE);
		System.out.println("");
	}
}
