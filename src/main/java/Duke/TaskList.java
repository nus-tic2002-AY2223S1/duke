package Duke;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private final ArrayList<Task> arrayList ;
    public TaskList() {
        Task[] arr = {};
        this.arrayList = new ArrayList<>(Arrays.asList(arr));
    }

    public ArrayList<Task> getList(){
        return this.arrayList;
    }
}
