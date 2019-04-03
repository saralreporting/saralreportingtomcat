package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
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
import com.saral.reporting.model.AttributeMasterDataSql;
import com.saral.reporting.model.TaskInfoJson;
import com.saral.reporting.service.AttributeMasterDataSqlService;
import com.saral.reporting.service.TaskInfoJsonService;

@Transactional
@Controller
public class TaskInfoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	TaskInfoJsonService taskInfoJsonService;

	@Autowired
	AttributeMasterDataSqlService attributeMasterDataSqlService;
	
	@RequestMapping(value = { "/fetchTaskInfoData" }, method = RequestMethod.GET)
	public ResponseEntity<?> fetchTaskInfoData (ModelMap model, HttpServletRequest request, @RequestParam Long applId, @RequestParam Long serviceId) throws ServletException, IOException {
		
		Long servID = (Long) request.getSession().getAttribute("service_id");
		System.out.println("Service Id From Session : " + servID);
		List<AttributeMasterDataSql> listAttributes= attributeMasterDataSqlService.findByBaseServiceID(serviceId);
		Map<String,Long> values = new LinkedHashMap<>();
		
		for(AttributeMasterDataSql s :listAttributes) {
			values.put(s.getAttributeLabel(),s.getAttributeID());
		}
		
		//Long applId = 18220L;
		List<TaskInfoJson> taskInfoJsonList = taskInfoJsonService.findByApplIdOrderByCurrentProcessId(applId);
		System.out.println("taskInfoJsonList" + taskInfoJsonList.size());
		String jsonCol = new Gson().toJson(taskInfoJsonList);
		return ResponseEntity.ok(jsonCol);
		
	}
}
