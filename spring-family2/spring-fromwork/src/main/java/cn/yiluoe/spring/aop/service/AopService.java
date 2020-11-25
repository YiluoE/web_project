package cn.yiluoe.spring.aop.service;

import org.springframework.stereotype.Component;

/**
 * @program: spring-family2
 * @description ${null}
 * @user: 本以罗伊斯
 * @create: 2020/11/25 11:34
 **/

@Component
public interface AopService {

    public abstract long insert();
    public abstract long update();
    public abstract long insertAno(String name,int age);
    public abstract long updateAnp(String name,int age);

}
