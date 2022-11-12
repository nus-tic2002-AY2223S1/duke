package nus.duke.frontend;

/**
 * This is a class containing all the print statements commonly used through the code, to keep the code neat.
 */
public class CommonPrintStatements {
    public static final String FILE_WRITER_IOEXCEPTION_ERROR_MESSAGE = "[ERROR] IO Exception thrown by FileWriter";
    public static final String TASK_CANNOT_BE_EMPTY_ERROR_MESSAGE = "[ERROR] Task cannot be empty";
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "[ERROR] This is not a valid command";
    public static final String MISSING_KEYWORD_ERROR_MESSAGE = "[ERROR] You are missing a keywords such as /by for deadlines and /at or /on for events";
    public static final String MISSING_DATE_ERROR_MESSAGE = "[ERROR] Please key in a date in the correct format i.e. dd/mm/yyyy is correct and dd-mm-yyyy is wrong";
    public static final String ASK_FOR_USER_INPUT_AFTER_ERROR_MESSAGE_WAS_DISPLAYED_MESSAGE = "Please key in the correct command:";
    public static final String ASK_FOR_USER_INPUT_MESSAGE = "ʕ •ᴥ•ʔ \uD83D\uDCAC Please key in a command from the command menu:";
    public static final String HARDDISK_LOADED_MESSAGE = "\n ʕ •ᴥ•ʔ \uD83D\uDCAC Hard disk data has been loaded. Program is now ready";
    public static final String HARDDISK_CREATED_MESSAGE = "\n ʕ •ᴥ•ʔ \uD83D\uDCAC A hard disk file was created. You can now save all your tasks and it will be loaded the next time you log on";
    public static final String START_PROGRAM_MESSAGE = " \uD83D\uDCAC My name is Dobby and I am a free elf! Please also meet my friend ʕ •ᴥ•ʔ pooh bear!";
    public static final String EXIT_PROGRAM_MESSAGE = "ʕ •ᴥ•ʔ \uD83D\uDCAC Goodbye, my friend. Dobby must now hurry to Harry Potter";
    public static final String TASKS_ARE_SAVED_MESSAGE = "ʕ •ᴥ•ʔ \uD83D\uDCAC Your tasks are saved";
    public static final String TASKLIST_IS_EMPTY_MESSAGE = "ʕ •ᴥ•ʔ \uD83D\uDCAC There are 0 tasks in your list";
    public static final String TASK_ALREADY_MARKED_DONE_MESSAGE = "ʕ •ᴥ•ʔ \uD83D\uDCAC Task was already marked done";
    public static final String MARKED_AS_TRUE = "[T]";
    public static final String MARKED_AS_FALSE = "[F]";
    public static final String TRUE = "T";
    public static final String BY = "/by"; // a specific date
    public static final String AT = "/at"; // a specific venue
    public static final String ON = "/on"; // a specific date
    public static final String REMOVED = "* Removed *";
    public static final String MARKED = "* Marked *";
    public static final String UNMARKED = "* Unmarked *";
    public static final String EXIT = "EXIT";
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public static final String DOBBY_ARTWORK =
            "   _____\n" +
                    "  /     \\\n" +
                    "/- (*) |*)\\\n" +
                    "|/ \\   _>/\\|" + "     \uD83D\uDCAC My name is Dobby and I am a free elf! Please also meet my friend ʕ •ᴥ•ʔ pooh bear!\n" +
                    "    \\__/    |\\\n" +
                    "   _| |_   \\-/\n" +
                    "  /|\\__|\\  / /\n" +
                    " |/|   |\\\\//\n" +
                    " |||   | ~'\n" +
                    " ||| __|\n" +
                    " /_\\| ||\n" +
                    " \\_/| ||\n" +
                    "   |7 |7\n" +
                    "   || ||\n" +
                    "   || ||\n" +
                    "   \\\\ \\\\  \n" +
                    "  ^^^^ ^^^";

    public static final String LOADING_ARTWORK =
            "\n" +
                    "Loading…\n" +
                    "█▒▒▒▒▒▒▒▒▒\n" +
                    "\n" +
                    "10%\n" +
                    "███▒▒▒▒▒▒▒\n" +
                    "\n" +
                    "30%\n" +
                    "█████▒▒▒▒▒\n" +
                    "\n" +
                    "50%\n" +
                    "███████▒▒▒\n" +
                    "\n" +
                    "\u200E0███████████████████100%";
}
