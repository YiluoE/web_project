package com.yiluoe.cims.person.service.impl;

import com.yiluoe.cims.person.entity.Person;
import com.yiluoe.cims.person.factory.PersonFactory;
import com.yiluoe.cims.person.repository.PersonRepository;
import com.yiluoe.cims.person.service.PersonService;

import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/6 14:57
 **/
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository = PersonFactory.getRepository();

    @Override
    public int insert(Person entity) {
        return personRepository.insert(entity);
    }

    @Override
    public int delete(int id) {
        return personRepository.delete(id);
    }

    @Override
    public int delete(String[] ids) {
        int[] array = new int[ids.length];
        for (int i = 0; i < ids.length; i++)
            array[i] = Integer.parseInt(ids[i]);
        return personRepository.delete(array);
    }

    @Override
    public int update(Person entity) {
        return personRepository.update(entity);
    }

    @Override
    public List<Person> queryAll() {
        return personRepository.queryAll();
    }

    @Override
    public List<Person> queryByPage(Map<String, Object> params) {
        return personRepository.queryByPage(params);
    }

    @Override
    public Person queryById(int id) {
        return personRepository.queryById(id);
    }

    @Override
    public long queryByCount(Map<String, Object> params) {
        return personRepository.queryByCount(params);
    }

    @Override
    public Map<String, Object> findSubsidy(int id) {
        return personRepository.findSubsidy(id);
    }
}
