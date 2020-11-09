package com.yiluoe.cims.subsidy.factory;

import com.yiluoe.cims.subsidy.repository.SubsidyRepository;
import com.yiluoe.cims.subsidy.repository.impl.SubsidyRepositoryImpl;
import com.yiluoe.cims.subsidy.service.SubsidyService;
import com.yiluoe.cims.subsidy.service.impl.SubsidyServiceImpl;

/**
 * @program: cims
 * @description 补贴模块工厂类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:09
 **/
public class SubsidyFactory {

    /**
     * 获取补贴模块数据层接口
     * @return 数据层实现类
     */
    public static SubsidyRepository getRepository(){
        return new SubsidyRepositoryImpl();
    }

    /**
     * 获取补贴模块逻辑层接口
     * @return 逻辑层实现类
     */
    public static SubsidyService getService(){
        return new SubsidyServiceImpl();
    }

}
