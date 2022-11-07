import data.DataInterface;
import data.FileInfo;
import logic.BotCallback;
import task.TaskInterface;
import task.Todo;
import ui.UIInterface;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class RouterTest {
    @Test
    public void initializeBothUIAndDataTest() {
        Router c = initialize("test", "somewhere", "folder");
        assertNotNull(c.ui);
        assertNotNull(c.data);
    }

    @Test
    public void loadEmptyDataTest() {
        Router c = initialize("test", "empty.txt", "folder");
        DelegateSpy spy = new DelegateSpy();
        c.delegate = spy;
        c.loadData();
        assertEquals(spy.data.size(), c.delegate.getSize());
    }

    @Test
    public void loadHaveDataTest() {
        Router c = initialize("test", "hasData.txt", "folder");
        DelegateSpy delegate = new DelegateSpy();
        c.delegate = delegate;
        delegate.data.add(new Todo("clean house"));
        c.addSuccess(new Todo("clean house"));
        try {
            ArrayList<TaskInterface> t = c.data.loadData();
            assertEquals(delegate.data.size(), t.size());
        } catch (Exception e){
            assert(false);
        }
    }

    @Test
    public void addSuccessTest() {
        UISpy uispy = new UISpy();
        DataInterface dataspy = new DataSpy("a", "b");
        DelegateSpy delegate = new DelegateSpy();
        Router router = new Router(uispy, dataspy, null);
        router.delegate = delegate;
        delegate.data.add(new Todo("clean house"));
        TaskInterface t = new Todo("new task");
        router.addSuccess(t);
        try {
            assertEquals(delegate.getSize(), dataspy.loadData().size());
            assertEquals(uispy.task.getID(), t.getID());
            assertEquals(uispy.size, delegate.getSize());
        } catch (Exception e){
            assert(false);
        }
    }

    @Test
    public void showListWhenHasFewTasksTest() throws InterruptedException {
        UISpy uispy = new UISpy();
        DataInterface dataspy = new DataSpy("a", "b");
        Router router = new Router(uispy, dataspy, null);
        DelegateSpy delegate = new DelegateSpy();
        router.delegate = delegate;
        ArrayList<TaskInterface> list = new ArrayList<>();
        int i = 0;
        while(i < 10) {
            TimeUnit.MILLISECONDS.sleep(1);
            list.add(new Todo("todo " + i));
            i++;
        }
        router.showList(list);
        assertTrue(list.equals(uispy.list));
    }
    @Test
    public void markSuccessTest() {
        UISpy uispy = new UISpy();
        DataSpy dataspy = new DataSpy("a", "b");
        Router router = new Router(uispy, dataspy, null);
        TaskInterface t = new Todo("house work");
        DelegateSpy delegate = new DelegateSpy();
        router.delegate = delegate;
        router.addSuccess(t);
        t.markTask(true);
        router.markSuccess(t);
        try {
            TaskInterface dataTask = dataspy.data.stream().filter(d -> d.getID() == t.getID()).findFirst().get();

            assertEquals(dataTask.isDone(), true);
            assertEquals(uispy.task.isDone(), true);
        } catch (Exception e) {
            assert false;
        }

    }

    @Test
    public void markFaildTest() {
        UISpy uispy = new UISpy();
        DataSpy dataspy = new DataSpy("a", "b");
        Router router = new Router(uispy, dataspy, null);
        TaskInterface t = new Todo("house work");
        DelegateSpy delegate = new DelegateSpy();
        router.delegate = delegate;
        router.addSuccess(t);
        t.markTask(false);
        router.markSuccess(t);
        try {
            TaskInterface dataTask = dataspy.data.stream().filter(d -> d.getID() == t.getID()).findFirst().get();

            assertEquals(dataTask.isDone(), false);
            assertEquals(uispy.task.isDone(), false);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void deleteTaskTest() throws InterruptedException {
        UISpy uispy = new UISpy();
        DataSpy dataspy = new DataSpy("a", "b");
        Router router = new Router(uispy, dataspy, null);
        TaskInterface t = new Todo("house work");
        DelegateSpy delegate = new DelegateSpy();

        router.delegate = delegate;
        ArrayList<TaskInterface> test = new ArrayList<>();

        TaskInterface t1 = new Todo("Task 1");
        TimeUnit.MILLISECONDS.sleep(1);
        TaskInterface t2 = new Todo("Task 2");
        TimeUnit.MILLISECONDS.sleep(1);
        TaskInterface t3 = new Todo("Task 3");
        test.add(t1);
        test.add(t2);
        delegate.data.add(t1);
        delegate.data.add(t2);
        router.addSuccess(t1);
        router.addSuccess(t2);
        router.addSuccess(t3);
        router.deleteSuccess(t3);
        assertTrue(uispy.list.equals(test));
        assertTrue(dataspy.data.equals(test));
        assertEquals(uispy.size, test.size());
    }

    //MARK: HELPER
    private Router initialize(String name, String path, String folder) {
        UIInterface a = new UISpy();
        DataInterface b = new DataSpy(path, folder);
        Router c = new Router(a, b, null);
        return c;
    }

    private class DelegateSpy implements BotCallback {
        ArrayList<TaskInterface> data = new ArrayList<>();
        @Override
        public void loadData(ArrayList<TaskInterface> lists) {
            data = lists;
        }

        @Override
        public int getSize() {
            return data.size();
        }
    }

    private class DataSpy implements DataInterface {
        String path;
        String folder;

        public ArrayList<TaskInterface> data = new ArrayList<>();
        DataSpy(String path, String folder) {
            this.path = path;
            this.folder = folder;
        }

        @Override
        public ArrayList<TaskInterface> loadData() {
            return data;
        }

        @Override
        public void update(TaskInterface task) {
            data.removeIf(t -> t.getID() == task.getID());
            data.add(task);
        }

        @Override
        public void delete(TaskInterface task)  {
            data.removeIf(t -> t.getID() == task.getID());
        }

        @Override
        public void add(TaskInterface task) {
            data.add(task);
        }

        @Override
        public void changeFile(String path) {
            this.path = path;
        }
    }

    private class UISpy implements UIInterface {
        public TaskInterface task;
        public int size;
        public ArrayList<TaskInterface> list = new ArrayList<>();

        @Override
        public void start() {

        }

        @Override
        public void addSuccess(TaskInterface task) {
            this.task = task;
            this.list.add(task);
        }

        @Override
        public void showList(ArrayList<TaskInterface> lists) {
            this.list = lists;
        }

        @Override
        public void markSuccess(TaskInterface task) {
            this.task = task;
        }

        @Override
        public void markFailed(TaskInterface task) {
            this.task = task;
        }

        @Override
        public void deleteSuccess(TaskInterface task) {
            list.removeIf(t -> t.getID() == task.getID());
        }

        @Override
        public void unsupportedTaskType() {

        }

        @Override
        public void indexOutOFBound() {

        }

        @Override
        public void unsupportedFormat(String text) {

        }

        @Override
        public void invalidFormat(String text) {

        }

        @Override
        public void goodbye() {

        }

        @Override
        public void displaySize(int size) {
            this.size = size;
        }

        @Override
        public void customError(String text) {

        }

        @Override
        public void unexpectedError() {

        }

        @Override
        public void addFileSuccess(String text) {

        }

        @Override
        public void addFileFailed(String text) {

        }

        @Override
        public void showFiles(ArrayList<FileInfo> files) {

        }

        @Override
        public void setActiveSuccess(String alias) {

        }

        @Override
        public void setActiveFailed(String alias) {

        }

        @Override
        public void getActiveFile(String alias) {

        }

        @Override
        public void invalidCommandFormat(String valid, String invalid) {
            
        }

        @Override
        public void showFilteredList(ArrayList<TaskInterface> lists) {

        }
    }
}
