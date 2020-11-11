<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <link rel="shortcut icon" href="img/favicon.html">
    <title>补贴添加</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" />
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
  <body>
  <section id="container" class="">
    <div class="row">
    <div class="col-lg-12">
      <section class="panel">
          <%
              String type = request.getParameter("type");
              String pageType = request.getParameter("pageType");
          %>
          <header class="panel-heading">
              <c:if test='<%=type.equals("1")%>'>
                  供暖补贴
              </c:if>
              <c:if test='<%=type.equals("2")%>'>
                  物业补贴
              </c:if>
              <c:if test='<%=pageType.equals("create")%>'>
                  添加
              </c:if>
              <c:if test='<%=pageType.equals("update")%>'>
                  修改
              </c:if>
          </header>
          <div class="panel-body">
              <form class="form-horizontal tasi-form" id="subsidyForm" action="${pageContext.request.contextPath}/subsidy.do" method="post">
                  <input hidden name="pageType" value="<%=pageType%>">
                  <input hidden name="type" value="<%=type%>">
                  <div class="form-group">
                      <label class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" placeholder="输选择姓名" readonly required>
                          <span class="help-block">请选择人员!</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">身份证号</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" placeholder="请选择姓名证件编号"  readonly required>
                          <span class="help-block">根据选择的人员自动显示该人员的证件编号!</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">补贴金额</label>
                      <div class="col-sm-10">
                          <input type="text"  class="form-control" placeholder="请输入补贴金额">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">更改原因</label>
                      <div class="col-sm-10">
                          <input type="text"  class="form-control" placeholder="请输入更改原因">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label"></label>
                      <div class="col-lg-10">
                         <button type="submit" class="btn btn-success">添加</button>
                         <button type="submit" class="btn btn-success">重置</button>
                      </div>
                  </div>
              </form>
          </div>
      </section>
  </section>

    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
      $(document).ready(function() 
      {
          /*为 添加提交按钮 绑定事件*/
          /*$('#submit').on('click',function () {

          });*/


        $('#date').datepicker({
                format: 'yyyy-mm-dd'
        });
      });
    </script>
  </body>
</html>
