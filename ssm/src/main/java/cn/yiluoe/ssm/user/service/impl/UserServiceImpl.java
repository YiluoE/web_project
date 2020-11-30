package cn.yiluoe.ssm.user.service.impl;

import cn.yiluoe.ssm.user.entity.User;
import cn.yiluoe.ssm.user.repository.UserRepository;
import cn.yiluoe.ssm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @program: ssm
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/29 22:56
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int insert(User entity) {
        entity.setId(UUID.randomUUID().toString().replace("-",""));
        userRepository.insert(entity);
        return 0;
    }

    @Override
    public int create(User entity) {
        return userRepository.insert(entity);
    }

    @Override
    public int update(User entity) {
        return userRepository.update(entity);
    }

    @Override
    public int delete(String id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> queryAll() {
        return userRepository.queryAll();
    }

    @Override
    public User queryById(String id) {
        return userRepository.queryById(id);
    }

    @Override
    public long queryByCount() {
        return userRepository.queryByCount();
    }
}
