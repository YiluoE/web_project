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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            System.out.println("-----------------------------------------------------------");

            String name = req.getParameter("name");
            params.put("name",name);

            String card = req.getParameter("card");
            params.put("card",card);

            System.out.println("-----------------------------------------------------------");

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
