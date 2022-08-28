import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private List<String> storage;

    public Storage() {
        storage = new ArrayList<>();
    }

    public Storage(List<String> storage) {
        this.storage = storage;
    }

    public List<String> getStorage() {
        return storage;
    }

    public void setStorage(List<String> storage) {
        this.storage = storage;
    }

    public void addItem(String item) {
        storage.add(item);
    }
}
