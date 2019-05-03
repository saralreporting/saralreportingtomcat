package com.saral.reporting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.google.gson.Gson;
import com.saral.reporting.DAO.LoginDAO;
import com.saral.reporting.DAO.ReportViwerDAO;
import com.saral.reporting.model.DashBoardReportData;
import com.saral.reporting.model.ReportDomainMaster;
import com.saral.reporting.model.ReportOrganizations;
import com.saral.reporting.model.ReportUserMaster;
import com.saral.reporting.model.SpeServiceCoverageDetails;
import com.saral.reporting.repo.ServiceMasterRepository;
import com.saral.reporting.service.ApplInfoJsonService;
import com.saral.reporting.service.ApplInfoService;
import com.saral.reporting.service.DashBoardReportDataService;
import com.saral.reporting.service.ReportDomainService;
import com.saral.reporting.service.ReportOrganizationService;
import com.saral.reporting.service.ReportUserMasterService;
import com.saral.reporting.service.SpeServiceCoverageDetailsService;
import com.saral.reporting.utils.DeparmentAdminCountUtills;

@Transactional
@Controller
@SessionAttributes({ "sign_no", "user_id", "user_name", "hm", "department_level_name", "department_id",
		"saralUserServiceList", "designation_id", "designation_name", "location_Id", "deptidwithName", "filterString",
		"filterbserviceId", "filterbdeptId", "filterbdisttId", "org_located_at_levels" })
public class LoginController implements Serializable {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(LoginController.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 6972235621273773344L;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private ReportViwerDAO reportViwer;

	@Autowired
	ApplInfoJsonService applInfoJsonService;

	@Autowired
	private ReportDomainService reportDomainService;

	@Autowired
	private ReportUserMasterService reportUserMasterService;

	@Autowired
	private ReportOrganizationService reportOrganizationService;

	@Autowired
	private ServiceMasterRepository serviceMasterRepository;

	@Autowired
	private ApplInfoService applInfoService;

	@Autowired
	private DashBoardReportDataService dashBoardReportDataService;

	@Autowired
	private SpeServiceCoverageDetailsService speServiceCoverageDetailsService;

	@RequestMapping(value = { "/", "/login" })
	public String login(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}

		if (error != null) {
			model.put("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.put("message", "Logged out successfully.");
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";// You can redirect wherever you want, but generally it's a good practice to
										// show login screen again.
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, ModelMap model, Pageable pageable) {
		System.out.println(request.getAttribute("sign_no"));
		System.out.println(getPrincipal());
		String sign_no = getPrincipal();
		List<Object[]> values = loginDAO.getUserInfo(sign_no);
		if (values.size() > 0) {
			Object[] l1 = values.get(0);

			String sign_no1 = (String) l1[0];
			Long user_id = (Long) l1[1];
			String user_name = (String) l1[2];
			String department_level_name = (String) l1[5];
			Long department_id = (Long) l1[6];
			Long designation_id = (Long) l1[7];
			String designation_name = (String) l1[8];
			Long location_Id = (Long) l1[11];
			Long org_located_at_levels = (Long) l1[12];
			HashMap<Long, String> hm = new HashMap<>();

			for (Object[] result : values) {
				Long role_id = (Long) result[3];
				String role_name = (String) result[4];
				hm.put(role_id, role_name);
			}

			String json = new Gson().toJson(hm);
			List<DashBoardReportData> listReportData = dashBoardReportDataService
					.findDistinctTop5ByDepartmentIdOrderByCountDesc(department_id);
			System.out.println("ss" + listReportData);
			model.put("sign_no", sign_no1);
			model.put("user_id", user_id);
			model.put("user_name", user_name);
			model.put("department_level_name", department_level_name);
			model.put("department_id", department_id);
			model.put("designation_id", designation_id);
			model.put("designation_name", designation_name);
			model.put("hm", json);
			model.put("location_Id", location_Id);
			model.put("listReportData", listReportData);
			model.put("org_located_at_levels", org_located_at_levels);
			List<ReportOrganizations> deptidwithName = new ArrayList<ReportOrganizations>();

			ReportOrganizations rpOrgName = reportDomainService.findByOrgCode(department_id);
			if ((!rpOrgName.getOrgName().equals("")) && (!rpOrgName.getOrgName().equals(null))) {
				deptidwithName.add(rpOrgName);
			}

			org.json.JSONArray mapForChartData = null;
			try {
				mapForChartData = reportViwer.ColumnServiceCountByDeprt(department_id.toString(), 'L');
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (mapForChartData.length() > 0) {
				String jsonCol = new Gson().toJson(mapForChartData);
				model.put("mapForChartData", jsonCol);
			} else {
				String jsonCol = new Gson().toJson("No Data available.");
				model.put("mapForChartData", jsonCol);
			}

			if (deptidwithName.size() > 0) {
				model.put("deptidwithName", deptidwithName);
				System.out.println("filterbdeptIdWithName :" + deptidwithName.size());
			}

			/*
			 * Code to find out services assigned to Saral User
			 */

			List<SpeServiceCoverageDetails> servicesForSaralUser = speServiceCoverageDetailsService
					.findBySpdiDesigOfcrUserid(user_id);
			List<Long> serviceListForSaralUser = new ArrayList<>();

			for (SpeServiceCoverageDetails SpeServiceCoverageDetails1 : servicesForSaralUser) {
				String serviceID = SpeServiceCoverageDetails1.getSpdiServiceId().toString();
				serviceID = serviceID.substring(0, serviceID.length() - 4);
				serviceListForSaralUser.add(Long.parseLong(serviceID));
			}
			System.out.println("Service Allocated to the user : " + sign_no1 + " are :" + serviceListForSaralUser);
			model.put("saralUserServiceList", serviceListForSaralUser);
			// List<HrOrgUnits> vas = loginDAO.testSelectJsonbEntity(location_Id);

			getCount(model, pageable);
			return "welcome";
		} else {
			ReportUserMaster reportUserMaster = reportUserMasterService.findBySignNO(sign_no);
			model.put("sign_no", reportUserMaster.getSignNO());
			model.put("user_name", reportUserMaster.getSignNO());
			model.put("user_id", reportUserMaster.getUserDataId());
			model.put("department_id", 0L);
			model.put("designation_id", 0L);
			model.put("location_Id", 0L);
			model.put("designation_name", reportUserMaster.getSignNO());
			HashMap<Integer, String> hm = new HashMap<>();
			for (int i = 0; i < reportUserMaster.getRoleId().size(); i++) {

				hm.put(reportUserMaster.getRoleId().get(i), reportUserMaster.getRoleName().get(i));

			}
			String json = new Gson().toJson(hm);
			model.put("hm", json);

			// Code for filter String starts here
			long domainUserId = reportUserMaster.getUserDataId();
			List<ReportDomainMaster> reportDomainMaster = reportDomainService.getDomainUserId(domainUserId);
			List<Long> bserviceId = new ArrayList<Long>();
			List<Long> bdisttId = new ArrayList<Long>();
			List<Long> bdeptId = new ArrayList<Long>();

			/*
			 * code for string purpose
			 * 
			 * 
			 * List<String> bserviceId = new ArrayList<String>(); List<String> bdisttId =
			 * new ArrayList<String>(); List<String> bdeptId = new ArrayList<String>();
			 * 
			 * 
			 * 
			 * 
			 * for(ReportDomainMaster rp : reportDomainMaster){ Long rpdmnId =
			 * rp.getDomainFilterId(); if(rpdmnId==1L){
			 * bdeptId.add(rp.getDomainValueId().toString()); }else if(rpdmnId==2L){
			 * bdisttId.add(rp.getDomainValueId().toString()); }else if(rpdmnId==3L){
			 * bserviceId.add(rp.getDomainValueId().toString()); } }
			 */

			for (ReportDomainMaster rp : reportDomainMaster) {
				Long rpdmnId = rp.getDomainFilterId();
				if (rpdmnId == 1L) {
					bdeptId.add(Long.valueOf(rp.getDomainValueId()));
				} else if (rpdmnId == 2L) {
					bdisttId.add(Long.valueOf(rp.getDomainValueId()));
				} else if (rpdmnId == 3L) {
					bserviceId.add(Long.valueOf(rp.getDomainValueId()));
				}
			}

			List<ReportOrganizations> deptidwithName = new ArrayList<>();
			for (Long bdeptId1 : bdeptId) {
				ReportOrganizations rpOrgName = reportDomainService.findByOrgCode(bdeptId1);
				if ((!rpOrgName.getOrgName().equals("")) && (!rpOrgName.getOrgName().equals(null))) {
					deptidwithName.add(rpOrgName);
				}
			}

			if (deptidwithName.size() > 0) {
				model.put("deptidwithName", deptidwithName);
				System.out.println("filterbdeptIdWithName :" + deptidwithName.size());
			}

			model.put("filterbserviceId", bserviceId);
			model.put("filterbdisttId", bdisttId);
			model.put("filterbdeptId", bdeptId);
			System.out.println("asjdgaksgdukasgdilagsdgasd" + bdeptId);
			List<DashBoardReportData> listReportData = dashBoardReportDataService.findDistinctTop5OrderByCountDesc();
			System.out.println("ss" + listReportData);
			model.put("listReportData", listReportData);
			/*
			 * String listString=""; if(bdeptId.size()>0){ listString = String.join(", ",
			 * bdeptId); }
			 * 
			 * String listString2 = ""; if(bdisttId.size()>0){ listString2 =
			 * String.join(", ", bdisttId); }
			 * 
			 * String listString3 = ""; if(bserviceId.size()>0){ listString3 =
			 * String.join(", ", bserviceId); }
			 * 
			 * String completeFilter = ""; if(listString!=""){ listString =
			 * "department_id IN (" + listString + ")"; completeFilter = "Where " +
			 * listString; }
			 * 
			 * 
			 * code for distric filters if((listString2!="") &&(completeFilter!="")){
			 * listString2 = " AND district_id IN (" + listString2 + ")"; completeFilter =
			 * completeFilter + listString2; }else if((listString2!="")
			 * &&(completeFilter=="")){ listString2 = "district_id IN (" + listString2 +
			 * ")"; completeFilter = "Where " + listString2; }
			 * 
			 * if((listString3!="") &&(completeFilter!="")){ listString3 =
			 * " AND base_service_id IN (" + listString3 + ")"; completeFilter =
			 * completeFilter + listString3; }else if((listString3!="")
			 * &&(completeFilter=="")){ listString3 = "base_service_id IN (" + listString3 +
			 * ")"; completeFilter = "Where " + listString3; }
			 * 
			 * System.out.println("My Final Filter is " + completeFilter); //Code for filter
			 * String ends here model.put("filterString", completeFilter);
			 */
			List<Integer> intlist = new ArrayList<>();
			org.json.JSONArray mapForChartData = null;
			try {
				mapForChartData = reportViwer.DeptCountForAdminDash(bdeptId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (mapForChartData.length() > 0) {
				String jsonCol = new Gson().toJson(mapForChartData);
				model.put("mapForChartData", jsonCol);
				intlist.add(1);
				model.put("list", intlist);
			} else {
				String jsonCol = new Gson().toJson("No");
				model.put("mapForChartData", jsonCol);
				model.put("list", intlist);
			}
			getCount(model, pageable);
			return "welcome";
		}
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private void getCount(ModelMap model, Pageable pageable) {

		List<DeparmentAdminCountUtills> applInfoCount = applInfoService.findTop5ApplInfoCount(pageable);
		System.out.println(applInfoCount.size());
		Long departments = reportOrganizationService.count();
		Long services = serviceMasterRepository.count();
		Long applicationRecieved = applInfoJsonService.count();
		model.put("departmentsCount", departments);
		model.put("servicesCount", services);
		model.put("departmentsCount", departments);
		model.put("applicationRecieved", applicationRecieved);
		model.put("applInfoCount", applInfoCount);

	}

}