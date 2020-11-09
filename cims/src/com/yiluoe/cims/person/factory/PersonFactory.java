package com.yiluoe.cims.person.factory;

import com.yiluoe.cims.person.repository.PersonRepository;
import com.yiluoe.cims.person.repository.impl.PersonRepositoryImpl;
import com.yiluoe.cims.person.service.PersonService;
import com.yiluoe.cims.person.service.impl.PersonServiceImpl;

/**
 * @program: cims
 * @description 人员模块工厂类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:09
 **/
public class PersonFactory {

    /**
     * 获取人员模块数据层接口
     * @return 数据层实现类
     */
    public static PersonRepository getRepository(){
        return new PersonRepositoryImpl();
    }

    /**
     * 获取人员模块逻辑层接口
     * @return 逻辑层接口实现类
     */
    public static PersonService getService(){
        return new PersonServiceImpl();
    }

}
