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
                        <li >
                            <a href="<%=request.getContextPath()%>/manage"><i class="icon-chevron-right"></i> 用户管理</a>
                        </li>
                        <li class="activeli">
                            <a href="<%=request.getContextPath()%>/department"><i class="icon-chevron-right"></i> 部门管理</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/dictionary"><i class="icon-chevron-right"></i> 内容管理</a>
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
                                <div class="muted pull-left">部门列表</div>
                                
                            </div>
                            <div class="btn-group">
                                         <a href="#"><button onclick="goAdddepart()" class="btn btn-success">新增部门<i class="icon-plus icon-white"></i></button></a>
                                      </div>
                            <div class="block-content collapse in">
                                <div class="span12">
  									<table class="table table-striped">
						              <thead>
						                <tr>
						                  <th>序号</th>
						                  <th>名称</th>
						                  <th>操作</th>
						                </tr>
						              </thead>
						              <tbody>
						              <c:forEach items="${page.results }" var="depart">
							               <tr>
							                  <td>${depart.id} </td>
							                  <td>${depart.name}</td>
							                  <td>
							                  	<button class="btn btn-danger" onclick="editDepart(${depart.id})">修改</button>
							                  	<button class="btn btn-danger" onclick="deldepart(${depart.id})">删除</button>
							                  </td>
							                </tr>
									   </c:forEach>
						              </tbody>
						            </table>
						            共${page.totalSize }条记录共${page.pages }页&nbsp;&nbsp;当前第${page.currentPage }页&nbsp;&nbsp;
			<a href="#" onclick="firstPage('${page.currentPage}','${page.pages }');">首页</a>&nbsp;&nbsp; 
			<a href="#" onclick="previousPage('${page.currentPage}','${page.pages }');">上一页</a>&nbsp;&nbsp;
			<a href="#" onclick="nextPage('${page.currentPage}','${page.pages }');">下一页</a>&nbsp;&nbsp; 
			<a href="#" onclick="lastPage('${page.currentPage}','${page.pages }');">尾页</a>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>


                </div>
            </div>
            <hr>
           
        </div>
        <!--/.fluid-container-->
<jsp:include page="../common/bottom.jsp" flush="true"/> 
<script src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/assets/scripts.js"></script>
<script src="<%=request.getContextPath()%>/callstatic/assets/DT_bootstrap.js"></script>
<script>
function deldepart(departId){
	if(window.confirm('你确定要删除吗？')){
        //alert("确定");
		window.location.href="<%=request.getContextPath()%>/department/deldepartment?id="+departId;
     }else{
        //alert("取消");
        return false;
    }
	
}
function goAdddepart(){
	window.location.href="<%=request.getContextPath()%>/department/goadddepartment";
}
function editDepart(departId){
	window.location.href="<%=request.getContextPath()%>/department/editdepartment?id="+departId;
}
//第一页
function firstPage(currentPage,totalPage){
	if(currentPage == 1){
		alert("已经是第一页数据");
		return false;
	}else{
		window.location.href="<%=request.getContextPath()%>/department/list?pageNo=1";
		return true;
	}
}
//下一页
function nextPage(currentPage,totalPage){
	if(currentPage == totalPage){
		alert("已经是最后一页数据");
		return false;
	}else{
		window.location.href="<%=request.getContextPath()%>/department/list?pageNo=" + (parseInt(currentPage)+1);
		return true;
	}
}
//上一页
function previousPage(currentPage,totalPage){
	if(currentPage == 1){
		alert("已经是第一页数据");
		return false;
	}else{
		window.location.href = "<%=request.getContextPath()%>/department/list?pageNo=" + (currentPage-1);
		return true;
	}
}

// 尾页
function lastPage(currentPage,totalPage){
	
	if(currentPage == totalPage){
		alert("已经是最后一页数据");
		return false;
	}else{
		window.location.href = "<%=request.getContextPath()%>/department/list?pageNo=" +totalPage;
		return true;
	}
}
$(function() {
    
});
</script>
</body>
</html>