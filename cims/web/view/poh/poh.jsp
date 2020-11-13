<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="javakc-zhg">
 	<link rel="shortcut icon" href="img/favicon.html">
    <title>归档</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" />
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
  </head>
  <body>
  <section id="container" class="">
      <div class="row">
          <div class="col-lg-12">
              <section class="panel">
                  <header class="panel-heading">
                      归档
                  </header>
                  <table class="table table-striped table-advance table-hover">
                      <thead>
	                      	<tr>
								<%--<th><input type="checkbox"/></th>--%>
	                          	<th><i class="icon-bullhorn"></i>序号</th>
	                          	<th><i class="icon-male"></i>姓名</th>
	                          	<th><i class="icon-bookmark"></i>身份证号</th>
	                          	<th><i class="icon-edit"></i>状态</th>
	                          	<th><i class="icon-tags"></i>职级</th>
	                          	<th><i class="icon-jpy"></i>起薪日期</th>
	                          	<th><i class="icon-sun"></i>供暖</th>
	                          	<th><i class="icon-briefcase"></i>物业</th>
	                          	<%--<th><i class="icon-home"></i>操作</th>--%>
	                      	</tr>
                      </thead>
                      <tbody>
                          <c:forEach items="${personList}" var="e" varStatus="index">
                              <tr>
                                  <td>${index.count+1}</td>
                                  <td>${e.name}</td>
                                  <td>${e.card}</td>
                                  <td><span class="label label-default label-mini">
                                      <c:forEach items="${applicationScope.state}" var="state">
                                          <c:if test="${state.key == e.state}">
                                              归档
                                          </c:if>
                                      </c:forEach>
                                  </span></td>
                                  <td>部级副职 </td>
                                  <td>2018-10-11 </td>
                                  <td>是 </td>
                                  <td>否 </td>
                              </tr>
                          </c:forEach>
                      </tbody>
                  </table>
                  <%--<div class="btn-row">
                      <div class="btn-toolbar">
                          <div class="btn-group">
                              <button class="btn btn-primary" type="button">首页</button>
                              <button class="btn btn-primary" type="button">上页</button>
                              <button class="btn btn-primary" type="button">下页</button>
                              <button class="btn btn-primary" type="button">末页</button>
                          </div>
                      </div>
                  </div>--%>
              </section>
          </div>
      </div>
  	</section>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script>
		$(document).ready(function() 
		{

		});
    </script>
  </body>
</html>