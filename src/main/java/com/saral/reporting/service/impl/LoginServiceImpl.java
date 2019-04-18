package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.LoginData;
import com.saral.reporting.model.ReportDistrict;
import com.saral.reporting.model.ReportState;
import com.saral.reporting.repo.DistrictRepository;
import com.saral.reporting.repo.LoginRepository;
import com.saral.reporting.repo.StateRepository;
import com.saral.reporting.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService  {
	
	@Autowired
	LoginRepository repository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	DistrictRepository districtRepository;

	@Override
	public List<LoginData> findByUserNameAndPasswd(String username, String passwd) {
		List<LoginData> list = repository.findAll();
		return list;
	}
	public List<ReportState> findByIsActive(boolean isActive) {
		List<ReportState> list = stateRepository.findByIsActive(isActive);
		return list;
	}
	public List<ReportDistrict> findByDistrictslcAndIsActive(Long slc,boolean isActive) {
		List<ReportDistrict> list = districtRepository.findAll();
		return list;
	}	
	
	
}
