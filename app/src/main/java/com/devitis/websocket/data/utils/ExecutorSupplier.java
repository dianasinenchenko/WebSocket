package com.devitis.websocket.data.utils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Diana on 11.04.2019.
 */

public class ExecutorSupplier {

    private static ExecutorSupplier executorSupplier;
    private final ThreadPoolExecutor workerThreadExecutor;

    private ExecutorSupplier() {
        workerThreadExecutor = new ThreadPoolExecutor(2, 2, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    public static ExecutorSupplier getInstance() {
        if (executorSupplier == null) {
            executorSupplier = new ExecutorSupplier();
        }

        return executorSupplier;
    }

    public ThreadPoolExecutor getWorkerThreadExecutor() {
        return workerThreadExecutor;
    }
}

