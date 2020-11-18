<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <body>
  <section id="container" class="">
     
    <div class="row">
    <div class="col-lg-12">
      <section class="panel">
          <header class="panel-heading">
             人员添加${pageContext}
          </header>
          <div class="panel-body">
              <form class="form-horizontal tasi-form" id="createForm" action="${pageContext.request.contextPath}/person.do" method="post" <%--enctype="application/x-www-form-urlencoded"--%>>
                  <input type="hidden" name="formType" value="create">
                  <div class="form-group">
                      <label class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="name" name="name" placeholder="输入姓名">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">身份证号</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="card" name="card" placeholder="请输入正确的证件编号, 并确认该编号是唯一的!">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">状态</label>
                      <div class="col-sm-10">
                          <select class="form-control" id="state" name="state">
                            <option value="0">请选择</option>
                            <option value="1">离休</option>
                            <option value="2">退休</option>
                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">职级</label>
                      <div class="col-sm-10">
                          <select class="form-control" name="grade" id="grade">
                            <option value="0">请选择</option>
                            <c:forEach items="${applicationScope.grade}" var="grade">
                                <option value="${grade.key}">
                                        ${grade.value}
                                </option>
                            </c:forEach>
                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">起薪日期</label>
                      <div class="col-sm-10">
                          <input class="form-control" type="text" id="start" name="start" placeholder="点击这里选择日期..." readonly>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-lg-2">补贴项目</label>
                      <div class="col-lg-10">
                          <label class="checkbox-inline">
                              <input type="checkbox" value="1" id="heating" name="heating"> 供暖
                          </label>
                          <label class="checkbox-inline">
                              <input type="checkbox" value="1" id="property" name="property"> 物业
                          </label>
                          <span class="help-block" id="msg" style="color: #FFFFFF">至少选择一条补贴项！</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">更改原因</label>
                      <div class="col-sm-10">
                          <input type="text"  class="form-control" placeholder="请输入更改原因" name="reason" id="reason">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label"></label>
                      <div class="col-lg-10">
                         <button type="button" id="addButton" class="btn btn-success" disabled>添加</button>
                         <button type="button" class="btn btn-success" onclick="javascript:window.history.go(-1)">返回</button>
                      </div>
                  </div>
              </form>
          </div>
      </section>
    </div>
    </div>
  </section>

    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/layer-v3.1.1/layer/layer.js"></script>
  <script type="text/javascript">
      $(document).ready(function () {
          /*动态效验*/ /*jQuery封装版本*/
          $('[name=card]').blur(function () {
              $('[name=msg]').remove();

              let obj = $(this);
              let card = $(obj).val();

              if ($.trim(card).match(/^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/) != null) {
                  obj.next().remove();

                  $.ajax({
                      url: '${pageContext.request.contextPath}/person.do',
                      type: 'post',
                      data: {formType: "card",card: card},
                      dataType: 'json',
                      success: function (result) {
                          if( result.success ){
                              $(obj).css({"border-color": "#00ff00"});
                              $('#addButton').attr('disabled', false);
                          }
                          else {
                              $(obj).css({"border-color": "#ff0000"});
                              $('#addButton').attr('disabled', true);
                              obj.after('<font name="msg" color="red">card不是唯一的！</font>');
                          }
                      }
                  });
              }
              else {
                    if( ($('[name=msg]').length == 0) )
                        obj.after('<font name="msg" color="red">格式不正确喏！</font>');
              }

          });

          /*动态效验*/ /*原始版本*/
          $('[name=card]').on('yiluoe', function () {
              let object = $(this);
              let card = $(this).val();

              /*412723199909255976*/
              if ($.trim(card).match(/^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/) != null) {
                  /*通过ajax发送异步请求*/
                  //1. 创建ajax引擎对象
                  let xmlHttpRequest = new XMLHttpRequest();

                  //2.指定响应处理函数
                  xmlHttpRequest.onreadystatechange = function () {
                      /**
                       * readyState 0-4
                       * 0: ajax未初始化
                       * 1: 未配置请求参数
                       * 2: 已配置请求参数，但为发出请求
                       * 3: 客户端发出请求，服务器还未返回响应
                       * 4: 服务器已经返回响应（其它的可能出现多次，尤其是3）
                       */

                      //2.1 发出请求既弹出装载层
                      layer.load();
                      if (xmlHttpRequest.readyState === 4) {
                          //2.2 服务器返回响应后关闭装载...
                          layer.closeAll('loading');
                          //2.3 接收服务器返回响应状态码
                          if (xmlHttpRequest.status === 200) {
                              /**
                               * ajax只能接收服务器返回的两种数据格式
                               * 1. string 字符串 responseText
                               * 2. xml 文档 responseXML
                               */

                                  //2.4 获取服务器返回的响应内容
                              let addBtnObj = $('#addButton');
                              if (0 == xmlHttpRequest.responseText) {
                                  $(object).next().html('<font></font>');
                                  $(object).css({"border-color": "#00ff00"});
                                  $(addBtnObj).attr('disabled', false);
                              } else {
                                  $(object).css({"border-color": "#ff0000"});
                                  $(object).next().html('<font color="#ff0000">card不是惟一的！</font>');
                                  $(addBtnObj).attr('disabled', true);
                              }

                              /*XML类型操作*/
                              /*Document document = xmlHttpRequest.responseXML;
                              Node node = document.getRootNode();*/
                              /*...*/

                          } else {
                              layer.msg('出现了点问题。');
                          }
                      }
                  }

                  //3.配置请求相关参数
                  xmlHttpRequest.open('post', '${pageContext.request.contextPath}/person.do');
                  //4.发出请求并携带参数
                  xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                  xmlHttpRequest.send('formType=card&card=' + card);


              }
              else
                  $(object).next().html('<font color="#ff0000">格式不正确！</font>');

          });


          $('#addButton').on('click', function () {

              let ids_f = [];
              let ids_t = [];
              let obj = $('#name');

              if (obj.val().match(/^[\u4e00-\u9fa5]{1,15}$/) == null)
                  ids_f[ids_f.length] = obj.attr('id');
              else
                  ids_t[ids_t.length] = obj.attr('id');

              obj = $('#card');
              if (obj.val().match(/^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/) == null)
                  ids_f[ids_f.length] = obj.attr('id');
              else
                  ids_t[ids_t.length] = obj.attr('id');

              obj = $('#state');
              if (obj.val().match(/1|2/) == null)
                  ids_f[ids_f.length] = obj.attr('id');
              else
                  ids_t[ids_t.length] = obj.attr('id');

              obj = $('#grade');
              if (obj.val().match(/[1-8]/) == null)
                  ids_f[ids_f.length] = obj.attr('id');
              else
                  ids_t[ids_t.length] = obj.attr('id');

              obj = $('#start');
              if (obj.val().match(/(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)/) == null)
                  ids_f[ids_f.length] = obj.attr('id');
              else
                  ids_t[ids_t.length] = obj.attr('id');

              /*补贴的处理*/
              if (true) {
                  obj = $('#heating');
                  let num = 0;
                  if (obj.prop(('checked')) == true)
                      num++;

                  obj = $('#property');
                  if ($('#property').prop(('checked')) == true)
                      num++;

                  /*有点小浮动~*/
                  if (num < 1)
                      $('#msg').html('<font color="#ff0000">至少选择一条补贴项！</font>')
                  else
                      $('#msg').html('<font></font>')

                  $('#property').css({"border-color": "#ff0000"});
              }

              obj = $('#reason');
              if (obj.val().match(/^[\u4e00-\u9fa5]{1,50}$/) == null)
                  ids_f[ids_f.length] = obj.attr('id');
              else
                  ids_t[ids_t.length] = obj.attr('id');


              ids_f.forEach((v) => {
                  $('#' + v).css({"border-color": "#ff0000"});
              });

              ids_t.forEach((v) => {
                  $('#' + v).css({"border-color": "#00ff00"});
              });

              if (ids_f.length >= 1) {
                  let id = ids_f[0];

                  $('#' + id).focus();
              } else {
                  $('#createForm').submit();
              }

          });

          $('#start').datepicker({
              format: 'yyyy-mm-dd'
          });
      });
  </script>
  </body>
</html>
