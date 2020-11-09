package com.yiluoe.cims.test.entity;

import java.util.Date;

/**
 * @program: cims
 * @description 数据库对象实体
 * @user: 本以罗伊斯
 * @create: 2020/11/5 17:37
 **/
public class Test {

    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
