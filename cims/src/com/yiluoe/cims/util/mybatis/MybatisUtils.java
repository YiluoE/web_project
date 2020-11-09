package com.yiluoe.cims.util.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: cims
 * @description mybatis工具类，负责提供SqlSession接口
 * @user: 本以罗伊斯
 * @create: 2020/11/6 13:56
 **/
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static{
        try {
            /*1.读取mybatis配置文件*/
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            /*2.构建SqlSessionFactory工厂*/
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession
     * @return SqlSession
     */
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

    public static SqlSession openSqlSession(){
        return sqlSessionFactory.openSession(true);
    }

}
