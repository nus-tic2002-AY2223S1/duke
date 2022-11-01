package Duke;
import java.io.IOException;
import java.util.Collections;

import Interface.Ui;
import Util.DateProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label meta;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img,String userSpecific, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isDuke){
            meta.setText(DateProcessor.getMetaTimeStamp());
        }else{
            meta.setText(DateProcessor.getMetaTimeStamp() + " ✓");
        }
        dialog.setText(text);
        dialog.setStyle(userSpecific+";-fx-padding:10;-fx-label-padding:2,0,2,0;-fx-wrap-text:true;-fx-background-radius:10;-fx-max-width:500");
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img,"-fx-background-color: #90ee90",false);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img,"-fx-background-color: #dcdcdc",true);
        db.flip();
        return db;
    }
}