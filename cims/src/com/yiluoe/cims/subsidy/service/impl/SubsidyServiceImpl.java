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
    public int delete(Map<String, Object> params) {
        return subsidyRepository.delete(params);
    }

    @Override
    public int batch(String[] ids, Map<String, Object> params) {
        int[] array = new int[ids.length];
        for (int i = 0; i < ids.length; i++)
            array[i] = Integer.parseInt(ids[i]);

        return subsidyRepository.batch(array,params);
    }

    @Override
    public List<Map<String, Object>> querySubsidyPerson(int type) {
        return subsidyRepository.querySubsidyPerson(type);
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
        return subsidyRepository.queryById(id);
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        return subsidyRepository.queryByCount(params);
    }
}
