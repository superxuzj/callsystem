<!DOCTYPE html>
<html class="no-js">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<%@ page contentType="text/html; charset=UTF-8"  %>
<head>
<title>搜救中心财务综合服务平台</title>
<!-- Bootstrap -->
<link href="/callstatic/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/callstatic/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="/callstatic/assets/styles.css" rel="stylesheet" media="screen">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<script src="/callstatic/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<style type="text/css">
#linecancle{
	margin-right:10px;
}
.form-horizontal{
	margin-top:15px;
}
.form-horizontal .control-group{
margin-bottom:15px;
}
#order{
	margin-right:30px;
}
</style>
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="#">搜救中心财务综合服务平台</a>
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="icon-user"></i> 张三 <i class="caret"></i> </a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="#">修改密码</a>
								</li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="login.html">退出</a>
								</li>
							</ul>
						</li>
				</div>
			</div>
		</div>
	</div>

	<a href="#" onclick="excelToDatabase()">excel->database</a>
<footer>
		</footer>
	<!--/.fluid-container-->
	<script src="/callstatic/vendors/jquery-1.9.1.min.js"></script>
	<script src="/callstatic/bootstrap/js/bootstrap.min.js"></script>
	<script src="/callstatic/assets/scripts.js"></script>
	<script type="text/javascript"
		src="/callstatic/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript"
		src="/callstatic/bootstrap/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			/* $("#showtable").hide(); */
		});
		function excelToDatabase(){
			$.ajax({
				url: "/util/addxls",
				async: true,
				type: 'POST',
				dataType: 'jsonp',
				jsonp: 'jsonpcallback',
				success:function(data){
					//alert(data.message);
				}
			});
		}
	</script>
</body>

</html>