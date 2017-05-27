<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<%@ page contentType="text/html; charset=UTF-8"  %>
<head>
<title>搜救中心财务报销预约系统</title>
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
body{
font-size:larger;
}
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
				<div class="alert alert-info">
				<font color="black">
					<h3>通知公告栏</h3>
					<p>公告信息：</p>
					
					<p>
					<c:choose>
					<c:when test="${empty publicNotice}">
							暂无公告！
					</c:when>
					<c:otherwise>
						${publicNotice.content}
					</c:otherwise>
					</c:choose>
					</p>
</font>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span12 " id="content">
				<div class="row-fluid">
					<div class="alert alert-error">
					<font color="black">
						<h3>排队信息</h3>
						<h3>
							您的号码是
							<mark>${orderNum.orderNum }</mark>
							号，您前面还有
							<mark>${count }</mark>
							位在等待。
							<p />
						</h3>
						
						${ORDER_SE} 
						<br/>
						<c:if test="${business.maxNum>0}">
							今天财务预计最大报销数为${business.maxNum}号.
						</c:if>
						</font>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 ">
				<div class="row-fluid">
					<div class="alert alert-success">
					<font color="black">
						<h3>温馨提示</h3>
						<p>
						${FIRST} 
						</p>
						
						<!-- <c:choose>
					    <c:when test="${business.status== '3'}">
					   
					    <c:if test="${business.type== '1'}">
							     横
						</c:if>	 
						<c:if test="${business.type== '2'}"> 
							     纵
						</c:if>	    
							向业务报销今天暂未开启或已经结束！
					    </c:when>
					    <c:otherwise>
					    <c:if test="${business.type== '1'}">
							     横
						</c:if>	 
						<c:if test="${business.type=='2'}">
							     纵
						</c:if>
					      	向业务报销今天已经开启，请有需要报销的员工抢号排队，财务会根据排号依次处理报销，请注意短信提醒。
					    </c:otherwise>
					</c:choose> -->
					${HUNBEGIN }
					${HBEGIN }
					${ZUNBEING }
					${ZBEGIN }
						</font>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6 offset5">
				<div class="row-fluid">
					<button class="btn btn-large"  type="button"  onclick="goback()">返回</button>
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
		$(document).ready(function(){
			/* $("#showtable").hide(); */
		});
		function goback(){
			self.location=document.referrer;
		}
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