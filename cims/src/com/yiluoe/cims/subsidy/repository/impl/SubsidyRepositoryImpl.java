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
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int row = sqlSession.insert("subsidy.insert",entity);
        sqlSession.close();
        return row;
    }

    @Override
    public int delete(Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int row = sqlSession.delete("subsidy.delete",params);
        sqlSession.close();
        return row;
    }

    @Override
    public int batch(int[] ids, Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int row = sqlSession.delete("subsidy.batch",Map.of("ids",ids,"type",params.get("type")));
        sqlSession.close();
        return row;
    }

    @Override
    public List<Map<String, Object>> querySubsidyPerson(int type) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Map<String,Object>> list = sqlSession.selectList("subsidy.querySubsidyPerson",Map.of("type",type));
        sqlSession.close();
        return list;
    }

    @Override
    public int update(Subsidy entity) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int row = sqlSession.update("subsidy.update",entity);
        sqlSession.close();
        return row;
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
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Subsidy subsidy = sqlSession.selectOne("subsidy.queryById",id);
        sqlSession.close();
        return subsidy;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        long count = sqlSession.selectOne("subsidy.queryByCount",params);
        sqlSession.close();
        return count;
    }
}
