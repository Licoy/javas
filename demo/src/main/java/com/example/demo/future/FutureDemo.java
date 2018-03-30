package com.example.demo.future;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @author licoy.cn
 * @version 2018/3/19
 */
public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(() -> {
            System.out.println("running task："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Thread.sleep(10000);
            return "return task!!! 10S后的相应";
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("线程睡眠失败");
        }

        System.out.println("do something else");  //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情

        try {
            while (true){ //轮训监听结果
                if(future.isDone()){
                    System.out.println("任务已完成："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    break;
                }else{
                    System.out.println("任务暂未完成");
                }
                Thread.sleep(1000);
            }
            //System.out.println(future.get(1, TimeUnit.SECONDS));  //等待 future 的执行结果，执行完毕之后打印出来
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("32行爆出异常");
        } finally {
            executorService.shutdown();
        }

    }
}
