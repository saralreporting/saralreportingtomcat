package com.saral.reporting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.saral.reporting.model.ReportOrganizations;
import com.saral.reporting.model.ServiceMaster;
import com.saral.reporting.service.ReportBeanService;
import com.saral.reporting.service.ReportDomainService;
import com.saral.reporting.service.ServiceMasterService;

@Transactional
@Controller
public class DesignAdminReportController {
	
	@Autowired
	private ReportDomainService reportDomainService;

	@Autowired
	ReportBeanService reportBeanService;
	
	@Autowired
	private ServiceMasterService serviceMasterService;
	
	@RequestMapping(value = { "/DesignAdminReptPage" }, method = RequestMethod.GET)
	public String showDesignAdminReport(ModelMap model) {

		return "designPageAdmin";
	}
	
	@RequestMapping(value = { "/DesignAdminDeptReptPage" }, method = RequestMethod.GET)
	public String showDesignAdminReportDept(ModelMap model) {

		return "designPageAdminDept";
	}
	
	
	@RequestMapping(value = "/DesignReportFetchServices", method = {
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> showDesignReportAdminService(ModelMap model, @RequestParam String deptOid, @RequestParam String action,
			HttpServletRequest request) throws ServletException, IOException {
		String action1 = action;

		System.out.println(action1);
		if (action.equals("fetchService")) {
			System.out.println("In side fetch service" + deptOid);
			List<ServiceMaster> serviceList = new ArrayList<ServiceMaster>();
			if(deptOid=="1"){
				serviceList = serviceMasterService.getAllServiceMaster();
			}else{
			serviceList = serviceMasterService.findByDeptCode(deptOid);
			}

			Map<String, String> mapList = new HashMap<String, String>();
			for (ServiceMaster i : serviceList)
				mapList.put(i.getServiceCode(), i.getServiceName());

			model.put("serviceList", mapList);
			System.out.println(mapList.isEmpty());
			System.out.println(serviceList.size());
			String json = new Gson().toJson(mapList);
			return ResponseEntity.ok(json);
		}
		return (ResponseEntity<?>) ResponseEntity.ok();
	}
/*	@RequestMapping(value = { "/fetchReportListAdmin" }, method = RequestMethod.GET)
	public String reportViewPageAdmin(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		String sign_no = (String) request.getSession().getAttribute("sign_no");
		Long department_id = Long.parseLong(String.valueOf(request.getSession().getAttribute("department_id")));
		List<ReportBean> listReport = reportBeanService.findByDepartmentId(department_id); 
		//List<ReportBean> listReport = reportBeanService.findBySignNo(sign_no);
		PagedListHolder<ReportBean> pagedListHolder = new PagedListHolder<ReportBean>(listReport);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(3);
		model.put("pagedListHolder", pagedListHolder);
		model.put("sign_no", sign_no);
		model.put("l2", listReport);
		return "reportViewerPage";
	}*/
	
	
	@RequestMapping(value = "/fetchOrganizations", method = {
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> DesignReportAdmin(ModelMap model, @RequestParam String action, HttpServletRequest request)
			throws ServletException, IOException {
		String action1 = action;
		System.out.println(action1);
		if (action.equals("fetchOrganizations")) {
			System.out.println("In side fetch Organinsations");
			long slc = 6;
			List<ReportOrganizations> orgList = reportDomainService.getAllReportOrganizations(slc);

			Map<Long, String> mapList = new HashMap<Long, String>();
			for (ReportOrganizations i : orgList)
				mapList.put(i.getOrgCode(), i.getOrgName());

			model.put("orgList", mapList);
			String json = new Gson().toJson(mapList);
			return ResponseEntity.ok(json);
		}
		return (ResponseEntity<?>) ResponseEntity.ok();
	}
}
