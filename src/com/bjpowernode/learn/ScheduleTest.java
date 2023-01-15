package com.bjpowernode.learn;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    public static void main(String[] args) {
        // 获取对象
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
        // 延时2s之后再执行, 每间隔3s再执行任务
        s.scheduleAtFixedRate(() -> {
                    System.out.println(System.currentTimeMillis());
                    // 模拟耗时操作
                    try {
                        TimeUnit.SECONDS.sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, 2, 3, TimeUnit.SECONDS
        );
        // s.shutdown();
    }

    public static void schedule() {
        // 获取对象
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
        // 延时2s之后再执行
        s.schedule(() -> System.out.println(Thread.currentThread().getName()), 2, TimeUnit.SECONDS);
        // 关闭
        s.shutdownNow();
    }
}
