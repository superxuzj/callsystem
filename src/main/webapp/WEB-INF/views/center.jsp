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
/* body{
padding-top:0px;
padding-bottom:0px;
 /* background-color:#eaf5fb; 
}
#imgcenter{
	width:100%;
} */
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,p,blockquote,th,td{
padding-top:0px;
padding-bottom:0px;
margin:0;padding:0;border:none;}
select,input,img{vertical-align:middle;}
fieldset,img{border:0;}
.main{ position:absolute;left:50%; top:41%; background:url(<%=request.getContextPath()%>/callstatic/images/bg3.png) no-repeat; width:850px; height:540px; margin:-234px 0 0 -386px;}
.input-box{ position:absolute; top:160px; left:105px;color:#0952a1;width:600px;}
.input-box .hxqh{ position:absolute; top:-40px; left:0px;display:block ;}/*相对于input-box的位置*/
.input-box .zxqh{ position:absolute; top:100px; left:0px;display:block ;}/*相对于input-box的位置*/
.input-box button{display:block ;height:80px;}
.input-box p{ line-height:20px;}

.input-box .gzcx{ position:absolute; top:250px; left:0px;display:block ;}/*相对于input-box的位置*/
</style>
</head>
<body>
 <jsp:include page="common/top.jsp" flush="true"/>
	<div class="container" id="imgcenter">
		<div class="row-fluid">
			<div class="span12" align="center" >
		<div class="main">
		<div class="login-box">
			<div class="input-box">
				<c:choose>
					<c:when test="${hstatus== '3'}">
					<button type="button" class="btn btn-large btn-block hxqh" onclick="order_hnum()">
						<font size=6>横向取号(未开始)</font>
					</button>	
					</c:when>
					<c:otherwise>
					<button type="button" class="btn btn-large btn-block btn-primary hxqh" onclick="order_hnum()">
						<font size=6>横向取号</font>
					</button>
					</c:otherwise>
				</c:choose>
			
				<p></p>
				<c:choose>
					<c:when test="${zstatus== '3'}">
					<button type="button" class="btn btn-large btn-block zxqh" onclick="order_znum()">
						<font size=6>纵向取号(未开始)</font>
					</button>	
					</c:when>
					<c:otherwise>
					<button type="button" class="btn btn-large btn-block btn-primary zxqh" onclick="order_znum()">
						<font size=6>纵向取号</font>
					</button>
					</c:otherwise>
				</c:choose>
				
				<p></p>
				<button type="button" class="btn btn-large btn-block btn-success gzcx" onclick="go_salary()">
						<font size=6>工资查询</font>
					</button>
				
			</div>
			
		</div>
		<div class="text">
			<p></p>
		</div>
	</div>
	  </div>
		</div>
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
	function go_salary(){
		window.location.href = "<%=request.getContextPath()%>/salary/vercode";
	}
	function order_hnum(){
		window.location.href = "<%=request.getContextPath()%>/center/horder";
	}
	function order_znum(){
		window.location.href = "<%=request.getContextPath()%>/center/zorder";
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