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
import duke.impl.Ui;
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
    private static final String CACHE_DIR = "data/tmp/";
    private static final String MODE_CACHE_FILE_PATH = "data/tmp/mode";
    private static final String LIST_CACHE_FILE_PATH = "data/tmp/list";
    private static final String DARK_CSS_FILE_PATH = "/view/dark.css";
    private Duke duke;
    private boolean newChat = true;
    private boolean isDark = false;
    private boolean isListOnLaunch = false;

    private Ui.LocaleRegion locale;
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
    private MenuItem listOnLaunchButton;
    @FXML
    private MenuItem clearChatButton;
    @FXML
    private Parent rootPane;
    @FXML
    private MenuItem darkModeButton;

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

        String[] s = duke.getWelcome(isListOnLaunch);
        for (String str : s) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(str, dukeImage, 0)
            );
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
            switch (locale) {
            case EN:
                darkModeButton.setText("Dark Mode");
                break;
            case CN:
                darkModeButton.setText("夜间模式");
            }
            s.getStylesheets().remove(
                    Objects.requireNonNull(getClass().getResource(DARK_CSS_FILE_PATH))
                            .toExternalForm());
        } else {
            setDarkModeText();
            s.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource(DARK_CSS_FILE_PATH))
                            .toExternalForm());
        }
        cacheMode(isDark);
    }

    private void cacheMode(boolean isDark) throws IOException {
        Files.createDirectories(Paths.get(CACHE_DIR));
        FileWriter writer = new FileWriter(MODE_CACHE_FILE_PATH);
        writer.write(isDark ? "dark=1" : "dark=0" + System.lineSeparator());
        writer.close();
    }

    public void setDarkModeText() {
        isDark = true;
        switch (locale) {
        case EN:
            darkModeButton.setText("\u2713 Dark Mode");
            break;
        case CN:
            darkModeButton.setText("\u2713 夜间模式");
        }
    }

    public void setListOnLaunchText() {
        isListOnLaunch = true;
        switch (locale) {
        case EN:
            listOnLaunchButton.setText("\u2713 List on launch");
            break;
        case CN:
            listOnLaunchButton.setText("\u2713 启动显示任务列表");
        }
    }

    @FXML
    private void clearChatAction() {
        Database.dropTable();
        refreshAction();
    }

    @FXML
    private void toggleListOnLaunch() throws IOException {
        Scene s = rootPane.getScene();
        if (isListOnLaunch) {
            isListOnLaunch = false;
            switch (locale) {
            case EN:
                listOnLaunchButton.setText("List on launch");
                break;
            case CN:
                listOnLaunchButton.setText("启动显示任务列表");
            }
        } else {
            setListOnLaunchText();
        }
        cacheLaunch(isListOnLaunch);
    }

    private void cacheLaunch(boolean isShow) throws IOException {
        Files.createDirectories(Paths.get(CACHE_DIR));
        FileWriter writer = new FileWriter(LIST_CACHE_FILE_PATH);
        writer.write(isShow ? "list=1" : "list=0" + System.lineSeparator());
        writer.close();
    }

    public void setLocale(Ui.LocaleRegion l) {
        locale = l;
    }
}
