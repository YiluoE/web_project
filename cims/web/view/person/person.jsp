<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="javakc-zhg">

    <title>人员管理</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" />
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
  <body>
  <section id="container" class="">
      <div class="row">
          <div class="col-lg-12">
              <section class="panel">
                  <header class="panel-heading">
                      人员管理
                  </header>
				  <form class="form-inline" role="dorm" id="personForm" method="post"> <%--默认提交给自己person.do--%>
					  <%--隐藏域--%>
					  <input type="hidden" id="thisPage" name="thisPage" value="${requestScope.thisPage}">
					  <input type="hidden" id="maxPage" name="maxPage" value="${requestScope.maxPage}">
					  <%--搜索区域--%>
					  <div class="row">
						  <div class="col-lg-12">
							  <section class="panel">
								  <div class="panel-body">
									  <div class="form-group">
										  <label class="">姓名</label>
										  <input type="text" class="form-control" name="name" value="${requestScope.params.name}" placeholder="输入姓名">
									  </div>
									  <div class="form-group">
										  <label class="">身份证号</label>
										  <input type="text" class="form-control" name="card" value="${requestScope.params.card}" placeholder="请输入身份证号">
									  </div>
									  <div class="form-group">
										  <label class="">状态</label>
										  <select class="form-control" name="state" id="state">
											<option value="0">请选择</option>
											  <c:forEach items="${applicationScope.state}" var="state">
												  <option value="${state.key}" ${requestScope.params.state == state.key?"selected":""}>
														  ${state.value}
												  </option>
											  </c:forEach>


											<%--不如直接用jQuery--%>
											<%--<option value="1" ${requestScope.params.state == 1?"selected":""}>离休</option>
											<option value="2" ${requestScope.params.state == 2?"selected":""}>退休</option>--%>
										  </select>
										  </div>
										  <div class="form-group">
										  <label class="">起薪开始日期</label>
										  <input type="text" id="sdate" name="sdate" value='<f:formatDate value="${requestScope.params.sdate}" pattern="yyyy-MM-dd"/>' class="form-control" placeholder="请输入开始日期" readonly>
									  </div>
									  <div class="form-group">
										  <label class="">起薪结束日期</label>
										  <input type="text" id="edate" name="edate" value='<f:formatDate value="${requestScope.params.edate}" pattern="yyyy-MM-dd"/>' class="form-control" placeholder="请输入截止日期" readonly>
									  </div>
									  <div class="form-group">
										  <label class="">供暖补贴</label>
										  <input type="checkbox" name="heating" ${requestScope.params.heating == 1? "checked":""} class="form-control">
									  </div>
									  <div class="form-group">
										  <label class="">物业补贴</label>
										  <input type="checkbox" name="property" class="form-control">
									  </div>
									  <button type="submit" class="btn btn-success">搜索</button>
									  <button type="button" id="create" class="btn btn-info">添加</button>
									  <button type="button" id="batch" class="btn btn-danger" disabled>批量删除</button>
								  </div>
							  </section>
						  </div>
					  </div>
					  <%--数据展示区域--%>
					  <table class="table table-striped table-advance table-hover">
						  <thead>
								<tr>
									<th><input type="checkbox" id="checkAll"/></th>
									<th><i class="icon-bullhorn"></i>序号</th>
									<th><i class="icon-male"></i>姓名</th>
									<th><i class="icon-bookmark"></i>身份证号</th>
									<th><i class="icon-edit"></i>状态</th>
									<th><i class="icon-tags"></i>职级</th>
									<th><i class="icon-jpy"></i>起薪日期</th>
									<th><i class="icon-sun"></i>供暖</th>
									<th><i class="icon-briefcase"></i>物业</th>
									<th><i class="icon-home"></i>操作</th>
								</tr>
						  </thead>
						  <tbody>
							  <c:forEach items="${personList}" var="entity" varStatus="index">
								  <tr>
									  <th><input type="checkbox" name="ids" value="${entity.id}"/></th>
									  <td>${(requestScope.thisPage-1)*10+index.count}</td>
									  <td>${entity.name}</td>
									  <td>${entity.card}</td>
									  <td>
										  <c:forEach items="${applicationScope.state}" var="state">
											  <c:if test="${state.key == entity.state}"> <%--默认requestScope--%>
												  <c:if test="${entity.state == 1}">
													  <span class="label label-success label-mini">${state.value}</span>
												  </c:if>
												  <c:if test="${entity.state == 2}">
													  <span class="label label-danger label-mini">${state.value}</span>
												  </c:if>
											  </c:if>
										  </c:forEach>

									  </td>
									  <td>
										  <c:forEach items="${applicationScope.grade}" var="grade">
											  <c:if test="${entity.grade == grade.key}"> <%--为什么用了个el比较不可以--%>
												  ${grade.value}
											  </c:if>
										  </c:forEach>

									  </td>
									  <td>
										  <f:formatDate value="${entity.start}" pattern="yyyy-MM-dd"/>
									  </td>
									  <td>${entity.heating == 0?"否":"是"}</td>
									  <td>${entity.property == 0?"否":"是"}</td>
									  <td>
										  <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/person.do?id=${entity.id}&formType=load"><i class="icon-pencil"></i></a>
										  <button type="button" name="delete" value="${entity.id}" class="btn btn-danger btn-xs"><i class="icon-trash "></i></button>
									  </td>
								  </tr>
							  </c:forEach>
						  </tbody>
					  </table>
					  <%--分页按钮区域--%>
					  <div class="btn-row">
						  <div style="text-align: center;">
							  <div class="btn-group">
								  <c:if test="${requestScope.thisPage != 1}">
									  <button class="btn btn-primary" id="first" type="button">首页</button>
									  <button class="btn btn-primary" id="prev" type="button">上页</button>
								  </c:if>
								  <c:if test="${requestScope.thisPage != requestScope.maxPage}">
									  <button class="btn btn-primary" id="next" type="button">下页</button>
									  <button class="btn btn-primary" id="last" type="button">末页</button>
								  </c:if>
							  </div>
							  <div class="btn-group">
								  <button class="btn btn-danger">${requestScope.thisPage}/${requestScope.maxPage} 共${requestScope.count}条</button>
							  </div>
						  </div>
					  </div>
				  </form>
              </section>
          </div>
      </div>
  	</section>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

    <script>

		$(document).ready(function() 
		{

			$('.btn-group:first>button').on('click',function ()
			{
				let id = $(this).attr('id');
				let object = $('#thisPage');
				let thisPage = $(object).val();
				let maxPage = $('#maxPage').val();

				if(id === 'first'){
					thisPage = 1;
				}
				else if(id === 'prev'){
					thisPage = parseInt(thisPage) - 1;
				}
				else if(id === 'next'){
					thisPage = parseInt(thisPage) + 1;
				}
				else if(id === 'last'){
					thisPage = maxPage;
				}

				$(object).val(thisPage);
				$('#personForm').submit();

			});

			$('[name=delete]').on('click',function () /*为什么是name因为id是惟一的所以不能绑id*/
			{
				let pid = $(this).val();

				let content = '';
				let xmlHttpRequest = new XMLHttpRequest();
				xmlHttpRequest.onreadystatechange = function () {

					if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200){
						let data = xmlHttpRequest.responseText;
						content = JSON.parse(data);
					}
				};

				xmlHttpRequest.open('get','${pageContext.request.contextPath}/person.do?formType=findSubsidy&pid='+pid,false);
				xmlHttpRequest.send();



				let index = layer.confirm('该人员已经绑定了：'+ content.count + '个补贴记录'
						+ '，总金额： ' +  content.sum + '$',
				{
					btn: ['确认','取消']
				},function () {
					$('#personForm').attr('action','${pageContext.request.contextPath}/person.do?formType=delete&id='+pid).submit();

					layer.close(index);
				});

			});

			/*设置create按钮的绑定事件*/
			$('#create').bind('click', function()
			{
				/*设置触发事件将当前页面跳转到create.jsp	*/
				window.location.href='${pageContext.request.contextPath}/view/person/create.jsp';
			});

			/*全选按钮绑定事件*/
			$('#checkAll').on('click',function () {
				/*绑定动态属性*/
				$('[name=ids]').prop('checked',this.checked);
			});

			/*动态改变全选按钮状态*/
			$('[name=ids]').on('click',function () {
				let object = $('#checkAll');

				let idsNum = $('[name=ids]').length;
				let idsCheckedNum = $('[name=ids]:checked').length;

				if( idsCheckedNum === idsNum )
					object.prop('checked',true);
				else
					object.prop('checked',false);
			});

			/*为批量删除按钮绑定事件*/
			$('#batch').on("click",function () {

				if( $('[name=ids]:checked').length === 0 ){
					layer.alert('请至少选择一条记录删除！',{
						skin: 'layui-layer-molv',
						closeBtn: 0,
						anim: 4
					});

					return; /*一条没选则终止执行*/
				}

				$('#personForm').attr('action','${pageContext.request.contextPath}/person.do?formType=batch').submit();

			});

			$('#sdate').datepicker({
	            format: 'yyyy-mm-dd'
	        });
	        $('#edate').datepicker({
	            format: 'yyyy-mm-dd'
	        });
		});
    </script>
  </body>
</html>