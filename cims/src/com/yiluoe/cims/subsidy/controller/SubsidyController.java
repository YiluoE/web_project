package com.yiluoe.cims.subsidy.controller;

import com.google.gson.Gson;
import com.yiluoe.cims.person.entity.Person;
import com.yiluoe.cims.subsidy.entity.Subsidy;
import com.yiluoe.cims.subsidy.factory.SubsidyFactory;
import com.yiluoe.cims.subsidy.service.SubsidyService;
import com.yiluoe.cims.util.validate.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cims
 * @description 测试模块表现层实现类
 * @user: 本以罗伊斯
 * @create: 2020/11/5 18:18
 **/

@WebServlet(
        urlPatterns = "/subsidy.do"
)
public class SubsidyController extends HttpServlet {

    private static SubsidyService subsidyService = SubsidyFactory.getService(); /*注入逻辑层接口*/
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
    private Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> params = new HashMap<>();

        //. 获取请求类型 pageType: update submitUpdate create
        String pageType = req.getParameter("pageType");

        //. 收集补贴类型 一张表的难处
        String typeParam = req.getParameter("type");
        int type = 0;
        if (Validator.isInteger(typeParam))
            type = Integer.parseInt(typeParam);

        /*........感觉没用.........*/
        /*如果是提交类型*/
        String submitType = req.getParameter("submitType");
        if(null != submitType && !"".equals(submitType))
            pageType = submitType;

        params.put("pageType",pageType);
        params.put("type",type);

        //.默认查询响应类型
        if(null == pageType || !Validator.isNotEmpty(pageType)){
            //.页数处理
            int thisPage = 1;
            int pageSize = 10;
            String thisPageParams = req.getParameter("thisPage");
            if(Validator.isNotEmpty(thisPageParams)){
                if(Validator.isInteger(thisPageParams)){
                    thisPage = Integer.parseInt(thisPageParams);
                }
            }

            //.收集查询参数
            String name = req.getParameter("name");
            if(Validator.isNotEmpty(name) && !"".equals(name))
                params.put("name",name);

            String card = req.getParameter("card");
            if(Validator.isNotEmpty(card) && !"".equals(card))
                params.put("card",card);

            /*在sql语句里按日期查询*/
            String dateStr = req.getParameter("month");
            if(Validator.isNotEmpty(dateStr) && !"".equals(dateStr)){
                Date utilDate = null;
                try {
                    utilDate = simpleDateFormat.parse(dateStr);
                    params.put("date",dateStr);
                    params.put("utilDate",utilDate);
                } catch (ParseException e) {
                    System.out.println("啊？？？？" + e.getMessage());
                }
            }

            //.查询符合条件的总条数
            long count = subsidyService.queryByCount(params);
            int maxPage = (int)Math.ceil(count*1.0 / pageSize);

            int offset = (thisPage-1)*10;
            //.收集分页参数
            params.put("offset",offset);
            params.put("pageSize",pageSize);
            //.为前端收集参数
            params.put("thisPage",thisPage);
            params.put("maxPage",maxPage);
            params.put("count",count);

            //.查询符合条件的所有数据且分页
            req.setAttribute("subsidyList",subsidyService.queryByPage(params));
            req.setAttribute("params",params);

            req.getRequestDispatcher("view/subsidy/subsidy.jsp").forward(req,resp);
        }
        else if("create".equals(pageType)){
            Subsidy subsidy = pkg(req);
            subsidy.setType(type);

            System.out.println();
            subsidyService.insert(subsidy);

            resp.sendRedirect(req.getContextPath()+"/subsidy.do?type="+type);
        }
        else if("update".equals(pageType)){
            String id = req.getParameter("id");
            if(Validator.isNotEmpty(id) && Validator.isInteger(id)){
                //.收集数据id并查询返回 和 回显
                Subsidy subsidy = subsidyService.queryById(Integer.parseInt(id));
                req.setAttribute("subsidy",subsidy);
                req.setAttribute("pageType",pageType+"Submit");
                /*id 和 type在 params 里*/
                req.getRequestDispatcher("/view/subsidy/update.jsp").forward(req,resp);
            }
        }
        else if("updateSubmit".equals(pageType)){
            Subsidy subsidy = pkg(req);
            subsidy.setType(type);

            subsidyService.update(subsidy);
            resp.sendRedirect(req.getContextPath()+"/subsidy.do?type="+type);
        }
        else if("delete".equals(pageType)){
            String idParam = req.getParameter("id");
            if(Validator.isInteger(idParam)){
                params.put("id",Integer.parseInt(idParam));
                params.put("type",type);

                subsidyService.delete(params);
            }
            resp.sendRedirect(req.getContextPath()+"/subsidy.do?type="+type);
        }
        else if("batch".equals(pageType)){ /*这样判断可以防止空指*/
            String[] ids = req.getParameterValues("ids");
            if(ids.length < 1)
                throw new NullPointerException("话说一条没选的话能进到这儿么！");

            //.剩下的交给逻辑层
            subsidyService.batch(ids,Map.of("type",type));

            resp.sendRedirect(req.getContextPath()+"/subsidy.do?type="+typeParam);//到这了 数据层还没写呢
        }
        else if("subsidyPerson".equals(pageType)){
            List<Map<String,Object>> list = subsidyService.querySubsidyPerson(type);

            /*StringBuilder stringBuilder = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> o = list.get(i);

                stringBuilder.append("{\"id\": "+o.get("id")+",\"name\": \""+o.get("name")+"\",\"card\": \""+o.get("card")+"\"}");
                if(i < list.size() -1)
                    stringBuilder.append(",");
            }
            stringBuilder.append("]");*/

            String json = gson.toJson(list);

            resp.setContentType("application/json;charset=utf8");
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        }

    }

    /*数据验证什么的可以写在这里*/
    private Subsidy pkg(HttpServletRequest req){
        Subsidy subsidy = new Subsidy();

        String id = req.getParameter("id");
        if(null != id && Validator.isNotEmpty(id))
            subsidy.setId(Integer.parseInt(id));

        String money = req.getParameter("money");
        if(null != money && Validator.isNotEmpty(money) && Validator.isInteger(money))
            subsidy.setMoney(new BigDecimal(money));

        String personID = req.getParameter("personID");
        if(null != money && Validator.isNotEmpty(personID) && Validator.isInteger(personID))
            subsidy.setPersonID(Integer.parseInt(personID));

        Person person = new Person();
        person.setName(req.getParameter("name"));
        person.setCard(req.getParameter("card"));
        person.setReason(req.getParameter("reason"));

        subsidy.setPerson(person);
        return subsidy;
    }

}