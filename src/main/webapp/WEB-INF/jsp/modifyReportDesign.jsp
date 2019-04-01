<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- <div class="main-container ace-save-state" id="main-container"> -->

<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
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
				<!-- <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn"> -->
				<!-- <i class="ace-icon fa fa-cog bigger-130"></i> -->
				<!-- </div> -->

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
				<!-- <h1> -->
				<!-- Dashboard -->
				<!-- <small> -->
				<!-- <i class="ace-icon fa fa-angle-double-right"></i> -->
				<!-- overview &amp; stats -->
				<!-- </small> -->
				<!-- </h1> -->
			</div>
			<!-- /.page-header -->
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 container" id="reprt2"
								style="height: 200px; width: 80%; float: none; margin: auto; display: none; background-color: aliceblue;">
								<h3>Please select Service below :</h3>
								<select id="selectedRecord" name="selectedRecord"
									style="width: 600px">
									<option value="0">Please Select</option>
								</select> <input type="submit" class="btn btn-purple no-border"
									id="getcol" value="Get Columns" />
				</div>
				
				<div class="col-lg-12 col-md-12 col-xs-12 container" id="updateReportLabel" style="height: 50px; width: 80%; float: none; margin: auto; display: none; background-color: aliceblue;">
				</div>
				<div class="hr hr32 hr-dotted" ></div>
			</div>
			
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
				
					<form id="mycustreport" method="post" enctype="multipart/form-data">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li id="c1" class="active" ><a href="#sql" data-toggle="tab" onclick="javascript:changeTab0()"><img
										src="assets/images/icons/customize.png"
										class="img-responsive tab-img"><span>Step-1. Columns Selection</span></a></li>
								<li id="c2"><a href="#customize" data-toggle="tab" onclick="javascript:changeTab()"><img
										src="assets/images/icons/customize.png"
										class="img-responsive tab-img"><span>Step-2. Report Details</span></a></li>
								<li id="c3"><a href="#divForOrdr" data-toggle="tab" onclick="javascript:changeTab2()"><img
										src="assets/images/icons/customize.png"
										class="img-responsive tab-img"><span>Step-3. Order By
											Conditions</span></a></li>
								<li id="c4"><a href="#divForWhere" data-toggle="tab" onclick="javascript:changeTab3()"><img
										src="assets/images/icons/customize.png"
										class="img-responsive tab-img"><span>Step-4. Where
											Conditions</span></a></li>
								<li id="c5"><a href="#divForHaving" data-toggle="tab" onclick="javascript:changeTab4()"><img
										src="assets/images/icons/customize.png"
										class="img-responsive tab-img"><span>Step-5. Having
											Conditions</span></a></li>
							</ul>
							<div class="tab-content">

								<div class="tab-pane active" id="sql">
									<div class="col-lg-12 col-md-12 col-xs-12 container" id="selcol" style="display: none;">
										<div class="container " id="collist"
											style="width:33%; float: left;">
											<h4 align="left">Please select Service Attributes below :</h4>
											<div class="container" id="ContentPlaceHolder1_CheckBoxList1"
												style="overflow: scroll; height: 300px; width: 100%; float: left; BACKGROUND-COLOR: aliceblue;">
											</div>
										</div>
										<div class="container " id="applInfoCollist"
											style="width:33%; float: left;">
											<h4 align="left">Please select ApplInfo Columns below:</h4>
											<div class="container" id="ContentPlaceHolder2_CheckBoxList2"
												style="overflow: scroll; height: 300px; width: 100%; float: left; BACKGROUND-COLOR: aliceblue;">
											</div>
										</div>
										<div class="container" id="selcollist"
											style="width:33%; float: left;">
											<H4 align="left">Selected columns for report</H4>
											<div id="sortable1"
												style="overflow: scroll; height: 150px; width: 100%; float: left; background-color: aliceblue;">
												<ul id="sortable" class="cus-jquery-tabs"
													style="cursor: pointer;">
												</ul>
											</div>
											<div id="sortable5"
												style="overflow: scroll; height: 150px; width: 100%; float: left; background-color: aliceblue;">
												<ul id="sortableB" class="cus-jquery-tabs"
													style="cursor: pointer;">
												</ul>
											</div>
										</div>
									</div>
									<div id="hdndivval">
										<input type="hidden" name="deptid" value=${department_id} id="deptid" />
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="margin-top: 15px;">
									<a id="next1" href="#customize" class="cus-next-bttn"  data-toggle="tab" onclick="javascript:changeTab()">Next</a>
									</div>
								</div>
								<!-- <div class="tab-pane" id="setting">Setting</div> -->
								<div class="tab-pane" id="customize">
									<span class="cus-filter">Report Details</span> <input
										type="hidden" id="hddnJSONB" name="hddnJSONB" /> <input
										type="hidden" id="hddnJSON" name="hddnJSON" /> <input
										type="hidden" id="departmentID" name="departmentID" value="" />
									<input type="hidden" id="serviceID" name="serviceID" value="" />
									<input type="hidden" id="reportID" name="reportID" value="" />
									<input type="hidden" id="isAdminReport" name="isAdminReport" value="" />
									<input type="hidden" id="desigID" name="desigID"
										value=${designation_id } /> <input type="hidden" id="userID"
										name="userID" value=${user_id } /> <input type="hidden"
										id="sign_no" name="sign_no" value=${sign_no } /> <input
										type="hidden" id="selectedColList" name="selectedColList" />
									<input type="hidden" id="whrclsJSON" name="whrclsJSON" /> <input
										type="hidden" id="hvngclsJSON" name="hvngclsJSON" /> <input
										type="hidden" id="odrclsJSON" name="odrclsJSON" />
										<input type="hidden" id="agrclsJSON" name="agrclsJSON" />
									<div class="form-group">
										<label class="control-label col-sm-3" for="rpName">Report
											Name:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="rpName"
												placeholder="Enter Report Name" name="rpName"
												required="required">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-3" for="rpColor">
											Table Color:</label>
										<div class="col-sm-9">
											<input class="jscolor form-control" id="rpColor"
												name="rpColor" value="FFFFFF">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-3" for="tooltip">
											Tool-tip:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="tooltip"
												placeholder="Enter Tool-tip" name="tooltip">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-3" for="bgtext">Report
											Background text:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="bgtext"
												placeholder="Enter Background text " name="bgtext">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-3" for="rpGrpBy">
											Select Group By:</label>
										<div class="col-sm-9">
											<select multiple  class="form-control" name="rpGrpBy" id="rpGrpBy">
												 <c:forEach items="${colListforUpdate}" var="category">
													<option value="${category.reportSelectedColumnId}">${category.reportSelectedColumnName}</option>
												 </c:forEach>
											</select>
										</div>
									</div>
									<input class="form-group" type="hidden" name="grpIdName"
										id="grpIdName" />

									<div class="form-group">
										<label class="control-label col-sm-3" for="rpHeader">Report
											Header Text:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="rpHeader"
												placeholder="Enter Report Header Text" name="rpHeader">
										</div>
									</div>

									<script>
													CKEDITOR.replace('rpHeader');
												</script>

									<div class="form-group">
										<label class="control-label col-sm-3" for="rpFooter">
											Report Footer Text:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="rpFooter"
												placeholder="Enter Report Footer Text" name="rpFooter">
										</div>
									</div>
									<script>
													CKEDITOR.replace('rpFooter');
									</script>
									<div id="customDesign" style="width: 400px;"></div>
									<div id="JSONTEXT"
										style="color: black; font-size: 20px; text-decoration: double; display: none;"></div>
									<div id="JSONTEXTB"
										style="color: black; font-size: 20px; text-decoration: double; display: none;"></div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
									<a id="next2" href="#divForOrdr" class="cus-next-bttn"  data-toggle="tab" onclick="javascript:changeTab2()">Next</a>
									</div>
								</div>

								<div class="tab-pane" id="divForOrdr">
									<span class="cus-filter">Report Details - Order By</span>
									<div class="form-group">
										<label class="control-label col-sm-3" for="rpODCondition">Order
											By Condition :</label>
										<div class="col-sm-3">
											<select class="form-control" name="rpGrpBy9" id="rpGrpBy9">
												<c:forEach items="${colListforUpdate}" var="category">
													<option value="${category.reportSelectedColumnId}">${category.reportSelectedColumnName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-3">
											<select class="form-control" name="rpODCondition"
												id="rpODCondition">
												<option value="0">Please Select</option>
												<option value="ASC">Ascending</option>
												<option value="DESC">Descending</option>
											</select>
										</div>
										<div class="col-sm-2">
											<input type="button" style="" class="btn no-border"
												value="Add More" onclick="javascript:AddODCondition()" />
										</div>
									</div>
									<div class="form-group">
										<div>
											<label class="control-label col-sm-4"
												for="rpODConditionGridView">Order BY Grid View:</label>
											<table id="rpODConditionGridView">
												<thead>
													<tr>
														<th class="col-sm-1"><button
																class="btn no-border .delete-row" type="button"
																onclick="javascript:DeleteThisRowOrdr()">Delete Row</button></th>
														<th style="display: none;" class="col-sm-1">ColumnId</th>
														<th class="col-sm-4">Column</th>
														<th class="col-sm-3">Order</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
									<a id="next3" href="#divForWhere" class="cus-next-bttn"  data-toggle="tab" onclick="javascript:changeTab3()">Next</a>
									</div>
								</div>
								<div class="tab-pane" id="divForWhere">
									<span class="cus-filter">Report Details - Where
										Condition</span>
									<div class="form-group">
										<label class="control-label col-sm-3" for="rpWhrCondition">WHERE
											Condition :</label>
										<div class="col-sm-2">
											<select class="form-control" name="rpGrpBy2" id="rpGrpBy2">
												<c:forEach items="${colListforUpdate}" var="category">
													<option value="${category.reportSelectedColumnId}">${category.reportSelectedColumnName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-2">
											<select class="form-control" name="rpWhrCondition" id="rpWhrCondition">
												<option value="0">Please Select</option>
											</select>	
										</div>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="rpWhrCondition2"
												placeholder="Enter Condition Value" name="rpWhrCondition2">
										</div>
										
										<div class="col-sm-1">
											<select class="form-control" name="rpWhr1Condition" id="rpWhr1Condition">
												<option value="0">Please Select</option>
												<option value="and">And</option>
												<option value="or">Or</option>
											</select>	
										</div>
										<div class="col-sm-1">
											<input type="button" style=""
										class="btn no-border" value="Add More"
										onclick="javascript:AddWhrCondition()" />
										</div>
									</div>

									<div class="form-group">
										<div>
											<label class="control-label col-sm-3" for="rpWhrConditionGridView">Where 
												Condition Grid View:</label>
											<table id="rpWhrConditionGridView">
												<thead>
													<tr>
														<th class="col-sm-1"><button class="btn no-border .delete-row" type="button" onclick="javascript:DeleteThisRow()">Delete Row</button></th>
														<th style="display: none;" class="col-sm-1">ColumnId</th>
														<th class="col-sm-3">Column</th>
														<th class="col-sm-2">Condition</th>
														<th class="col-sm-1">Value</th>
														<th class="col-sm-1">And Or</th>
													</tr>
												</thead>
												<tbody>
												
												</tbody>
											</table>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
									<a id="next4" href="#divForHaving" class="cus-next-bttn"  data-toggle="tab" onclick="javascript:changeTab4()">Next</a>
									</div>
								</div>
								<div class="tab-pane" id="divForHaving">
									<span class="cus-filter">Report Details - Having
										Conditions</span>
										<label class="control-label col-sm-2" for="rpHvngCondition">Having
											Condition :</label>
									<div class="form-group">
											<div class="col-sm-2">
											<select class="form-control" name="rpGrpBy3" id="rpGrpBy3">
												<c:forEach items="${colListforUpdate}" var="category">
													<option value="${category.reportSelectedColumnId}">${category.reportSelectedColumnName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-2">
											<select class="form-control" name="rphvngCondition" id="rphvngCondition">
												<option value="0">Please Select</option>
											</select>
										</div>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="rphvngCondition2"
												placeholder="Enter Condition Value" name="rphvngCondition2">
										</div>
										<div class="col-sm-2">
											<select class="form-control" name="rphvn1Condition" id="rphvn1Condition">
												<option value="0">Please Select</option>
												<option value="=">equal</option>
												<option value=">">greaterThan</option>
												<option value="<">lessThan</option>
												<option value=">=">greaterThanEqual</option>
												<option value="<=">lessThanEqual</option>
												
											</select>	
										</div>
										<div class="col-sm-1">
											<input type="button" style=""
										class="btn no-border" value="Add More"
										onclick="javascript:AddHvngCondition()" />
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-3" for="rpHvngConditionGridView">Having 
													Condition Grid View:</label>
										<div>
											<table id="rpHvngConditionGridView">
												<thead>
													<tr>
														<th class="col-sm-1"><button class="btn no-border .delete-row" type="button" onclick="javascript:DeleteThisHvngRow()">Delete Row</button></th>
														<th style="display: none;" class="col-sm-1">ColumnId</th>
														<th class="col-sm-2">Column</th>
														<th class="col-sm-2">Function</th>
														<th class="col-sm-1">Value</th>
														<th class="col-sm-2">Condition</th>
													</tr>
												</thead>
												<tbody>
												
												</tbody>
											</table>
										</div>
									</div>
									<span class="cus-filter">Report Details - Aggregation</span> 
									<label class="control-label col-sm-3" 
										for="rpAggrrCondition">Aggregation :</label>
									<div class="form-group">
										<div class="col-sm-3">
											<select class="form-control" name="rpGrpBy19" id="rpGrpBy19">
												<c:forEach items="${colListforUpdate}" var="category">
													<option value="${category.reportSelectedColumnId}">${category.reportSelectedColumnName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-3">
											<select class="form-control" name="rpAGCondition" id="rpAGCondition">
												<option value="0">Please Select</option>
												<option value="sum">Sum</option>
												<option value="count">Count</option>
											</select>	
										</div>
										<div class="col-sm-2">
											<input type="button" style=""
										class="btn no-border" value="Add More"
										onclick="javascript:AddAGCondition()" />
										</div>
									</div>
									<div class="form-group">
										
										<div>
										<label class="control-label col-sm-4" for="rpAGConditionGridView">Aggregation 
											Grid View:</label>
											<table id="rpAGConditionGridView">
												<thead>
													<tr>
														<th class="col-sm-1"><button class="btn no-border .delete-row" type="button" onclick="javascript:DeleteThisRowAgr()">Delete Row</button></th>
														<th style="display: none;" class="col-sm-1">ColumnId</th>
														<th class="col-sm-3">Column</th>
														<th class="col-sm-3">Function</th>
													</tr>
												</thead>
												<tbody>
												
												</tbody>
											</table>
										</div>
										<br> <br> <br>
										<div class="form-group">
											<input type="submit" style="" class="btn btn-purple no-border" value="Save Report Design"
											onclick="javascript:MyJsonFunction()" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- PAGE CONTENT ENDS -->
</div>
<!-- /.col -->
<!-- /.row -->

<a href="#" id="btn-scroll-up"
	class="btn-scroll-up btn btn-sm btn-inverse"> <i
	class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<!-- <script src="assets/js/jquery-3.1.1.js"></script> -->

<!-- <![endif]-->

<!--[if IE]>

<![endif]-->
<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="/js/jquery.tabletojson.min.js"></script>
	<script src="/js/underscore-min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
<script src="assets/js/newjs/bootstrap.min.js"></script>
<script src="assets/js/newjs/jquery.dataTables.min.js"></script>
<script src="assets/js/newjs/dataTables.buttons.min.js"></script>
<script src="assets/js/newjs/buttons.flash.min.js"></script>
<script src="assets/js/newjs/jszip.min.js"></script>
<script src="assets/js/newjs/pdfmake.min.js"></script>
<script src="assets/js/newjs/vfs_fonts.js"></script>
<script src="assets/js/newjs/buttons.html5.min.js"></script>
<script src="assets/js/newjs/buttons.print.min.js"></script>
<script src="assets/js/newjs/pivot.js"></script>


<script src="assets/js/jquery-ui.custom.min.js"></script>

	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.easypiechart.min.js"></script>
	<script src="assets/js/jquery.sparkline.index.min.js"></script>



<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
			jQuery(function($) {
			
				/////////////////////////////////////
				$(document).one('ajaxloadstart.page', function(e) {
					$tooltip.remove();
				});
			
				$('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('.tab-content')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			
				$('.dialogs,.comments').ace_scroll({
					size: 300
			    });




            $(function(){
                        $("#output").pivot(
        [
            {color: "blue", shape: "circle"},
            {color: "red", shape: "triangle"}
        ],
        {
            rows: ["color"],
            cols: ["shape"]
        }
    );
             });
			});
		</script>

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="assets/css/newcss/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/css/newcss/jquery.dataTables.min.css" />
<link rel="stylesheet"
	href="assets/css/newcss/buttons.dataTables.min.css" />
<link rel="stylesheet" href="assets/css/newcss/pivot.css" />
<link rel="stylesheet"
	href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />


<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="assets/css/style.css" />


<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<link href="assets/css-new/owl.carousel.css" rel="stylesheet">
<link href="assets/css-new/owl.theme.default.min.css" rel="stylesheet">

<link href="assets/css-new/magnific-popup.css" rel="stylesheet">
<link href="assets/css-new/owl.carousel.css" rel="stylesheet">

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->




<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		
		
<script type="text/javascript">
			
		changeTab0=function(){
			$( "#c1" ).prop( "class", "Active" );
			$( "#c2" ).prop( "class", "" );
			$( "#c3" ).prop( "class", "" );
			$( "#c4" ).prop( "class", "" );
			$( "#c5" ).prop( "class", "" );
		};
		
		changeTab=function(){
			$( "#c2" ).prop( "class", "Active" );
			$( "#c1" ).prop( "class", "" );
			$( "#c3" ).prop( "class", "" );
			$( "#c4" ).prop( "class", "" );
			$( "#c5" ).prop( "class", "" );
		};
		
		changeTab2=function(){
			$( "#c3" ).prop( "class", "Active" );
			$( "#c1" ).prop( "class", "" );
			$( "#c2" ).prop( "class", "" );
			$( "#c4" ).prop( "class", "" );
			$( "#c5" ).prop( "class", "" );
		}; 
		
		changeTab3=function(){
			$( "#c4" ).prop( "class", "Active" );
			$( "#c1" ).prop( "class", "" );
			$( "#c2" ).prop( "class", "" );
			$( "#c3" ).prop( "class", "" );
			$( "#c5" ).prop( "class", "" );
		};		
		
		changeTab4=function(){
			$( "#c5" ).prop( "class", "Active" );
			$( "#c1" ).prop( "class", "" );
			$( "#c2" ).prop( "class", "" );
			$( "#c3" ).prop( "class", "" );
			$( "#c4" ).prop( "class", "" );
		};

			AddODCondition = function(){
				var ColumnId = $("#rpGrpBy9").val();				
				var Column = $("#rpGrpBy9 option:selected").text();
	            var Condition = $("#rpODCondition").val();
	            if(ColumnId=="" || Column=="Please Select" || Condition==0){
	            	alert("Please select values from dropdown");
	            	return false;
	            }
	            var markup = "<tr><td><input type='checkbox' name='recordgridodr' id='recordgridodr'></td><td style='display:none;'>" + ColumnId + "</td><td>" + Column + "</td><td>" + Condition + "</td></tr>";
	            $("#rpODConditionGridView tbody").append(markup);
	            MyOrderByFunction();
			};
	
			//Add row to Aggregation Grid
			AddAGCondition = function(){
				var ColumnId = $("#rpGrpBy19").val();				
				var Column = $("#rpGrpBy19 option:selected").text();
				var Condition = $("#rpAGCondition").val();
				if(ColumnId=="" || Column=="Please Select" || Condition==0){
					alert("Please select values from dropdown");
					return false;
				}
				var markup = "<tr><td><input type='checkbox' name='recordgridagr' id='recordgridagr'></td><td style='display:none;'>" + ColumnId + "</td><td>" + Column + "</td><td>" + Condition + "</td></tr>";
				$("#rpAGConditionGridView tbody").append(markup);
				MyAggregationFunction();
			};
			
			//Add row to Where condition grid
			AddWhrCondition = function(){
				var ColumnId = $("#rpGrpBy2").val();				
				var Column = $("#rpGrpBy2 option:selected").text();
				var Condition = $("#rpWhrCondition").val();
				var Value = $("#rpWhrCondition2").val();
				var AndOr = $("#rpWhr1Condition").val();
				if(ColumnId=="" || Column=="Please Select" || Condition==0){
					alert("Please select values from dropdown");
					return false;
				}
				var markup = "<tr><td><input type='checkbox' name='recordgridwhr' id='recordgridwhr'></td><td style='display:none;'>" + ColumnId + "</td><td>" + Column + "</td><td>" + Condition + "</td><td>" + Value + "</td><td>" + AndOr + "</td></tr>";
				$("#rpWhrConditionGridView tbody").append(markup);
				MyWhereFunction();
			};
			
			//add row to having grid
			AddHvngCondition = function(){
				var ColumnId = $("#rpGrpBy3").val();
				var Column = $("#rpGrpBy3 option:selected").text();
				var Function = $("#rphvngCondition").val();
				var Value = $("#rphvngCondition2").val();
				var Condition = $("#rphvn1Condition").val();
				if(ColumnId=="" || Column=="Please Select" || Condition=="0"){
					alert("Please select values from dropdown");
					return false;
				}
				var markup = "<tr><td><input type='checkbox' name='recordgridHvng' id='recordgridHvng'></td><td style='display:none;'>" + ColumnId + "</td><td>" + Column + "</td><td>" + Function + "</td><td>" + Value + "</td><td>" + Condition + "</td></tr>";
				$("#rpHvngConditionGridView tbody").append(markup);
				MyHavingFunction();
			};
			
			//to remove from Having condition grid
			DeleteThisHvngRow = function(){
	            $("#rpHvngConditionGridView tbody").find('input[name="recordgridHvng"]').each(function(){
	            	if($(this).is(":checked")){
	            		$(this).parent().parent().remove();
	                }
	            });
	            MyHavingFunction();
	        };
			
	        //to remove from where condition grid
			DeleteThisRow = function(){
	            $("#rpWhrConditionGridView tbody").find('input[name="recordgridwhr"]').each(function(){
	            	if($(this).is(":checked")){
	            		$(this).parent().parent().remove();
	                }
	            });
	            MyWhereFunction();
	        };
	        
	      //to remove from Sort condition grid
			DeleteThisRowOrdr = function(){
	            $("#rpODConditionGridView tbody").find('input[name="recordgridodr"]').each(function(){
	            	if($(this).is(":checked")){
	            		$(this).parent().parent().remove();
	                }
	            });
	            MyOrderByFunction();
	        };
	        
	      //to remove from Aggregation grid
	        DeleteThisRowAgr = function(){
				$("#rpAGConditionGridView tbody").find('input[name="recordgridagr"]').each(function(){
					if($(this).is(":checked")){
						$(this).parent().parent().remove();
					}
				});
				MyAggregationFunction();
			};
			
	        
	        MyOrderByFunction = function(){
	        	var table1 = $('#rpODConditionGridView').tableToJSON();
				function arrUnique(arr){
					   	var cleaned = [];
					    table1.forEach(function(itm6){
					        var unique = true;
					        cleaned.forEach(function(itm9){
					            if (_.isEqual(itm6, itm9)) unique = false;
					        });
					        if (unique)  cleaned.push(itm6);
					    });
					    return cleaned;
					}
				var uniqueStandards = arrUnique(table1);
				for(var i = 0; i < uniqueStandards.length; i++) {
					delete uniqueStandards[i]['Delete Row'];
				}
				table1 = {"Order": uniqueStandards};//make myrows the parent object
				var tab1 = JSON.stringify(table1);
				$("#odrclsJSON").val(tab1);
				//alert(tab1);
	        }
	        
			MyWhereFunction = function(){
				//create json of table values For Where GRID Conditions
				var table1 = $('#rpWhrConditionGridView').tableToJSON();
				function arrUnique(arr){
					   	var cleaned = [];
					    table1.forEach(function(itm6){
					        var unique = true;
					        cleaned.forEach(function(itm9){
					            if (_.isEqual(itm6, itm9)) unique = false;
					        });
					        if (unique)  cleaned.push(itm6);
					    });
					    return cleaned;
					}
				var uniqueStandards = arrUnique(table1);
				for(var i = 0; i < uniqueStandards.length; i++) {
					delete uniqueStandards[i]['Delete Row'];
				}
				table1 = {"Where": uniqueStandards};//make myrows the parent object
				var tab1 = JSON.stringify(table1);
				$("#whrclsJSON").val(tab1);
				//alert(tab1);
	        }
			
			MyHavingFunction = function(){
				//create json of table values for Having GRID Conditions
				var table = $('#rpHvngConditionGridView').tableToJSON();
				function arrUniqueB(arr){
					   	var cleaned = [];
					    table.forEach(function(itm8){
					        var unique = true;
					        cleaned.forEach(function(itm7){
					            if (_.isEqual(itm8, itm7)) unique = false;
					        });
					        if (unique)  cleaned.push(itm8);
					    });
					    return cleaned;
					}
				var uniqueStandardsB = arrUniqueB(table);
				for(var i = 0; i < uniqueStandardsB.length; i++) {
					delete uniqueStandardsB[i]['Delete Row'];
				} 	
				table = {"Having": uniqueStandardsB};//make myrows the parent object
				var tab2 = JSON.stringify(table);
				$("#hvngclsJSON").val(tab2);
	        }
			
			MyAggregationFunction = function(){
				var table = $('#rpAGConditionGridView').tableToJSON();
				function arrUniqueB(arr) {
						var cleaned = [];
						table.forEach(function(itm) {
							var unique = true;
							cleaned.forEach(function(itm2) {
								if (_.isEqual(itm, itm2)) unique = false;
							});
							if (unique)  cleaned.push(itm);
						});
						return cleaned;
					}
				var uniqueStandardsB = arrUniqueB(table);
				for(var i = 0; i < uniqueStandardsB.length; i++) {
					delete uniqueStandardsB[i]['Delete Row'];
				} 	
				table = {"Aggregation": uniqueStandardsB};//make myrows the parent object
				var tab3 = JSON.stringify(table);
				$("#agrclsJSON").val(tab3);
				//alert(tab3);
			}
			
			//Submit function to calculate values
	        MyJsonFunction = function(){         
	             var abcB = $("#JSONTEXTB").text();
	             var abc = $("#JSONTEXT").text(); 
	             if(abc!="" && abcB!=""){
	             var l1 = abcB.substring(0, abcB.length-1);
	        	 var l2 = abc.substring(1, abc.length);
	        	 var finalObj = l1 + "," + l2;
	             $("#selectedColList").val(finalObj);
	             } else if(abc=="" && abcB!=""){
	            	$("#selectedColList").val(abcB);
	             }  else if(abcB=="" && abc!=""){
	            	$("#selectedColList").val(abc);
	             } 
	             
	             var nvg = $("#rpGrpBy").val();
	             var tobesave = nvg;
	             $("#grpIdName").val(tobesave);
	            
	             var desc = CKEDITOR.instances['rpHeader'].getData();
	             $("#rpHeader").val(desc);
	             
	             var desc1 = CKEDITOR.instances['rpFooter'].getData();
	             $("#rpFooter").val(desc1);
					 
					var x1= $("#hvngclsJSON").val(); 
					if(x1==""){
						abc = [];
						var table1 = {"Having": abc};
						var tab1 = JSON.stringify(table1);
						$("#hvngclsJSON").val(tab1);  
						//$("#hvngclsJSON").val("[]");
					}

					var x2= $("#odrclsJSON").val(); 
					if(x2==""){
						abc = [];
						var table1 = {"Order": abc};
						var tab1 = JSON.stringify(table1);
						$("#odrclsJSON").val(tab1);  
						//$("#odrclsJSON").val("[]");
					}
					
					var x3= $("#whrclsJSON").val(); 
					if(x3==""){
						abc = [];
						var table1 = {"Where": abc};//make myrows the parent object
						var tab1 = JSON.stringify(table1);
						$("#whrclsJSON").val(tab1);  
						//$("#whrclsJSON").val("[]");
					}
					
					var x4= $("#agrclsJSON").val(); 
					if(x4==""){
						abc = [];
						var table1 = {"Aggregation": abc};//make myrows the parent object
						var tab1 = JSON.stringify(table1);
						$("#agrclsJSON").val(tab1);  
						//$("#whrclsJSON").val("[]");
					}
	        }
    	</script>
	<script type="text/javascript">
		 var addition = [];
		 var additionB = [];
	      var querystring = '';
	      var Declarequerystring = '';
	      var wherequerystring = '';
		$(document).ready(function(){
				
	    		
	    			$.ajax({
	    				type : "get",
	    				url : "/DesignReport",
	    				dataType : 'json',
	    				data : {
	    					deptOid : $('#deptid').val(),
	    					action : "fetchService"
	    				},
	    				contentType : 'application/json',
	    				success : function(responseJson) {
	    					console.log(responseJson);
	    					 //$("#reprt2").show();
	    					 var $select = $("#selectedRecord");                         
	    				        $select.find("option").remove();
	    				        $("<option>").val(0).text("Please Select").appendTo($select);		
	    				        $.each(responseJson, function(key, value) {              
	    				            $("<option>").val(key).text(value).appendTo($select); 
	    				        });
	    				}
	    			});
	    		
	    		
	    		
	    		
	    		//To fetch where conditions #rpWhrCondition
	    			$.ajax({
	    				type : "post",
	    				url : '/fetchReportsName',
	    				data : {},
	    				success : function(responseJsonWhere) {
	    					console.log(responseJsonWhere);
	    					$('#rpWhrCondition').empty();
   						  	$.each(responseJsonWhere, function(key, value) {               
   				          	var newOption = $('<option/>');
   							newOption.text(key);
   							newOption.attr('value', value);
   							$('#rpWhrCondition').append(newOption);
   				        });	       
	    				}
	    			});
	    	
	    		
	    		//To fetch Having conditions #rphvngCondition
	    			$.ajax({
	    				type : "post",
	    				url : '/fetchHavingConditions',
	    				data : {},
	    				success : function(responseJsonHvng) {
	    					$('#rphvngCondition').empty();
	    					console.log(responseJsonHvng);
	    					$.each(responseJsonHvng, function(key, value) {               
	    				            var newOption = $('<option/>');
	    							newOption.text(key);
	    							newOption.attr('value', value);
	    							$('#rphvngCondition').append(newOption);
	    				        });       
	    				}
	    			});
	    		
	    		//To fetch Where conditions
		    		$.ajax({
		    				type : "post",
		    				url : '/fetchWhereConditions',
		    				data : {},
		    				success : function(responseJsonWhere) {
		    					
		    					console.log(responseJsonWhere);
		    					
		    						   $.each(responseJsonWhere, function(key, value) {               
		    				           var newOption = $('<option/>');
		    							newOption.text(key);
		    							newOption.attr('value', value);
		    							$('#rpWhrCondition').append(newOption);
		    				        });	       
		    				}
		    			});
	    		
//Code for update fetch starts here	    		
	            	$("#rpName").val("${reportName}");
	            	$("#rpColor").val("${reportColor}");
	            	$("#tooltip").val("${listReport.tooltip}");
	            	$("#serviceID").val("${listReport.serviceId}");
	            	$('#selectedRecord').val("${listReport.serviceId}");
	            	$("#bgtext").val("${listReport.getBackgroundText()}");
	            	//$("#rpHeader").val("${listReport.getReport_header()}");
	            	//$("#rpFooter").val("${listReport.getReport_footer()}");
	            	CKEDITOR.instances["rpHeader"].setData("${listReport.getReport_header()}");
	            	CKEDITOR.instances["rpFooter"].setData("${listReport.getReport_footer()}");
	            	
	            	$("#reportID").val("${listReport.getReportId()}");
	            	$("#isAdminReport").val("${listReport.getIsAdminReport()}");
	            	console.log("${reportColor}");
	            	console.log("${listReport.getDepartmentId()}");
	            	
	            	//OrderBy condition update funcationality
	            	var ordrObj= ${listReport.getOrderCondition()}
	            	$("#odrclsJSON").val(JSON.stringify(ordrObj));
	            	ordrObj = ordrObj.Order;
	            	console.log(ordrObj);
	    	        for(var i=0;i<ordrObj.length;i++)
	    	        {
	    	            var tr="<tr>";
	    	            var td0 = "<td><input type='checkbox' name='recordgridodr' id='recordgridodr'></td>"
	    	            var td1="<td style='display:none;'>"+ordrObj[i]["ColumnId"]+"</td>";
	    	            var td2="<td>"+ordrObj[i]["Column"]+"</td>";
	    	            var td3="<td>"+ordrObj[i]["Order"]+"</td></tr>";
	    	            
	    	          $("#rpODConditionGridView tbody").append(tr+td0+td1+td2+td3); 
	    	        }
	    	        
	    	        
	    	        
	            	//Where condition update funcationality
	            	var objWhere= ${listReport.getWhereCondition()}
	            	$("#whrclsJSON").val(JSON.stringify(objWhere));
	            	objWhere = objWhere.Where;
	    	        for(var i=0;i<objWhere.length;i++)
	    	        {
	    	            var tr="<tr>";
	    	            var td0 = "<td><input type='checkbox' name='recordgridwhr' id='recordgridwhr'></td>"
	    	            var td1="<td style='display:none;'>"+objWhere[i]["ColumnId"]+"</td>";
	    	            var td2="<td>"+objWhere[i]["Column"]+"</td>";
	    	            var td3="<td>"+objWhere[i]["Condition"]+"</td>";
	    	        	var td4="<td>"+objWhere[i]["Value"]+"</td>";
	    	        	var td5="<td>"+objWhere[i]["And Or"]+"</td></tr>";
	    	        	$("#rpWhrConditionGridView tbody").append(tr+td0+td1+td2+td3+td4+td5); 
	    	        }
	    	       
	            	
	    	      //Having condition update funcationality
	            	var objHaving= ${listReport.getHavingCls()}
	            	$("#hvngclsJSON").val(JSON.stringify(objHaving));
	            	objHaving = objHaving.Having;
	    	        for(var i=0;i<objHaving.length;i++)
	    	        {
	    	            var tr="<tr>";
	    	            var td0 = "<td><input type='checkbox' name='recordgridHvng' id='recordgridHvng'></td>"
	    	            var td1="<td style='display:none;'>"+objHaving[i]["ColumnId"]+"</td>";
	    	            var td2="<td>"+objHaving[i]["Column"]+"</td>";
	    	            var td3="<td>"+objHaving[i]["Function"]+"</td>";
	    	        	var td4="<td>"+objHaving[i]["Value"]+"</td>";
	    	        	var td5="<td>"+objHaving[i]["Condition"]+"</td></tr>";
	    	            
	    	          $("#rpHvngConditionGridView tbody").append(tr+td0+td1+td2+td3+td4+td5); 
	    	        }
	    	    
	    	        
	    	      //Aggregation condition update funcationality
	    			var objAggr= ${listReport.getAggregationCls()}
	    			$("#agrclsJSON").val(JSON.stringify(objAggr));
	            	objAggr = objAggr.Aggregation;
	    	        for(var i=0;i<objAggr.length;i++)
	    	        {
	    	            var tr="<tr>";
	    	            var td0 = "<td><input type='checkbox' name='recordgridagr' id='recordgridagr'></td>"
	    	            var td1="<td style='display:none;'>"+objAggr[i]["ColumnId"]+"</td>";
	    	            var td2="<td>"+objAggr[i]["Column"]+"</td>";
	    	            var td3="<td>"+objAggr[i]["Function"]+"</td></tr>";
	    	            
	    	          $("#rpAGConditionGridView tbody").append(tr+td0+td1+td2+td3); 
	    	        }
	    	        
	    	        
	    	        //To fetch Columns
    	   			$.ajax({
    	   				type : "post",
    	   				url : '/DesignReportCol',
    	   				data : {
    	   					deptid : $('#deptid').val(),
    	   					serviceid: $('#serviceID').val(),
    	   					action : "fetchColumns"
    	   				},
    	   				success : function(responseJson1) {
    	   					//$("#selcol").show();
    	   					document.getElementById("selectedRecord").disabled=true;
    	   					//console.log(responseJson1);
    	   					$('#ContentPlaceHolder1_CheckBoxList1').empty();
       						$.each(responseJson1, function(key, value) {
       				        	$('#ContentPlaceHolder1_CheckBoxList1').append('<input type="checkbox"  name="colmn" value="'+ key +'"/> ' +  '<label for="'+ key +'" >'+value.trim()+'</label>' +'<br/>');
       				        });
    					   	$('#departmentID').val($('#deptid').val());
    					   	$('#selectedRecord').val($('#serviceID').val());
    					   	//$('#serviceID').val($('#selectedRecord').val());
    						abc();
    						checkSelectedColumns();
    	   				}
    	   			});
	    	        
    	   			//To fetch Appl Info Columns #ContentPlaceHolder2_CheckBoxList2applInfoCollist
        			$.ajax({
        				type : "post",
        				url : '/fetchApplInfoCol',
        				data : {},
        				success : function(responseJsonAppInfoCol) {
        					$("#selcol").show();
        					//console.log(responseJsonAppInfoCol);
        					$('#ContentPlaceHolder2_CheckBoxList2').empty();
        					$.each(responseJsonAppInfoCol, function(key, value) {               
        				    	$('#ContentPlaceHolder2_CheckBoxList2').append('<input type="checkbox"  name="colmn" value="'+ key +'"/> ' +  '<label for="'+ key +'" >'+value.trim() +'</label>' +'<br/>');
        				    });
        					initColJs();
        					checkSelectedColumnsforInit();
        					setGroupBy();
        				}
        			});
    	   			
        			 function checkSelectedColumns(){
       		    	 	var data = ${selectedColforRepjson};
       				//	console.log(data);
       					Object.keys(data).forEach(function(key) {
	     				//	console.log('Key : ' + key + ', Value : ' + data[key]);
	     					if(key==data[key]){
	  							
	  						 }else{
	  							$("#ContentPlaceHolder1_CheckBoxList1").find("input[value="+ key +"]").prop("checked", "checked").change();
	  						 }
       					});
       		    	 }
        					
        			 function checkSelectedColumnsforInit(){
        		    	 	var data = ${selectedColforRepjson};
        				//	console.log(data);
        					Object.keys(data).forEach(function(key) {
 	     					//console.log('Key : ' + key + ', Value : ' + data[key]);
 	     					if(key==data[key]){
 	  							$("#ContentPlaceHolder2_CheckBoxList2").find("input[value="+ key +"]").prop("checked", "checked").change();
 	  						 }
        					});
        		    	 }
        			
        			 //To set group by
        			 function setGroupBy(){
        	 	        var string = "${listReport.getGrouping()}"
        	 	        var array = string.split(',');
        	 	        for(i=0;i<=array.length;i++){
        	 	        var a = array[i];
        	 	        $("#rpGrpBy").find("option[value="+ a +"]").prop("selected", "selected");
        	 	        }
        			 }
        			 
        			 
        			 var servID = $('#serviceID').val();
        			 var deptId = ${listReport.getDepartmentId()};
        			 var isAdminReport = $('#isAdminReport').val();
        			 if(isAdminReport=="Y"){
    					 $("#collist").hide();
    					 $("#sortable1").hide();
    					 $("#sortable5").css('height', '300px');
    				 }
        			 if(servID!=null && servID!=0){
        				 $("#updateReportLabel").text("Modify Report For : " + "${ServiceName}");
        				 $("#updateReportLabel").show();
        				
        			 }else if(deptId!=0 && servID==0){
        				if(deptId==1){
        					$("#updateReportLabel").text("Modify Report For All Departments ");
                			$("#updateReportLabel").show();
        				}else if(deptId!=1 && deptId!=null){
        					$("#updateReportLabel").text("Modify Report For : " + "${SelectedDepartmentName}");
        					$("#updateReportLabel").show();
        				}	
        			 }
        			
	    	   			
//Code for update fetch ends here

	  		   $('#mycustreport').submit(function (e){
		 			 e.preventDefault();
		            $.ajax({
		                type: "post",
		                url: "/modifyReportDesignData",
		                data: $(this).serialize(),
						success:function(result){
							
							if(result="Report has been saved"){
								alert(result);
								window.location.href='/fetchReportsName';
							}else{
							console.log("My ajax 1");
							console.log(result);
							 $('#customDesign').text(result);
							 console.log("My ajax");
							 e.preventDefault();
							}
						}
		            });
		        });
	    		
	    		var url = document.URL;
	    		function abc(){
	    			$('#ContentPlaceHolder1_CheckBoxList1').find('input[type="checkbox"]').on('change', function () {
	    			
	          		  if ($(this).prop("checked") == true) {
	                var number = $(this).next('label').text();
	                var index = addition.map(function (e) { return e.key; }).indexOf(number); // check if exists in array
	                if (index !== -1) {
	                    var numberTobeusedRandomly = 1;
	                    var next_suggested_Name = $(this).next('label').text() + "_" + numberTobeusedRandomly;
	                    while (addition.map(function (e) { return e.key; }).indexOf(next_suggested_Name) !== -1) {
	                        numberTobeusedRandomly = numberTobeusedRandomly + 1;
	                        next_suggested_Name = $(this).next('label').text() + "_" + numberTobeusedRandomly;
	                    }
	                    //
	                    var AnotherFieldName = prompt('"' + $(this).next('label').text() + '"' + " field is already taken !! Kindly enter another Name for this field.", next_suggested_Name);
	                    if (AnotherFieldName != null) {
	                        if (addition.map(function (e) { return e.key; }).indexOf(AnotherFieldName) !== -1) {
	                            $(this).prop("checked", false);
	                            $(this).next('label').css('color', 'black');
	                            alert('Choose another field name !!');
	                        }
	                        else {
	                            $(this).next('label').text(AnotherFieldName);
	                            $(this).next('label').css('color', 'red');
	                            AppendToSortable('ul#sortable', '<li key="' + $(this).next('label').text() + '" value="' + $(this).val() + '" class="ui-state-default tagme ui-sortable-handle ui-draggable ui-draggable-handle"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>' + $(this).next('label').text() + '</li>', $(this).next('label').text(), $(this).val());

	                        }
	                    }
	                    else {
	                        $(this).prop("checked", false);
	                        $(this).next('label').css('color', 'black');
	                    }
	                }
	                else {

	                    $(this).next('label').css('color', 'blue');
	                    //add item to tag system
	                    AppendToSortable('ul#sortable', '<li key="' + $(this).next('label').text() + '" value="' + $(this).val() + '" class="ui-state-default tagme ui-sortable-handle ui-draggable ui-draggable-handle"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>' + $(this).next('label').text() + '</li>', $(this).next('label').text(), $(this).val());
	                }
	            }
	            else {
	                $(this).next('label').css('color', 'black');
	                DeleteFromSortable($(this).val(), $(this).next('label').text());
	            }

	            $('#JSONTEXT').html(JSON.stringify(addition));
	            $("#hddnJSON").val(JSON.stringify(addition));
	            initSelBox_Product();
	        	});
	    		}
	    		
	    		
	    		function initColJs(){
	    			$('#ContentPlaceHolder2_CheckBoxList2').find('input[type="checkbox"]').on('change', function () {
	    			
	          		  if ($(this).prop("checked") == true) {
	                var number = $(this).next('label').text();
	                var index = additionB.map(function (e) { return e.key; }).indexOf(number); // check if exists in array
	                if (index !== -1) {
	                    var numberTobeusedRandomly = 1;
	                    var next_suggested_Name = $(this).next('label').text() + "_" + numberTobeusedRandomly;
	                    while (additionB.map(function (e) { return e.key; }).indexOf(next_suggested_Name) !== -1) {
	                        numberTobeusedRandomly = numberTobeusedRandomly + 1;
	                        next_suggested_Name = $(this).next('label').text() + "_" + numberTobeusedRandomly;
	                    }
	                    //
	                    var AnotherFieldName = prompt('"' + $(this).next('label').text() + '"' + " field is already taken !! Kindly enter another Name for this field.", next_suggested_Name);
	                    if (AnotherFieldName != null) {
	                        if (additionB.map(function (e) { return e.key; }).indexOf(AnotherFieldName) !== -1) {
	                            $(this).prop("checked", false);
	                            $(this).next('label').css('color', 'black');
	                            alert('Choose another field name !!');
	                        }
	                        else {
	                            $(this).next('label').text(AnotherFieldName);
	                            $(this).next('label').css('color', 'red');
	                            AppendToSortableB('ul#sortableB', '<li key="' + $(this).next('label').text() + '" value="' + $(this).val() + '" class="ui-state-default tagme ui-sortable-handle ui-draggable ui-draggable-handle"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>' + $(this).next('label').text() + '</li>', $(this).next('label').text(), $(this).val());
	                        }
	                    }
	                    else {
	                        $(this).prop("checked", false);
	                        $(this).next('label').css('color', 'black');
	                    }
	                }
	                else {

	                    $(this).next('label').css('color', 'blue');
	                    //add item to tag system
	                    AppendToSortableB('ul#sortableB', '<li key="' + $(this).next('label').text() + '" value="' + $(this).val() + '" class="ui-state-default tagme ui-sortable-handle ui-draggable ui-draggable-handle"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>' + $(this).next('label').text() + '</li>', $(this).next('label').text(), $(this).val());
	                }
	            }
	            else {
	                $(this).next('label').css('color', 'black');
	                DeleteFromSortableB($(this).val(), $(this).next('label').text());
	            }
	            $('#JSONTEXTB').html(JSON.stringify(additionB));
	            $("#hddnJSONB").val(JSON.stringify(additionB));
	            initSelBox_Product();
	        	});
	    		}
			});
		
		
		//append selected columns in dropdown list
	     function initSelBox_Product() {
			
			try{
		    	var titelliste= $("#hddnJSON").val();
		    	if(titelliste!=""){
		    		var a = JSON.parse(titelliste);
		    	}		    	
		    	var titellisteB = $("#hddnJSONB").val();
		    	if(titellisteB!=""){
		    		var b = JSON.parse(titellisteB);
		    	}
		    	if(titelliste!="" && titellisteB!=""){
		    		var o = b.concat(a);
		    	}else if(titelliste!="" && titellisteB==""){
		    		var o = a;
		    	}else if(titelliste=="" && titellisteB!=""){
		    		var o = b;
		    	}
		    	console.log(o);
		    	$('#rpGrpBy, #rpGrpBy2, #rpGrpBy3, #rpGrpBy19, #rpGrpBy9').empty();
		    	var newOption1 = $('<option/>');
				newOption1.text("Please Select");
				newOption1.attr('value', "0");
				$('#rpGrpBy2, #rpGrpBy3, #rpGrpBy19, #rpGrpBy9').append(newOption1);
	 
				var length = (o.length);
				for(var j = 0; j < length; j++)
					{
						var newOption = $('<option/>');
						newOption.text(o[j].key);
						newOption.attr('value', o[j].Value);
						$('#rpGrpBy, #rpGrpBy2, #rpGrpBy9, #rpGrpBy19, #rpGrpBy3').append(newOption);
					}
			} catch(err){
				console.log(err);
			}	    	
		}
			
	//append to sortable list
	function AppendToSortable(element, item, key, value) {
	    $(element).append(item);
	    var addition_Property = {};
	    addition_Property['key'] = key;
	    addition_Property['Value'] = value;
	    addition.push(addition_Property);
	    //

	    $("#sortable").sortable({
	        change: function (event, ui) {
	            // drop and rebuild addition array again as per new alignment

	        },
	        stop: function (event, ui) {
	            checkifeventofdragisAlive = '1';
	            addition = [];
	            $(this).parent().find('li').each(function (index, item) {
	                var addition_Property = {};
	                addition_Property['key'] = $(this).attr('key');
	                addition_Property['Value'] = $(this).attr('value');
	                addition.push(addition_Property);
	            });
	            $('#JSONTEXT').html(JSON.stringify(addition));
	            $("#hddnJSON").val(JSON.stringify(addition));
	            initSelBox_Product();
	            event.stopPropagation();
	            event.stopImmediatePropagation();
	            checkifeventofdragisAlive = '0';
	        }
	    });
	}

	
	//To append in sortable for initjson
	function AppendToSortableB(element, item, key, value) {
	    $(element).append(item);
	    var additionB_Property = {};
	    additionB_Property['key'] = key;
	    additionB_Property['Value'] = value;
	    additionB.push(additionB_Property);
	    
	    $("#sortableB").sortable({
	        change: function (event, ui) {
	            // drop and rebuild addition array again as per new alignment

	        },
	        stop: function (event, ui) {
	            checkifeventofdragisAlive = '1';
	            additionB = [];
	            $(this).parent().find('li').each(function (index, item) {
	                var additionB_Property = {};
	                additionB_Property['key'] = $(this).attr('key');
	                additionB_Property['Value'] = $(this).attr('value');
	                additionB.push(additionB_Property);
	            });
	            $('#JSONTEXTB').html(JSON.stringify(additionB));
	            $("#hddnJSONB").val(JSON.stringify(additionB));
	            initSelBox_Product();
	            event.stopPropagation();
	            event.stopImmediatePropagation();
	            checkifeventofdragisAlive = '0';
	        }
	    });
	}
		
		//code to remove selected item from list
        function DeleteFromSortable(value, key) {
            var index = addition.map(function (e) { return e.key; }).indexOf(key);
            if (index !== -1) addition.splice(index, 1);
            var elementToRemove = 'ul#sortable > li[key="' + key + '"][value="' + value + '"]';
            $(elementToRemove).remove();
        }
		
		//code to remove selected item from Init list
        function DeleteFromSortableB(value, key) {
            var index = additionB.map(function (e) { return e.key; }).indexOf(key);  
            if (index !== -1) additionB.splice(index, 1);
            var elementToRemove = 'ul#sortableB > li[key="' + key + '"][value="' + value + '"]';
            $(elementToRemove).remove();
        }
             
        $(function () {
            $("#sortable").sortable({
                change: function (event, ui) {
                },
                stop: function (event, ui) {
                    checkifeventofdragisAlive = '1';
                    addition = [];
                    $(this).parent().find('li').each(function (index, item) {
                        var addition_Property = {};
                        addition_Property['key'] = $(this).attr('key');
                        addition_Property['Value'] = $(this).attr('value');         
                        addition.push(addition_Property);
                    });
                   
                    $('#JSONTEXT').html(JSON.stringify(addition));
                   
                    event.stopPropagation();
                    event.stopImmediatePropagation();
                    checkifeventofdragisAlive = '0';
                }
            });
            $("#sortable").disableSelection();
        });
        
		
        </script>
		
		
