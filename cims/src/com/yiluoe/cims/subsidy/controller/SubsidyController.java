package com.yiluoe.cims.subsidy.controller;

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

        //1.分页查询
        if(true){

            int thisPage = 1;
            String thisPageParams = req.getParameter("thisPage");
            if(Validator.isNotEmpty(thisPageParams)){
                if(Validator.isInteger(thisPageParams)){
                    thisPage = Integer.parseInt(thisPageParams);
                }
            }

            int pageSize = 10;
            long count = subsidyService.queryByCount(null);
            int maxPage = (int)Math.ceil(count*1.0 / pageSize);

            int offset = (thisPage-1)*10;

            //1.1.收集查询参数
            params.put("offset",offset);
            params.put("pageSize",pageSize);
            //1.2.收集前端参数
            params.put("thisPage",thisPage);
            params.put("maxPage",maxPage);
            params.put("count",count);
        }

        req.setAttribute("subsidyList",subsidyService.queryByPage(params));
        req.setAttribute("params",params);

        req.getRequestDispatcher("view/subsidy/estate.jsp").forward(req,resp);
    }
}
