<%@ page import="java.util.Enumeration" %>
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
    <title>补贴</title>
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
              String type = request.getParameter("type");/*补贴类型*/
              String title = "create".equals(request.getParameter("pageType"))?"添加":"修改"; /*操作类型*/
              String pageType = request.getParameter("submitType");
          %>
          <header class="panel-heading">
              ${param.type == 1?"供暖":"物业"}补贴${param.pageType == "create"?"添加":"修改"}
          </header>
          <div class="panel-body">
              <form class="form-horizontal tasi-form" id="subsidyForm" action='${pageContext.request.contextPath}/subsidy.do' method="post">
                  <input hidden name="pageType" value="<%=pageType%>">
                  <input hidden name="type" value="<%=type%>">
                  <input hidden name="id" value="${id}">
                  <input hidden name="personID" id="personID" value="">
                  <c:if test="${requestScope.submitType != null}">
                      <input hidden name="submitType" value="${requestScope.submitType}">
                  </c:if>

                  <div class="form-group">
                      <label class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" placeholder="输选择姓名" name="name" id="name" value="${subsidy.person.name}" readonly required>
                          <span class="help-block">请选择人员!</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">身份证号</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" name="card" id="card" value="${subsidy.person.card}" placeholder="请选择姓名证件编号" ${requestScope.submitType == "createSubmit"?"":"readonly"} required>
                          <span class="help-block">根据选择的人员自动显示该人员的证件编号!</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">补贴金额</label>
                      <div class="col-sm-10">
                          <input type="text" value="${subsidy.money}￥" name="money" class="form-control" placeholder="请输入补贴金额">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">更改原因</label>
                      <div class="col-sm-10">
                          <input type="text" value="${subsidy.person.reason}" name="reason" class="form-control" placeholder="请输入更改原因">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label"></label>
                      <div class="col-lg-10">
                         <button class="btn btn-success"><%=title%></button>
                         <button type="button" class="btn btn-success" onclick="javascript:window.history.go(-1)">重置</button>
                      </div>
                  </div>
              </form>
          </div>
          </div>
          </div>
      </section>
  </section>

    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript">
      $(document).ready(function() 
      {

        // 为人员选择绑定点击事件
        $('#name').click(function () {
            //1.查询需要补贴的人员
            /**
             * 1. 创建ajax引擎对象
             * 2. 指定响应处理函数
             *      2.1 判断状态 请求状态
             *      2.2 判断状态 响应状态
             *      2.3 接收服务器返回内容 string/xml
             * 3. 配置请求相关参数
             * 4. 发送请求并携带参数
             */

            let content = '';

            let xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.onreadystatechange = function () {

                if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200){
                    let date = xmlHttpRequest.responseText;
                    content = JSON.parse(date); /*stringify*/
                }

            };
            /*params 1:请求类型 2：请求路径
                * 3:true异步 3:false同步
                * 3:默认异步
            */
            xmlHttpRequest.open('get','${pageContext.request.contextPath}/subsidy.do?pageType=subsidyPerson&type=${param.type}',false);
            xmlHttpRequest.send();

            //2.弹窗并显示补贴人员

            let html = '';
            html+='    <table class="table table-striped table-advance table-hover">';
            html+='        <thead>';
            html+='            <tr>';
            html+='                <th></th>';
            html+='                <th><i class="icon-bullhorn"></i>序号</th>';
            html+='                <th><i class="icon-male"></i>姓名</th>';
            html+='                <th><i class="icon-bookmark"></i>身份证号</th>';
            //html+='                <th><i class="icon-edit"></i>状态</th>';
            html+='            </tr>';
            html+='        </thead>';

            html+='        <tbody>';
            $.each(content,function (i,o) {
            html+='        <tr>';
            html+='            <td><input type="radio" name="personIDS" value="'+i+'"></td>'; /*太能了*/
            html+='            <td>'+(i+1)+'</td>';
            html+='            <td>'+o.name+'</td>';
            html+='            <td>'+o.card+'</td>';
            //html+'            <td>'+o.state+'</td>';
            html+='        </tr>';
            });
            html+='        </tbody>';

            html+='        <tfoot>';
            html+='            <tr>';
            html+='                <td colspan="4" style="text-align: center;"><button type="button" id="choose" class="btn btn-info"> 确定 </button></td>';
            html+='            </tr>';
            html+='        </tfoot>';
            html+='    </table>';

            let index = layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                area: ['600px','500px'],
                content: html
            });

            /*显现到页面中后才能找到这个id*/
            $('#choose').click(function ()
            {
                let radioArray = $('[name=personIDS]:checked');
                if( $(radioArray).length === 0 ){
                    layer.alert('请选择补贴人员！',{
                        skin: 'layui-layer-molv',
                        closeBtn: 0,
                        anim: 4
                    });

                    return;
                }

                let person = content[$(radioArray).first().val()]; /*何时加$*/
                $('#personID').val(person.id);
                $('#name').val(person.name);
                $('#card').val(person.card);

                layer.close(index);
                // layer.closeAll(); /*不安全呢*/
            });

        });

        $('#date').datepicker({
                format: 'yyyy-mm-dd'
        });
      }
      );
    </script>
  </body>
</html>
