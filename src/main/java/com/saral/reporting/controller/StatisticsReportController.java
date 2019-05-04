package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.saral.reporting.model.HrOrgLocatedAtLevels;
import com.saral.reporting.service.HrOrgLocatedAtLevelsService;
import com.saral.reporting.service.ServicePlusTransactionService;
import com.saral.reporting.utils.DepartmentAmountSumUtils;

@Transactional
@Controller

@SessionAttributes({ "deptidSelectedCollctn", "timePeriod"})
public class StatisticsReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	HrOrgLocatedAtLevelsService hrOrgLocatedAtLevelsService;
	
	@Autowired
	ServicePlusTransactionService servicePlusTransactionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/appOutcomeReport" }, method = RequestMethod.GET)
	public String AppOutcomeReport(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		//String sign_no = (String) request.getSession().getAttribute("sign_no");
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
		
		}	
		return "appOutcomeReportList";
	}

	

	@RequestMapping(value = { "/deptCollectionReportRef" }, method = RequestMethod.GET)
	public String DeptCollectionReportRef(ModelMap model,@RequestParam Long timePeriod, @RequestParam Long deptidSelectedCollctn,
			HttpServletRequest request) throws ServletException, IOException {
		
			request.getSession().setAttribute("deptidSelectedCollctn", deptidSelectedCollctn);
			model.put("deptidSelectedCollctn", deptidSelectedCollctn);
			System.out.println("THis is what i am getting now : " + deptidSelectedCollctn);
		
		
			request.getSession().setAttribute("timePeriod", timePeriod);
			model.put("timePeriod", timePeriod);
			System.out.println("THis is what i am getting now : " + timePeriod);
		
		return "deptCollectionReport";
	}
	
	
	@RequestMapping(value = { "/deptCollectionReport" }, method = RequestMethod.GET)
	public String DeptCollectionReport(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		
		
		Long department_id = (Long) request.getSession().getAttribute("department_id");
		Long deptidSelectedCollctn = (Long) request.getSession().getAttribute("deptidSelectedCollctn");
		Long timePeriod = (Long) request.getSession().getAttribute("timePeriod");
		
		Long periodDef = 30L;
		if(deptidSelectedCollctn==null) {
			
		}else {
			department_id = deptidSelectedCollctn;
		}
		
		if(timePeriod == null) {
			
		}else {
			periodDef = timePeriod;
		}
		
		System.out.println("1st timePeriod : " + timePeriod);
		System.out.println("2nd deptidwithNameSelected : " + deptidSelectedCollctn);
		System.out.println("Department id before loops : " + department_id);
		
		if (department_id == 0L) {
			List<Long> filterbdeptId = (List<Long>) request.getSession().getAttribute("filterbdeptId");
			
			System.out.println("dept list is :" +filterbdeptId);
			List<Object[]> mydata = new ArrayList<>();
			if(periodDef==30) {
				mydata = servicePlusTransactionService.findSumCount30days(filterbdeptId.get(0));
			}else if(periodDef==7){
				mydata = servicePlusTransactionService.findSumCount7days(filterbdeptId.get(0));
			}else if(periodDef==1){
				mydata = servicePlusTransactionService.findSumCount1days(filterbdeptId.get(0));
			}else {
				mydata = servicePlusTransactionService.findSumCount30days(filterbdeptId.get(0));
			}
			BigInteger sum = BigInteger.valueOf(0);
			List<DepartmentAmountSumUtils> listing = new ArrayList<>();
			for (Object[] l : mydata) {
				DepartmentAmountSumUtils utils = new DepartmentAmountSumUtils();
				List<Object> list = Arrays.asList(l);
				System.out.println(list);
				utils.setServiceName((String) list.get(1));
				if(list.get(0)!=null) {
					utils.setSum((BigInteger) list.get(0));
					BigInteger value =  ((BigInteger)list.get(0));
					sum = sum.add(value);}
				else {
					utils.setSum(BigInteger.valueOf(0));
				}
				listing.add(utils);
			}
			DepartmentAmountSumUtils utils = new DepartmentAmountSumUtils(); 
			utils.setServiceName("Grand Total");
			utils.setSum(sum);
			listing.add(utils);
			System.out.println(listing);
			model.put("collectionList", listing);
			model.put("collctnDeptSelected", filterbdeptId.get(0));
			model.put("collctnPeriodSelected", periodDef);
			
		} else {
			List<Object[]> mydata = new ArrayList<>();
			if(periodDef==30) {
				mydata = servicePlusTransactionService.findSumCount30days(department_id);
			}else if(periodDef==7){
				mydata = servicePlusTransactionService.findSumCount7days(department_id);
			}else if(periodDef==1){
				mydata = servicePlusTransactionService.findSumCount1days(department_id);
			}else {
				mydata = servicePlusTransactionService.findSumCount30days(department_id);
			}
			
			BigInteger sum = BigInteger.valueOf(0);
			List<DepartmentAmountSumUtils> listing = new ArrayList<>();
			for (Object[] l : mydata) {
				DepartmentAmountSumUtils utils = new DepartmentAmountSumUtils();
				List<Object> list = Arrays.asList(l);
				System.out.println(list);
				utils.setServiceName((String) list.get(1));
				if(list.get(0)!=null) {
					utils.setSum((BigInteger) list.get(0));

					BigInteger value =  ((BigInteger)list.get(0));
					sum = sum.add(value);}
					else {
					utils.setSum(BigInteger.valueOf(0));
					}
			listing.add(utils);

			}
			DepartmentAmountSumUtils utils = new DepartmentAmountSumUtils(); 
			utils.setServiceName("Grand Total");
			utils.setSum(sum);
			listing.add(utils);
			System.out.println(listing);
			model.put("collectionList", listing);
			model.put("collctnDeptSelected", department_id);
			model.put("collctnPeriodSelected", periodDef);
			
		}	
		return "deptCollectionReport";
	}
	
	
}
