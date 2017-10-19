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
.input-box{ position:absolute; top:160px; left:105px;color:#0952a1;width:600px;}
.input-box .hxqh{ 
	position:absolute; 
	top:60px; 
	left:0px;
	display:block ;
	width:450px;
	heigh:200px;
	border-radius:60px;
}/*相对于input-box的位置*/
.input-box .zxqh{ 
	position:absolute; 
	top:60px; 
	left:600px;
	display:block ;
	width:530px;
	heigh:200px;
	border-radius:60px;
}/*相对于input-box的位置*/
.input-box button{display:block ;height:120px;}
.input-box p{ line-height:20px;}
body{
	background-color:#cce2ff;
}
</style>
</head>
<body>
 <jsp:include page="common/top2.jsp" flush="true"/>
	<div class="container" id="imgcenter">
		<div class="row-fluid">
			<div class="span12" align="center" >
		<div class="main">
		<div class="login-box">
			<div class="input-box">
				<button type="button" class="btn btn-primary btn-large btn-block hxqh" onclick="gocaiwu()">
					<font size=6>搜救中心财务综合服务平台</font>
				</button>
				<button type="button" class="btn btn-success btn-large btn-block zxqh" onclick="gofazhan()">
					<font size=6>中国地震局发展与财务管理信息系统</font>
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