package cn.yiluoe.spring.ioc.repository.impl;

import cn.yiluoe.spring.ioc.repository.IocRepository;

/**
 * @program: spring-family
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/24 0:38
 **/
public class IocRepositoryImpl implements IocRepository {

    public IocRepositoryImpl(){
        System.out.println("数据层实例化。");
    }

    @Override
    public long insert() {
        return 1000;
    }

    @Override
    public long update() {
        return 1111;
    }
}
