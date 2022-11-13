package duke.gui;
import java.io.File;
import java.io.IOException;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a Text
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private static final Paint PALE_BLUE = Color.rgb(187,210, 240);
    private static final Paint PALE_PINK = Color.rgb(240,187, 231);

    private static final CornerRadii ROUND_CORNER = new CornerRadii(10);

    private static final Insets USER_DIALOG_INSETS = new Insets(0,35,5,10);
    private static final Insets DUKE_DIALOG_INSETS = new Insets(0,5,5,35);

    private DialogBox(String text, Image img) {
        try {
            assert new File("src/main/resources/view/DialogBox.fxml").exists() : "DialogBox.fxml not found";
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Creates a dialog box that contains the user's instruction.
     *
     * @param text A string that represents the user's instruction.
     * @param img An image that represents the user.
     * @return A dialog box that contains the user's instruction.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        String indentation = String.format("%33s", "");
        DialogBox userDialog =  new DialogBox(indentation + text, img);
        userDialog.setBackground(new Background(new BackgroundFill(
                PALE_BLUE,
                ROUND_CORNER,
                USER_DIALOG_INSETS)));
        return userDialog;
    }

    /**
     * Creates a dialog box that contains Duke's reply to an instruction.
     *
     * @param text A string that represents Duke's reply.
     * @param img An image that represents Duke.
     * @return A dialog box that contains Duke's reply.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dukeDialog = new DialogBox(text, img);
        dukeDialog.setBackground(new Background(new BackgroundFill(
                PALE_PINK,
                ROUND_CORNER,
                DUKE_DIALOG_INSETS)));
        dukeDialog.flip();
        return dukeDialog;
    }
}