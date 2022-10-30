package processor;

import domain.task.Task;

import java.util.List;

public interface MemoryProcessor {
    void save(List<Task> taskList);
    void load(List<Task> taskList);
}
