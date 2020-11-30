package cn.yiluoe.spring.datasource.service.impl;

import cn.yiluoe.spring.datasource.repository.DataSourceRepository;
import cn.yiluoe.spring.datasource.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @program: spring-datasourc2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/27 16:58
 **/

@Service
public class DataSourceServiceImplTransactionTemplate implements DataSourceService {

    @Autowired
    private DataSourceRepository dataSourceRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void insert(String name,final int age) {

        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {

                dataSourceRepository.insert(name,age);
                dataSourceRepository.insert("xxxxx",1123);

                return null;
            }
        });

    }
}
