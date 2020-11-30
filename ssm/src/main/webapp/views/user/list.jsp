<%--
  Created by IntelliJ IDEA.
  User: 本以罗伊斯
  Date: 2020/11/30
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("path",request.getContextPath());
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <a href="${path}/views/user/insert.jsp">添加</a>
    <table>
        <thead>
            <tr>
                <td>序号</td>
                <td>姓名</td>
                <td>年龄</td>
                <td>性别</td>
                <td>手机</td>
                <td>出生日期</td>
                <td>家庭住址</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="#{userList}" var="user" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.sex== 1? "男":"女"}</td>
                    <td>${user.phone}</td>
                    <td>
                        <f:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日"/>
                    </td>
                    <td>${user.address}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
