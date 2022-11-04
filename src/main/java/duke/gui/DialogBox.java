package duke.gui;

import java.io.IOException;
import java.util.Collections;

import duke.utils.DateProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Renders the chat bubble for both User and Duke
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label meta;
    @FXML
    private ImageView displayPicture;
    private Parent rootPane;

    private DialogBox(String text, Image img, String userSpecific, boolean isDuke, String ts) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isDuke) {
            meta.setText(ts);
        } else {
            meta.setText(ts + " \u2713");
        }
        dialog.setText(text);
        dialog.setStyle(userSpecific
                + ";-fx-padding:10;"
                + "-fx-label-padding:2,0,2,0;"
                + "-fx-wrap-text:true;"
                + "-fx-background-radius:10;"
                + "-fx-max-width:500");
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img, int ts) {
        String timestamp;
        if (ts == 0) {
            timestamp = DateProcessor.getMetaTimeStamp();
        } else {
            timestamp = DateProcessor.unixToSimplifiedString(ts);
        }
        return new DialogBox(text, img, "-fx-background-color: #90ee90", false, timestamp);
    }

    public static DialogBox getDukeDialog(String text, Image img, int ts) {
        String timestamp;
        if (ts == 0) {
            timestamp = DateProcessor.getMetaTimeStamp();
        } else {
            timestamp = DateProcessor.unixToSimplifiedString(ts);
        }
        var db = new DialogBox(text, img, "", true, timestamp);
        db.flip();
        return db;
    }
}
