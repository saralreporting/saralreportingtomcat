<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>





<!-- <div class="main-container ace-save-state" id="main-container"> -->

<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="/welcome">Report Domain Master</a>
				</li>
				<li class="active">Dashboard</li>
			</ul>
			<!-- /.breadcrumb -->

			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon"> <input type="text"
						placeholder="Search ..." class="nav-search-input"
						id="nav-search-input" autocomplete="off" /> <i
						class="ace-icon fa fa-search nav-search-icon"></i>
					</span>
				</form>
			</div>
			<!-- /.nav-search -->
		</div>

		<div class="page-content">
			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
					id="ace-settings-btn">
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
							<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
								id="ace-settings-navbar" autocomplete="off" /> <label
								class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
								id="ace-settings-sidebar" autocomplete="off" /> <label
								class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
								id="ace-settings-breadcrumbs" autocomplete="off" /> <label
								class="lbl" for="ace-settings-breadcrumbs"> Fixed
								Breadcrumbs</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-rtl" autocomplete="off" /> <label class="lbl"
								for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
								id="ace-settings-add-container" autocomplete="off" /> <label
								class="lbl" for="ace-settings-add-container"> Inside <b>.container</b>
							</label>
						</div>
					</div>
					<!-- /.pull-left -->

					<div class="pull-left width-50">
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-hover" autocomplete="off" /> <label
								class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-compact" autocomplete="off" /> <label
								class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-highlight" autocomplete="off" /> <label
								class="lbl" for="ace-settings-highlight"> Alt. Active
								Item</label>
						</div>
					</div>
					<!-- /.pull-left -->
				</div>
				<!-- /.ace-settings-box -->
			</div>
			<!-- /.ace-settings-container -->

			<div class="page-header">
				<h1>
					Report Domain Master
					<!-- <small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									overview &amp; stats
								</small> -->
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<!-- <div class="alert alert-block alert-success">
								<button type="button" class="close" data-dismiss="alert">
									<i class="ace-icon fa fa-times"></i>
								</button>

								<i class="ace-icon fa fa-check green"></i> Welcome to <strong
									class="green"> Saral Haryana <small>(v1.0)</small>
								</strong>, Report Designer.
							</div>

							<div class="hr hr32 hr-dotted"></div> -->
					<div id="hdndivval">
						<input type="hidden" name="deptid" value=${department_id
							} id="deptid" />
					</div>
					<div class="container">
						<c:if test="${DomainUpdate=='Add'}">
							<spring:url value="/saveReportDomainMaster" var="saveURL" />
						</c:if>
						<c:if test="${DomainUpdate=='Update'}">
							<spring:url value="/updateReportDomainMaster" var="saveURL" />
						</c:if>
						<!-- <h2>Report Domain Master</h2> -->
						<form:form modelAttribute="reportDomainMaster" method="post"
							action="${saveURL}" cssClass="form">


							<div class="form-group">
								<div>
									<label><b>User Name</b></label>
									<form:select path="domainUserId" id="Userdetail"
										class="form-control">
										<form:option value="" label="--- Select User ---" />
										<form:options items="${userList}" itemValue="userDataId"
											itemLabel="signNO" />
									</form:select>
								</div>
								<div>

									<label><b>Role Name</b></label>
									<form:select path="domainRoleId" id="UserRoledetail"
										class="form-control">
										<form:option value="" label="--- Select Role ---" />
										<%-- <c:if test="${DomainUpdate=='Update'}">
												 <form:options items="${roleList}" itemValue="reportRoleId"
													itemLabel="reportRoleName" /> 
												</c:if> --%>
									</form:select>
									<form:hidden path="domainRoleId" id="hiddenMainRole"
										cssClass="form-control" />

								</div>
								<div>
									<label><b>Filter Name</b></label>
									<form:select path="domainFilterId" id="UserFilterdetail"
										class="form-control">
										<form:option value="" label="--- Select Filter ---" />
										<form:options items="${filterList}" itemValue="reportFilterId"
											itemLabel="reportFilterName" />
									</form:select>

								</div>
								<div id="organistionsDiv">
									<br /> <label><b>Organizations</b></label> <input
										type="checkbox" name="all" id="checkall" />Check All<br />
									<div id="orgDiv">
										<!-- <br /> <label><b>Organizations</b></label> <input type="checkbox" name="all" id="checkall" />Check All<br /> -->
										<c:if test="${DomainUpdate=='Update'}">
											<c:forEach var="organization" items="${organizationList}">
												<input type="checkbox" value="${organization.orgCode},1"
													name="domainValueId"
													<c:forEach var="DomainList" items="${reportDomainUserList}">
															 <c:if test = "${organization.orgCode==DomainList.domainValueId}">
															checked
															 </c:if> 
																											 
													</c:forEach> />
												<label><c:out value="${organization.orgName}" /></label>
												<form:hidden path="domainValueName"
													value="${organization.orgName}" />
												<br />

											</c:forEach>

										</c:if>
										<c:if test="${DomainUpdate=='Add'}">
											<c:forEach var="organization" items="${organizationList}">
												<form:checkbox path="domainValueId"
													value="${organization.orgCode},1"
													label="${organization.orgName}" />
												<br />
												<%-- <c:out value="${organization.orgName}" /><br/> --%>
												<form:hidden path="domainValueName"
													value="${organization.orgName}" />
											</c:forEach>
										</c:if>

									</div>
								</div>
								<div id="districtDivMain">
									<br /> <label><b>Districts</b></label><input type="checkbox"
										name="all" id="checkallDistricts" />Check All Districts <br />
									<div id="districtDiv">
										<c:if test="${DomainUpdate=='Update'}">
											<c:forEach var="district" items="${districtList}">

												<input type="checkbox" value="${district.districtCode},2"
													name="domainValueId"
													<c:forEach var="DomainList" items="${reportDomainUserList}">
															 <c:if test = "${district.districtCode==DomainList.domainValueId}">
															checked
															 </c:if> 
															
													</c:forEach> />

												<c:out value="${district.districtNameEnglish}" />
												<form:hidden path="domainValueName"
													value="${district.districtNameEnglish}" />
												<br />
											</c:forEach>
										</c:if>

										<c:if test="${DomainUpdate=='Add'}">
											<c:forEach var="district" items="${districtList}">

												<form:checkbox path="domainValueId"
													value="${district.districtCode},2"
													label="${district.districtNameEnglish}" />
												<br />

												<%-- <c:out value="${organization.orgName}" /><br/> --%>
												<form:hidden path="reportDomainId" />
												<form:hidden path="domainValueName"
													value="${district.districtNameEnglish}" />
											</c:forEach>

										</c:if>
										<br />
									</div>
								</div>
								<div id="serviceDivMain">
									<br /> <label><b>Service</b></label><input type="checkbox"
										name="all" id="checkallServices" />Check All Services <br />
									<div id="servDiv">
										<c:if test="${DomainUpdate=='Update'}">
											<ul>
												<c:forEach var="service" items="${serviceList}"
													varStatus="domainValueId">
													<form:hidden path="reportDomainId" />
													<li style="display: none;"><input type="checkbox"
														value="${service.serviceCode},3"
														servdeptid="${service.deptCode}" name="domainValueId"
														<c:forEach var="DomainList" items="${reportDomainUserList}">
															 <c:if test = "${service.serviceCode==DomainList.domainValueId}">
															checked
															 </c:if> 
															<c:out value="${DomainList.domainValueId}" /> 
													</c:forEach> />
														<label><c:out value="${service.serviceName}" /></label> <form:hidden
															path="domainValueName" value="${service.serviceName}" />
													</li>

												</c:forEach>
											</ul>
										</c:if>



										<c:if test="${DomainUpdate=='Add'}">
											<ul>
												<c:forEach var="service" items="${serviceList}">
													<form:hidden path="reportDomainId" />
													<li style="display: none;"><form:checkbox
															path="domainValueId" value="${service.serviceCode},3"
															label="${service.serviceName}"
															servdeptid="${service.deptCode}" /> <form:hidden
															path="domainValueName" value="${service.serviceName}" />
														<%-- <c:out value="${service.serviceName}" /> --%></li>
												</c:forEach>

											</ul>
										</c:if>
										<br />
									</div>
								</div>
							</div>
							<c:if test="${DomainUpdate=='Add'}">
								<button type="submit" class="btn btn-primary">Save</button>
							</c:if>
							<c:if test="${DomainUpdate=='Update'}">
								<button type="submit" class="btn btn-primary">Update</button>
							</c:if>

						</form:form>

					</div>
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
	</div>
</div>
<!-- /.main-content -->

<!-- My having crud -->


<a href="#" id="btn-scroll-up"
	class="btn-scroll-up btn btn-sm btn-inverse"> <i
	class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- </div> -->
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
	/*  Select State from the ReportUserMasterController
	$(document).ready(function(){
	    			$.ajax({
	    				type : "get",
	    				url : "/UserReport",
	    				dataType : 'json',
	    				// contentType : 'application/json; charset=utf-8', 
	    				data : {
	    					action : "fetchUser"
	    				},
	    				contentType : 'application/json',
	    				success : function(responseJson) {
	    					console.log(responseJson);
	    					var $select = $("#selectedUser");                         
	    				        $select.find("option").remove();
	    				        $("<option>").val(0).text("Please Select User").appendTo($select);		
	    				        $.each(responseJson, function(key, value) {              
	    				            $("<option>").val(key).text(value).appendTo($select); 
	    				        });
	    				}
	    			})
	 			});
	*/
	
	// Select Role from the ReportRoleMasterController
	
 	$(document).ready(function(){	
			$.ajax({
				type : "get",
				url : "/RoleReport",
				dataType : 'json',
				data : {
					action : "fetchRole",
					userDataId : $("#Userdetail").val()
				},
				contentType : 'application/json',
				success : function(responseJson) {
					console.log(responseJson);
					var $select = $("#UserRoledetail");                         
				        $select.find("option").remove();
				        $("<option>").val(0).text("Please Select Role").appendTo($select);		
				        $.each(responseJson, function(key, value) {              
				            $("<option>").val(key).text(value).appendTo($select); 
				        });
				        var abc = $("#hiddenMainRole").val();
				        if(abc!=""){
				        $("#UserRoledetail").val(abc);
				        }
				}
			})
	});
	
	$(document).ready(function(){
		$('#Userdetail').change(function(){	
			$.ajax({
				type : "get",
				url : "/RoleReport",
				dataType : 'json',
				data : {
					action : "fetchRole",
					userDataId : $("#Userdetail").val()
				},
				contentType : 'application/json',
				success : function(responseJson) {
					console.log(responseJson);
					var $select = $("#UserRoledetail");                         
				        $select.find("option").remove();
				        $("<option>").val(0).text("Please Select Role").appendTo($select);		
				        $.each(responseJson, function(key, value) {              
				            $("<option>").val(key).text(value).appendTo($select); 
				        });
				}
			})
		})
	});

	/*  Select Filter from the ReportFilterMasterController
	$(document).ready(function(){
		$.ajax({
			type : "get",
			url : "/FilterReport",
			dataType : 'json',
			data : {
				action : "fetchfilter"
			},
			contentType : 'application/json',
			success : function(responseJson) {
				console.log(responseJson);
				var $select = $("#selectedFilter");                         
			        $select.find("option").remove();
			        $("<option>").val(0).text("Please Select Filter").appendTo($select);		
			        $.each(responseJson, function(key, value) {              
			            $("<option>").val(key).text(value).appendTo($select); 
			        });
			}
		})
		});
	*/
	
	/*  Select Filter from the ReportORganizationMasterController
	$(document).ready(function(){
		$.ajax({
			type : "get",
			url : "/FilterOrganization",
			dataType : 'json',
			data : {
				action : "fetchOrganization"
			},
			contentType : 'application/json',
			success : function(responseJson) {
				console.log(responseJson);
				var $select = $("#selectedOrganization");                         
			        $select.find("option").remove();
			        $("<option>").val(0).text("Please Select Organization").appendTo($select);		
			        $.each(responseJson, function(key, value) {              
			            $("<option>").val(key).text(value).appendTo($select); 
			        });
			        abc();
			}
		})
		});
	*/
	
	/*  Select District from the ReportDomainController
	$(document).ready(function(){
		$.ajax({
			type : "get",
			url : "/FilterDistirct",
			dataType : 'json',
			data : {
				action : "fetchDistirct"
			},
			contentType : 'application/json',
			success : function(responseJson) {
				console.log(responseJson);
				var $select = $("#selecteddistrict");                         
			        $select.find("option").remove();
			        $("<option>").val(0).text("Please Select Organization").appendTo($select);		
			        $.each(responseJson, function(key, value) {              
			            $("<option>").val(key).text(value).appendTo($select); 
			        });
			}
		})
		});
	*/
	
	
	/*  Select Schemes from the ReportDomainController
	$(document).ready(function(){
		$.ajax({
			type : "get",
			url : "/FilterSchemes",
			dataType : 'json',
			data : {
				action : "fetchScheme"
			},
			contentType : 'application/json',
			success : function(responseJson) {
				console.log(responseJson);
				var $select = $("#selectedScheme");                         
			        $select.find("option").remove();
			        $("<option>").val(0).text("Please Select Organization").appendTo($select);		
			        $.each(responseJson, function(key, value) {              
			            $("<option>").val(key).text(value).appendTo($select); 
			        });
			}
		})
		});
	*/
	
	$(document).ready(function(){
		$('#orgDiv').find('input[type="checkbox"]').on('change', function () {
			
			if ($(this).prop("checked") == true) {
				var number = $(this).next('label').text();
				var number2 = $(this).val();
				$(this).next('label').css('color', 'blue');
				var tobepass = '[servdeptid=' + $(this).val().split(',')[0]  + ']'; 
				$(tobepass).parent().show();
			}
			else {
				$(this).next('label').css('color', 'black');
				var tobepass = '[servdeptid=' + $(this).val().split(',')[0] + ']';
				$(tobepass).removeAttr('checked');
				$(tobepass).parent().hide();
			}
		});
	});
	
	$(document).ready(function(){
		
		$("#UserFilterdetail").change(function (){
			var filterSelected = $("#UserFilterdetail").val();
			if(filterSelected == 1){
				$("#organistionsDiv").show();
				$("#districtDivMain").hide();
				$("#serviceDivMain").hide();
			}else if(filterSelected==2){
				$("#organistionsDiv").hide();
				$("#districtDivMain").show();
				$("#serviceDivMain").hide();
			}else if(filterSelected==3){
				$("#organistionsDiv").hide();
				$("#districtDivMain").hide();
				$("#serviceDivMain").show();
			}else if(filterSelected==4){
				$("#organistionsDiv").show();
				$("#districtDivMain").hide();
				$("#serviceDivMain").hide();
			}else if(filterSelected==5){
				$("#organistionsDiv").show();
				$("#districtDivMain").hide();
				$("#serviceDivMain").hide();
			}else{
				$("#organistionsDiv").show();
				$("#districtDivMain").show();
				$("#serviceDivMain").show();
			}
		});
				
		
		$('#checkall:checkbox').change(function () {
			if ($(this).prop("checked") == true){
		    	$('#orgDiv').find('input[type="checkbox"]').prop( "checked",true);
		    	abc();
		    }
		    else{
		    	$('#orgDiv').find('input[type="checkbox"]').prop( "checked",false);
		    	$(this).next('label').css('color', 'black');
		    	abcnotChecked();
		    }
		});
		
		$('#checkallServices:checkbox').change(function () {
			if ($(this).prop("checked") == true){
		    	$('#servDiv').find('input[type="checkbox"]').prop( "checked",true);
		    }
		    else{
		    	$('#servDiv').find('input[type="checkbox"]').prop( "checked",false);
		    }
		});
		
		$('#checkallDistricts:checkbox').change(function () {
			if ($(this).prop("checked") == true){
		    	$('#districtDiv').find('input[type="checkbox"]').prop( "checked",true);
		    }
		    else{
		    	$('#districtDiv').find('input[type="checkbox"]').prop( "checked",false);
		    }
		});
		
		
		
		function abc(){
			$.each($('#orgDiv').find('input[type="checkbox"]:checked'), function(){            
		        var number = $(this).next('label').text();
				var number2 = $(this).val();
				$(this).next('label').css('color', 'blue');
				var tobepass = '[servdeptid=' + $(this).val().split(',')[0]  + ']'; 
				$(tobepass).parent().show();
		     });
		}
		
		function abcnotChecked(){
			$.each($('#orgDiv').find('input[type="checkbox"]:not(:checked)'), function(){            
				$(this).next('label').css('color', 'black');
				var tobepass = '[servdeptid=' + $(this).val().split(',')[0] + ']';
				$(tobepass).removeAttr('checked');
				$(tobepass).parent().hide();
		     });
		}
		
		abc();
	});
	
		</script>





