package ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Screen extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private String name;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScreenDelegate delegate;

    public Screen(String name, ScreenDelegate delegate) {
        this.name = name;
        this.delegate = delegate;
    }
    @Override
    public void start(Stage stage) throws Exception {
        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle(name);
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(400.0, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public void exit() {
        delay(2000, () -> Platform.exit());
    }

    private void handleUserInput() {
        String text = userInput.getText();
        Label userText = new Label(userInput.getText());
        userText.setStyle("-fx-padding: 10px; -fx-text-fill: white; -fx-font-size: 16px");
        DialogBox db = new DialogBox(userText, new ImageView(user));
        String style = dbStyle();
        style += "-fx-background-color: #3676f5;";
        db.setStyle(style);
        dialogContainer.getChildren().add(db);
        userInput.clear();
        delegate.userInput(text);
    }

    public void dukeReply(String text){
        Label dukeReply = new Label();
        dukeReply.setText(text);
        dukeReply.setStyle("-fx-padding: 10px; -fx-text-fill: black; -fx-font-size: 16px;");
        DialogBox db = new DialogBox(dukeReply, new ImageView(duke));
        String style = dbStyle();
        style += "-fx-background-color: white;";
        db.setStyle(style);
        db.flip();
        delay(500, () -> dialogContainer.getChildren().add(db));
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    private String dbStyle() {
        String style = "-fx-padding:10px;";
        style += "-fx-border-insets: 5px;";
        style += "-fx-background-insets: 5px;";
        style += "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);";
        return style;
    }
}
