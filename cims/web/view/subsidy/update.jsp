<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <link rel="shortcut icon" href="img/favicon.html">
    <title>补贴修改</title>
    <link href="${pageContext.servletContext.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" />
    <link href="${pageContext.servletContext.contextPath}/static/css/style.css" rel="stylesheet">
  <body>
  <section id="container" class="">
     
    <div class="row">
    <div class="col-lg-12">
      <section class="panel">
          <header class="panel-heading">
             ${param.type == 1?"供暖":"物业"}修改
          </header>
          <div class="panel-body">
              <form class="form-horizontal tasi-form" action="${pageContext.servletContext.contextPath}/subsidy.do" method="post">
                  <input hidden name="id" value="${param.id}">
                  <input hidden name="type" value="${param.type}">
                  <input hidden name="pageType" value="${pageType}">
                  <div class="form-group">
                      <label class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" <%--name="name"--%> value="${subsidy.person.name}" readonly required>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">身份证号</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" <%--name="card"--%> value="${subsidy.person.card}"  readonly required>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">补贴金额</label>
                      <div class="col-sm-10">
                          <input type="text"  class="form-control" name="money" value="${subsidy.money}" placeholder="请输入补贴金额">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">更改原因</label>
                      <div class="col-sm-10">
                          <input type="text"  class="form-control" name="reason" value="${subsidy.person.reason}" placeholder="请输入更改原因">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label"></label>
                      <div class="col-lg-10">
                         <button type="submit" class="btn btn-success">修改</button>
                         <button type="submit" class="btn btn-success" onclick="javascript:window.history.go(-1)">返回</button>
                      </div>
                  </div>
              </form>
          </div>
      </section>
    </div>
    </div>
  </section>

    <script src="${pageContext.servletContext.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.servletContext.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
      $(document).ready(function() 
      {
        $('#date').datepicker({
                format: 'yyyy-mm-dd'
        });
      });
    </script>
  </body>
</html>
