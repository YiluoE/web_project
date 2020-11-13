package com.yiluoe.cims.person.controller;

import com.yiluoe.cims.person.entity.Person;
import com.yiluoe.cims.person.factory.PersonFactory;
import com.yiluoe.cims.person.service.PersonService;
import com.yiluoe.cims.util.validate.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: cims
 * @description 归档
 * @user: 本以罗伊斯
 * @create: 2020/11/13 13:23
 **/
@WebServlet(
        urlPatterns = "/pigeonhole.do"
)
public class Pigeonhole extends HttpServlet {

    PersonService personService = PersonFactory.getService(); /*让它替你查*/

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*封装动态查询参数*/
        Map<String,Object> params = new HashMap<>();
        /*查询所有归档人员*/
        params.put("sign",0);

        /*一页显示所有*/
        long count = personService.queryByCount(params);
        params.put("offset",0);
        params.put("pageSize",count);

        List<Person> personList = personService.queryByPage(params);
        req.setAttribute("personList",personList);

        req.getRequestDispatcher("/view/poh/poh.jsp").forward(req,resp);
    }
}
