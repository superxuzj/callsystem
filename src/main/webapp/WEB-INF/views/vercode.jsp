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
.getCode{
	margin-left:10px;
}
</style>
</head>
<body>
 <jsp:include page="common/top.jsp" flush="true"/>
	<div class="container">
            <div class="row-fluid">
                <div class="span12" id="content">
                    <div class="row-fluid">
                        <div class="block">
                            <div class="navbar navbar-inner ">
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                     <form class="form-horizontal" id="codeform" action="/salary/view">
                                      <fieldset>
                                        <legend>手机验证</legend>
                                        <div class="control-group">
                                          <label class="control-label">您的手机号为：</label>
                                          <div class="controls">
                                            <span class="input-xlarge uneditable-input">${phone}</span>
                                            <input type="button" class="btn getCode" id="getCode" value="获取验证码" onclick="getVercode('${phone}')">
                                          	<input id="randomCode" type="hidden" value="">
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="focusedInput">验证码：</label>
                                          <div class="controls">
                                            <input class="input-xlarge focused" id="focusedInput" type="text" placeholder="填写验证码">
                                          </div>
                                        </div>
                                        <div class="form-actions">
                                          <button type="submit" class="btn btn-primary" onclick="return check()">确定</button>
                                        </div>
                                      </fieldset>
                                    </form>

                                </div>
                            </div>
                        </div>
                        <!-- /block -->
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
	/* 表单提交验证 */
	function check(){
		if($("#focusedInput").val()==""){
			alert("请填写验证码！");
			return false;
		}
		if($("#focusedInput").val()==$("#randomCode").val()){
			$("#codeform").submit();
		}else{
			alert("请填写正确验证码！");
			return false;
		}
		
	}
	/*ajax获取后台验证码 */
	function getVercode(phone){
	    $.ajax({    
	           type: 'post',    
	           url: '/salary/code?phone=' + phone,    
	           dataType: "json",    
	           success: function (data) {    
	               $("#getCode").attr('disabled',true);
	               $("#randomCode").attr('value',data);
	               settime(30);
	           },    
	           error: function (data) { 
	        	   window.location.reload();
	             	//alert(data);
	           }    
	       });    
	}
	/*60s计时*/
	function settime(countdown) {
		if (countdown == 0) {
			$("#getCode").attr("value", "获取验证码");
			$("#getCode").attr('disabled',false);
			return;
		} else {
			$("#getCode").attr("value", "重新发送(" + countdown + ")");
			countdown--;
		}
		setTimeout(function() {
			settime(countdown)
		}, 1000)
	}
	</script>
</body>

</html>