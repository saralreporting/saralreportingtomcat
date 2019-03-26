package com.saral.reporting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.saral.reporting.model.ReportBean;
import com.saral.reporting.model.ReportSelectColumn;
import com.saral.reporting.service.ReportBeanService;
import com.saral.reporting.service.ServiceMasterService;

@Transactional
@Controller
@SessionAttributes({ "reportIdToModify" })
public class ModifyReportController {

	@Autowired
	ReportBeanService reportBeanService;
	
	@Autowired
	ServiceMasterService serviceMasterService;
	
	@RequestMapping(value = { "/modifyReportDesign" }, method = RequestMethod.GET)
	public String modifyReportDesign(ModelMap model, Pageable pageable, @RequestParam String reportId, HttpServletRequest request)
			throws ServletException, IOException, ParseException {

		Long repId = Long.parseLong(reportId);
		model.put("reportIdToModify", repId);
		return "showReportNew";

	}
	
	@RequestMapping(value = { "/modifySelectedReportDesign" }, method = RequestMethod.GET)
	public String reportSelectedReport(ModelMap model, Pageable pageable, HttpServletRequest request)
			throws ServletException, IOException, ParseException {

		Long repId = (Long) request.getSession().getAttribute("reportIdToModify");
		
		System.out.println("asdbashmdbahdbahkbdahksbdalsdbajlsdbaksbdasjdbajkbdajksdbkasbdasd" + repId);
		ReportBean listReport = reportBeanService.findByReportId(repId);
		List<ReportSelectColumn> colListforUpdate = listReport.getReportSelectColumnList();
		Map<String ,String> mapForCol = new LinkedHashMap<>();
		for(int i =0; i<colListforUpdate.size();i++){
			mapForCol.put(colListforUpdate.get(i).getReportSelectedColumnId(), colListforUpdate.get(i).getReportSelectedColumnName());
		}
		System.out.println(mapForCol);
		String selectedColforRepjson = new Gson().toJson(mapForCol);
		System.out.println(selectedColforRepjson);
		model.put("listReport", listReport);
		model.put("selectedColforRepjson", selectedColforRepjson);
		model.put("colListforUpdate", colListforUpdate);
		model.put("reportColor", listReport.getTableColor());
		model.put("reportName", listReport.getReportName());
		System.out.println("service_id is " + listReport.getServiceId());
		return "modifyReportDesign";
		
	}
	
	@RequestMapping(value = { "/modifyReportDesignData" }, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> modifyReportDesignData(HttpServletRequest request) throws ParseException, JSONException {
		
		Long repId = (Long) request.getSession().getAttribute("reportIdToModify");
		ReportBean reportBean =reportBeanService.findByReportId(repId);
		String res = "";

		//Long departmentID = Long.parseLong(request.getParameter("departmentID"));//From Frontend For Report Purpose
		//Long serviceID = Long.parseLong(request.getParameter("serviceID"));
		//Long desigID = Long.parseLong(request.getParameter("desigID"));
		//System.out.println("service ID is :" + serviceID);
		//System.out.println("dep ID is :" + departmentID);

		/*Long department_id = (Long) request.getSession().getAttribute("department_id");//From Session
		if(department_id==0L){
			reportBean.setIsAdminReport('Y');
		}else{
			reportBean.setIsAdminReport('N');
		}*/
		
		reportBean.setReportName(request.getParameter("rpName"));
		reportBean.setTableColor(request.getParameter("rpColor"));
		//reportBean.setDepartmentId(departmentID);
		//reportBean.setServiceId(serviceID);
		//reportBean.setUserId(request.getParameter("userID"));
		//reportBean.setSignNo(request.getParameter("sign_no"));
		// reportBean.setSortBy(request.getParameter("sortBy"));
		// reportBean.setSortingOrder(request.getParameter("sortByOrder"));
		reportBean.setVersionNo(reportBean.getVersionNo() + 1L); // need to get front end
		//reportBean.setDesignationId(desigID); // need to get front end
		reportBean.setTooltip(request.getParameter("tooltip"));
		// org.json.simple.JSONArray whrclsJSON
		// =reportBean.jsonManipulateWhere(request.getParameter("whrclsJSON"));
		reportBean.setWhereCondition(request.getParameter("whrclsJSON"));
		reportBean.setOrderCondition(request.getParameter("odrclsJSON"));
		System.out.println("hey"+ request.getParameter("agrclsJSON"));
		reportBean.setAggregationCls(request.getParameter("agrclsJSON"));
		/* report.setGrouping(request.getParameter("rpGrpBy")); */
		String grpby = request.getParameter("grpIdName");
		if (grpby.equals("0") || (grpby == null)) {
			reportBean.setGrouping("");
		} else {
			reportBean.setGrouping(grpby);
		}

		/*
		 * String sortBy = request.getParameter("sortIdName"); if
		 * (sortBy.equals("0")||(sortBy==null)) { reportBean.setSortBy("");
		 * reportBean.setSortingOrder(""); } else {
		 * reportBean.setSortBy(sortBy);
		 * reportBean.setSortingOrder(request.getParameter("sortByOrder")); }
		 */

		/* report.setGrouping(request.getParameter("grpIdName")); */
		System.out.println("Group by :" + reportBean.getGrouping());
		reportBean.setBackgroundText(request.getParameter("bgtext"));
		reportBean.setFilterCls(""); // need to discuss
		//org.json.simple.JSONArray hvngclsJSON = reportBean.jsonManipulateHaving(request.getParameter("hvngclsJSON"));
		reportBean.setHavingCls(request.getParameter("hvngclsJSON"));
		reportBean.setTableFormat(""); // need to discuss
		System.out.println("Report Header is" + request.getParameter("rpHeader"));
		//reportBean.setReport_header(request.getParameter("rpHeader"));
		//reportBean.setReport_footer(request.getParameter("rpFooter"));

		String selectedColList = request.getParameter("selectedColList");
		System.out.println("Selected selectedColList list : " + selectedColList);

		if ((selectedColList.isEmpty()) || selectedColList == "" || selectedColList == null) {
			res = "Please select atleast one column to save report format";
			String jsonresp = new Gson().toJson(res);
			System.out.println("jsonresp = " + jsonresp);
			return ResponseEntity.ok(jsonresp);
		}

		JSONArray jsonArrObject = new JSONArray(selectedColList);
		System.out.println("jsonob " + jsonArrObject);
		List<ReportSelectColumn> reportSelectColumnlist = new ArrayList<ReportSelectColumn>();
		int len = jsonArrObject.length();

		for (int i = 0; i < len; i++) {
			ReportSelectColumn col = new ReportSelectColumn();
			JSONObject objc = jsonArrObject.getJSONObject(i);
			if (objc.getString("Value").equals(objc.getString("key"))) {
				col.setStatus('I');
			} else {
				col.setStatus('A');
			}

			col.setReportSelectedColumnId(objc.getString("Value"));
			col.setReportSelectedColumnName(objc.getString("key"));
			reportSelectColumnlist.add(col);
			reportBean.addReportSelectColumn(col);
			System.out.println(col);

		}
		reportBean.setReportSelectColumnList(reportSelectColumnlist);

		ReportBean bean = reportBeanService.save(reportBean);
		System.out.println(bean.getReportId());

		if (bean.getReportId() != null) {
			res = "Report has been saved";
			String jsonresp = new Gson().toJson(res);
			System.out.println("jsonresp = " + jsonresp);
			return ResponseEntity.ok(jsonresp);
		} else {
			res = "Issue occured while saving report. Please try again by filling all records";
			String jsonresp = new Gson().toJson(res);
			System.out.println("jsonresp = " + jsonresp);
			return ResponseEntity.ok(jsonresp);
		}

	}
	
}
