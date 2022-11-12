package ui;

/**
 * Allows ui to delegate user input to the main class to be process
 */
public interface UIDelegate {
    /**
     * Calls when user has entered the command
     *
     * @return boolean if its true continue, false is to stop
     * @param text text is the user input
     */
    public boolean userInput(String text);
}
