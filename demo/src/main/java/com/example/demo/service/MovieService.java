package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.bean.Movie;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/3/21
 */
public interface MovieService extends IService<Movie> {

    List<Movie> findAllUser();

    Movie findMovieById();

}
