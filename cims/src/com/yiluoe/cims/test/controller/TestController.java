package com.yiluoe.cims.test.controller;

import com.yiluoe.cims.test.entity.Test;
import com.yiluoe.cims.test.factory.TestFactory;
import com.yiluoe.cims.test.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @program: cims
 * @description 测试模块表现层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:18
 **/

@WebServlet(
        urlPatterns = "/test.do"
)
public class TestController extends HttpServlet {

    private TestService testService = TestFactory.getService(); /*注入逻辑层接口*/

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.service(req, resp);*/

        testService.insert(null);

        List<Test> list = new ArrayList<>();
        for (int i = 0; i <=10; i++) {
            Test test = new Test();
            test.setId(i+1);
            test.setAge((i+1)*2);
            test.setName("yiluoe"+(i+1));
            test.setBirthday(new Date());

            list.add(test);
        }
        req.setAttribute("list",list);

        /*先进controller再进success 如果用重定向是两次访问，请求作用域的数据会消失*/
        req.getRequestDispatcher("/success.jsp").forward(req,resp);
    }
}
