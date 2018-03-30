package com.example.demo.future;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author licoy.cn
 * @version 2018/3/19
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        });*/

        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        });*/

        /*try {
//            future.get();
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        System.out.println("done");
    }
}
