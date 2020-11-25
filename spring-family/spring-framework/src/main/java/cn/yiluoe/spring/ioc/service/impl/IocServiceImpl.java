package cn.yiluoe.spring.ioc.service.impl;

import cn.yiluoe.spring.ioc.repository.IocRepository;
import cn.yiluoe.spring.ioc.service.IocService;

/**
 * @program: spring-family
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/23 23:41
 **/
public class IocServiceImpl implements IocService {

    private IocRepository icoRepository;

    public IocServiceImpl(){
        System.out.println("逻辑层实例化。");
    }

    public IocServiceImpl(IocRepository iocRepository){
        this.icoRepository = iocRepository;
        System.out.println("逻辑层使用了构造注入。");
    }

    @Override
    public void setIcoRepository(IocRepository icoRepository) {
        this.icoRepository = icoRepository;
    }

    @Override
    public long insert() {
        if(null != icoRepository)
            return icoRepository.insert();
        else
            return 0;
    }

    @Override
    public long update() {
        System.out.println("update");
        return 0;
    }
}
