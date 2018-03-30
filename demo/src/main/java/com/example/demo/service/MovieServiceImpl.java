package com.example.demo.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.bean.Movie;
import com.example.demo.mapper.MovieMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/3/21
 */
@Service
@Slf4j
public class MovieServiceImpl extends ServiceImpl<MovieMapper,Movie> implements MovieService {

    @Resource
    private OtherService otherService;

    @Override
    @Cacheable(value = "all-user")
    public List<Movie> findAllUser(){
        log.debug("执行查询");
        List<Movie> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            users.add(Movie.builder().year(i).name("user"+i).build());
        }
        return users;
    }

    @Override
    @Cacheable(value = "findMovieById")
    public Movie findMovieById(){
        log.debug("执行用户ID查询："+1);
        return this.selectById(1);
    }

    private String removeBeforeAndAfterSplitStr(String str){
        if(str!=null && !"".equals(str)){
            return str.substring(1,str.length()-1);
        }
        return null;
    }


}
