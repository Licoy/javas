package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author licoy.cn
 * @version 2018/3/26
 */
@Aspect
@Configuration
@Slf4j
public class FunctionAOP {

    /*@Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void doing(){}*/

    @Pointcut("@annotation(com.example.demo.annotation.Function)")
    public void doing(){}

    @Before("doing()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("AOP---：url：{}",request.getRequestURL().toString());
        log.info("AOP---：http_method：{}",request.getMethod());
        log.info("AOP---：ip：",request.getRemoteAddr());
        log.info("AOP---：class_method：",joinPoint.getSignature().getDeclaringTypeName()+
                "."+joinPoint.getSignature().getName());
        log.info("AOP---：args：", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "o",pointcut = "doing()")
    public void doAfter(Object o){
        log.info("AOP---：方法处理完成，返回值如下：");
        log.info("AOP---：返回值："+o.toString());
    }

    @AfterThrowing(pointcut = "doing()")
    public void throwAfter(){
        log.error("AOP---：方法执行时出现异常");
    }

    @After("doing()")
    public void after(){
        log.info("AOP---：方法执行完成最终通知");
    }

    @Around("doing()")
    public Object around(ProceedingJoinPoint joinPoint) throws Exception{
        log.info("AOP---：方法环绕：start");
        try {
            Object o = joinPoint.proceed();
            log.info("AOP---：方法环绕：proceed");
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
