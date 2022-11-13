package duke.gui;

import duke.Duke;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.SaveException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;


    public SimpleBooleanProperty isExit;
    private Duke duke;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));

    /**
     * Initializes the main window of Duke GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        isExit = new SimpleBooleanProperty();
    }

    /**
     * Set the main window's Duke object.
     * @param d The Duke object to be associated with the main window.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Shows the welcome message in the GUI.
     */
    public void showWelcome() {
        String welcomeMessage = new Ui().showWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        welcomeMessage,
                        dukeImage));
    }

    /**
     * Shows the result of loading past tasks from the local file.
     */
    public void load() {
        String message = "nice to meet you!";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        message,
                        dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InvalidInputException, SaveException, IOException, DukeException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            isExit.set(true);
        }
        String response = duke.getR(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}