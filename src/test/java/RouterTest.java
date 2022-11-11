import data.DataFileFactory;
import data.DataInterface;
import data.FileInfo;
import data.FileInterface;
import logic.BotCallback;
import task.Task;
import task.TaskInterface;
import task.Todo;
import ui.UIInterface;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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

    @Test
    public void unsupportedTaskTypeTest() {
        UISpy uispy = new UISpy();
        Router router = new Router(uispy, null, null);
        boolean checker = uispy.checker;
        router.unsupportedTaskType();
        assertEquals(!checker, uispy.checker);
    }

    @Test
    public void indexOutOFBoundTest() {
        UISpy uispy = new UISpy();
        Router router = new Router(uispy, null, null);
        boolean checker = uispy.checker;
        router.indexOutOFBound();
        assertEquals(!checker, uispy.checker);
    }

    @Test
    public void unsupportedFormatTest() {
        UISpy uispy = new UISpy();
        Router router = new Router(uispy, null, null);
        router.unsupportedFormat("unsupported");
        assertEquals("unsupported", uispy.checkerString);
    }

    @Test
    public void goodbyeTest() {
        UISpy uispy = new UISpy();
        Router router = new Router(uispy, null, null);
        boolean checker = uispy.checker;
        router.goodbye();
        assertEquals(!checker, uispy.checker);
    }

    @Test
    public void loadActiveFileTest() {
        Todo a = new Todo("asd");
        UISpy uiSpy = new UISpy();
        FileDataSpy fileSpy = new FileDataSpy();
        DataSpy dataSpy = new DataSpy("a", "b");
        Router router = new Router(uiSpy, dataSpy, fileSpy);
        router.loadActiveFile();
        assertEquals(dataSpy.path, fileSpy.active);
        assertTrue(dataSpy.data.size() == 1);
        assertEquals(dataSpy.data.get(0).getString(), a.getString());
    }

    @Test
    public void addNewFile() {
        UISpy uiSpy = new UISpy();
        FileDataSpy fileSpy = new FileDataSpy();
        DataSpy dataSpy = new DataSpy("a", "b");
        Router router = new Router(uiSpy, dataSpy, fileSpy);
        router.addNewFile("nihao");
        assertEquals(uiSpy.checkerString, "nihao");
        assertEquals(dataSpy.path, fileSpy.active);
    }

    @Test
    public void showAllFilesTest() {
        UISpy uiSpy = new UISpy();
        FileDataSpy fileSpy = new FileDataSpy();
        Router router = new Router(uiSpy,null, fileSpy);
        router.showAllFiles();
        FileInfo a = new FileInfo("a", true, "a");
        assertEquals(a.getAlias(), uiSpy.checkerString);
    }

    @Test
    public void getActiveFileTest() {
        UISpy uiSpy = new UISpy();
        FileDataSpy fileSpy = new FileDataSpy();
        Router router = new Router(uiSpy,null, fileSpy);
        router.getActiveFile();
        assertEquals(uiSpy.checkerString, fileSpy.active);
    }

    @Test
    public void invalidFormatTest() {
        UISpy uiSpy = new UISpy();
        Router router = new Router(uiSpy,null, null);
        router.invalidFormat("cde");
        assertEquals(uiSpy.checkerString, "cde");
    }

    @Test
    public void showInvalidFormatTest() {
        UISpy uiSpy = new UISpy();
        Router router = new Router(uiSpy,null, null);
        router.showInvalidFormat("asd","cde");
        assertEquals(uiSpy.checkerString, "asdcde");
    }

    @Test
    public void showFilteredListTest() {
        UISpy uiSpy = new UISpy();
        Router router = new Router(uiSpy,null, null);
        ArrayList<TaskInterface> a = new ArrayList<>();
        a.add(new Todo("asd"));
        router.showFilteredList(a);
        assertEquals(1, uiSpy.list.size());
        assertEquals(a.get(0).toString(), uiSpy.list.get(0).toString());
    }

    @Test
    public void customErrorTest() {
        UISpy uiSpy = new UISpy();
        Router router = new Router(uiSpy,null, null);
        router.customError("cde");
        assertEquals(uiSpy.checkerString, "cde");
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
            this.data = new ArrayList<>();
            this.data.add(new Todo("asd"));
        }
    }

    private class UISpy implements UIInterface {
        public TaskInterface task;
        public int size;
        public ArrayList<TaskInterface> list = new ArrayList<>();

        public boolean checker = false;
        public String checkerString = "";

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
            this.checker = !this.checker;
        }

        @Override
        public void indexOutOFBound() {
            this.checker = !this.checker;
        }

        @Override
        public void unsupportedFormat(String text) {
            checkerString = text;
        }

        @Override
        public void invalidFormat(String text) {
            checkerString = text;
        }

        @Override
        public void goodbye() {
            this.checker = !this.checker;
        }

        @Override
        public void displaySize(int size) {
            this.size = size;
        }

        @Override
        public void customError(String text) {
            checkerString = text;
        }

        @Override
        public void unexpectedError() {
            checker = !checker;
        }

        @Override
        public void addFileSuccess(String text) {
            this.checkerString = text;
        }

        @Override
        public void addFileFailed(String text) {

        }

        @Override
        public void showFiles(ArrayList<FileInfo> files) {
            this.checkerString = files.get(0).getAlias();
        }

        @Override
        public void setActiveSuccess(String alias) {

        }

        @Override
        public void setActiveFailed(String alias) {

        }

        @Override
        public void getActiveFile(String alias) {
            this.checkerString = alias;
        }

        @Override
        public void invalidCommandFormat(String valid, String invalid) {
            this.checkerString = valid + invalid;
        }

        @Override
        public void showFilteredList(ArrayList<TaskInterface> lists) {
            list = lists;
        }
    }

    private class FileDataSpy implements FileInterface {
        String active = "active";
        @Override
        public ArrayList<FileInfo> getAllFile() {
            ArrayList<FileInfo> a = new ArrayList<>();
            a.add(new FileInfo("a", true, "a"));
            return a;
        }

        @Override
        public boolean setActive(String alias) throws IOException {
            return false;
        }

        @Override
        public FileInfo getActiveFile() throws IOException {
            return new FileInfo(active, true, active);
        }

        @Override
        public boolean addNewFile(String alias) throws IOException {
            this.active = alias;
            return true;
        }
    }
}
