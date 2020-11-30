package cn.yiluoe.ssm.user.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Data 据说能生成 get、set、toString、hashCode、equals 等等一系列方法
 * @program: ssm
 * @description 用户模块实体类
 * @user: 本以罗伊斯
 * @create: 2020/11/29 22:31
 **/

@Data
public class User {
    private String id; /*用户主键ID*/
    private String name; /*用户姓名*/
    private int age; /*用户年龄*/
    private int sex; /*用户性别*/
    private int phone; /*用户手机号*/
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) /*来自用户输入的数据.所以要格式化一下*/
    private Date birthday; /*用户出生日期*/
    private String address; /*家庭住址*/
 }
