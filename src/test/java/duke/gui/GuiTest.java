package duke.gui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.control.Label;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class GuiTest {

    @Start
    private void start(Stage stage) {
        MainWindow.clearChat();
        Main m = new Main();
        m.start(stage);
    }

    @Test
    void checkButtons(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#sendButton")
                .queryAs(Label.class)).hasStyle("-fx-graphic: url('/view/icons8-send-35.png');");
        Assertions.assertThat(robot.lookup("#listButton")
                .queryAs(Label.class)).hasStyle("-fx-graphic: url('/view/icons8-reminders-35.png');");
        Assertions.assertThat(robot.lookup("#helpButton")
                .queryAs(Label.class)).hasStyle("-fx-graphic: url('/view/icons8-ask-35.png');");
    }

    @Test
    void checkDialogDisplay(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#dialog1")
                .queryAs(Label.class)).hasText("» Hello from Duke. What can I do for you?");
    }

    @Test
    void checkLabelsDisplay(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#chatLabel")
                .queryAs(Label.class)).hasText("Duke");
        Assertions.assertThat(robot.lookup("#status")
                .queryAs(Label.class)).hasText("Online");
    }
}
