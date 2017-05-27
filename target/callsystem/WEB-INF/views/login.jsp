<!DOCTYPE html>
<html class="no-js">
<head>
<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<meta charset="utf-8">
<title>搜救中心财务报销预约系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/callstatic/vendors/html5shiv.min.js"></script> 
<script src="<%=request.getContextPath()%>/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<script src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.min.js" type="text/javascript"></script>
</head>
<style>
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,p,blockquote,th,td{margin:0;padding:0;border:none;}
body{font-size:12px; font-family:Cambria, "Hoefler Text", "Liberation Serif", Times, "Times New Roman", serifbackground:#fff;color:#2b2b2b;}
address,caption,cite,code,dfn,em,strong,th,var{font-style:normal;font-weight:normal;}
select,input,img{vertical-align:middle;}
table{border-collapse:collapse;border-spacing:0;}
table,td,tr,th{font-size:12px;}
a{text-decoration:none;cursor:pointer;}
fieldset,img{border:0;}

.main{ position:absolute;left:50%; top:50%; background:url(<%=request.getContextPath()%>/callstatic/images/login2.png) no-repeat; width:772px; height:468px; margin:-234px 0 0 -386px;}

.input-box{ position:absolute; top:110px; left:410px;color:#0952a1;}
.input-box input{ border:1px solid #7491b5; width:154px; height:28px; background-color:#f5fafe; padding-left:4px; line-height:28px;}
.input-box p{ line-height:40px;}
.input-box .check{ width:14px; height:14px; margin-left:48px;}
.input-box .record{ margin-left:6px;}
.input-box .link{ margin-top:14px; margin-left:70px;}
a{ height:28px; width:72px; display:inline-block; color:#fff; line-height:28px; text-align:center; margin-top:10px;}
.log{ background:url(<%=request.getContextPath()%>/callstatic/images/log.png) no-repeat; margin-right:16px; margin-left:48px}

.reset{ background:url(<%=request.getContextPath()%>/callstatic/images/reset.png) no-repeat; color:#6d6d6d;}
.main-box .copy{ text-align:center; margin-top:8px; color:#666666;}
.text{ text-align:center; color:#fff; margin-top:398px; line-height:24px;}

</style>

<body style="background-color:#b3d1ff;">

	<div class="main">
		<div class="login-box">
		<form action="<%=request.getContextPath()%>/dologin" method="POST" id="loginForm">
			<div class="input-box">
				<p>
					用户名：<input type="text" name="phone" value="" class="user" id="phone"/>
				</p>
				<p>
				<span style="padding-right: 12px;">
				密</span>码：<input type="password" class="password" name="password" id="password"/>
				</p>
				<p>
					<!-- <input type="checkbox" class="check" /><span class="record">记住登录名</span> -->
				</p>
				<a href="#" onclick="login_val()" class="log">登&nbsp;录</a>
				<a href="#" onclick="cancel()" class="reset">取&nbsp;消</a>
			</div>
			</form>
		</div>
		<div class="text">
			<p></p>
		</div>
	</div>
	<!-- <jsp:include page="common/bottom.jsp" flush="true"/>  -->
<script type="text/javascript">
$('#password').keydown(function(e){
	if(e.keyCode==13){
		login_val();
	}
	});
function cancel(){
	$("#phone").val("");
	$("#password").val("")
}
function login_val(){
	var phone = $("#phone").val();
	var password = $("#password").val();
	if(phone=="" || password==""){
		alert("用户名或密码不能为空！")
			return false;
	}
	$.ajax({
		url: "<%=request.getContextPath()%>/user/validate",
		data: {"phone":phone,"password":password},
		async: true,
		type: 'POST',
		dataType: 'jsonp',
		jsonp: 'jsonpcallback',
		success:function(data){
			if(data.message=="OK"){
				var formElement = document.getElementById("loginForm");
				formElement.submit();
			}else{
				alert("用户名或密码错误！");
				return false;
			}
			
		}
	});
}


</script>


</body>
</html>
