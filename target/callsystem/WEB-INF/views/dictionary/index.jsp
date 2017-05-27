<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<html>
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
                            <a href="<%=request.getContextPath()%>/manage"><i class="icon-chevron-right"></i> 用户管理</a>
                        </li>
                         <li class="activeli">
                            <a href="<%=request.getContextPath()%>/dictionary"><i class="icon-chevron-right"></i> 内容管理</a>
                        </li>
                       <!-- <li>
                            <a href="#"><i class="icon-chevron-right"></i> 通告管理</a>
                        </li> -->
                        
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                 <div class="row-fluid">
                        <!-- block -->
                        <!-- /block -->
                    </div>
                    <div class="row-fluid">
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">用户列表</div>
                                
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
  									<table class="table table-striped">
						              <thead>
						                <tr>
						                  <th>序号</th>
						                  <th>名称</th>
						                  <th>内容</th>
						                  <th>操作</th>
						                </tr>
						              </thead>
						              <tbody>
						              <c:forEach items="${dictionaryList }" var="dictionary">
							               <tr>
							                  <td>${dictionary.id} </td>
							                  <td>${dictionary.dictName}</td>
							                  <td><textarea style="width:350px" rows=2 id='${dictionary.id}id'>${dictionary.dictValue}</textarea></td>
							                  <td>
							                  	<button class="btn btn-danger" onclick="editDictionary(${dictionary.id})">修改</button>
							                  </td>
							                </tr>
									   </c:forEach>
						              </tbody>
						            </table>
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
<script>

function editDictionary(dictionaryid){
	var val = $("#"+dictionaryid+"id").val();
	if(val==""){
		alert("不能为空！");
		return false;
	}
	window.location.href="<%=request.getContextPath()%>/dictionary/editDictionary?dictionaryid="+dictionaryid+"&dictValue="+ encodeURI(encodeURI(val));
}

</script>
</body>
</html>