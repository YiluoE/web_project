package cn.yiluoe.spring.aop.service.impl;

import cn.yiluoe.spring.aop.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @program: spring-family2
 * @description 静态代理 代理类
 * @user: 本以罗伊斯
 * @create: 2020/11/25 12:11
 **/

/*---------------静态代理--------------*/
/*表现层不再直接调用原来的逻辑层，注入静态代理类从而间接调用*/

@Service
public class ProxyAopService implements AopService {

    private AopService aopService;

    public ProxyAopService(@Autowired AopService aopService) {
        this.aopService = aopService;
    }

    @Override
    public long insert() {
        long rows = aopService.insert();
        System.out.println("日志输出：" + LocalDateTime.now());
        return rows;
    }

    @Override
    public long update() {
        long rows = aopService.update();
        System.out.println("日志输出：" + LocalDateTime.now());
        return rows;
    }

    @Override
    public long insertAno(String name,int age) {
        System.out.println("ProxyAopService insertAno");
        return 0;
    }

    @Override
    public long updateAnp(String name,int age) {
        System.out.println("ProxyAopService updateAnp");
        return 0;
    }

}
