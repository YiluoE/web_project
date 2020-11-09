package com.yiluoe.cims.test.service.impl;

import com.yiluoe.cims.test.entity.Test;
import com.yiluoe.cims.test.factory.TestFactory;
import com.yiluoe.cims.test.repository.TestRepository;
import com.yiluoe.cims.test.service.TestService;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 测试模块逻辑层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:08
 **/
public class TestServiceImpl implements TestService {

    private TestRepository testRepository = TestFactory.getRepository(); /*注入数据层接口*/

    @Override
    public int insert(Test entity) {
        testRepository.insert(entity);

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
        return null;
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
