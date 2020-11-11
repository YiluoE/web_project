package com.yiluoe.cims.subsidy.controller;

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
import java.math.BigDecimal;
import java.util.HashMap;
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
    //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> params = new HashMap<>();

        //.收集页面参数 type:1供暖 type:2物业 pageType:create添加 pageType:update修改
        String pageType = req.getParameter("pageType");
        String[] pageTypeAry = req.getParameterValues("pageType");
        if(null != pageTypeAry && pageTypeAry.length > 1)
            throw new NullPointerException("它出现了，两个pageType!");

        String typeParam = req.getParameter("type");
        int type = 0;
        if (Validator.isInteger(typeParam))
            type = Integer.parseInt(typeParam);

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
            String date = req.getParameter("month");
            if(Validator.isNotEmpty(date) && !"".equals(date))
                params.put("date",date);

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
            req.setAttribute("submitType",pageType+"Submit");
            //.收集并封装参数
            req.getRequestDispatcher("/view/subsidy/create.jsp").forward(req,resp);
        }
        else if("createSubmit".equals(pageType)){
            /*收集数据直接插*/
            /*但需要验证哦*/
        }
        else if("update".equals(pageType)){
            String id = req.getParameter("id");
            if(Validator.isNotEmpty(id) && Validator.isInteger(id)){
                //.收集数据id并查询返回 和 回显
                Subsidy subsidy = subsidyService.queryById(Integer.parseInt(id));
                req.setAttribute("subsidy",subsidy);
                req.setAttribute("submitType",pageType+"Submit");
                req.setAttribute("id",id);
                req.getRequestDispatcher("/view/subsidy/create.jsp").forward(req,resp);
            }
        }
        else if("updateSubmit".equals(pageType)){
            String id = req.getParameter("id");
            Subsidy subsidy = pkg(req);
            /*去写修改表的sql语句 记得要修改两个表哦 person标的name card reason 还有subsidy表的money*/
            /*然后重定向到展示页面就可以了*/
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

            params.put("type",type);
            //.剩下的交给逻辑层
            subsidyService.batch(ids,params);

            resp.sendRedirect(req.getContextPath()+"/subsidy.do?type="+typeParam);//到这了 数据层还没写呢
        }

    }

    private Subsidy pkg(HttpServletRequest req){
        Subsidy subsidy = new Subsidy();
        subsidy.setPerson(new Person());

        String money = req.getParameter("money");
        Pattern pattern = Pattern.compile("\\d+[.]?\\d+");
        Matcher matcher = pattern.matcher(money);
        if(matcher.find())
            subsidy.setMoney(new BigDecimal(matcher.group()));

        subsidy.getPerson().setCard(req.getParameter("card"));
        subsidy.getPerson().setName(req.getParameter("name"));
        subsidy.getPerson().setReason(req.getParameter("reason"));

        return subsidy;
    }

}