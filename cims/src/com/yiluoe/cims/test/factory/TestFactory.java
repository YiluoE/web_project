package com.yiluoe.cims.test.factory;

import com.yiluoe.cims.test.repository.TestRepository;
import com.yiluoe.cims.test.repository.impl.TestRepositoryImpl;
import com.yiluoe.cims.test.service.TestService;
import com.yiluoe.cims.test.service.impl.TestServiceImpl;

/**
 * @program: cims
 * @description 测试模块工厂类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:09
 **/
public class TestFactory {

    /**
     * 获取测试模块数据层接口
     * @return 数据层实现类
     */
    public static TestRepository getRepository(){
        return new TestRepositoryImpl();
    }

    /**
     * 获取测试模块逻辑层接口
     * @return 逻辑层接口实现类
     */
    public static TestService getService(){
        return new TestServiceImpl();
    }

}
