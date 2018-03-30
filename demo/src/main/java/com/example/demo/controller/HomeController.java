package com.example.demo.controller;

import com.example.demo.annotation.Function;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author licoy.cn
 * @version 2017/11/6
 */
@Controller
@RequestMapping(value = {"/"})
public class HomeController {

    @RequestMapping
    @ResponseBody
    @Function(name = "home")
    public String index(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("start："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ok："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        });
        return "response ok!";
    }


}
