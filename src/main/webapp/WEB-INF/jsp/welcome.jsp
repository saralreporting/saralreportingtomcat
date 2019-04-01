<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
					Dashboard
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="alert alert-block alert-success" style="margin-bottom: 0;">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<i class="ace-icon fa fa-check green"></i> Welcome to <strong
							class="green"> Saral Haryana <small>(v1.0)</small>
						</strong>, Report Designer.
					</div>

					<div class="row">
						<div class="col-sm-12 col-xs-12 infobox-container" style="padding: 0;">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 cus-small-tabs">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-small-tab-inner border1">
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 cus-img-outer" style="padding: 0;">
			                            <img class="img-responsive cus-icon-img" src="../assets/images/district.png">
			                        </div>
			                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 cus-values" style="padding: 0;">
			                            <p id="districtCount" class="cus-value-num">22</p>
			                            <button id="districtRldBtn" class="cus-reload-bttn" data-toggle="Tooltip" title="Click to refresh" style="display:none" type="button"></button>
			                        </div>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                            <h4>Districts</h4>
			                        </div>
		                        </div>
		                    </div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 cus-small-tabs">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-small-tab-inner border2">
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 cus-img-outer" style="padding: 0;">
			                            <img class="img-responsive cus-icon-img" src="../assets/images/dept.png">
			                        </div>
			                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 cus-values" style="padding: 0;">
			                            <p id="deptCount" class="cus-value-num">${departmentsCount}</p>
			                            <button id="districtRldBtn" class="cus-reload-bttn" data-toggle="Tooltip" title="Click to refresh" style="display:none" type="button"></button>
			                        </div>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                            <h4>Departments</h4>
			                        </div>
		                        </div>
		                    </div>
		                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 cus-small-tabs">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-small-tab-inner border3">
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 cus-img-outer" style="padding: 0;">
			                            <img class="img-responsive cus-icon-img" src="../assets/images/service.png">
			                        </div>
			                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 cus-values" style="padding: 0;">
			                            <p id="districtCount" class="cus-value-num">${servicesCount}</p>
			                            <button id="districtRldBtn" class="cus-reload-bttn" data-toggle="Tooltip" title="Click to refresh" style="display:none" type="button"></button>
			                        </div>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                            <h4>Total Services</h4>
			                        </div>
		                        </div>
		                    </div>
		                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 cus-small-tabs">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-small-tab-inner border4">
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 cus-img-outer" style="padding: 0;">
			                            <img class="img-responsive cus-icon-img" src="../assets/images/total-app.png">
			                        </div>
			                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 cus-values" style="padding: 0;">
			                            <p id="districtCount" class="cus-value-num">${applicationRecieved}</p>
			                            <button id="districtRldBtn" class="cus-reload-bttn" data-toggle="Tooltip" title="Click to refresh" style="display:none" type="button"></button>
			                        </div>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                            <h4>Applications Received</h4>
			                        </div>
		                        </div>
		                    </div>
		                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 cus-small-tabs">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-small-tab-inner border5">
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 cus-img-outer" style="padding: 0;">
			                            <img class="img-responsive cus-icon-img" src="../assets/images/comp-applications.png">
			                        </div>
			                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 cus-values" style="padding: 0;">
			                            <p id="districtCount" class="cus-value-num">6,251</p>
			                            <button id="districtRldBtn" class="cus-reload-bttn" data-toggle="Tooltip" title="Click to refresh" style="display:none" type="button"></button>
			                        </div>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                            <h4>Applications Processed</h4>
			                        </div>
		                        </div>
		                    </div>
		                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 cus-small-tabs">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-small-tab-inner border6">
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 cus-img-outer" style="padding: 0;">
										<img class="img-responsive cus-icon-img" src="../assets/images/comp-services.png">
									</div>
									<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 cus-values" style="padding: 0;">
										<p id="districtCount" class="cus-value-num">0</p>
										<button id="districtRldBtn" class="cus-reload-bttn" data-toggle="Tooltip" title="Click to refresh" style="display:none" type="button"></button>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
										<h4>Processed within RTS</h4>
									</div>
								</div>
							</div>
						
								<div class="infobox-data">
									<span class="infobox-data-number">0</span>
									<div class="infobox-content">Processed within RTS</div>
								</div>

						</div>
						<div class="space-6"></div>
					</div>

						<div class="vspace-12-sm"></div>
						<%-- <c:if test="${not empty list}"> --%>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 cus-chart-div" style="padding: 0;">
							<div class="widget-box ext-widget-box">
								<div
									class="widget-header widget-header-flat widget-header-small">
									<h5 class="widget-title">
										<i class="ace-icon fa fa-signal"></i> Traffic Sources & Pie-Chart Data in Tabular Form
									</h5>

									
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 widget-body" id="selectedReportChartDiv" style="padding: 0;">
									<div class="widget-main">
										<div id="piechart-placeholder" style="display: none;"></div>
										<div id="piechart"
											style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

										<div class="hr hr8 hr-double"></div>

										
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.widget-box -->
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 widget-body" id="selectedReportChartDivCount" style="padding: 0;">
									<div class="widget-main cus-overflow">
										<table id="example" class="table table-striped table-bordered table-hover dataTable no-footer table-responsive cus-grids-table" style="overflow: scroll;">
											<thead>
												<tr>
													<th>Name</th>
													<th>Count</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>

						<!-- /.row -->
						<%-- </c:if> --%>
						<div class="hr hr32 hr-dotted"></div>

						
						
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 cus-grids cus-flex-div">
								<div class="widget-box transparent">
									<div class="widget-header widget-header-flat">
										<h4 class="widget-title lighter">
											<i class="ace-icon fa fa-star orange"></i> Popular Reports
										</h4>

										<div class="widget-toolbar">
											<a href="#" data-action="collapse"> <i
												class="ace-icon fa fa-chevron-up"></i>
											</a>
										</div>
									</div>
									<c:set var="count" value="0" scope="page" />
									<c:if test="${not empty listReportData}">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<table class="table table-bordered table-striped table-responsive cus-grids-table">
													<thead class="thin-border-bottom">
														<tr>
															<th><i class="ace-icon fa fa-caret-right blue"></i>
																S.No</th>

															<th><i class="ace-icon fa fa-caret-right blue"></i>ReportName
															</th>

															<th class="hidden-480"><i
																class="ace-icon fa fa-caret-right blue"></i>CountView</th>
														</tr>
													</thead>

													<tbody>

														<c:forEach items="${listReportData}" var="lists"
															varStatus="counter">
															<tr>
																<td>${counter.count}</td>

																<td>${lists.reportName}</td>

																<td>${lists.count}</td>
															</tr>
														</c:forEach>


													</tbody>
												</table>
											</div>
											<!-- /.widget-main -->
										</div>
										<!-- /.widget-body -->
									</c:if>
								</div>

								<!-- /.widget-box -->
							</div>
							<!-- /.col -->
						


						<sec:authorize access="hasRole('ADMIN')">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 cus-grids cus-flex-div">
								<div class="widget-box transparent">
									<div class="widget-header widget-header-flat">
										<h4 class="widget-title lighter">
											<i class="ace-icon fa fa-star orange"></i> Popular
											Departments
										</h4>

										<div class="widget-toolbar">
											<a href="#" data-action="collapse"> <i
												class="ace-icon fa fa-chevron-up"></i>
											</a>
										</div>
									</div>
									<c:set var="count" value="0" scope="page" />
									<c:if test="${not empty applInfoCount}">
										<div class="widget-body">
											<div class="widget-main no-padding table-respnsive cus-grids-table">
												<table class="table table-bordered table-striped">
													<thead class="thin-border-bottom">
														<tr>
															<th><i class="ace-icon fa fa-caret-right blue"></i>
																S.No</th>

															<th><i class="ace-icon fa fa-caret-right blue"></i>Department
																Name</th>

															<th class="hidden-480"><i
																class="ace-icon fa fa-caret-right blue"></i>Applications
																Count</th>
														</tr>
													</thead>

													<tbody>

														<c:forEach items="${applInfoCount}" var="lists"
															varStatus="counter">
															<tr>
																<td>${counter.count}</td>

																<td>${lists.departmentName}</td>

																<td>${lists.cnt}</td>
															</tr>
														</c:forEach>


													</tbody>
												</table>
											</div>
											<!-- /.widget-main -->
										</div>
										<!-- /.widget-body -->
									</c:if>
								</div>

								<!-- /.widget-box -->
							</div>
							<!-- /.col -->
						</sec:authorize>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.page-content -->
</div>
<!-- /.main-content -->



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
<script src="assets/js/jquery.flot.min.js"></script>
<script src="assets/js/jquery.flot.pie.min.js"></script>
<script src="assets/js/jquery.flot.resize.min.js"></script>
<script src="assets/js/chartjs/highcharts.js"></script>

<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
			jQuery(function($) {
				$('.easy-pie-chart.percentage').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
					var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
					var size = parseInt($(this).data('size')) || 50;
					$(this).easyPieChart({
						barColor: barColor,
						trackColor: trackColor,
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: parseInt(size/10),
						animate: ace.vars['old_ie'] ? false : 1000,
						size: size
					});
				})
			
				$('.sparkline').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
					$(this).sparkline('html',
									 {
										tagValuesAttribute:'data-values',
										type: 'bar',
										barColor: barColor ,
										chartRangeMin:$(this).data('min') || 0
									 });
				});
			
			
			  //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
			  //but sometimes it brings up errors with normal resize event handlers
			  $.resize.throttleWindow = false;
			
			  var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
			  
			  var data = [
					{ label: "social networks",  data: 38.7, color: "#68BC31"},
					{ label: "search engines",  data: 24.5, color: "#2091CF"},
					{ label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
					{ label: "direct traffic",  data: 18.6, color: "#DA5430"},
					{ label: "other",  data: 10, color: "#FEE074"}
				  ]
			  
			  function drawPieChart(placeholder, data, position) {
			 	  $.plot(placeholder, data, {
					series: {
						pie: {
							show: true,
							tilt:0.8,
							highlight: {
								opacity: 0.25
							},
							stroke: {
								color: '#fff',
								width: 2
							},
							startAngle: 2
						}
					},
					legend: {
						show: true,
						position: position || "ne", 
						labelBoxBorderColor: null,
						margin:[-30,15]
					}
					,
					grid: {
						hoverable: true,
						clickable: true
					}
				 })
			 }
			 drawPieChart(placeholder, data);
			
			 /**
			 we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			 so that's not needed actually.
			 */
			 placeholder.data('chart', data);
			 placeholder.data('draw', drawPieChart);
			
			
			  //pie chart tooltip example
			  var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
			  var previousPoint = null;
			
			  placeholder.on('plothover', function (event, pos, item) {
				if(item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : " + item.series['percent']+'%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}
				
			 });
			
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
				
				
				//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
				//so disable dragging when clicking on label
				var agent = navigator.userAgent.toLowerCase();
				if(ace.vars['touch'] && ace.vars['android']) {
				  $('#tasks').on('touchstart', function(e){
					var li = $(e.target).closest('#tasks li');
					if(li.length == 0)return;
					var label = li.find('label.inline').get(0);
					if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
				  });
				}
			
				$('#tasks').sortable({
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'draggable-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					stop: function( event, ui ) {
						//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
					}
				);
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
					if(this.checked) $(this).closest('li').addClass('selected');
					else $(this).closest('li').removeClass('selected');
				});
			
			
				//show the dropdowns on top or bottom depending on window height and menu position
				$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
					var offset = $(this).offset();
			
					var $w = $(window)
					if (offset.top > $w.scrollTop() + $w.innerHeight() - 100) 
						$(this).addClass('dropup');
					else $(this).removeClass('dropup');
				});
			
			})
			
		</script>
<script type="text/javascript">
$(document).ready(function(){
	abc(${mapForChartData});
});


	function abc(data){
	var jsonVar = JSON.stringify(data);
	console.log(jsonVar);
	jsonVar = JSON.parse(jsonVar);
	console.log(jsonVar);
	if(jsonVar=="No Data available."){
		$("#selectedReportChartDiv").hide();
	}else{
		jsonVar = jsonVar.myArrayList;
		console.log(jsonVar);
	/* 	jsonVar = JSON.stringify(jsonVar);
		console.log(jsonVar); */
		
		 let t=jsonVar.reduce((o,a)=>{
			 o.push(a.map);
			 return o;
			},[])
			
			console.log(t); 
		//var newdata = JSON.stringify(json);
		
		Highcharts.chart('piechart', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'pie'
		    },
		    title: {
		        text: 'Pie-Chart data'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                style: {
		                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                }
		            }
		        }
		    },
		    series: [{
		        name:  $("#colforPieChart option:selected").text(),
		        colorByPoint: true,
		        data: t
		    }]
		});
		
		if(t){
            var len = t.length;
            var txt = "";
            if(len > 0){
                for(var i=0;i<len;i++){
                        txt += "<tr><td>"+t[i].name+"</td><td>"+t[i].y+"</td></tr>";
                }
               $("#example").append(txt);   
            }
        }
	}
}
</script>