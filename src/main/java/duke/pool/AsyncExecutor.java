package duke.pool;

import duke.constant.Constant;
import duke.util.ExceptionUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread pool class which used to run the task in async way.
 *
 * @author Dex
 * @date 2022/09/08
 */
public class AsyncExecutor {

    /**
     * Variable store the thread pool.
     */
    private static final ExecutorService executor = Executors.newFixedThreadPool(Constant.MAX_THREAD);

    private AsyncExecutor() {}

    /**
     * Execute the task in separate thread, not blocking the main thread.
     *
     * @param runnable: Class which implement runnable interface.
     */
    public static void execute(Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Exception exception) {
            ExceptionUtil.getStackTraceAsString(exception);
        }
    }
}
