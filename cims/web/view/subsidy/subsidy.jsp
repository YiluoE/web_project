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
    <title>${requestScope.params.type == 1?"供暖":"物业"}管理</title>
    <link href="${pageContext.servletContext.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" />
    <link href="${pageContext.servletContext.contextPath}/static/css/style.css" rel="stylesheet">
  </head>
  <body>
  <section id="container" class="">
      <div class="row">
          <div class="col-lg-12">
              <section class="panel">
                  <header class="panel-heading">
					  ${requestScope.params.type == 1?"供暖":"物业"}补贴
                  </header>
				  <form class="form-inline" role="form" id="subsidy">
					  <input hidden id="thisPage" name="thisPage" value="${requestScope.params.thisPage}">
					  <input hidden name="type" value="${requestScope.params.type}">
					  <div class="row">
						  <div class="col-lg-12">
							  <section class="panel">
								  <div class="panel-body">
										  <div class="form-group">
											  <label class="">姓名</label>
											  <input type="text" class="form-control" name="name" placeholder="输入姓名">
										  </div>
										  <div class="form-group">
											  <label class="">身份证号</label>
											  <input type="text" name="card" class="form-control" placeholder="请输入身份证号">
										  </div>
										  <div class="form-group">
											  <label class="">月份</label>
											  <input type="text" id="sdate" name="month" class="form-control" placeholder="请输入开始日期" readonly>
										  </div>
										  <button type="submit" class="btn btn-success">搜索</button>
										  <button type="button" id="create" class="btn btn-info">添加</button>
										  <button type="submit" class="btn btn-danger">批量删除</button>
								  </div>
							  </section>
						  </div>
					  </div>
					  <table class="table table-striped table-advance table-hover">
						  <thead>
								<tr>
									<th><input type="checkbox" id="checkAll"/></th>
									<th><i class="icon-bullhorn"></i>序号</th>
									<th><i class="icon-male"></i>月份</th>
									<th><i class="icon-bookmark"></i>姓名</th>
									<th><i class="icon-edit"></i>身份证</th>
									<th><i class="icon-tags"></i>职级</th>
									<th><i class="icon-jpy"></i>金额</th>
									<th><i class="icon-home"></i>操作</th>
								</tr>
						  </thead>
						  <tbody>
								<c:forEach items="${requestScope.subsidyList}" var="e" varStatus="index">
									<tr>
										<th><input type="checkbox" name="ids" value="${e.id}"/></th>
										<td>${(params.thisPage-1)*10+index.count}</td>
										<td>
											<f:formatDate value="${e.month}" pattern="yyyy年MM月"/>
										</td>
										<td>${e.person.name}</td>
										<td>${e.person.card}</td>
										<td>
											<c:forEach items="${applicationScope.grade}" var="grade">
												<c:if test="${e.person.grade == grade.key}">
													${grade.value}
												</c:if>
											</c:forEach>
										</td>
										<td>${e.money}￥</td>
										<td>
											<button class="btn btn-primary btn-xs"><i class="icon-pencil"></i></button>
											<button class="btn btn-danger btn-xs"><i class="icon-trash "></i></button>
										</td>
									</tr>
								</c:forEach>
						  </tbody>
					  </table>
					  <div class="btn-row">
						  <div class="btn-toolbar">
							  <div class="btn-group">
								  <c:if test="${requestScope.params.thisPage != 1}">
									  <button class="btn btn-primary" type="button" id="first">首页</button>
									  <button class="btn btn-primary" type="button" id="prev">上页</button>
								  </c:if>
								  <c:if test="${params.thisPage != params.maxPage}">
									  <button class="btn btn-primary" type="button" id="next">下页</button>
									  <button class="btn btn-primary" type="button" id="last">末页</button>
								  </c:if>
								  <button class="btn btn-warning" type="button">
									  ${requestScope.params.thisPage}/${requestScope.params.maxPage} 共${requestScope.params.count}条
								  </button>
							  </div>
						  </div>
					  </div>
				  </form>
              </section>
          </div>
      </div>
  	</section>
    <script src="${pageContext.servletContext.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.servletContext.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script>

		$(document).ready(function()
		{
			/*添加操作*/
			$('#create').bind('click',function () {
				window.location.href="${pageContext.request.contextPath}/view/subsidy/create.jsp?type=${requestScope.params.type}&pageType=create";
			});

			/*全选按钮*/
			$('#checkAll').on('click',function () {
				$('[name=ids]').prop('checked',this.checked);
			});

			/*动态全选*/
			$('[name=ids]').on('click',function () {
				let object = $('#checkAll');

				let idsNum = $('[name=ids]').length;
				let idsCheckedNum = $('[name=ids]:checked').length;

				if( idsCheckedNum === idsNum )
					object.prop('checked',true);
				else
					object.prop('checked',false);
			});

			// 为翻页按钮绑定事件
			$('.btn-group>button').on('click',function () {
				let id = $(this).attr('id');
				let thisPage = $('#thisPage').val();

				if(id == "first"){
					thisPage = 1;
				}
				else if(id == "prev"){
					thisPage--;
				}
				else if(id == "next"){
					thisPage++;
				}
				else if(id == "last"){
					thisPage = ${requestScope.params.maxPage};
				}

				$('#thisPage').val(thisPage);

				$('#subsidy').submit();
			});

			$('#sdate').datepicker({
	            format: 'yyyy-mm'
	        });
		});
    </script>
  </body>
</html>