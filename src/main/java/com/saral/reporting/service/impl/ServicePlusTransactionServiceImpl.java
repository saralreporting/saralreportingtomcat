package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.repo.ServicePlusTransactionRepository;
import com.saral.reporting.service.ServicePlusTransactionService;
import com.saral.reporting.utils.DeparmentAdminCountUtills;
import com.saral.reporting.utils.DepartmentAmountSumUtils;

@Service
public class ServicePlusTransactionServiceImpl implements ServicePlusTransactionService{

	@Autowired
	ServicePlusTransactionRepository servicePlusTransactionRepository;
	@Override
	public List<Object[]> findSumCount30days(Long deptcode) {
		// TODO Auto-generated method stub
		return servicePlusTransactionRepository.findSumCount30days(deptcode);
	}

	
	@Override
	public List<Object[]> findSumCount7days(Long deptcode) {
		// TODO Auto-generated method stub
		return servicePlusTransactionRepository.findSumCount7days(deptcode);
	}

	
	@Override
	public List<Object[]> findSumCount1days(Long deptcode) {
		// TODO Auto-generated method stub
		return servicePlusTransactionRepository.findSumCount1days(deptcode);
	}

}


