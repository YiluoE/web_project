package com.yiluoe.cims.person.repository.impl;

import com.mysql.cj.PreparedQuery;
import com.mysql.cj.Session;
import com.yiluoe.cims.person.entity.Person;
import com.yiluoe.cims.person.repository.PersonRepository;
import com.yiluoe.cims.util.mybatis.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 人员模块数据层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/6 14:50
 **/
public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public int insert(Person entity) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int row = 0;
        try {
            row = sqlSession.insert("person.insert",entity);
            sqlSession.commit();
        }
        catch (Exception e){
            sqlSession.rollback();
        }
        finally{
            sqlSession.close();
        }
        return row;
    }

    @Override
    public int delete(int id) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int rows = sqlSession.delete("person.delete",id);
        sqlSession.close();
        return rows;
    }

    @Override
    public int delete(int[] ids) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int rows = sqlSession.delete("person.batch",ids);
        sqlSession.close();
        return rows;
    }

    @Override
    public int update(Person entity) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        int rows = sqlSession.update("person.update",entity);
        sqlSession.close();
        return rows;
    }

    @Override
    public List<Person> queryAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Person> list = sqlSession.selectList("person.queryAll");
        sqlSession.close();
        return list;
    }

    @Override
    public List<Person> queryByPage(Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Person> list = sqlSession.selectList("person.queryByPage",params);
        sqlSession.close();
        return list;
    }

    @Override
    public Person queryById(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Person person = sqlSession.selectOne("person.queryById",id);
        sqlSession.close();
        return person;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        long count = sqlSession.selectOne("person.queryByCount",params);
        sqlSession.close();
        return count;
    }

    @Override
    public Map<String, Object> findSubsidy(int id) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        Map<String,Object> resultMap = sqlSession.selectOne("person.findSubsidy",id);
        sqlSession.close();
        return resultMap;
    }

    @Override
    public long queryByLetterOfAdmin() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        long count = sqlSession.selectOne("person.letterOfAdmin");
        sqlSession.close();
        return count;
    }
}
