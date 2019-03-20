<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet"
	href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<style>
.error {
  color: red;
}
.container.cus-report {
	border: 1px solid #438EB9;
	border-radius: 4px;
}

.cus-report h2 {
	padding: 10px;
	width: 100%;
	margin-top: -22px;
	margin-bottom: 25px;
	background: #438EB9;
	color: #fff;
	font-size: 18px;
	font-family: 'Adamina';
}

.report-photo {
	margin-top: -5px;
}

.input-icon.input-icon-right>.ace-icon {
	right: 20px;
}
</style>
<!-- <div class="main-container ace-save-state" id="main-container"> -->

<div class="main-content" style="margin-left:190px;">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="/welcome">Home</a>
				</li>
				<li class="active">Report User Master</li>
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
					Report User Master
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
						<input type="hidden" name="deptid"
							value=${department_id} id="deptid" />
					</div>
					<div class="container">
						<spring:url value="/saveReportUserMaster" var="saveURL" />
						<!-- <h2>Report User Master</h2> -->
						<form:form modelAttribute="reportUserMaster" method="post"
							action="${saveURL}" cssClass="form">
							<form:hidden path="userDataId" />
							<div class="form-group">
								<label>User Name</label>
								<form:input path="signNO" cssClass="form-control" id="userNameForm"/>
								<form:errors path="signNO" cssClass="error" /><br>
								<label>Password</label>
								<form:password path="password" id="password" cssClass="form-control" />
								<form:hidden path="securedPassword" id="securedPassword" cssClass="form-control" />
								<form:errors path="password" cssClass="error" /><br>
								
								<label>Confirm Password</label>
								<form:password path="retypePassword" id="password" cssClass="form-control" />
								<form:errors path="retypePassword" cssClass="error" /><br>
								
								<label>Roles (Press control key (Ctrl) to select multiple values:)</label>
								<br>
										<form:select id="selectedRole" path="roleId"
											items="${mapColList}" multiple="true" itemValue="roleId"
											itemLabel="roleName"  />
											<form:hidden id="roleName" path="roleName"/>
									
								<br>
								<form:errors path="roleId" cssClass="error" /><br>
								<label>Designation</label>
								<form:input path="designation" cssClass="form-control"/>
								<form:errors path="designation" cssClass="error" /><br>
								<label>Detail of User</label>
								<form:input path="userDetail" cssClass="form-control"  />
								<td><form:errors path="userDetail" cssClass="error" /></td><br>
								<label>Mobile Number</label>
								<form:input path="mobilenumber" cssClass="form-control" />
								<td><form:errors path="mobilenumber" cssClass="error" /></td><br>
								<label>Email</label>
								<form:input path="email" cssClass="form-control"  />
								<td><form:errors path="email" cssClass="error" /></td><br>
								<label>State</label><br> 
								<select id="selectedState" name="stateId"
									style="width: 1000px" >
									<option value="0">Please Select State</option>
								</select>
								<form:hidden path="stateId" id="hiddenSelectedState" cssClass="form-control" />
								<form:errors path="stateId" cssClass="error" />
								<input type="hidden" id="isuserNameAvail" value=""/>
								<input type="hidden" id="isUpdateFunction" value=""/>
								<button type="submit" class="btn btn-primary" onclick="javascript:MyFunction()">Save</button>
							</div>
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
<script type="text/javascript" src="js/md5.min.js"></script>

<script type="text/javascript">        
	MyFunction = function() {
		var passwrd = $("#password").val();
		if (passwrd == "" || passwrd == null) {
			return false;
		} else {
			var newpass = md5(passwrd);
			$("#securedPassword").val(newpass);
		}
		
		var checkUp = $("#isUpdateFunction").val();
		if(checkUp==0){
		var isuA = $("#isuserNameAvail").val();
			if(isuA==0){
				alert("This username is not available. Please enter some other username");
				return false;
			}
		}
	}
</script>		

<script type="text/javascript">
	
	/*  Select State from the ReportUserMasterController*/
	$(document).ready(
			function() {
				$.ajax({
					type : "get",
					url : "/userStateReport",
					dataType : 'json',
					/* contentType : 'application/json; charset=utf-8', */
					data : {
						action : "fetchState"
					},
					contentType : 'application/json',
					success : function(responseJson) {
						console.log(responseJson);
						var $select = $("#selectedState");
						$select.find("option").remove();
						$("<option>").val(0).text("Please Select State").appendTo($select);
						$.each(responseJson, function(key, value) {
							$("<option>").val(key).text(value).appendTo($select);
						});
						
						var abc = $("#hiddenSelectedState").val();
				       	if(abc!=""){
				        	$("#selectedState").val(abc);
				        }
					}
				})
			});

	/*  Select State from the ReportRoleMasterController*/
	$(document).ready(
			function() {
				$.ajax({
					type : "get",
					url : "/userRoleReport",
					dataType : 'json',
					data : {
						action : "fetchRole"
					},
					contentType : 'application/json',
					success : function(responseJson) {
						console.log(responseJson);
						var $select = $("#selectedRole");
						$select.find("option").remove();
						/* $("<option>").val(0).text("Please Select Role").appendTo($select); */
						$.each(responseJson, function(key, value) {
							$("<option>").val(key).text(value).appendTo($select);
						});
					}
				})
			});
	
	//Check user name availability
	$(document).ready(function() {
				
		var checkUpdate = $("#userNameForm").val();
		if(checkUpdate==""){
			$("#isUpdateFunction").val(0);
			
			$("#userNameForm").on('focusout', function() {
				$.ajax({
					type : "get",
					url : "/checkUserAvaialability",
					dataType : 'json',
					/* contentType : 'application/json; charset=utf-8', */
					data : {
						signNO : $("#userNameForm").val()
					},
					contentType : 'application/json',
					success : function(responseJson) {
						console.log(responseJson);
						if(responseJson=="This UserName is not available."){
							alert("UserName is not available");
							$("#isuserNameAvail").val(0);
						}else if(responseJson=="This UserName is available."){
							//alert("This UserName is available.");
							$("#isuserNameAvail").val(1);
						}
						
					}
				})
			});
		} else{
			$("#isUpdateFunction").val(1);
		}
	});

	
</script>
<script type="text/javascript">
$(document).ready(function() {
    $("button").click(function(){
        var roleName = [];
        $.each($("#selectedRole option:selected"), function(){            
        	roleName.push($(this).text());
        
        });
        //alert("You have selected the Roles - " + roleName.join(", "));
        $("#roleName").val(roleName);
        console.log(roleName);
    });
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	var array = $('#roleName').val().split(",");
	$.each(array,function(i){
		alert(array);
		
		  $('#selectedRole option').filter(function() { 
		        return ($(this).text() == array[i]); //To select Blue
		    }).attr('selected', true);
		});
	
});

</script>