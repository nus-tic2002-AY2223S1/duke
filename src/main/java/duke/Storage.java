package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.exception.SaveException;
import duke.exception.SaveException.SaveExceptionType;

public class Storage {
    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    protected TaskList load() throws IOException, SaveException {
        TaskList tasks = null;
        FileInputStream fs = null;
        ObjectInputStream os = null;
        try {
            fs = new FileInputStream(this.filepath);
            os = new ObjectInputStream(fs);
            Object obj = os.readObject();

            if (obj instanceof TaskList) {
                tasks = (TaskList) obj;
                tasks.setStorage(this);
            } else {
                throw new SaveException(SaveExceptionType.INVALID_SAVE);
            }
        } catch (FileNotFoundException e) {
            throw new SaveException(SaveExceptionType.NO_SAVE, e);
        } catch (ClassNotFoundException e) {
            throw new SaveException(SaveExceptionType.INVALID_SAVE, e);
        } finally {
            // Close all open file and object handles
            if (os != null) {
                os.close();
            }
            if (fs != null) {
                fs.close();
            }
        }
        return tasks;
    }

    protected void save(TaskList tasks) throws IOException, SaveException {
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = new FileOutputStream(this.filepath);
            os = new ObjectOutputStream(fs);

            // Nullify storage attribute before saving
            tasks.setStorage(null);
            os.writeObject(tasks);
            tasks.setStorage(this);
        } catch (FileNotFoundException e) {
            // Thrown by `new FileOutputStream`, usually when `file` is a folder or cannot be created
            throw new SaveException(SaveExceptionType.INVALID_PATH);
        } finally {
            // Close all open file and object handles
            if (os != null) {
                os.close();
            }
            if (fs != null) {
                fs.close();
            }
        }
    }
}