package cn.yiluoe.spring.datasource.repository.impl;

import cn.yiluoe.spring.datasource.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-datasourc2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/27 16:58
 **/

@Repository
public class DataSourceRepositoryImpl implements DataSourceRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void insert(String name, int age) {
        jdbcOperations.update("insert into spring_jdbc(name,age) values(?,?)",new Object[]{name,age});
    }
}
