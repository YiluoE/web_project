package com.yiluoe.cims.subsidy.repository.impl;

import com.yiluoe.cims.subsidy.entity.Subsidy;
import com.yiluoe.cims.subsidy.repository.SubsidyRepository;
import com.yiluoe.cims.util.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 补贴模块数据层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/9 15:42
 **/
public class SubsidyRepositoryImpl implements SubsidyRepository {
    @Override
    public int insert(Subsidy entity) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(Subsidy entity) {
        return 0;
    }

    @Override
    public List<Subsidy> queryAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Subsidy> list = sqlSession.selectList("subsidy.queryAll");
        sqlSession.close();
        return list;
    }

    @Override
    public List<Subsidy> queryByPage(Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Subsidy> list = sqlSession.selectList("subsidy.queryByPage",params);
        sqlSession.close();
        return list;
    }

    @Override
    public Subsidy queryById(int id) {
        return null;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        long count = sqlSession.selectOne("subsidy.queryByCount");
        sqlSession.close();
        return count;
    }
}
