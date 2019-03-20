package com.saral.reporting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.saral.reporting.model.ReportRoleMaster;
import com.saral.reporting.service.ReportRoleMasterService;


@Controller
public class ReportRoleMasterController {
	@Autowired
	ReportRoleMasterService reportRoleMasterService;

	@RequestMapping(value = "/RoleMasterlist", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		List<ReportRoleMaster> RoleMasterList = reportRoleMasterService.getAllReportRoleMaster();
		model.addObject("RoleMasterlist", RoleMasterList);
		model.setViewName("reportRoleMasterlist");
		return model;
	}

	@RequestMapping(value = "/addReportRoleMaster" , method = RequestMethod.GET)
	public String addRoleMaster(ModelMap model) {
		
		ReportRoleMaster reportRoleMaster = new ReportRoleMaster();
		model.put("reportRoleMaster", reportRoleMaster);
		
		return "reportrolemasterform";
	}

	@RequestMapping(value = "/updateReportRoleMaster", method = RequestMethod.GET)
	public String editArticle(ModelMap model, @RequestParam("id") long id) {

		ReportRoleMaster reportRoleMaster = reportRoleMasterService.getReportRoleMasterById(id);
		model.put("reportRoleMaster", reportRoleMaster);

		return "reportrolemasterform";
	}

	@RequestMapping(value = "/saveReportRoleMaster", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("reportRoleMaster") ReportRoleMaster reportRoleMaster) {
		System.out.println("here"+reportRoleMaster);
		reportRoleMasterService.save(reportRoleMaster);

		return new ModelAndView("redirect:/RoleMasterlist");
	}

	@Transactional
	@RequestMapping(value = "/deleteReportRoleMaster", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") long id) {
		reportRoleMasterService.deleteReportRoleMaster(id);

		return new ModelAndView("redirect:/RoleMasterlist");
	}
	
	
	
	
	// To fetch Role Column Name
			@RequestMapping(value = "/userRoleReport",method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
			@ResponseBody
			public ResponseEntity<?> showRole(ModelMap model,@RequestParam String action,HttpServletRequest request)
					throws ServletException, IOException {
				if (action.equals("fetchRole")) {
				List<ReportRoleMaster> RoleList = reportRoleMasterService.getAllReportRoleMaster();
					Map<Long, String> mapColList = new HashMap<Long, String>();
					for (ReportRoleMaster i : RoleList)
						mapColList.put(i.getReportRoleId(), i.getReportRoleName());

					model.put("RoleList", mapColList);
					String jsonCol = new Gson().toJson(mapColList);
					return ResponseEntity.ok(jsonCol);
				}
				return (ResponseEntity<?>) ResponseEntity.ok();

			}

}
