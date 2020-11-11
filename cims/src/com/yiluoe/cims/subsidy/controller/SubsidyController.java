package com.yiluoe.cims.subsidy.controller;

import com.yiluoe.cims.subsidy.entity.Subsidy;
import com.yiluoe.cims.subsidy.factory.SubsidyFactory;
import com.yiluoe.cims.subsidy.service.SubsidyService;
import com.yiluoe.cims.util.validate.Validator;
import jdk.swing.interop.SwingInterOpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

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

        //.页面请求类型 添加|修改
        String pageType = req.getParameter("pageType");
        String typeParam = req.getParameter("type");

        if(Validator.isNotEmpty(pageType) && !"".equals(pageType) && ( typeParam.equals("1") || typeParam.equals("2") ) ){

            int type = Integer.parseInt(typeParam);

            System.out.println(">>>>>>>>>>>>>>>>");
            System.out.println(pageType);
            System.out.println(type);

            //.添加操作
            if(pageType.equals("create")){
                //.封装

            }

            //.修改属性
            if(pageType.equals("update")){

            }
        }

        Map<String,Object> params = new HashMap<>();

        if(true){

            //.页数处理
            int thisPage = 1;
            int pageSize = 10;
            String thisPageParams = req.getParameter("thisPage");
            if(Validator.isNotEmpty(thisPageParams)){
                if(Validator.isInteger(thisPageParams)){
                    thisPage = Integer.parseInt(thisPageParams);
                }
            }

            //.收集类型参数 1:供暖补贴 2:物业补贴
            String type = req.getParameter("type");
            if(Validator.isNotEmpty(type) && Validator.isInteger(type)){
                params.put("type",Integer.parseInt(type));
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
            if(Validator.isNotEmpty(date) && !"".equals(date)){
                params.put("date",date);
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
        }

        //.查询符合条件的所有数据且分页

        req.setAttribute("subsidyList",subsidyService.queryByPage(params));
        req.setAttribute("params",params);

        req.getRequestDispatcher("view/subsidy/subsidy.jsp").forward(req,resp);
    }
}
