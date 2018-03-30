package com.example.demo;

import org.springframework.util.AntPathMatcher;

/**
 * @author licoy.cn
 * @version 2018/3/19
 */
public class Test {
    public static void main(String[] args) {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/app/name/**/*.html","/app/name/12/category/fsaf.html"));
    }
}
