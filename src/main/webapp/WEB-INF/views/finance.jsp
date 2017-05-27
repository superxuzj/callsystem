<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<%@ page contentType="text/html; charset=UTF-8"  %>
<head>
<title>搜救中心财务报销预约系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/callstatic/bootstrap/css/jquery.datetimepicker.css"/>
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="<%=request.getContextPath()%>/callstatic/assets/styles.css" rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/callstatic/vendors/html5shiv.min.js"></script> 
<script src="<%=request.getContextPath()%>/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<script src="<%=request.getContextPath()%>/callstatic/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<style type="text/css">
body {
	
}
.btn-inverse{
	margin-left: 0px
}
.nav-list>.active>a{
	background-color: #5bc0de;
}
.nav-list>.active>a:hover{
	background-color: #5bc0de;
}
.nav-list>.active>a:focus{
	background-color: #5bc0de;
}

#sidebar {
	padding: 0px;
}

#topjumbotron {
	padding: 0px 30px 0px 30px;
}

#floatright {
	display: block;
	float: right;
}

.floatleft {
	float: left;
	margin: 0px 5px 0px 5px;
}

.floatleft label {
	float: left;
	font-size: 14px;
	font-weight: normal;
	color: #333;
	padding-top: 3px;
	padding-right: 5px;
}

.floatleft div {
	float: left;
}

#ullist>li {
	/* width: 260px; */
}

#buttonid {
	display: table-cell;
	vertical-align: middle;
}
.pagination pull-right>li{
	list-style-type:none;
}
#demo3{
	list-style-type:none;
}
.table{
	margin-bottom:5px;
}
.table th, .table td{
	padding:2px 8px 2px 8px;
}
.news-item{
	margin:0 0 0 0 ;
	line-height:13px;
}
.block-content{
 margin:5px 10px 5px 10px;
}
.row-fluid .span4{
 
}
.col-md-4{
	width:100px;
}
.span3{
	padding-left:20px;
}
.span2{
	height:80px;
}
.datetimepicker1{
	width:150px;
}
input[type="text"]{
background-color:#f5f5f0;
}
.next{
	margin-top:20px;
}
hr{
	margin-top:2px;
}
.centermessage{
	padding-top:8px;
}
.litest{
	padding-left:12px;
	padding-right:12px;
}
</style>
</head>

<body>
	<jsp:include page="common/top.jsp" flush="true"/> 
<div class="jumbotron" id="topjumbotron">
<!-- 		<h5>横向业务</h5> -->
<div class="container-fluid">
<div class="row-fluid">
	<div class="span9">
	 <form  action="<%=request.getContextPath()%>/business/start" method="post" id="startForm">
	 <input type="hidden" name="id" id="businessid" value="${business.id}"/>
	<div class="row-fluid">
		<div class="span4">
			<div class="form-group floatleft">
				<label for="dtp_input3" class="col-md-2 control-label">上午业务开始时间:</label>
				<div class="input-group col-md-4">
				<c:choose>
			   		<c:when test="${empty business.amStart }">
			   		 <input type="text" id="amStart" name="amStart" class="form-control datetimepicker1" value="8:00" />
			   		</c:when>
			   		<c:otherwise>
			   		<input type="text" id="amStart" name="amStart" class="form-control datetimepicker1" value="${business.amStart }" />
			   		</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		<div class="span4">
			<div class="form-group floatleft">
				<label for="dtp_input3" class="col-md-2 control-label">上午业务结束时间:</label>
				<div class="input-group col-md-4">
				<c:choose>
			   		<c:when test="${empty business.amEnd }">
			   		 <input type="text" id="amEnd" name="amEnd" class="form-control datetimepicker1" value="8:00" />
			   		</c:when>
			   		<c:otherwise>
			   		<input type="text" id="amEnd" name="amEnd" class="form-control datetimepicker1" value="${business.amEnd }" />
			   		</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		<div class="span4">
		<div class="form-group floatleft">
				<label for="dtp_input3" class="col-md-2 control-label">最大业务数:</label>
				<input id="maxNumid" type="text" placeholder="默认不限制" name="maxNum"
				value="${business.maxNum }"
					style="width: 100px; background-color: #f5f5f0;" />
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<div class="form-group floatleft">
				<label for="dtp_input3" class="col-md-2 control-label">下午业务开始时间:</label>
				<div class="input-group col-md-4">
				<c:choose>
			   		<c:when test="${empty business.pmStart }">
			   		 <input type="text" id="pmStart" name="pmStart" class="form-control datetimepicker1" value="13:00" />
			   		</c:when>
			   		<c:otherwise>
			   		<input type="text" id="pmStart" name="pmStart" class="form-control datetimepicker1" value="${business.pmStart }" />
			   		</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		<div class="span4">
			<div class="form-group floatleft">
				<label for="dtp_input3" class="col-md-2 control-label">下午业务结束时间:</label>
				<div class="input-group col-md-4">
				<c:choose>
			   		<c:when test="${empty business.pmEnd }">
			   		 <input type="text" id="pmEnd" name="pmEnd" class="form-control datetimepicker1" value="13:00" />
			   		</c:when>
			   		<c:otherwise>
			   		<input type="text" id="pmEnd" name="pmEnd" class="form-control datetimepicker1" value="${business.pmEnd }" />
			   		</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		<div class="span4">
			<div class="input-group">
			<button class="btn btn-success"  type="button" id="startbutton" onclick="submitForm(${business.id})">开始</button>
		    <button class="btn btn-warning" type="button" id="pausebutton" onclick="pauseBusiness(${business.id})">暂停</button>
		    <c:if test="${!empty current }">
			 <button class="btn btn-inverse" type="button" id="endbutton" onclick="endBusiness(${business.id},${current.id })">结束</button>
			</c:if>
			<c:if test="${empty current}">
			 <button class="btn btn-inverse" type="button" id="endbutton" onclick="endBusiness(${business.id},0)">结束</button>
			</c:if>
		   <button class="btn"  type="button"  onclick="clearText()">清空</button>
		    <button class="btn-info"  type="button"  onclick="refresh()">刷新</button>
				</div>
			</div>
		</div>
		</form>
		</div>
		<div class="span2" >
		<c:if test="${!empty current }">
			<button class="btn btn-danger btn-large next" onclick="nextOne(${business.id},${current.id } )">下一位准备</button>
		</c:if>
		<c:if test="${empty current}">
			<button class="btn btn-danger btn-large next" onclick="nextOne(${business.id},0 )">下一位准备</button>
		</c:if>
		</div>
	</div>
	<hr />
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span3" id="sidebar">
		<c:if test="${usertype==4 }">
	 		<div class="row-fluid">
                   <select class="span4 m-wrap" id="businessChoose" onchange="onchangeType()">
					<option value="1">横向业务</option>
					<option value="2">纵向业务</option>
				</select>
              </div>
          </c:if>
               <div class="row-fluid" >
			<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
				<li class="active"><a href="#"> 当天排队员工信息 ----    <font color="red">${business.name}</font> </a></li>
				<li>
					<div style="position:absolute; height:300px;width:230px; overflow:auto">
		           		<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse" id="ullist">
							<c:if test="${!empty listOrderNum }">
								<c:forEach items="${listOrderNum}" var="orderNum">
									<c:choose>
									<c:when test="${orderNum.status=='3'}">
										<li style=" background:#f2f2f2;">
											<a href="#">
											<span class="litest">${orderNum.orderNum}</span>
											<span class="litest">${orderNum.creatorName}</span>
											<span class="litest">已处理</span>
											</a>
										</li>
									</c:when>
									<c:when test="${orderNum.status=='5'}">
										<li style=" background:#f2f2f2;">
											<a href="#">
											<span class="litest">${orderNum.orderNum}</span>
											<span class="litest">${orderNum.creatorName}</span>
											<span class="litest">作废</span>
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="#">
											<span class="litest">${orderNum.orderNum}</span>
											<span class="litest">${orderNum.creatorName}</span>
											<span class="litest">未处理</span>
											</a>
										</li>
									</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${empty listOrderNum}">
							
							<li><a href="#">暂未有人排号！</a></li>
							</c:if>
						</ul>
					</div>
				</li>
			<!-- <li class="dropdown"><a href="#" data-toggle="dropdown"
				class="dropdown-toggle">1张三</a>
				 <ul class="dropdown-menu" id="menu1">
					<li><a href="#">号码保留</a></li>
					<li><a href="#">优先叫号</a></li>
				</ul> 
			</li> -->
		</ul>
		</div>
	</div>

	<!--/span-->
	<div class="span6" id="content">
		<div class="row-fluid">
		 <div class="panel-body">
					<div class="row">
		<c:if test="${business.type==1 }">
		 当前是<font color="red">横向业务</font>，状态是
		</c:if>
		<c:if test="${business.type==2 }">
		 当前是<font color="red">纵向业务</font>，状态是
		</c:if>  
		 <c:if test="${business.status==1 }">
		<font color="red">开始</font>
		</c:if>
		<c:if test="${business.status==2 }">
		<font color="red">暂停</font>
		</c:if>
		<c:if test="${business.status==3 }">
		<font color="red">结束</font>
		</c:if>
		 </font>
		 </div>
		 </div>
		</div>
		<div class="row-fluid">
				<div class="panel-body">
					<div class="row">
					  <c:choose>
			    <c:when test="${empty current}">
					     暂未开始叫号！
			    </c:when>
			    <c:otherwise>
			      	<div class="col-xs-12">
							<ul id="demo3">
								<li class="news-item">
									<!-- block -->
									<div class="block" id="nextph">
										<div class="navbar navbar-inner block-header">
											<div class="muted pull-left">当前报销员工</div>
											<div class="pull-right">
												<span class="">
												<button class="btn"  type="button"  onclick="cancel(${current.id })">作废</button>
												</span>
											</div>
										</div>
										<div class="block-content collapse in">
											<div class="span12">
											<div class="alert alert-success alert-block">
								<h4 class="alert-heading">${current.creatorName }   ${current.orderNum }号</h4>
								<p class="centermessage"/>
								<span class="centermessage">电话:${current.phone }</span>
								<p class="centermessage"/>
								<span class="centermessage">部门:${current.department }</span>
												</div>
											</div>
										</div>
									</div></li>
								<li class="news-item">
									<!-- block -->
									<div class="block" id="nextph">
										<div class="navbar navbar-inner block-header">
											<div class="muted pull-left">下一位报销员工</div>
											<div class="pull-right">
												<span class="badge badge-warning"></span>

											</div>
										</div>
										 <c:choose>
										<c:when test="${empty next}">
												     无！
										    </c:when>
										    <c:otherwise>
										    <div class="block-content collapse in">
											<div class="span12">
											<div class="alert alert-info alert-block">
								<h4 class="alert-heading">${next.creatorName }   ${next.orderNum }号</h4>
								<p class="centermessage"/>
								<span class="centermessage">电话:${next.phone }</span>
								<p class="centermessage"/>
								<span class="centermessage">部门:${next.department }</span>
													</div>
											</div>
											</div>
										    </c:otherwise>
										</c:choose>
									</div></li>
							</ul>
						</div>
			    </c:otherwise>
			</c:choose>
					</div>
				<!-- <div class="panel-footer"></div>  -->
			</div>
		</div>
	</div>

		<div class="span3">
			<div class="alert alert-block">
				<h4 class="alert-heading">公告栏</h4>
				<p></p>
				<form  action="<%=request.getContextPath()%>/notice/add" method="post" id="noticeForm">
				<input type="hidden" name="businessId" value="${business.id}"/>
				<textarea id="noticeContext" class="input-xlarge textarea" placeholder="Enter text ..." 
                           name="content" style="width: 200px; height: 200px"></textarea>
				<button class="btn btn-warning" type="button" id="noticebutton" onclick="noticeForm(${business.id})">发布公示</button>  
					
					<button class="btn-success" type="button" onclick="saveNotice()">保留</button>
					</form>
				</div>
			</div>
		</div>
	<hr>
	
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
	<script src="<%=request.getContextPath()%>/callstatic/bootstrap/js/jquery.datetimepicker.full.js"></script>
		<script src="<%=request.getContextPath()%>/callstatic/layer/layer.js"></script>
	<script type="text/javascript">
	function cancel(currentid){
		if(window.confirm('您确定作废该号码？')){
            //alert("确定");
			window.location.href="<%=request.getContextPath()%>/business/cancel?currentid="+currentid;
         }else{
            //alert("取消");
            return false;
        }
	}
	function refresh(){
		window.location.reload();
	}
	function clearText(){
		$("input[type='text']").val("");
	}
	function onchangeType(){
		var type = $("#businessChoose").val();
		window.location.href="<%=request.getContextPath()%>/business?type="+type;
	}
	
	$(document).ready(function(){
		var usertype = ${usertype}+"";
		var businesstype=${business.type}+"";
		if(usertype=="4" && businesstype!=""){
			 $("#businessChoose").val(businesstype);
		}
	});
	$(document).ready(function(){
		var length = $("#ullist li").length;
		if(length==1){
			window.setInterval(function(){ 
				showalert(); 
			}, 120000);
		}
	});
	 
	function showalert() { 
		var id = $("#businessid").val();
		$.ajax({
			url: "<%=request.getContextPath()%>/business/timeleft",
			data: {"id":id},
			async: true,
			type: 'POST',
			dataType: 'jsonp',
			jsonp: 'jsonpcallback',
			success:function(data){
				 if(data.message==""){
					return;
				}
				$("#ullist").find("li").remove(); 
				$("#ullist").append(data.message);
			}
		});
	} 
	function saveNotice(){
		alert("该功能暂未开放！");
	}
	function nextOne(businessId,currentId){
		var businessstatus=${business.status}+"";
		if(businessstatus=="3"){
			alert("业务已经结束!");
			return;
		}
		if(businessstatus=="2"){
			alert("暂停状态不能点下一位!");
			return;
		}
		window.location.href="<%=request.getContextPath()%>/business/nextOne?businessId="+businessId+"&currentId="+currentId;
	}
	function noticeForm(id){
		if($("#noticeContext").val()==""){
			alert("请输入内容！");
			return ;
		}
		$.ajax({
			url: "<%=request.getContextPath()%>/business/businessStatus",
			data: {"id":id},
			async: true,
			type: 'POST',
			dataType: 'jsonp',
			jsonp: 'jsonpcallback',
			success:function(data){
				if(data.message=="2"||data.message=="3"){
					$("#noticeForm").submit();	
				}else{
					alert("业务处于暂停状态或结束状态才能发公告！");
				}
			}
		});
						
	}
	function endBusiness(id,currentid){
		if(window.confirm('您确定结束今天该业务工作吗？')){
            //alert("确定");
			window.location.href="<%=request.getContextPath()%>/business/end?id="+id+"&currentid="+currentid;
         }else{
            //alert("取消");
            return false;
        }
		//这里是否要用ajax 然后发送短信
		
	}
	function pauseBusiness(id){
		$.ajax({
			url: "<%=request.getContextPath()%>/business/businessStatus",
			data: {"id":id},
			async: true,
			type: 'POST',
			dataType: 'jsonp',
			jsonp: 'jsonpcallback',
			success:function(data){
				if(data.message!="1"){
					alert("业务处于开始状态才能暂停！");
				}else{
					window.location.href="<%=request.getContextPath()%>/business/pause?id="+id;
				}
				
			}
		});
	}
	
	function submitForm(id){
		if($("#amStart").val()=="" && $("#pmStart").val()==""){
			alert("请选择开始时间！");
			return false;
		}
		if($("#amEnd").val()=="" && $("#pmEnd").val()==""){
			alert("请选择结束时间！");
			return false;
		}
		if($("#amStart").val()>$("#amEnd").val()){
			alert("开始时间不能大于结束时间!");
			 window.location.reload();
			return false;
		}
		if($("#pmStart").val()>$("#pmEnd").val()){
			alert("开始时间不能大于结束时间!");
			 window.location.reload();
			return false;
		}
		$.ajax({
			url: "<%=request.getContextPath()%>/business/businessStatus",
			data: {"id":id},
			async: true,
			type: 'POST',
			dataType: 'jsonp',
			jsonp: 'jsonpcallback',
			success:function(data){
				if(data.message=="1"){
					alert("业务已开始！请勿重复开始！");
					return false;
				}else{
					$("#startForm").submit();					
				}
			}
		});
	}
	
	$('.datetimepicker1').datetimepicker({
		datepicker:false,
		format:'H:i',
		step:5
	});
	
	/* $('.form_time').datetimepicker({
        language:  'ch',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    }); */
	/* $("#next").click(function(){
		$("#nextph").slideUp("slow");
		
	}); */

</script>
	<script>
        $(function() {
          
        	
        });
        </script>
</body>

</html>