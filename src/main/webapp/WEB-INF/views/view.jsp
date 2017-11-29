<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<title>搜救中心财务综合服务平台</title>
<!-- Bootstrap -->
<link
	href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/callstatic/assets/button.css"
	rel="stylesheet" media="screen">
<link
	href="<%=request.getContextPath()%>/callstatic/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/callstatic/assets/styles.css"
	rel="stylesheet" media="screen">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="/callstatic/vendors/html5shiv.min.js"></script> 
<script src="/callstatic/vendors/respond.min.js"></script> 
<![endif]-->
<script
	src="<%=request.getContextPath()%>/callstatic/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<style type="text/css">
/* body{
padding-top:0px;
padding-bottom:0px;
 /* background-color:#eaf5fb; 
}
#imgcenter{
	width:100%;
} */
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,p,blockquote,th,td
	{
	padding-top: 0px;
	padding-bottom: 0px;
	margin: 0;
	padding: 0;
	border: none;
}

.getCode {
	margin-left: 10px;
}

table {
	border-collapse: collapse
}

.bottombor {
	border-bottom: 1px solid #C7C7C7
}
.yuefenlab{
	float: left;
    width: 50px;
    padding-top: 5px;
    text-align: right;
}
.export{
	margin-left:500px;
	width:105px;
}
.btcls{
	margin-top:-10px !important;
	margin-left:10px !important;
	width:80px;
}
th{
background-color:#f9f9f9;
}
td{
background-color:#ffffff !important;
}
.trbg{
background-color:#d6e4fd !important;
}
</style>
</head>
<body>
	<jsp:include page="common/top.jsp" flush="true" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12" id="content">
				<div class="row-fluid">
					<div class="block">
						<div class="navbar navbar-inner "></div>
						<div class="block-content collapse in">
							<div class="span12">
								<div class="row-fluid">
									<!-- block -->
										<div class="span12" id="content">
											<div class="row-fluid">
												<div class="control-group">
													<label class="control-label yuefenlab">月份</label> 
													<input type="text" id="datatime" name="datatime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate"/>
													<button class="btn btn-warning btcls" onclick="doSearch()">查找</button>
												</div>
											</div>
										</div>
									<!-- /block -->
								</div>

								<div class="row-fluid">
									<!-- block -->
									<div class="block">
										<div class="navbar navbar-inner block-header">
											<div class="muted pull-left">${datatime} 工资详情 </div>
										</div>

										<div class="block-content collapse in">
											<div class="span12">
												<table class="table table-striped">
													<thead>
														<tr>
															<th>部门名称</th>
															<th>职员姓名</th>
															<th>薪级工资</th>
															<th>岗位工资</th>
															<th>工改保留</th>
															<th>津贴</th>
															<th>浮动工资</th>
														</tr>
													</thead>
													<tbody>
														<tr class="bottombor">
															<td>${salaryOne.department }</td>
															<td>${salaryOne.name }</td>
															<td>${salaryOne.xinji }</td>
															<td>${salaryOne.gangwei }</td>
															<td>${salaryOne.gonggai }</td>
															<td>${salaryOne.jintie }</td>
															<td>${salaryOne.fudong }</td>
														</tr>
													</tbody>

													<thead>
														<tr>
															<th>地区补助</th>
															<th>交补</th>
															<th>未纳入</th>
															<th>提租补贴</th>
															<th>回婴独</th>
															<th>菜蓝子</th>
															<th>生活补贴</th>

														</tr>
													</thead>
													<tbody>
														<tr class="bottombor">
															<td>${salaryOne.diqu }</td>
															<td>${salaryOne.jiaobu }</td>
															<td>${salaryOne.weinaru }</td>
															<td>${salaryOne.tizu }</td>
															<td>${salaryOne.huiyingdu }</td>
															<td>${salaryOne.cailanzi }</td>
															<td>${salaryOne.shenghuo }</td>
														</tr>
													</tbody>
													<thead>
														<tr>
															<th>固定岗位绩效</th>
															<th>浮动岗位绩效</th>
															<th>物业费</th>
															<th>项目绩效</th>
															<th>补发</th>
															<th>应发工资合计</th>
															<th>其他</th>
														</tr>
													</thead>
													<tbody>
														<tr class="bottombor">
															<td>${salaryOne.guginggangwei }</td>
															<td>${salaryOne.fudonggangwei }</td>
															<td>${salaryOne.wuyefei }</td>
															<td>${salaryOne.xiangmujixiao }</td>
															<td>${salaryOne.bufa }</td>
															<td>${salaryOne.yingfaheji }</td>
															<td>${salaryOne.qita }</td>
														</tr>
													</tbody>

													<thead>
														<tr>
															<th>公积金</th>
															<th>养老保险</th>
															<th>失业保险</th>
															<th>预扣养老金</th>
															<th>职补</th>
															<th>通信补贴</th>
															<th>个税</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr class="bottombor">
															<td>${salaryOne.gongjijin }</td>
															<td>${salaryOne.yanglao }</td>
															<td>${salaryOne.shiye }</td>
															<td>${salaryOne.yukou }</td>
															<td>${salaryOne.zhibu }</td>
															<td>${salaryOne.tongxin }</td>
															<td>${salaryOne.geshui }</td>

														</tr>
													</tbody>

													<thead>
														<tr>
															<th>编辑费</th>
															<th>值班费</th>
															<th>讲课费</th>
															<th>出车补助</th>
															<th>应发费用合计</th>
															<th>费用个税</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr class="bottombor">
															<td>${salaryTwo.bianji }</td>
															<td>${salaryTwo.zhiban }</td>
															<td>${salaryTwo.jiangke }</td>
															<td>${salaryTwo.chuche }</td>
															<td>${salaryTwo.yingfaheji }</td>
															<td>${salaryTwo.geshui }</td>
															<td></td>

														</tr>
													</tbody>

													<thead><!-- 空一行 不显示 -->
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
													</thead>
													<thead>
														<tr>
															<th class="trbg">实发工资合计</th>
															<th class="trbg">实发费用合计</th>
															<th class="trbg"></th>
															<th class="trbg"></th>
															<th class="trbg"></th>
															<th class="trbg"></th>
															<th class="trbg">合计</th>
														</tr>
													</thead>
													<tbody>
														<tr class="bottombor">
															<td>${salaryOne.shifaheji }</td>
															<td>${salaryTwo.shifaheji }</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td>${salaryOne.heji }</td>
														</tr>
													</tbody>
													
												</table>
												<button class="btn btn-primary export" onclick="exportSalary('${datatime}')"> 导出</button>
											</div>
										</div>
									</div>
									<!-- /block -->
								</div>
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
	<script
		src="<%=request.getContextPath()%>/callstatic/vendors/jquery-1.9.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/callstatic/assets/scripts.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/bootstrap-datetimepicker.fr.js"
		charset="UTF-8"></script>
	<script
		src="<%=request.getContextPath()%>/callstatic/bootstrap/js/jquery.bootstrap.newsbox.js"
		type="text/javascript"></script>
	<script
			src="<%=request.getContextPath()%>/callstatic/js/WdatePicker.js"></script>
	<script type="text/javascript">
	function doSearch(){
		var datatime = $("#datatime").val();
		if(datatime==""){
			alert("请选择生成月报月份！");
			return;
		}else{
			window.location.href ="/salary/view?datatime="+datatime;
		}
	}
	
	function exportSalary(datatime){
		window.location.href ="/salary/export?datatime="+datatime;
	}
	
	</script>
</body>

</html>