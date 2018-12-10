package com.leokongwq.blog;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/1/17
 * Time: 下午12:16
 * Email:leokongwq@gmail.com
 */
public class FutureTaskTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        }, "hello");
        Future future1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello java";
            }
        });
        try {
            Thread.sleep(1 * 1000);
            Object object = future.get();
            System.out.println(object);

            System.out.println(future1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
