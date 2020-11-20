package com.yiluoe.cims.person.controller;

import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Gson gson = new Gson();

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
                throw new NullPointerException("怎么可能！");

            id = Integer.parseInt(idParam);

            personService.delete(id);
            /*返回响应*/
            resp.sendRedirect(req.getContextPath()+"/person.do");
        }
        /*查询人员绑定的补贴*/
        else if(Validator.isNotEmpty(formType) && "findSubsidy".equals(formType)){
            String pid = req.getParameter("pid");
            Map<String,Object> result = personService.findSubsidy(Integer.parseInt(pid));

            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"count\":"+result.get("count")+",\"sum\":"+result.get("sum"));
            stringBuilder.append("}");

            resp.setContentType("application/json;charset-utf8");
            PrintWriter writer = resp.getWriter();
            writer.write(stringBuilder.toString());
            writer.flush();
            writer.close();
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
        /*效验身份证唯一性*/
        else if(Validator.isNotEmpty(formType) && "card".equals(formType)){
            //1. 获取前端请求的身份证号码
            String card = req.getParameter("card");

            if(Validator.isNotEmpty(card) && card.length() == 18 ){
                //2.查询数据库
                Map<String,Object> p = Map.of("card",card);
                long count = personService.queryByCount(p);

                /*3.转为洁身字符串，且设置响应类型为洁身*/
                resp.setContentType("application/json");
                String json = gson.toJson(Map.of("success",count == 0));

                //3.返回响应
                PrintWriter writer = resp.getWriter();
                writer.write(json);
                writer.flush();
                writer.close();
            }
        }
        /*查询操作*/
        else {
            //req.setAttribute("personList",personService.queryAll());

            /*封装动态查询参数*/
            Map<String,Object> params = new HashMap<>();
            params.put("name",req.getParameter("name"));
            params.put("card",req.getParameter("card"));
            params.put("sign",1); /*查询所有未归档人员*/

            String state = req.getParameter("state");
            if(Validator.isInteger(state)){
                params.put("state",Integer.parseInt(state));
            }

            /*查询日期区间*/
            String ssd = req.getParameter("sdate");
            String sed = req.getParameter("edate");
            params.put("ssd",ssd);
            params.put("sed",sed);

            try {
                if(Validator.isDate(ssd)){
                    Date sdate = simpleDateFormat.parse(ssd);
                    params.put("sdate",sdate);
                }
                if(Validator.isDate(sed)){
                    Date edate = simpleDateFormat.parse(sed);
                    params.put("edate",edate);
                }

                //LocalDate slocalDate = LocalDate.parse(sdate,DateTimeFormatter.ISO_DATE);
                /*开始日期和不能大于结束日期就不加了吧还没单条件查询好用*/
                //long ms = edate.getTime() - sdate.getTime();
                //if(ms < 0) ...;

            }
            catch (ParseException e){ e.printStackTrace(); }

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
            /*-----------------------分页查-----------------------*/
            int thisPage = 1;
            String thisPageParam = req.getParameter("thisPage");
            if(Validator.isInteger(thisPageParam))
                thisPage = Integer.parseInt(thisPageParam);

            int pageSize = 10;
            long count = personService.queryByCount(params);
            int maxPage = (int)Math.ceil(count*1.0/pageSize);

            /*分页查*/ /*从多少开始查询*/
            /*如果总结果条数的页数大于等于当前页则按当前页查询 否则从0查*/
            int offset = 0;
            if( maxPage >= thisPage )
                offset = (thisPage-1)*pageSize;
            else
                thisPage = 1;


            params.put("offset",offset);
            params.put("pageSize",pageSize);

            req.setAttribute("params",params);
            req.setAttribute("thisPage",thisPage);
            req.setAttribute("count",count);
            req.setAttribute("maxPage",maxPage);
            req.setAttribute("personList",personService.queryByPage(params));


            req.getRequestDispatcher("/view/person/person.jsp").forward(req,resp);
        }
    }
}
