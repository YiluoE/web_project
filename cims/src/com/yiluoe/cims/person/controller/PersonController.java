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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cims
 * @description 人员管理表现层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/6 14:26
 **/
@WebServlet(
        name = "PersonController",
        urlPatterns = "/person.do",
        loadOnStartup = 0
)
public class PersonController extends HttpServlet {

    private PersonService personService = PersonFactory.getService();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 收集客户端操作类型
        String formType = req.getParameter("formType");
        /*添加操作*/
        if(Validator.isNotEmpty(formType) && "create".equals(formType)){
            //1.收集参数
            String name = req.getParameter("name");
            String card = req.getParameter("card");
            String state = req.getParameter("state");
            String grade = req.getParameter("grade");
            String start = req.getParameter("start");
            String heating = req.getParameter("heating");
            String property = req.getParameter("property");
            String reason = req.getParameter("reason");
            //2.封装属性
            Person person = new Person();
            person.setName(name);
            person.setCard(card);
            if(Validator.isInteger(state)){
                person.setState(Integer.parseInt(state));
            }
            if(Validator.isInteger(grade)){
                person.setGrade(Integer.parseInt(grade));
            }
            if(Validator.isDate(start)){
                try {
                    person.setStart( simpleDateFormat.parse(start) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(Validator.isInteger(heating)){
                person.setHeating(1);
            }
            if(Validator.isInteger(property)){
                person.setProperty(1);
            }
            person.setReason(reason);
            //3.调用逻辑层
            personService.insert(person);
            //4.返回响应
            resp.sendRedirect(req.getContextPath()+"/person.do");
        }
        /*跳转至修改界面*/
        else if(Validator.isNotEmpty(formType) && "load".equals(formType)){
            String id = req.getParameter("id");
            if(Validator.isInteger(id)){
                Person person = personService.queryById(Integer.parseInt(id));
                req.setAttribute("entity",person);
                req.getRequestDispatcher("/view/person/update.jsp").forward(req,resp);
                return;
            }
            /*id不合法则重定向到查询页面*/
            /*话说什么情况id才会不合法呢*/
            resp.sendRedirect(req.getContextPath()+"person.do");
        }
        /*修改人员数据*/
        else if(Validator.isNotEmpty(formType) && "update".equals(formType)){
            //1.收集参数
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String card = req.getParameter("card");
            String state = req.getParameter("state");
            String grade = req.getParameter("grade");
            String start = req.getParameter("start");
            String heating = req.getParameter("heating");
            String property = req.getParameter("property");
            String reason = req.getParameter("reason");
            //2.封装属性
            Person person = new Person();
            if(Validator.isInteger(id)){
                person.setId(Integer.parseInt(id));
            }
            person.setName(name);
            person.setCard(card);
            if(Validator.isInteger(state)){
                person.setState(Integer.parseInt(state));
            }
            if(Validator.isInteger(grade)){
                person.setGrade(Integer.parseInt(grade));
            }
            if(Validator.isDate(start)){
                try {
                    person.setStart( simpleDateFormat.parse(start) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(Validator.isInteger(heating)){
                person.setHeating(1);
            }
            if(Validator.isInteger(property)){
                person.setProperty(1);
            }
            person.setReason(reason);
            //3.调用逻辑层
            personService.update(person);
            //4.返回响应
            resp.sendRedirect(req.getContextPath()+"/person.do");
        }
        /*删除人员数据*/
        else if(Validator.isNotEmpty(formType) && "delete".equals(formType)){
            String idParam = req.getParameter("id");
            int id = 0;
            if(!Validator.isInteger(idParam))
                return;

            id = Integer.parseInt(idParam);

            personService.delete(id);
            /*返回响应*/
            resp.sendRedirect(req.getContextPath()+"/person.do");
        }
        /*批量删除人员数据*/
        else if(Validator.isNotEmpty(formType) && "batch".equals(formType)){
            //1.收集参数
            String[] ids = req.getParameterValues("ids");
            //2.调用逻辑层
            if(Validator.isNotEmpty(ids)){
                personService.delete(ids);
            }
            //3.返回响应
            resp.sendRedirect(req.getContextPath()+"/person.do");
        }
        /*查询操作*/
        else {
            //req.setAttribute("personList",personService.queryAll());

            int thisPage = 1;
            String thisPageParam = req.getParameter("thisPage");

            if(Validator.isInteger(thisPageParam))
                thisPage = Integer.parseInt(thisPageParam);

            /*最大页*/
            int pageSize = 10;

            /*从多少开始查询*/
            int offset = (thisPage-1)*pageSize;

            /*封装动态查询参数*/
            Map<String,Object> params = new HashMap<>();
            params.put("offset",offset);
            params.put("pageSize",pageSize);
            params.put("name",req.getParameter("name"));
            params.put("card",req.getParameter("card"));

            String state = req.getParameter("state");
            if(Validator.isInteger(state)){
                params.put("state",Integer.parseInt(state));
            }

            /*查询日期区间*/
            String ssd = req.getParameter("sdate");
            String sed = req.getParameter("edate");

            if(Validator.isDate(ssd) && Validator.isDate(sed)){
                //LocalDate slocalDate = LocalDate.parse(sdate,DateTimeFormatter.ISO_DATE);

                try {
                    Date sdate = simpleDateFormat.parse(ssd);
                    Date edate = simpleDateFormat.parse(sed);

                    long ms = edate.getTime() - sdate.getTime();
                    /*如果s大于e则不查询，或者你自己去完善...*/
                    if(ms >= 0){
                        params.put("ssd",ssd);
                        params.put("sdate",sdate);
                        params.put("sed",sed);
                        params.put("edate",edate);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            /*补贴查询*/
            String heating = req.getParameter("heating");
            if(Validator.isNotEmpty(heating)){
                if("on".equals(heating))
                    params.put("heating",1);
                else
                    params.put("heating",0);
            }

            String property = req.getParameter("property");
            if(Validator.isNotEmpty(property)){
                if("on".equals(property))
                    params.put("property",1);
                else
                    params.put("property",0);
            }

            long count = personService.queryByCount(params);
            int maxPage = (int)Math.ceil(count*1.0/pageSize);

            req.setAttribute("params",params);
            req.setAttribute("thisPage",thisPage);
            req.setAttribute("count",count);
            req.setAttribute("maxPage",maxPage);
            req.setAttribute("personList",personService.queryByPage(params));


            req.getRequestDispatcher("/view/person/person.jsp").forward(req,resp);
        }
    }
}
