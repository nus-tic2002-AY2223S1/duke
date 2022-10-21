import Data.DataInterface;
import Logic.BotUseCase;
import Tasks.TaskInterface;
import UI.UIInterface;
import java.io.IOException;
import java.util.ArrayList;

public class Router extends BotUseCase {
    UIInterface ui;
    DataInterface data;

    public Router(UIInterface ui, DataInterface data) {
        this.ui = ui;
        this.data = data;
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
    public void invalidFormat(String text) { ui.invalidFormat(text); }
}