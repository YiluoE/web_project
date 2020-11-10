package com.yiluoe.cims.subsidy.service.impl;

import com.yiluoe.cims.subsidy.entity.Subsidy;
import com.yiluoe.cims.subsidy.factory.SubsidyFactory;
import com.yiluoe.cims.subsidy.repository.SubsidyRepository;
import com.yiluoe.cims.subsidy.service.SubsidyService;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 补贴模块逻辑层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/9 15:44
 **/
public class SubsidyServiceImpl implements SubsidyService {
    private SubsidyRepository subsidyRepository = SubsidyFactory.getRepository();

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
        return subsidyRepository.queryAll();
    }

    @Override
    public List<Subsidy> queryByPage(Map<String, Object> params) {
        return subsidyRepository.queryByPage(params);
    }

    @Override
    public Subsidy queryById(int id) {
        return null;
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        return subsidyRepository.queryByCount(params);
    }
}
