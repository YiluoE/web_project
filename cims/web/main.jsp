<%@page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">

    <title>干部收入管理系统-主页</title>

    <link href="<%=request.getContextPath()%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/static/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<%=request.getContextPath()%>/static/css/owl.carousel.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/css/style-responsive.css" rel="stylesheet" />
  </head>

  <body>

  <section id="container" class="">
      
      <!--main content start-->
      <section id="">
          <section class="wrapper">
			
			<!--state overview start-->
            <div class="row state-overview">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol terques">
                              <i class="icon-user"></i>
                          </div>
                          <div class="value">
                              <h1 id="user">0</h1>
                              <p>当月新用户</p>

                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol red">
                              <i class="icon-tags"></i>
                          </div>
                          <div class="value">
                              <h1 id="heating">0</h1>
                              <p>本周供暖补贴</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol yellow">
                              <i class="icon-shopping-cart"></i>
                          </div>
                          <div class="value">
                              <h1 id="property">0</h1>
                              <p>本周物业补贴</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol blue">
                              <i class="icon-bar-chart"></i>
                          </div>
                          <div class="value">
                              <h1 id="total">0</h1>
                              <p>本总补贴金额</p>
                          </div>
                      </section>
                  </div>
              </div>
			       <div class="row"> 
                <div class="col-lg-12">
                    <!--本月补贴 start-->
                    <div class="panel terques-chart">
                        <div class="panel-body chart-texture">
                            <div class="chart">
                                <div class="heading">
                                    <span>当前月补贴</span>
                                    <strong>￥ 4,880,457,00 | 35%</strong>
                                </div>
                                <div class="sparkline" data-type="line" data-resize="true" data-height="75" data-width="90%" data-line-width="1" data-line-color="#fff" data-spot-color="#fff" data-fill-color="" data-highlight-line-color="#fff" data-spot-radius="4" data-data="[200,135,667,333,526,996,564,123,890,564,455,200,135,667,333,526,996,564,123,890,564,455]"></div>
                            </div>
                        </div>
                        <div class="chart-tittle">
                            <span class="title">最新记录。。。</span>
                        </div>
                    </div>
                    <!--本月补贴 end-->

                    <div class="panel green-chart">
                        <div class="panel-body">
                            <div class="chart">
                                <div class="heading">
                                    <span>前一月补贴</span>
                                    <strong>30 天 | 99%</strong>
                                </div>
                                <div id="barchart"></div>
                            </div>
                        </div>
                        <div class="chart-tittle">
                            <span class="title">总补贴额</span>
                            <span class="value">￥ 76,524,678</span>
                        </div>
                    </div>
                </div>
            </div>
			
          </section>
      </section>
      <!--main content end-->
  </section>

    <script src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
    <%--<script src="<%=request.getContextPath()%>/static/js/common-scripts.js"></script>--%>
    <script src="<%=request.getContextPath()%>/static/js/echarts.min.js"/>
    <script type="text/javascript">
        
        $(document).ready(function() {
            function init() {
                $.get('url',function () {

                },'json');

            }

            setInterval(init,60000);
        });

    </script>
  </body>
</html>
