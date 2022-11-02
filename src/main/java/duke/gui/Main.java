package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application {
    private final String path = "data/save/output.txt";
    private final Duke duke = new Duke(path);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // create a menu
            ContextMenu contextMenu = new ContextMenu();

            // create menuitems
            MenuItem menuItem1 = new MenuItem("menu item 1");
            MenuItem menuItem2 = new MenuItem("menu item 2");
            MenuItem menuItem3 = new MenuItem("menu item 3");

            // add menu items to menu
            contextMenu.getItems().add(menuItem1);
            contextMenu.getItems().add(menuItem2);
            contextMenu.getItems().add(menuItem3);
            Label label1 = new Label("This is a ContextMenu example ");
            label1.setContextMenu(contextMenu);
            TilePane tilePane = new TilePane(label1);


            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
