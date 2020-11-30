package cn.yiluoe.spring.repository.advice;

import cn.yiluoe.spring.repository.DataSourceRepository;
import cn.yiluoe.spring.router.DataSourceSwitcher;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: spring-datasource
 * @description 数据源动态切换通知实现
 * @user: 本以罗伊斯
 * @create: 2020/11/26 22:47
 **/

/*@Component
@Aspect*/
public class DataSourceSwitcherAdvice {

    /*@Autowired
    private JdbcOperations jdbcOperations;

    *//*1.my*//*
    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Pointcut(value = "execution(* cn.yiluoe.spring.service.impl.*.*(..))")
    public void pointCut(){}

    @AfterThrowing("pointCut()") *//*这个注解有四个参数默认的是哪个阿。*//*
    public void afterThrowing(JoinPoint joinPoint){
        System.out.println(joinPoint.getTarget().toString());
        DataSourceSwitcher.setDataSourceKey("dataSourceAli"); *//*切换数据源*//*
        *//*2.my*//*
        Object[] args = joinPoint.getArgs();
        dataSourceRepository.insert(
                (String)args[0],
                (Integer)args[1]
        );
        System.out.println("插入AliMysqlDataBaseServer。");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("插入localhost~");
    }*/

}
