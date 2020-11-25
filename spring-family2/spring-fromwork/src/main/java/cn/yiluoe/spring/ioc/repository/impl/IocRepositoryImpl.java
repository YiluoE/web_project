package cn.yiluoe.spring.ioc.repository.impl;

import cn.yiluoe.spring.ioc.repository.IocRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/24 19:31
 **/

@Primary
@Repository(value = "iocRepository")
public class IocRepositoryImpl implements IocRepository {

    public IocRepositoryImpl(){
        System.out.println("repository init");
    }

    @Override
    public long insert() {
        return 1000;
    }

    @Override
    public long update() {
        return 2000;
    }
}
