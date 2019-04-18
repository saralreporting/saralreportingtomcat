package com.saral.reporting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.saral.reporting.model.ReportFilterMaster;
import com.saral.reporting.service.ReportFilterMasterService;


@Controller
public class ReportFilterMasterController {
	@Autowired
	ReportFilterMasterService reportFilterMasterService;

	@RequestMapping(value = "/FilterMasterlist", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		List<ReportFilterMaster> FilterMasterList = reportFilterMasterService.getAllReportFilterMaster();
		model.addObject("FilterMasterlist", FilterMasterList);
		model.setViewName("reportFilterMasterlist");
		return model;
	}

	@RequestMapping(value = "/addReportFilterMaster" , method = RequestMethod.GET)
	public String addFilterMaster(ModelMap model) {
		
		ReportFilterMaster reportFilterMaster = new ReportFilterMaster();
		model.put("reportFilterMaster", reportFilterMaster);
		
		return "reportFiltermasterform";
	}

	@RequestMapping(value = "/updateReportFilterMaster", method = RequestMethod.GET)
	public String editArticle(ModelMap model, @RequestParam("id") long id) {

		ReportFilterMaster reportFilterMaster = reportFilterMasterService.getReportFilterMasterById(id);
		model.put("reportFilterMaster", reportFilterMaster);

		return "reportFiltermasterform";
	}

	@RequestMapping(value = "/saveReportFilterMaster", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("reportFilterMaster") ReportFilterMaster reportFilterMaster) {
		System.out.println("here"+reportFilterMaster);
		reportFilterMasterService.save(reportFilterMaster);

		return new ModelAndView("redirect:/FilterMasterlist");
	}
	
	@Transactional
	@RequestMapping(value = "/deleteReportFilterMaster", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") long id) {
		reportFilterMasterService.deleteReportFilterMaster(id);

		return new ModelAndView("redirect:/FilterMasterlist");
	}

}
