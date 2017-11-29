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
        <link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/fileinput.css" rel="stylesheet" media="screen">
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/callstatic/vendors/html5shiv.min.js"></script> 
<script src="<%=request.getContextPath()%>/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<style type="text/css">
.activeli{
        background-color: #e0e0d1;
}
.submittijioa{
	margin-left:80px;
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
                        <li >
                            <a href="<%=request.getContextPath()%>/salary/manage"><i class="icon-chevron-right"></i>工资管理</a>
                        </li>
                       <!--  <li>
                            <a href="#"><i class="icon-chevron-right"></i> 通告管理</a>
                        </li> -->
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">工资文档上传</div>
                            </div>
                            <div class="btn-group">
                             	 <form action="/salary/uploadfile" enctype="multipart/form-data" method="post">
								       <input id="lefile" type="file" name="file" style="display:none">  
										<div class="input-append">  
										    <input id="photoCover" class="input-large" type="text" multiple="multiple" style="height:20px;">  
										    <a class="btn" onclick="$('input[id=lefile]').click();">上传</a>  
										</div>  
										<br/>
								        <input type="submit" class="btn btn-primary submittijioa" value="提交">
								    </form>
                             </div>
                        </div>
                        <!-- /block -->
                    </div>


                </div>
            </div>
            <hr>
           
        </div>
        <!--/.fluid-container-->
<script src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/assets/scripts.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/fileinput.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/zh.js"></script>
<script>
$('input[id=lefile]').change(function() {  
	$('#photoCover').val($(this).val());  
});  

$(function() {
    
});
</script>
</body>
</html>