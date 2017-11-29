<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ page contentType="text/html; charset=UTF-8"  %>
<head>
<title>搜救中心财务综合服务平台</title>
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="<%=request.getContextPath()%>/callstatic/assets/button.css" rel="stylesheet"
	media="screen">
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="<%=request.getContextPath()%>/callstatic/assets/styles.css" rel="stylesheet" media="screen">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="/callstatic/vendors/html5shiv.min.js"></script> 
<script src="/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<script src="<%=request.getContextPath()%>/callstatic/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<style type="text/css">
*{
            margin: 0;
            padding: 0;
            box-sizing:border-box;
        }
        section.index h2{
            margin: 60px auto 80px auto;
            width: 1038px;
            height: 172px;
            background:url(<%=request.getContextPath()%>/callstatic//img/but_1.png) no-repeat;
            background-size: 100%;
            line-height: 172px;
        }
        section.index h2 a{
            display: inline-block;
            width: 100%;
            height: 100%;
            text-decoration: none;
            color: #000;
            font-size:34px;
        }
        section.index h2 a i{ 
            width: 100px;
            height: 100px;
            display: inline-block;
            background: url(<%=request.getContextPath()%>/callstatic//img/icon_1.png) no-repeat;
            background-size:100%;
            vertical-align:middle;
            margin-left: 170px;
        }
        section.index h2 a span{
            margin-left: 80px;
            font-family: microsoft yahei;
        }
        section.index h2.bottom{
            background:url(<%=request.getContextPath()%>/callstatic//img/but_2.png) no-repeat;
            background-size: 100%;
        }
        section.index h2.bottom a i{
            background:url(<%=request.getContextPath()%>/callstatic//img/icon_2.png) no-repeat;
            background-size: 100%;
        }
</style>
</head>
<body>
 <jsp:include page="common/top2.jsp" flush="true"/>
	<div class="container" id="imgcenter">
		<section class="index">
        <h2 class="top">
          <a href="#" onclick="gocaiwu()"> 
                <i></i> <span>搜救中心财务综合服务平台</span>
          </a>
        </h2>
    <section>
        <h2 class="bottom">
          <a href="#" onclick="gofazhan()"> 
                <i></i> <span>中国地震局发展与财务管理信息系统</span>
          </a>
        </h2>
    </section>
		
	</div>
<!-- <jsp:include page="common/bottom.jsp" flush="true"/>  -->
	<!--/.fluid-container-->
	<script src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/callstatic/assets/scripts.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/jquery.bootstrap.newsbox.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	function gocaiwu(){
		window.location.href = "<%=request.getContextPath()%>/login";
	}
	function gofazhan(){
		window.open("http://10.59.0.2:7001");
	}
	
	$(document).ready(function(){
		$('#imgcenter').css('width', null);
		
	});
	$(document).ready(function(){
		window.setInterval(function(){ 
			  window.location.reload();
		},120000);
	});
	</script>
</body>

</html>