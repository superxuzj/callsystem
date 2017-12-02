<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <head>
        <title>搜救中心财务报销预约后台管理系统</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <!-- Bootstrap -->
        <link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="<%=request.getContextPath()%>/callstatic/assets/styles.css" rel="stylesheet" media="screen">
        <link href="<%=request.getContextPath()%>/callstatic/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/callstatic/vendors/html5shiv.min.js"></script> 
<script src="<%=request.getContextPath()%>/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<style type="text/css">
.activeli{
        background-color: #e0e0d1;
    }
</style>
    </head>
    
    <body>
       <jsp:include page="../common/top.jsp" flush="true"/> 
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li class="active">
                            <a href=""><i class="icon-chevron-right"></i> 菜单</a>
                        </li>
                        <li>
                            <a href="/manage"><i class="icon-chevron-right"></i> 用户管理</a>
                        </li>
                         <li  class="activeli">
                            <a href="<%=request.getContextPath()%>/department"><i class="icon-chevron-right"></i> 部门管理</a>
                        </li>
                       <li>
                            <a href="<%=request.getContextPath()%>/dictionary"><i class="icon-chevron-right"></i> 内容管理</a>
                        </li><!-- <li>
                            <a href="#"><i class="icon-chevron-right"></i> 业务管理</a>
                        </li>
                        <li>
                            <a href="#"><i class="icon-chevron-right"></i> 通告管理</a>
                        </li> -->
                        
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <div class="block">
                            <div class="block-content collapse in">
                            <div class="span12">
                                 <form class="form-horizontal" action="<%=request.getContextPath()%>/department/save" method="post" id="searchForm">
                                      <fieldset>
                                        <legend>新增部门</legend>
                                        <div class="control-group">
                                          <label class="control-label">名称</label>
                                          <div class="controls">
                                           <input class="input-xlarge focused" name="name" id="name" type="text">
                                          </div>
                                        </div>
                                        <div class="form-actions">
                                          <button onclick="submitForm()" type="button" class="btn btn-primary">保存</button>
                                          <button onclick="gohis()" class="btn">取消</button>
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
            <hr>
         <jsp:include page="../common/bottom.jsp" flush="true"/> 
        </div>
        <!--/.fluid-container-->

<script src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/assets/scripts.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/assets/DT_bootstrap.js"></script>
<script>
function submitForm(){
	if($("#name").val()==""){
		alert("请输入名称！");
		return false;
	}
	$("#searchForm").submit();
}
function gohis(){
	history.go(-1);
}
function test(userid){
	//alert(userid);
}

$(function() {
    
});
</script>
</body>
</html>