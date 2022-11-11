package ui.gui;

/**
 * Allows the screen to delegate user input to the main class to be process
 */
public interface ScreenDelegate {
    /**
     * Calls when user has entered press enter or send the command
     *
     * @param text text is the user input
     */
    public void userInput(String text);
}
