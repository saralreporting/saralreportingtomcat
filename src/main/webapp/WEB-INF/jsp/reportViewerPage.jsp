<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	

	
	
		<!-- <div class="main-container ace-save-state" id="main-container"> -->
			
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="/welcome">Home</a>
							</li>
							<li class="active">View Reports</li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
							<label>Select Department</label>
							<select name="deptidwithName" id="deptidwithName">
								<c:forEach items="${deptidwithName}" var="category1">
 								 <option value="${category1.orgCode}">${category1.orgName}</option>
								</c:forEach>
							</select>
						</div><!-- /.nav-search -->
					</div>

					<div class="page-content">
						<div class="ace-settings-container" id="ace-settings-container">
							<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
								<i class="ace-icon fa fa-cog bigger-130"></i>
							</div>

							<div class="ace-settings-box clearfix" id="ace-settings-box">
								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<div class="pull-left">
											<select id="skin-colorpicker" class="hide">
												<option data-skin="no-skin" value="#438EB9">#438EB9</option>
												<option data-skin="skin-1" value="#222A2D">#222A2D</option>
												<option data-skin="skin-2" value="#C6487E">#C6487E</option>
												<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
											</select>
										</div>
										<span>&nbsp; Choose Skin</span>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off" />
										<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off" />
										<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off" />
										<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off" />
										<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off" />
										<label class="lbl" for="ace-settings-add-container">
											Inside
											<b>.container</b>
										</label>
									</div>
								</div><!-- /.pull-left -->

								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off" />
										<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off" />
										<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off" />
										<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
									</div>
								</div><!-- /.pull-left -->
							</div><!-- /.ace-settings-box -->
						</div><!-- /.ace-settings-container -->

						<div class="page-header">
							<h1>
								View Reports
							<!-- 	 <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									View Reports
								</small>  -->
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<!-- <div class="alert alert-block alert-success">
									<button type="button" class="close" data-dismiss="alert">
										<i class="ace-icon fa fa-times"></i>
									</button>

									<i class="ace-icon fa fa-check green"></i>

									Welcome to
									<strong class="green">
										Saral Haryana
										<small>(v1.0)</small>
									</strong>,
										Report Designer.
								</div> -->

								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
						
					
						<jsp:useBean id="pagedListHolder" scope="request"
							type="org.springframework.beans.support.PagedListHolder" />
						<c:url value="/fetchReportList" var="pagedLink">
							<c:param name="p" value="~" />
						</c:url>

						<tg:paging pagedListHolder="${pagedListHolder}"
							pagedLink="${pagedLink}" />
							
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive cus-widget-box2">
						<table class="table table-striped table-bordered table-hover dataTable no-footer cus-grids-table cus-text-center">
							<tr>
								<th style="width: 5%;">Id</th>
								<th style="display: none;">Service ID</th>
								<th>Name</th>
								<th>Purpose</th>
								<th style="width: 5%;">View</th>
							</tr>
							<c:forEach items="${pagedListHolder.pageList}" var="item">
								<tr>
									<td style="width: 5%;">${item.reportId}</td>
									<td>${item.reportName}</td>
									<td style="display: none;">${item.serviceId}</td>
									<td>${item.tooltip}</td>
									<td style="width: 5%;"><%-- <spring:url	value="/viewSelectedReport?reportId=${item.reportId}&sign_no=${sign_no}&service_id=${item.serviceId}&page=1" var="viewURL" /> --%> 
										<a class="cus-view-bttn2"	href="javascript:void(0);" role="button" onclick="getData('${item.serviceId}','${item.reportId}')"></a></td>
								</tr>
							</c:forEach>
						</table>
						</div>
						<tg:paging pagedListHolder="${pagedListHolder}"
							pagedLink="${pagedLink}" />
		
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
	<!-- 	</div> -->
		<!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-ui.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/jquery.easypiechart.min.js"></script>
		<script src="assets/js/jquery.sparkline.index.min.js"></script>
	<!-- 	<!-- <script src="assets/js/jquery.flot.min.js"></script> 
		<script src="assets/js/jquery.flot.pie.min.js"></script>
		<script src="assets/js/jquery.flot.resize.min.js"></script> -->

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		
		
		
		<script type="text/javascript">
			function getData(servId,repId) {
				modifyData(servId,repId);
			}

			
			function modifyData(servId,repId) {
				var deptidwithNameSelected = $("#deptidwithName").val();
				$.ajax({
					type : "GET",
					url : '/viewSelectedReport?page=1',
					data : {
						deptidwithNameSelected : deptidwithNameSelected,
						reportId : repId,
						service_id : servId
					},
					success : function(data) {
						window.location.href='/viewSelectedReportData?page=1';
					}
				});
			}
		
		</script>
		