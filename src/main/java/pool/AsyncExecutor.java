package pool;

import constant.Constant;
import util.ExceptionUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description thread pool class which used to run the task in async way
 * @author Dex
 * @date 2022/09/08
 */
public class AsyncExecutor {

    private static final ExecutorService executor = Executors.newFixedThreadPool(Constant.MAX_THREAD);

    private AsyncExecutor() {}

    public static void execute(Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Exception exception) {
            ExceptionUtil.getStackTraceAsString(exception);
        }
    }
}
