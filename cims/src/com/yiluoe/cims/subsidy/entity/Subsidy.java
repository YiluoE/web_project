package com.yiluoe.cims.subsidy.entity;

import com.yiluoe.cims.person.entity.Person;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: cims
 * @description 补贴管理实体类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 17:37
 **/
public class Subsidy {

    //主键s
    private int id;
    //补贴 月份
    private Date month;
    //补贴 金额
    private BigDecimal money;
    //类型 1:供暖 2:物业
    private int type;

    private Person person;

    @Override
    public String toString() {
        return "Subsidy{" +
                "id=" + id +
                ", month=" + month +
                ", money=" + money +
                ", type=" + type +
                ", person=" + person +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
