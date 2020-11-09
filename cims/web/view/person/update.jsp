<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <title>人员添加</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
  </head>
  <body>
  <section id="container" class="">
     
    <div class="row">
    <div class="col-lg-12">
      <section class="panel">
          <header class="panel-heading">
             <%--人员添加--%>
          </header>
          <div class="panel-body">
              <form class="form-horizontal tasi-form" action="${pageContext.request.contextPath}/person.do" method="post">
                  <input type="hidden" name="formType" value="update">
                  <input type="hidden" name="id" value="${requestScope.entity.id}"> <%--算了下面还是简写吧--%>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" name="name" placeholder="输入姓名" value="${entity.name}">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">身份证号</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" name="card" placeholder="输入姓名证件编号" value="${entity.card}">
                          <span class="help-block">请输入正确的证件编号, 并确认该编号是唯一的!</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">状态</label>
                      <div class="col-sm-10">
                          <select class="form-control" name="state">
                            <option value="0">请选择</option>
                            <option value="1" ${entity.state == 1?"selected":""}>离休</option>
                            <option value="2" ${requestScope.entity.state == 2?"selected":""}>退休</option>
                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">职级</label>
                      <div class="col-sm-10">
                          <select class="form-control" name="grade"> <%--啊~ 这个回显可以...用jQuery做回显--%>
                            <option value="0">请选择</option>
                            <option value="1">部级正职</option>
                            <option value="2">部级副职</option>
                            <option value="3">司级正职</option>
                            <option value="4">司级副职</option>
                            <option value="5">处级正职</option>
                            <option value="6">处级副职</option>
                            <option value="7">科级正职</option>
                            <option value="8">科级副职</option>
                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">起薪日期</label>
                      <div class="col-sm-10">
                          <input class="form-control" type="text" name="start" value='<f:formatDate value="${requestScope.entity.start}" pattern="yyyy-MM-dd"/>' placeholder="点击这里选择日期..." id="date" readonly>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-lg-2">补贴项目</label>
                      <div class="col-lg-10">
                          <label class="checkbox-inline">
                              <input type="checkbox" value="1" name="heating" ${requestScope.entity.heating == 1?"checked":""}> 供暖
                          </label>
                          <label class="checkbox-inline">
                              <input type="checkbox" value="1" name="property" ${requestScope.entity.property == 1?"checked":""}> 物业
                          </label>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">更改原因</label>
                      <div class="col-sm-10">
                          <input type="text"  class="form-control" placeholder="请输入更改原因" name="reason" value="${requestScope.entity.reason}">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label"></label>
                      <div class="col-lg-10">
                         <button type="submit" class="btn btn-success">修改</button>
                         <button type="button" class="btn btn-success" onclick="javascript:window.history.go(-1)">返回</button>
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

      /*回显 在这儿呢*/
      /*给select了设置一个值它就会回显了，它到底经历了什么*/
      $('[name=grade]').val(${requestScope.entity.grade});

      $(document).ready(function() 
      {
        $('#date').datepicker({
                format: 'yyyy-mm-dd'
        });
      });
    </script>
  </body>
</html>