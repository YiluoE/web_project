package com.yiluoe.cims.person.repository;

import com.yiluoe.cims.person.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 人员模块数据层接口
 * @user: 本以罗伊斯
 * @create: 2020/11/5 17:45
 **/
public interface PersonRepository {

    /**
     * 添加人员数据
     * @param entity entity实体
     * @return 1/0 成功/失败
     */
    public abstract int insert(Person entity);

    /**
     * 删除人员数据
     * @param id 主键
     * @return 1/0 成功/失败
     */
    public abstract int delete(int id);
    public abstract int delete(int[] ids);

    /**
     * 修改人员数据
     * @param entity entity实体
     * @return 1/0 成功/失败
     */
    public abstract int update(Person entity);

    /** 查询数据库所有数据
     * @return 数据库所有数据集合
     */
    @Deprecated
    public abstract List<Person> queryAll();

    /**
     * 根据条件分页查询
     * @param params 查询条件
     * @return 查询结果集
     */
    public abstract List<Person> queryByPage(Map<String,Object> params);

    /**
     * 根据主键查询单条记录
     * @param id 主键
     * @return 单条数据
     */
    public abstract Person queryById(int id);

    /**
     * 根据条件查询数据总条数
     * @param params 查询条件
     * @return 符合条件的总条数
     */
    public abstract long queryByCount(Map<String,Object> params);
    public abstract Map<String,Object> findSubsidy(int id);

    public abstract long queryByLetterOfAdmin();
}
