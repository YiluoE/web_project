package com.yiluoe.cims.test.service;

import com.yiluoe.cims.test.entity.Test;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 测试模块逻辑层接口
 * @user: 本以罗伊斯
 * @create: 2020/11/5 17:45
 **/
public interface TestService {

    /**
     * 添加测试数据
     * @param entity entity实体
     * @return 1/0 成功/失败
     */
    public abstract int insert(Test entity);

    /**
     * 删除测试数据
     * @param id 主键
     * @return 1/0 成功/失败
     */
    public abstract int delete(int id);

    /**
     * 修改测试数据
     * @param entity entity实体
     * @return 1/0 成功/失败
     */
    public abstract int update(Test entity);

    /** 查询数据库所有数据
     * @return 数据库所有数据集合
     */
    @Deprecated
    public abstract List<Test> queryAll();

    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 查询结果集
     */
    public abstract List<Test> queryByPage(Map<String,Object> params);

    /**
     * 根据主键查询单条记录
     * @param id 主键
     * @return 单条数据
     */
    public abstract Test queryById(int id);

    /**
     * 根据条件查询数据总条数
     * @param params 查询条件
     * @return 符合条件的总条数
     */
    public abstract long queryByCount(Map<String,Object> params);

}
