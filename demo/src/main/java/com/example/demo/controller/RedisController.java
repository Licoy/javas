package com.example.demo.controller;

import com.example.demo.annotation.Function;
import com.example.demo.bean.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/3/20
 */
@RequestMapping(value = "/redis")
@Controller
@Function(name = "redis")
public class RedisController {

    @Resource
    private MovieService movieService;

    @Cacheable(value = "get")
    @RequestMapping(value = "/{id}")
    public Object get(@PathVariable("id") Integer id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("data",id);
        map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "11";
    }

    @RequestMapping(value = "all-user")
    public List<Movie> allUser(){
        return movieService.findAllUser();
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public Movie findMovieById(){
        return movieService.findMovieById();
    }



}
