package duke.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import duke.Duke;
import duke.impl.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for GUI, interfaces with Duke
 */
public class Main extends Application {
    private final String path = "data/save/output";
    private Duke duke;

    private Ui.LocaleRegion l;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = loadLocale();
            duke = new Duke(path, l);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setLocale(l);
            isDarkMode(scene, fxmlLoader);
            isListOnLaunch(fxmlLoader);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void isDarkMode(Scene scene, FXMLLoader fxmlLoader) throws IOException {
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
                scene.getStylesheets().add(
                        Objects.requireNonNull(
                                getClass().getResource("/view/dark.css")).toExternalForm());
                fxmlLoader.<MainWindow>getController().setDarkModeText();
            } else {
                scene.getStylesheets().add(
                        Objects.requireNonNull(
                                getClass().getResource("/view/light.css")).toExternalForm());
            }
        }
    }

    private void isListOnLaunch(FXMLLoader fxmlLoader) throws IOException {
        String path = "";
        Files.createDirectories(Paths.get("data/tmp/"));
        File f = new File("data/tmp/list");
        if (f.exists() && !f.isDirectory()) {
            Scanner scn = new Scanner(f);
            if (scn.hasNext()) {
                path = scn.nextLine();
            }
            String[] ss = path.split("=");
            if (Objects.equals(ss[0], "list") && Objects.equals(ss[1], "1")) {
                fxmlLoader.<MainWindow>getController().setListOnLaunchText();
            }
        }
    }

    private FXMLLoader loadLocale() throws IOException {
        FXMLLoader fxmlLoader = null;
        String path = "";
        Files.createDirectories(Paths.get("data/tmp/"));
        File f = new File("data/tmp/il8n");
        if (f.exists() && !f.isDirectory()) {
            Scanner scn = new Scanner(f);
            if (scn.hasNext()) {
                path = scn.nextLine();
            }
            String[] ss = path.split("=");
            if (Objects.equals(ss[0], "il8n") && Objects.equals(ss[1], "cn")) {

                fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow_cn.fxml"));
                l = Ui.LocaleRegion.CN;
                return fxmlLoader;
            }
        }

        fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow_en.fxml"));
        l = Ui.LocaleRegion.EN;
        return fxmlLoader;
    }
}
