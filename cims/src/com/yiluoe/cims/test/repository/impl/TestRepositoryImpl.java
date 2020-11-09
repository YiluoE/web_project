package com.yiluoe.cims.test.repository.impl;

import com.yiluoe.cims.test.entity.Test;
import com.yiluoe.cims.test.repository.TestRepository;
import com.yiluoe.cims.util.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 测试模块数据层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:04
 **/
public class TestRepositoryImpl implements TestRepository {
    @Override
public int insert(Test entity) {
        System.out.println("页面->逻辑层->数据层->this");
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(Test entity) {
        return 0;
    }

    @Override
    public List<Test> queryAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Test> list = sqlSession.selectList("test.queryAll");
        sqlSession.close();
        return list;
    }

    @Override
    public List<Test> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Test queryById(int id) {
        return null;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        return 0;
    }
}
