package com.example.demo.controller;

import com.example.demo.annotation.Function;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author licoy.cn
 * @version 2018/3/18
 */
@Controller
@RequestMapping("/error")
@EnableConfigurationProperties({ServerProperties.class})
@Function(name = "errors")
public class ErrorsController implements ErrorController {

    @GetMapping(value = {"/404"})
    public String errorFor404(){
        return "/error/404";
    }

    @GetMapping(value = {"/500"})
    public String errorFor500(){
        return "/error/500";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
