package com.yiluoe.cims.util.cache;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @program: cims
 * @description mybatis二级缓存
 * @user: 本以罗伊斯
 * @create: 2020/11/19 15:19
 **/
public class MyBatisCache implements Cache {

    private String id;

    public MyBatisCache(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 从数据库查询后传入查询结果
     * @param o
     * @param o1
     */
    @Override
    public void putObject(Object o, Object o1) {
        System.out.println("存放数据key：" + o);
        System.out.println("存放数据value：" + 1);
    }

    /**
     * 一级缓存没有则调用该方法
     * @param o
     * @return
     */
    @Override
    public Object getObject(Object o) {
        System.out.println("获取数据key：" + o);
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }


    @Override
    public int getSize() {
        return 0;
    }

    /*读写锁*/
    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
