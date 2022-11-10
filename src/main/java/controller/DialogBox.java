package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class DialogBox extends HBox {
    
    private Label text;
    private ImageView displayPicture;
    BackgroundSize bgSize;
    BackgroundImage bgImage;
    
    /**
     * DialogBox constructor
     *
     * @param l      Label variable to display message
     * @param iv     image variable to display profile image
     * @param isUser boolean value to identify if it is diaplay a user message
     */
    private DialogBox(Label l, ImageView iv, boolean isUser) {
        assert l != null && iv != null: "Element should not be null.";
        text = l;
        displayPicture = iv;
        
        text.setWrapText(true);
        text.setPadding(new Insets(10, 10, 10, 10));
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        
        Image image = new Image(this.getClass().getResourceAsStream("/images/square_green.png"));
        bgSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        
        if (!isUser) {
            image = new Image(this.getClass().getResourceAsStream("/images/square_grey.png"));
            flip();
        }
        
        bgImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bgSize);
        text.setBackground(new Background(bgImage));
        text.setMaxWidth(250.0);
    }
    
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
    
    /**
     * Generate a DialogBox to display user message
     *
     * @param l  Label variable to display message
     * @param iv image variable to display profile image
     * @return A DialogBox to MainWindow
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        assert l != null && iv != null: "Element should not be null.";
        return new DialogBox(l, iv, true);
    }
    
    /**
     * Generate a DialogBox to display Duke message
     *
     * @param l  Label variable to display message
     * @param iv image variable to display profile image
     * @return A DialogBox to MainWindow
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        assert l != null && iv != null: "Element should not be null.";
        return new DialogBox(l, iv, false);
    }
}