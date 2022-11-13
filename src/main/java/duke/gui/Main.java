package duke.gui;

import duke.Duke;

import java.io.File;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke("duke.save");

    /**
     * Starts a stage to display the GUI.
     *
     * @param stage A stage where Duke GUI is executed.
     */
    @Override
    public void start(Stage stage) {
        try {
            assert new File("src/main/resources/view/MainWindow.fxml").exists() : "MainWindow.fxml not found";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Anyer");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            fxmlLoader.<MainWindow>getController().showWelcome();
            fxmlLoader.<MainWindow>getController().load();
            fxmlLoader.<MainWindow>getController().isExit.addListener((observable, oldValue, newValue) -> {
                if (oldValue != newValue) {
                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(event -> stage.close());
                    delay.play();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}