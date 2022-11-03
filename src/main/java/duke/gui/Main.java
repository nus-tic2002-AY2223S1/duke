package duke.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for GUI, interfaces with Duke
 */
public class Main extends Application {
    private final String path = "data/save/output.txt";
    private final Duke duke = new Duke(path);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setResizable(false);

            String path = "";
            Files.createDirectories(Paths.get("data/tmp/"));
            File f = new File("data/tmp/mode");
            if (f.exists() && !f.isDirectory()) {
                Scanner scn = new Scanner(f);
                if (scn.hasNext()) {
                    path = scn.nextLine();
                }
                String[] ss = path.split("=");
                if (Objects.equals(ss[0], "dark") && Objects.equals(ss[1], "1")) {
                    scene.getStylesheets().add(getClass().getResource("/view/dark.css").toExternalForm());
                    fxmlLoader.<MainWindow>getController().setDarkModeText();
                } else {
                    scene.getStylesheets().add(getClass().getResource("/view/light.css").toExternalForm());
                }
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
