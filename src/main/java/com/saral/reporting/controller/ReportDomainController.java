package com.saral.reporting.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.saral.reporting.model.ReportDomainMaster;
import com.saral.reporting.model.ReportUserMaster;
import com.saral.reporting.service.ReportDomainService;
import com.saral.reporting.service.ReportFilterMasterService;
import com.saral.reporting.service.ReportRoleMasterService;
import com.saral.reporting.service.ReportUserMasterService;
import com.saral.reporting.service.ServiceMasterService;

@Controller
public class ReportDomainController {

	@Autowired
	private ReportDomainService reportDomainService;

	@Autowired
	private ReportUserMasterService reportUserMasterService;
	
	@Autowired
	private ReportRoleMasterService reportRoleMasterService;
	
	@Autowired
	private ReportFilterMasterService reportFilterMasterService;
	
	
	@Autowired
	private ServiceMasterService serviceMasterService;

	@RequestMapping(value = "/DomainMasterlist", method = { RequestMethod.GET})
	public String list1(ModelMap model, HttpServletRequest request) {
		System.out.println("Ashish");
		//ModelAndView model = new ModelAndView();
	List<ReportDomainMaster> domainMasterList = reportDomainService.getAllReportDomainMaster();
		
		PagedListHolder<ReportDomainMaster> pagedListHolder = new PagedListHolder<ReportDomainMaster>(domainMasterList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		model.put("pagedListHolder", pagedListHolder);
	
		model.put("l2", domainMasterList);
		

		return "reportDomainMasterlist";
	}

	@RequestMapping(value = "/addReportDomainMaster", method = {RequestMethod.GET})
	public String addFilterMaster(ModelMap model) {
		
		ReportDomainMaster reportDomainMaster = new ReportDomainMaster();
		model.addAttribute("reportDomainMaster", reportDomainMaster);
		long slc = 6;
		 boolean isActive=true;
			model.addAttribute("DomainUpdate","Add");
		model.addAttribute("userList", reportUserMasterService.getAllReportUserMaster());
		model.addAttribute("roleList", reportRoleMasterService.getAllReportRoleMaster());
		model.addAttribute("filterList", reportFilterMasterService.getAllReportFilterMaster());
		model.addAttribute("organizationList", reportDomainService.getAllReportOrganizations(slc));
		model.addAttribute("districtList", reportDomainService.getAllReportDistrict(slc,isActive));
		model.addAttribute("serviceList", serviceMasterService.getAllServiceMaster());
	
		
		return "reportDomainmasterform";
	}

	@RequestMapping(value = "/updateReportDomainMaster", method = RequestMethod.GET)
	public String editDomaindata(ModelMap model, @RequestParam("id") long id) {

		ReportDomainMaster reportDomainMaster = reportDomainService.getReportDomainId(id);
		System.out.println("Ashish Sharma"+  reportDomainMaster + "id" +id);
		model.addAttribute("reportDomainMaster", reportDomainMaster);
		
		long slc = 6;
		 boolean isActive=true;
		long domainUserId=reportDomainMaster.getDomainUserId();
		long domainRoleId=reportDomainMaster.getDomainRoleId();
		model.addAttribute("DomainUpdate","Update");
    	//model.addAttribute("reportDomainUserList",reportDomainService.getDomainUserId(domainUserId));
		model.addAttribute("reportDomainUserList",reportDomainService.getDomainUserIdAndDomainRoleId(domainUserId,domainRoleId));
		model.addAttribute("userList", reportUserMasterService.getAllReportUserMaster());
		model.addAttribute("roleList", reportRoleMasterService.getAllReportRoleMaster());
		model.addAttribute("filterList", reportFilterMasterService.getAllReportFilterMaster());
		model.addAttribute("organizationList", reportDomainService.getAllReportOrganizations(slc));
		model.addAttribute("districtList", reportDomainService.getAllReportDistrict(slc,isActive));
		model.addAttribute("serviceList", serviceMasterService.getAllServiceMaster());
		//model.setViewName("reportDomainmasterform");
	
		return "reportDomainmasterform";
	}

	@RequestMapping(value = "/saveReportDomainMaster", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("reportDomainMaster") ReportDomainMaster reportDomainMaster) {
		 
		
		List<String> items = Arrays.asList(reportDomainMaster.getDomainValueId().split("\\s*,\\s*"));
		
		System.out.println(reportDomainMaster);
		Iterator<String> iterator = items.iterator();
		while (iterator.hasNext()) {
			ReportDomainMaster	reportDomainMaster1  = new ReportDomainMaster();
			reportDomainMaster1.setDomainUserId(reportDomainMaster.getDomainUserId());
			reportDomainMaster1.setDomainRoleId(reportDomainMaster.getDomainRoleId());
			//reportDomainMaster1.setDomainFilterId(reportDomainMaster.getDomainFilterId());
			  Object element = iterator.next();
			  reportDomainMaster1.setDomainValueId(element.toString());
			  //new code bigins
			  Object element2 = iterator.next();
			  reportDomainMaster1.setDomainFilterId(Long.parseLong(element2.toString()));
			  reportDomainService.save(reportDomainMaster1);
		}

		return new ModelAndView("redirect:/DomainMasterlist");
	}
	@Transactional
	@RequestMapping(value = "/updateReportDomainMaster", method = RequestMethod.POST)
	public ModelAndView Update(@ModelAttribute("reportDomainMaster") ReportDomainMaster reportDomainMaster) {
		 
		////Delete all id's first
		System.out.println("Ashish Sharma"+  reportDomainMaster + "id " +reportDomainMaster.getDomainUserId() +"id2 " +reportDomainMaster.getDomainRoleId());
		reportDomainService.deleteReportDomainMasterDetail(reportDomainMaster.getDomainUserId(),reportDomainMaster.getDomainRoleId());
		System.out.println("reportDomainMaster.getDomainValueName()" + reportDomainMaster.getDomainValueName());
	////Insert New Id's in the first
		System.out.println(reportDomainMaster.getDomainValueId());
		List<String> items = Arrays.asList(reportDomainMaster.getDomainValueId().split("\\s*,\\s*"));
		System.out.println(reportDomainMaster);
		List<String> items1 = Arrays.asList(reportDomainMaster.getDomainValueName().split("\\s*,\\s*"));
		System.out.println(reportDomainMaster);
		
		Iterator<String> iterator = items.iterator();
		Iterator<String> iterator1 = items1.iterator();
		while (iterator.hasNext()) {
			ReportDomainMaster	reportDomainMaster1  = new ReportDomainMaster();
			  Object element = iterator.next();
			  reportDomainMaster1.setDomainValueId(element.toString());
			  Object element2 = iterator.next();
			  reportDomainMaster1.setDomainFilterId(Long.parseLong(element2.toString()));
				reportDomainMaster1.setDomainRoleId(reportDomainMaster.getDomainRoleId());
			reportDomainMaster1.setDomainUserId(reportDomainMaster.getDomainUserId());
			 Object element3 = iterator1.next();
			reportDomainMaster1.setDomainValueName(element3.toString());
			
			//reportDomainMaster1.setDomainFilterId(reportDomainMaster.getDomainFilterId());
		
			reportDomainService.save(reportDomainMaster1);
		}

		return new ModelAndView("redirect:/DomainMasterlist");
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/deleteReportDomainMaster", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") long id) {
		reportDomainService.deleteReportDomainMaster(id);

		return new ModelAndView("redirect:/DomainMasterlist");
	}

	// To fetch Role Column Name
		@RequestMapping(value = "/RoleReport", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public ResponseEntity<?> showRole(ModelMap model, @RequestParam String action, @RequestParam Long userDataId, HttpServletRequest request)
				throws ServletException, IOException {
			
			Long userDataId1 = userDataId;
			System.out.println(userDataId1);
			if (action.equals("fetchRole")) {
				List<ReportUserMaster> userList = reportUserMasterService.findByUserDataId(userDataId);
				//List<ReportUserMaster> userList = reportUserMasterService.getAllReportUserMaster();
				Map<Integer, String> mapColList = new HashMap<Integer, String>();
				for (ReportUserMaster i : userList)
					for(int j=0; j<i.getRoleId().size();j++){
					mapColList.put(i.getRoleId().get(j), i.getRoleName().get(j));
					}
				model.put("RoleList", mapColList);
				System.out.println("RoleList " + mapColList);
				String jsonCol = new Gson().toJson(mapColList);
				return ResponseEntity.ok(jsonCol);
			}
			return (ResponseEntity<?>) ResponseEntity.ok();
		}
	
	/*// To fetch State Column Name
	@RequestMapping(value = "/userReport", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> showUser(ModelMap model, @RequestParam String action, HttpServletRequest request)
			throws ServletException, IOException {
		if (action.equals("fetchUser")) {
			List<ReportUserMaster> list = reportUserMasterService.getAllReportUserMaster();
			Map<Long, String> mapColList = new HashMap<Long, String>();
			for (ReportUserMaster i : list)
				mapColList.put(i.getUserDataId(), i.getSignNO());

			model.put("UserList", mapColList);
			String jsonCol = new Gson().toJson(mapColList);
			return ResponseEntity.ok(jsonCol);
		}
		return (ResponseEntity<?>) ResponseEntity.ok();
	}

	// To fetch Role Column Name
	@RequestMapping(value = "/RoleReport", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> showRole(ModelMap model, @RequestParam String action, HttpServletRequest request)
			throws ServletException, IOException {
		if (action.equals("fetchRole")) {
		List<ReportRoleMaster> list = reportRoleMasterService.getAllReportRoleMaster();
			Map<Long, String> mapColList = new HashMap<Long, String>();
			for (ReportRoleMaster i : list)
				mapColList.put(i.getReportRoleId(), i.getReportRoleName());

			model.put("RoleList", mapColList);
			String jsonCol = new Gson().toJson(mapColList);
			return ResponseEntity.ok(jsonCol);
		}
		return (ResponseEntity<?>) ResponseEntity.ok();
	}

	
	// To fetch Filter Column Name
		@RequestMapping(value = "/FilterReport", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public ResponseEntity<?> showFilter(ModelMap model, @RequestParam String action, HttpServletRequest request)
				throws ServletException, IOException {
			if (action.equals("fetchFilter")) {
				
			List<ReportFilterMaster> list = reportFilterMasterService.getAllReportFilterMaster();
				Map<Long, String> mapColList = new HashMap<Long, String>();
				for (ReportFilterMaster i : list)
					mapColList.put(i.getReportFilterId(), i.getReportFilterName());

				model.put("filterList", mapColList);
				String jsonCol = new Gson().toJson(mapColList);
				return ResponseEntity.ok(jsonCol);
			}
			return (ResponseEntity<?>) ResponseEntity.ok();
		}
		
		
		// To fetch Department Column Name
		@RequestMapping(value = "/DistrictReport", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
		@ModelAttribute("DepartmentList")
		@ResponseBody
		   public ResponseEntity<?> getWebFrameworkList(ModelMap model, @RequestParam String action, HttpServletRequest request)
					throws ServletException, IOException {{
			if (action.equals("fetchDepartment")) {
				long slc = 6;
				List<ReportOrganizations> list = reportDomainService.getAllReportOrganizations(slc);
					Map<Long, String> mapColList = new HashMap<Long, String>();
					for (ReportOrganizations i : list)
						mapColList.put(i.getOrgCode(), i.getOrgName());

					model.put("DepartmentList", mapColList);
					String jsonCol = new Gson().toJson(mapColList);
					return ResponseEntity.ok(jsonCol);
				}
				return (ResponseEntity<?>) ResponseEntity.ok();
		      
		   }
		}
		
		// To fetch District Column Name
				@ModelAttribute("DistirctList")
				   public ResponseEntity<?> getDistrictList(ModelMap model, @RequestParam String action, HttpServletRequest request)
							throws ServletException, IOException {{
					if (action.equals("fetchDistict")) {
						long slc = 6;
						 boolean isActive=true;
						List<ReportDistrict> list = reportDomainService.getAllReportDistrict(slc,isActive);
							Map<Long, String> mapColList = new HashMap<Long, String>();
							for (ReportDistrict i : list)
								mapColList.put(i.getDistrictCode(), i.getDistrictNameEnglish());

							model.put("DistirctList", mapColList);
							String jsonCol = new Gson().toJson(mapColList);
							return ResponseEntity.ok(jsonCol);
						}
						return (ResponseEntity<?>) ResponseEntity.ok();
				      
				   }
				}*/
}

