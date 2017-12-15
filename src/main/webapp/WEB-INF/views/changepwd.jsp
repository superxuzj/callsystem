<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<%@ page contentType="text/html; charset=UTF-8"  %>
<head>
<title>搜救中心财务综合服务平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="<%=request.getContextPath()%>/callstatic/assets/styles.css" rel="stylesheet" media="screen">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/callstatic/vendors/html5shiv.min.js"></script> 
<script src="<%=request.getContextPath()%>/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<script src="<%=request.getContextPath()%>/callstatic/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

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
	<jsp:include page="common/top.jsp" flush="true"/> 
<div class="container">
	<div class="row-fluid">
		<div class="span12 ">
			<div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">基本信息</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                             <form class="form-horizontal" action="<%=request.getContextPath()%>/savechange"  method="post" id="savechangeForm">
                             <input  name="id" type="hidden" value="${user.id  }">
                              <fieldset>
                                <legend>修改信息${change}</legend>
                                <div class="control-group">
                                  <label class="control-label" for="focusedInput">姓名</label>
                                  <div class="controls">
                                    <input class="input-xlarge focused" name="name" type="text" value="${user.name }" readonly>
                                  </div>
                                </div>
                                <div class="control-group">
                                  <label class="control-label" for="focusedInput">部门</label>
                                  <div class="controls">
                                    <input class="input-xlarge focused" name="department" type="text" value="${user.department }" readonly>
                                  </div>
                                </div>
                                <div class="control-group warning">
                                  <label class="control-label" for="inputError">手机号码</label>
                                  <div class="controls">
                                    <input type="text" name="phone" id="phone" value="${user.phone }">
                                    <span class="help-inline">修改手机号后请联系管理员，确保能收到报销通知短信。</span>
                                  </div>
                                </div>
                                <div class="control-group error">
                                  <label class="control-label" for="inputError">旧密码</label>
                                  <div class="controls">
                                    <input type="password" id="oldpwd">
                                    <span class="help-inline"></span>
                                  </div>
                                </div>
                                <div class="control-group success">
                                  <label class="control-label" for="inputError">新密码</label>
                                  <div class="controls">
                                    <input type="password"  id="newpwd1">
                                    <span class="help-inline"></span>
                                  </div>
                                </div>
                                
                                <div class="control-group success">
                                  <label class="control-label" for="inputError">新密码确认</label>
                                  <div class="controls">
                                    <input type="password" name="password" id="newpwd2">
                                    <span class="help-inline"></span>
                                  </div>
                                </div>
                                
                                <div class="form-actions">
                                  <button type="button" class="btn btn-primary" onclick="saveUser()">保存</button>
                                  <button type="button" class="btn" onclick="cancel()">取消</button>
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
<jsp:include page="common/bottom.jsp" flush="true"/> 
	<!--/.fluid-container-->
	<script src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/callstatic/assets/scripts.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	<script type="text/javascript">
	function saveUser(){
		var oldpassword = '${user.password}';
		if($("#phone").val()==""){
			alert("请输入手机号！");
			return ;
		}
		if($("#oldpwd").val()==""){
			alert("请输入旧密码！");
			return ;
		}
		if($("#oldpwd").val()!=oldpassword){
			alert("密码不正确，请重新输入！");
			return ;
		}
		
		if($("#newpwd1").val()=="" && $("#newpwd2").val()==""){
			alert("请输入新密码！");
			return;
		}
		if($("#newpwd1").val().length <6 && $("#newpwd2").val().length<6){
			alert("密码最少为6位！");
			return;
		}	
		if($("#newpwd1").val()=="111111" || $("#newpwd2").val()=="111111"){
			alert("修改密码不能为初始密码！");
			return;
		}
		
		if($("#newpwd1").val()!="" && $("#newpwd2").val()!=""){
			if($("#newpwd1").val() != $("#newpwd2").val()){
				alert("两次输入新密码不一致！");
				return;
			}
		}
		
		if($("#newpwd1").val()!="" && $("#newpwd2").val()==""){
			alert("请输入新密码！");
			return;
		}
		if($("#newpwd1").val()=="" && $("#newpwd2").val()!=""){
			alert("请输入新密码！");
			return;
		}
		
		alert("密码修改成功，请重新登录！");
		$("#savechangeForm").submit();
	}
	function cancel(){
		window.history.go(-1);
	}
		$(document).ready(function(){
			/* $("#showtable").hide(); */
		});
		/* $("#qianghao").click(function(){
			$("#showtable").show();
		});
		$("#closed").click(function(){
			$("#showtable").hide();			
		});
		$("#linesucess").click(function(){
			alert("排号成功！");			
		})
		$("#add").click(function(){
			$("#tabletbody").append('<tr><td><input type="text"/></td><td><input type="text"/></td>'+
			'<td><input type="text"/></td><td><a href="javascript:;" onclick="deleteCurrentRow(this);"><font color="red">删除</font></a></td></tr>');
			
		});
		
		function deleteCurrentRow(obj){
			var isDelete=confirm("真的要删除吗？");
			if(isDelete){
				var tr=obj.parentNode.parentNode;
				var tbody=tr.parentNode;
				tbody.removeChild(tr);
			}
		} */
	</script>
</body>

</html>