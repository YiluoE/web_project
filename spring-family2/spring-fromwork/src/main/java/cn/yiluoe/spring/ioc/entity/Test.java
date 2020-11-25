package cn.yiluoe.spring.ioc.entity;

import javax.annotation.PostConstruct;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/25 3:38
 **/
public class Test {

    private String property;

    @PostConstruct
    public void init(){
        System.out.println(property);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
