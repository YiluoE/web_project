package cn.yiluoe.spring.datasource.service.impl;

import cn.yiluoe.spring.datasource.service.DataSourceService;
import cn.yiluoe.spring.datasource.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: spring-datasourc2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/27 16:58
 **/

/*@Service*/
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Override
    /*@Transactional*/
    public void insert(String name, int age) {
        dataSourceRepository.insert(name,age);
        dataSourceRepository.insert("xxxxxxxxxxxxx",1123);
    }
}
