package com.yiluoe.cims.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * @program: cims
 * @description 数据字典监听器
 * @user: 本以罗伊斯
 * @create: 2020/11/9 13:15
 **/

@WebListener
public class DictionaryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //1.获取servletContext对象，它约等于整个web项目
        ServletContext servletContext = sce.getServletContext();
        //2.初始化字典相关内容
        Map<Integer,String> grade = new TreeMap<>();
        grade.put(1,"部级正职");
        grade.put(2,"部级副职");
        grade.put(3,"司级正职");
        grade.put(4,"司级副职");
        grade.put(5,"处级正职");
        grade.put(6,"处级副职");
        grade.put(7,"科级正职");
        grade.put(8,"科级副职");

        //2.2初始化状态数据字典
        Map<Integer,String> state = new TreeMap<>();
        state.put(1,"离休");
        state.put(2,"退休");

        servletContext.setAttribute("grade",grade);
        servletContext.setAttribute("state",state);
    }
}
