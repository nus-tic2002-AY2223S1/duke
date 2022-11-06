import Data.DataInterface;
import Data.FileInfo;
import Data.FileInterface;
import Logic.BotUseCase;
import Tasks.TaskInterface;
import UI.UIInterface;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A router to route the logic to different factory, currently it is supporting a route to UIInterface and DataInterface
 */
public class Router extends BotUseCase {
    UIInterface ui;
    DataInterface data;
    FileInterface fileData;

    public Router(UIInterface ui, DataInterface data, FileInterface fileData) {
        this.ui = ui;
        this.data = data;
        this.fileData = fileData;
    }

    @Override
    public void loadData() {
        try {
            ArrayList<TaskInterface> list = data.loadData();
            delegate.loadData(list);
        } catch (Exception e) {

        }
    }
    @Override
    public void addSuccess(TaskInterface task) {
        try {
            data.add(task);
        } catch (IOException e) {

        }
        ui.addSuccess(task);
        int size = delegate.getSize();
        ui.displaySize(size);
    }
    @Override
    public void showList(ArrayList<TaskInterface> lists) {
        ui.showList(lists);
    }
    @Override
    public void markSuccess(TaskInterface task) {
        try {
            data.update(task);
        } catch (Exception e) {

        }
        ui.markSuccess(task);
    }
    @Override
    public void markFailed(TaskInterface task) {
        ui.markFailed(task);
    }
    @Override
    public void deleteSuccess(TaskInterface task) {
        ui.deleteSuccess(task);
        try {
            data.delete(task);
        } catch (Exception e) {

        }
        int size = delegate.getSize();
        ui.displaySize(size);
    }
    @Override
    public void unsupportedTaskType() { ui.unsupportedTaskType(); }
    @Override
    public void indexOutOFBound() { ui.indexOutOFBound(); }
    @Override
    public void unsupportedFormat(String text) { ui.unsupportedFormat(text); }
    @Override
    public void goodbye() { ui.goodbye(); }
    @Override
    public void loadActiveFile() {
        try {
            FileInfo info = fileData.getActiveFile();
            data.changeFile(info.getPath());
            this.loadData();
        } catch (Exception e) {

        }
    }
    @Override
    public void addNewFile(String alias) {
        try {
            if(fileData.addNewFile(alias)) {
                FileInfo file = fileData.getActiveFile();
                data.changeFile(file.getPath());
                ui.addFileSuccess(alias);
                this.loadData();
            } else {
                ui.addFileFailed(alias);
            }
        } catch (Exception e) {
            ui.unexpectedError();
        }
    }
    @Override
    public void showAllFiles() {
        ArrayList<FileInfo> files = fileData.getAllFile();
        ui.showFiles(files);
    }

    @Override
    public void setActiveFile(String alias) {
        try {
            fileData.setActive(alias);
            this.loadActiveFile();
            ui.setActiveSuccess(alias);
        } catch (Exception e) {

        }
    }

    @Override
    public void getActiveFile() {
        try {
            FileInfo file = fileData.getActiveFile();
            ui.getActiveFile(file.getAlias());
        } catch (IOException e) {

        }
    }
    @Override
    public void invalidFormat(String text) { ui.invalidFormat(text); }

    @Override
    public void showInvalidFormat(String valid, String invalid) {
        ui.invalidCommandFormat(valid, invalid);
    }

    public void customError(String text) { ui.customError(text); }
}