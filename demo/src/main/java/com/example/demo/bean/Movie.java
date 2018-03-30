package com.example.demo.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/1/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie implements Serializable {
    @TableId
    private Long id;
    private String name;
    private Integer year;
    private String language;
    private String genre;
    private String area;
    private String releaseDate;
    private String whenLong;
    private String alias;
    private String doubanScore;
    private String director;
    private String screenwriter;
    private String starring;
    private String introduce;
    private String coverUrl;
    private String quality;
    private String doubanId;
    private String doubanUrl;
    private String category;
    private Date createDate;
    private Long yearsId;
    private Integer doubanHasPlayVideo;
    private Integer views;
    private Integer support;
    private Integer oppose;
    private Integer share;
    private Integer collect;
}
