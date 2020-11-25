package cn.yiluoe.spring.aop.service.impl;

import cn.yiluoe.spring.aop.repository.AopRepository;
import cn.yiluoe.spring.aop.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/25 11:35
 **/

@Service
public class AopServiceImpl implements AopService {

    @Autowired
    private AopRepository aopRepository;

    @Override
    public long insert() {
        System.out.println("AopService insert");
        return 0;
    }

    @Override
    public long update() {
        System.out.println("AopService update");
        return 0;
    }

    @Override
    public long insertAno(String name,int age) {
        System.out.println("AopService insertAno");
        return 0;
    }

    @Override
    public long updateAnp(String name,int age) {
        System.out.println("AopService insertAno");
        return 0;
    }
}
