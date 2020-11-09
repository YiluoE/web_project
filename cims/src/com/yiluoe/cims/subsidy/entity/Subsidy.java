package com.yiluoe.cims.subsidy.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: cims
 * @description 数据库对象实体
 * @user: 本以罗伊斯
 * @create: 2020/11/5 17:37
 **/
public class Subsidy {

    private int id;
    private int personid;
    private Date date;
    private String name;
    private String card;
    private int grade;
    private BigDecimal money;

    @Override
    public String toString() {
        return "Subsidy{" +
                "id=" + id +
                ", personid=" + personid +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", grade=" + grade +
                ", money=" + money +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
