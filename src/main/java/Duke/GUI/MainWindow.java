package Duke.GUI;

import Duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    public Button listButton;
    public Button refreshButton;
    public Label status;
    public Label chatLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    boolean newChat = true;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/gigachad.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/duke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        if (newChat) {
            String[] s = duke.getWelcome();
            for (String str : s) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(str, dukeImage)
                );
            }
            newChat = false;
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (Objects.equals(input, "")) {
            return;
        }

        if (Objects.equals(input, "bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            userInput.clear();

            ActionListener taskPerformer = evt -> Platform.runLater(() -> {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            });
            Timer timer = new Timer(500, taskPerformer);
            timer.setRepeats(false);
            timer.start();

            ActionListener taskPerformer1 = evt -> Platform.runLater(() -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            Timer timer1 = new Timer(2000, taskPerformer1);
            timer1.setRepeats(false);
            timer1.start();
            return;
        }

        String[] ss = input.split(" ");
        if (ss.length == 2 && Objects.equals(ss[0], "restore")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
            refreshAction();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();

        ActionListener taskPerformer2 = evt -> Platform.runLater(() -> {
            chatLabel.setText("Duke");
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        });
        int randTime = (int) (Math.random() * (1500 - 500));
        Timer timer2 = new Timer((randTime), taskPerformer2);
        System.out.println(randTime);
        chatLabel.setText("Duke is typing...");
        timer2.setRepeats(false);
        timer2.start();
    }

    @FXML
    private void listAction() {
        String response = duke.getResponse("list");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("list", userImage)
        );

        ActionListener taskPerformer = evt -> Platform.runLater(() -> {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        });
        Timer timer = new Timer(500, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    @FXML
    private void refreshAction() {
        Stage stage = (Stage) refreshButton.getScene().getWindow();
        Main m = new Main();
        m.start(stage);
    }
}