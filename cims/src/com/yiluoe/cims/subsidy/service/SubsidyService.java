package com.yiluoe.cims.subsidy.service;

import com.yiluoe.cims.subsidy.entity.Subsidy;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 补贴模块逻辑层接口
 * @user: 本以罗伊斯
 * @create: 2020/11/5 17:
 **/
public interface SubsidyService {

    /**
     * 添加补贴数据
     * @param entity entity实体
     * @return 1/0 成功/失败
     */ 
    public abstract int insert(Subsidy entity);

    /**
     *s 删除补贴数据
     * @param id 主键
     * @return 1/0 成功/失败
     */
    public abstract int delete(Map<String, Object> params);
    public abstract int batch(String[] ids,Map<String,Object> params);

    public abstract List<Map<String,Object>> querySubsidyPerson(int type);

    /**
     * 修改补贴数据
     * @param entity entity实体
     * @return 1/0 成功/失败
     */
    public abstract int update(Subsidy entity);

    /** 查询数据库所有数据
     * @return 数据库所有数据集合
     */
    @Deprecated
    public abstract List<Subsidy> queryAll();

    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 查询结果集
     */
    public abstract List<Subsidy> queryByPage(Map<String,Object> params);

    /**
     * 根据主键查询单条记录
     * @param id 主键
     * @return 单条数据
     */
    public abstract Subsidy queryById(int id);

    /**
     * 根据条件查询数据总条数
     * @param params 查询条件
     * @return 符合条件的总条数
     */
    public abstract long queryByCount(Map<String,Object> params);

}
