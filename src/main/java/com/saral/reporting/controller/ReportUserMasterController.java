package com.saral.reporting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.saral.reporting.model.ReportDistrict;
import com.saral.reporting.model.ReportState;
import com.saral.reporting.model.ReportUserMaster;
import com.saral.reporting.service.LoginService;
import com.saral.reporting.service.ReportUserMasterService;
import com.saral.reporting.utils.FormValidation;

@Transactional
@Controller
@SessionAttributes({ "sign_no", "user_id", "user_name", "hm", "department_level_name", "department_id",
		"designation_id", "designation_name" })

public class ReportUserMasterController {
	@Autowired
	ReportUserMasterService reportUserMasterService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/UserMasterlist", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		List<ReportUserMaster> UserMasterList = reportUserMasterService.getAllReportUserMaster();
		model.addObject("UserMasterlist", UserMasterList);
		model.setViewName("reportUserMasterlist");
		return model;
	}

	@RequestMapping(value = "/addReportUserMaster", method = RequestMethod.GET)
	public String addUserMaster(ModelMap model) {

		ReportUserMaster reportUserMaster = new ReportUserMaster();
		model.put("reportUserMaster", reportUserMaster);

		return "reportUsermasterform";
	}

	@RequestMapping(value = "/updateReportUserMaster", method = RequestMethod.GET)
	public String editUser(ModelMap model, @RequestParam("id") long id) {

		ReportUserMaster reportUserMaster = reportUserMasterService.getReportUserMasterById(id);
		model.put("reportUserMaster", reportUserMaster);

		return "reportUsermasterform";
	}

	@RequestMapping(value = "/saveReportUserMaster", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("reportUserMaster") ReportUserMaster reportUserMaster, BindingResult bindingResult) {
		FormValidation formValidation = new FormValidation();    
	    
		  formValidation.validate(reportUserMaster, bindingResult);    
		if(bindingResult.hasErrors()) {
		System.out.println("here" + reportUserMaster);
		return new ModelAndView("reportUsermasterform");
		}
		reportUserMasterService.save(reportUserMaster);
		return new ModelAndView("redirect:/UserMasterlist");
	}

	@RequestMapping(value = "/deleteReportUserMaster", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") long id) {
		reportUserMasterService.deleteReportUserMaster(id);

		return new ModelAndView("redirect:/UserMasterlist");
	}

	// To fetch District Column Name
	@RequestMapping(value = "/UserDistrictReport",method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> showDistrictStateWise(ModelMap model,@RequestParam String action,
			@RequestParam Long slc, HttpServletRequest request)
			throws ServletException, IOException {
			boolean isActive;
			isActive=true;
			List<ReportDistrict> list = loginService.findByDistrictslcAndIsActive(slc, isActive);
			Map<Long, String> mapColList = new HashMap<Long, String>();
			for (ReportDistrict i : list)
				mapColList.put(i.getDistrictCode(), i.getDistrictNameEnglish());

			model.put("DistrictList", mapColList);
			String jsonCol = new Gson().toJson(mapColList);
			return ResponseEntity.ok(jsonCol);

	}
	
	// To fetch State Column Name
		@RequestMapping(value = "/userStateReport",method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public ResponseEntity<?> showState(ModelMap model,@RequestParam String action,HttpServletRequest request)
				throws ServletException, IOException {
			if (action.equals("fetchState")) {	
			 	 boolean isActive=true;
				System.out.println(isActive);
				List<ReportState> list = loginService.findByIsActive(isActive);
				Map<Long, String> mapColList = new HashMap<Long, String>();
				for (ReportState i : list)
					mapColList.put(i.getStateCode(), i.getStateNameEnglish());

				model.put("StateList", mapColList);
				String jsonCol = new Gson().toJson(mapColList);
				return ResponseEntity.ok(jsonCol);
			}
			return (ResponseEntity<?>) ResponseEntity.ok();

		}
		
		// To check user NameAvailability
		@RequestMapping(value = "/checkUserAvaialability",method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public ResponseEntity<?> checkUserAvaialability(ModelMap model,@RequestParam String signNO, HttpServletRequest request)
				throws ServletException, IOException {
				
				long count = 0L;
				count =  reportUserMasterService.countBySignNO(signNO);
			 	if(count>0){
			 		String jsonCol = new Gson().toJson("This UserName is not available.");
					return ResponseEntity.ok(jsonCol);
			 	}else{
			 		String jsonCol = new Gson().toJson("This UserName is available.");
					return ResponseEntity.ok(jsonCol);
			 	}
			 	
		}


}
