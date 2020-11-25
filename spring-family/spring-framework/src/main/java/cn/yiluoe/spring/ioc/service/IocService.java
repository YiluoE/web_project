package cn.yiluoe.spring.ioc.service;

import cn.yiluoe.spring.ioc.repository.IocRepository;

/**
 * @program: spring-family
 * @description ${null}
 * @user: 本以罗伊斯
 * @create: 2020/11/23 23:40
 **/
public interface IocService {

    /*用于ioc依赖注入*/
    public abstract void setIcoRepository(IocRepository icoRepository);

    public abstract long insert();
    public abstract long update();

}
