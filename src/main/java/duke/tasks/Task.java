package duke.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import duke.impl.Ui;
import duke.impl.UiCn;
import duke.impl.UiEn;
import duke.utils.DateProcessor;

/**
 * Main Class for Tasks
 */
public class Task {
    protected String description;
    protected String type = "Task";
    protected long due = 0;

    protected long to = 0;
    protected boolean isDone;

    protected DateProcessor d;
    protected Ui ui;

    /**
     * Initialized a Task
     * Initialized task description and state of completion
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u29bf" : "   ");
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getType() {
        return this.type;
    }

    public long getDue() {
        return this.due;
    }

    public long getTo() {
        return this.to;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIsDone() {
        return this.isDone ? "1" : "0";
    }

    public Ui.LocaleRegion getLocale() throws IOException {
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
                return Ui.LocaleRegion.CN;
            }
        }
        return Ui.LocaleRegion.EN;
    }

    /**
     * Initialize locale for DateProcessor and Ui
     *
     * @throws IOException Exception
     */
    public void initLocale() throws IOException {
        switch (getLocale()) {
        case EN:
            d = new DateProcessor(Ui.LocaleRegion.EN);
            ui = new UiEn();
            break;
        case CN:
            d = new DateProcessor(Ui.LocaleRegion.CN);
            ui = new UiCn();
            break;
        default:
        }
    }
}
