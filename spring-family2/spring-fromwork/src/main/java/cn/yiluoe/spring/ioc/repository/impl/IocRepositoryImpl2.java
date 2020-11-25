package cn.yiluoe.spring.ioc.repository.impl;

import cn.yiluoe.spring.ioc.repository.IocRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/25 2:06
 **/

@Repository(value = "iocRepository2")
public class IocRepositoryImpl2 implements IocRepository {
    @Override
    public long insert() {
        return 9999L;
    }

    @Override
    public long update() {
        return 4444L;
    }
}
