package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.bean.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author licoy.cn
 * @version 2018/1/11
 */
@Mapper
@Repository
public interface MovieMapper extends BaseMapper<Movie> {
}
