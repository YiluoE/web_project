package cn.yiluoe.spring.aop.repository.impl;

import cn.yiluoe.spring.aop.repository.AopRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/25 11:35
 **/

@Repository
public class AopRepositoryImpl implements AopRepository {
    @Override
    public long insert() {
        System.out.println("AopRepository insert");
        return 0;
    }

    @Override
    public long update() {
        System.out.println("AopRepository update");
        return 0;
    }
}
