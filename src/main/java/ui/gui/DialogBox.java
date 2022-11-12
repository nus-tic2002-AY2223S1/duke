package ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        displayPicture.setFitWidth(90.0);
        displayPicture.setFitHeight(90.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, picture());
    }

    private HBox picture() {
        String style = "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);";
        HBox top = new HBox();
        top.getChildren().add(displayPicture);
        top.setStyle(style);
        return top;
    }

    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
