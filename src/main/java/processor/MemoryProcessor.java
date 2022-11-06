package processor;

import domain.TaskList;

public interface MemoryProcessor {
    void save(TaskList taskList);
    void load(TaskList taskList);
}
