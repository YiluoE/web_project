package cn.yiluoe.spring.ioc.service.impl;

import cn.yiluoe.spring.ioc.repository.IocRepository;
import cn.yiluoe.spring.ioc.service.IocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/24 19:33
 **/

@Service(value = "iocService")
public class IocServiceImpl implements IocService { //--非也 /*默认是根据名称查找的，如果注解类时没有name则它会将该组件自动命名为实现的接口*/
    /*因为扫描仪把指定包路径下的所有组件(bean)装配到了ioContext所以能找到*/

    /*@Resource*/ /*根据类型装配*/
    /*@Resource(name = "iocRepository")*/ /*根据名称装配*/

    @Autowired
    @Qualifier(value = "iocRepository2")
    private IocRepository iocRepository;

    /*@Value("#{configProperties.property}")*/
    public String property;

    public IocServiceImpl(){ System.out.println("server init"); }

    @PostConstruct
    public void init(){
        System.out.println(">>> 初始化");
        System.out.println("property：" + property);
    }

    @PreDestroy
    public void destroy(){
        System.out.println(">>> 析构");
    }

   /*public IocServiceImpl(IocRepository iocRepository) {
        this.iocRepository = iocRepository;
    }*/

    @Override
    public long insert() {
        return iocRepository.insert();
    }

    @Override
    public long update() {
        return iocRepository.update();
    }
}
