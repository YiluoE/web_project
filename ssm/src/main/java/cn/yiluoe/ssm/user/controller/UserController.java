package cn.yiluoe.ssm.user.controller;

import cn.yiluoe.ssm.user.entity.User;
import cn.yiluoe.ssm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: ssm
 * @description 用户模块-表现层-实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/29 23:01
 **/

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService; /*为什么红线,我也写了实现类阿,只是它不够智能而已*/

    @RequestMapping("query")
    public String query(ModelMap model){
        model.put("userList",userService.queryAll());

        return "user/list";
    }

    @RequestMapping("insert")
    public String insert(User entity){

        System.out.println(entity);
        userService.insert(entity);

        return "redirect:query.do";
    }

}
