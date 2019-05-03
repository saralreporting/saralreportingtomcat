package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Transactional
@Controller
public class StatisticsReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/AppOutcomeReport" }, method = RequestMethod.GET)
	public String reportViewPage(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		String sign_no = (String) request.getSession().getAttribute("sign_no");

		Long department_id = (Long) request.getSession().getAttribute("department_id");

		Long org_located_at_levels = (Long) request.getSession().getAttribute("org_located_at_levels");
		System.out.println("Located at level code :" + org_located_at_levels);
		if (department_id == 0L) {
			List<Long> filterbserviceId = (List<Long>) request.getSession().getAttribute("filterbserviceId");
			//List<Long> filterbdisttId = (List<Long>) request.getSession().getAttribute("filterbdisttId");
			List<Long> filterbdeptId = (List<Long>) request.getSession().getAttribute("filterbdeptId");
			System.out.println("service list is :" + filterbserviceId);
			System.out.println("dept list is :" +filterbdeptId);
		} else {
			List<Long> userAllocatedServices = (List<Long>) request.getSession().getAttribute("saralUserServiceList");
			String hmfromSession = (String) request.getSession().getAttribute("hm");
			System.out.println("Roles From the session" + hmfromSession);
			System.out.println("This is the list we get from the session :" + userAllocatedServices);
			if (hmfromSession.contains("State Service Definer") || hmfromSession.contains("Department Admin")) {
				userAllocatedServices.clear();
				System.out.println("This is the list we get from the session inside loop:" + userAllocatedServices);
			}
		}	
		return "appOutcomeReportList";
	}

}
