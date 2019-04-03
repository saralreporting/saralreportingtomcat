package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saral.reporting.model.TaskInfoJson;
import com.saral.reporting.service.TaskInfoJsonService;

@Transactional
@Controller
public class ExecutionController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	TaskInfoJsonService taskInfoJsonService;
	
	@RequestMapping(value = { "/fetchTaskInfoData" }, method = RequestMethod.GET)
	public String fetchTaskInfoData (ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		
		Long servID = (Long) request.getSession().getAttribute("service_id");
		System.out.println("Service Id From Session : " + servID);
		
		Long applId = 18220L;
		List<TaskInfoJson> taskInfoJsonList = taskInfoJsonService.findByApplIdOrderByCurrentProcessId(applId);
		System.out.println("taskInfoJsonList" + taskInfoJsonList.size());
		return null;
		
	}
}
