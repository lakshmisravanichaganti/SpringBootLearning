package demo.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Processor {


    private ExecutorService executorService;

    private int threadPoolSize = 5;
    private int sleepTime = 1000;
    private int threadTimeOut = 1000;
    private boolean isBusyWaitingEnabled = true;

    private AtomicInteger threadCount;

    public Processor(@Qualifier("THREAD_EXECUTOR") ExecutorService executorService) {
        this.executorService = executorService;
        threadCount = new AtomicInteger(0);
    }

    public void send() throws InterruptedException {
        while (threadCount.get() >= threadPoolSize) {
            //Thread.sleep(sleepTime);
           // System.out.println("Busy waiting :: " + threadCount.get());
        }
        Runnable task = createRunnableTask();

        try {
            executeTask(task);
        } catch (Exception e) {
            System.out.println("Exception occurred while executing thread " + e.getMessage());
        }
    }

    private void executeTask(Runnable task) throws InterruptedException {
        threadCount.incrementAndGet();
        System.out.println("Thread count :: " + threadCount);
        //handle failure of the following execute method as well.
        // as the entry is already made and execution trigger of task failed.
        try {
            executorService.execute(task);
            Thread.sleep(1000);
            threadCount.decrementAndGet();
        } catch (Exception e) {
           threadCount.decrementAndGet();
            throw e;
        }
    }

    private Runnable createRunnableTask() {
        return () -> {


            ExecutorService executorService = Executors.newFixedThreadPool(1);

            try {
                Future<?> submit = executorService.submit(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                submit.get(threadTimeOut, TimeUnit.SECONDS);
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                if (e.getCause() instanceof TimeoutException) {
                    System.out.println("Thread execution timed out with Exception: " + e.getMessage());
                }
            } finally {
                executorService.shutdownNow();
            }
        };
    }
}
