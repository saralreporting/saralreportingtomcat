package com.saral.reporting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.repo.ReportOrganizationMasterRepository;
import com.saral.reporting.service.ReportOrganizationService;

@Service
public class ReportOrganizationServiceImpl implements ReportOrganizationService {
	
	@Autowired
	ReportOrganizationMasterRepository reportOrganizationMasterRepository;

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return reportOrganizationMasterRepository.count();
	}

}
