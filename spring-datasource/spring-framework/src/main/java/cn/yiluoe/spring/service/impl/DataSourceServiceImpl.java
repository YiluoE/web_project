package cn.yiluoe.spring.service.impl;

import cn.yiluoe.spring.repository.impl.DataSourceRepositoryImpl;
import cn.yiluoe.spring.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-datasource
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/26 19:10
 **/

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceRepositoryImpl dataSourceRepository;

    @Override
    public void insert(String name,int age) {
        /*dataSourceRepository.insert(name,age);*/
        dataSourceRepository.insert("yiluoe",111);
        dataSourceRepository.insert("yiyiyiyiyiyiyiyi",1112);
    }
}
