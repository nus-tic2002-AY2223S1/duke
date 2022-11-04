package duke.gui;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.Timer;

import duke.Duke;
import duke.orm.Database;
import duke.orm.DatabaseObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main window of the chat. Container of dialogBox, menu bar and buttons.
 */
public class MainWindow extends AnchorPane {
    private static final String MODE_CACHE_DIR = "data/tmp/";
    private static final String MODE_CACHE_FILE_PATH = "data/tmp/mode";
    private static final String DARK_CSS_FILE_PATH = "/view/dark.css";
    @FXML
    private Parent rootPane;
    @FXML
    private MenuItem darkModeButton;
    private Duke duke;
    private boolean newChat = true;
    private boolean isDark = false;
    @FXML
    private Label listButton;
    @FXML
    private Label refreshButton;
    @FXML
    private Label status;
    @FXML
    private Label chatLabel;
    private final Image userImage = new Image(
            Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/images/gigachad.png")));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/images/duke.png")));
    @FXML
    private MenuItem menuCloseButton;
    @FXML
    private Menu menuFile;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuRefreshButton;
    @FXML
    private MenuItem menuArchiveButton;
    @FXML
    private MenuItem menuRestoreButton;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Label sendButton;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) throws SQLException {
        duke = d;

        //restore chat
        Connection c = Database.init();
        Statement stmt;
        ResultSet r = null;
        try {
            stmt = c.createStatement();
            String sql = "SELECT id, sender, message, timestamp FROM "
                    + "(SELECT * FROM chat_tab ORDER BY id DESC LIMIT 500) "
                    + "ORDER BY id;";
            r = stmt.executeQuery(sql);
            if (!r.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (r.next()) {
                if (r.getInt(2) == DatabaseObject.Sender.DUKE.getLabel()) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getDukeDialog(r.getString(3), dukeImage, r.getInt(4))
                    );
                } else {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(r.getString(3), userImage, r.getInt(4)));
                }
            }
            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        c.close();

        if (newChat) {
            String[] s = duke.getWelcome();
            for (String str : s) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(str, dukeImage, 0)
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

        if (Objects.equals(input.trim(), "")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage, 0)
            );
            userInput.clear();
            return;
        }

        if (Objects.equals(input, "bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage, 0)
            );
            userInput.clear();

            ActionListener taskPerformer = evt -> Platform.runLater(() -> {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(response, dukeImage, 0)
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
                    DialogBox.getUserDialog(input, userImage, 0),
                    DialogBox.getDukeDialog(response, dukeImage, 0)
            );
            userInput.clear();
            refreshAction();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, 0)
        );
        userInput.clear();

        ActionListener taskPerformer2 = evt -> Platform.runLater(() -> {
            status.setText("Online");
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage, 0)
            );
        });
        int randTime = (int) (Math.random() * (2000 - 1000));
        Timer timer2 = new Timer((randTime), taskPerformer2);
        status.setText("Typing...");
        timer2.setRepeats(false);
        timer2.start();
    }

    @FXML
    private void listAction() {
        String response = duke.getResponse("list");
        ActionListener taskPerformer = evt -> Platform.runLater(() -> {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage, 0)
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
        Scene s = rootPane.getScene();
        s.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource(DARK_CSS_FILE_PATH))
                        .toExternalForm());
    }

    @FXML
    private void exitAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void restoreAction() {
        handleMenuInput("restore");
    }

    @FXML
    private void archiveAction() {
        handleMenuInput("archive");
    }

    @FXML
    private void aboutAction() throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://github.com/aaronangxz/duke"));
    }

    @FXML
    private void handleMenuInput(String command) {
        String response = duke.getResponse(command);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage, 0)
        );
    }

    @FXML
    private void toggleDarkMode() throws IOException {
        Scene s = rootPane.getScene();
        if (isDark) {
            isDark = false;
            darkModeButton.setText("Dark Mode");
            s.getStylesheets().remove(
                    Objects.requireNonNull(getClass().getResource(DARK_CSS_FILE_PATH))
                            .toExternalForm());
        } else {
            isDark = true;
            darkModeButton.setText("\u2713 Dark Mode");
            s.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource(DARK_CSS_FILE_PATH))
                            .toExternalForm());
        }
        cacheState(isDark);
    }

    private void cacheState(boolean isDark) throws IOException {
        Files.createDirectories(Paths.get(MODE_CACHE_DIR));
        FileWriter writer = new FileWriter(MODE_CACHE_FILE_PATH);
        writer.write(isDark ? "dark=1" : "dark=0");
        writer.close();
    }

    public void setDarkModeText() {
        isDark = true;
        darkModeButton.setText("\u2713 Dark Mode");
    }
}
