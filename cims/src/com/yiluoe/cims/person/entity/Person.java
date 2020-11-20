package com.yiluoe.cims.person.entity;

import java.util.Date;

/**
 * @program: cims
 * @description 人员管理实体类
 * @user: 本以罗伊斯
 * @create: 2020/11/6 14:41
 **/
public class Person implements java.io.Serializable{

    private int id;
    private String name;
    private String card;
    private int state;
    private int grade;
    private Date start;
    private int heating;
    private int property;
    private String reason;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", state=" + state +
                ", grade=" + grade +
                ", start=" + start +
                ", heating=" + heating +
                ", property=" + property +
                ", reason='" + reason + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getHeating() {
        return heating;
    }

    public void setHeating(int heating) {
        this.heating = heating;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
