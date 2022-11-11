package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Search for the next due item
 */
public class NextDueCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        String s;
        int id = -1;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime earliest = LocalDateTime.parse("Dec 28 9999 11:59 PM", format);
        for (int i = 0; i < tasks.tasks.size(); i++) {
            s = tasks.tasks.get(i).toOutput();
            if (s.split(" \\| ")[0].equals("D")) {
                LocalDateTime temp = LocalDateTime.parse(s.split(" \\| ")[3], format);
                if (temp.isBefore(earliest)) {
                    earliest = temp;
                    id = i;
                }
            }

        }
        if (id > -1) {
            System.out.println(tasks.tasks.get(id));
        } else {
            System.out.println("No due yet");
        }


    }

}
