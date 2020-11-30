package cn.yiluoe.spring.aop.advice;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/25 11:51
 **/

@Component
public class LoggerAdvice {

    public void logger(){
        System.out.println("日志添加：" + LocalTime.now());
    }


}
