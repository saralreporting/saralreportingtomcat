package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.LoginData;
import com.saral.reporting.model.ReportDistrict;
import com.saral.reporting.model.ReportState;


public interface LoginService   {
	
	
	List<LoginData> findByUserNameAndPasswd(String username , String passwd);
	List<ReportState> findByIsActive(boolean isActive);
	List<ReportDistrict> findByDistrictslcAndIsActive(Long slc,boolean isActive);
	}
