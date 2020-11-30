package cn.yiluoe.ssm.user.service;

import cn.yiluoe.ssm.user.entity.User;

import java.util.List;

/**
 * @program: ssm
 * @description ${null}
 * @user: 本以罗伊斯
 * @create: 2020/11/29 22:56
 **/
public interface UserService {

    /**
     * 用户注册
     * @param entity 实体类
     * @return 成功状态
     */
    public int insert(User entity);
    public int create(User entity);

    /**
     * 用户修改
     * @param entity 实体类
     * @return 成功状态
     */
    public int update(User entity);

    /**
     * 用户删除
     * @param id 主键ID
     * @return 成功状态
     */
    public int delete(String id);


    /**
     * 查询所有记录
     * @return 结果集
     */
    public List<User> queryAll();

    /**
     * 根据主键查询唯一对象
     * @param id 主键id
     * @return 结果
     */
    public User queryById(String id);

    /**
     * 查询总条数
     * @return 条数
     */
    public long queryByCount();

}
