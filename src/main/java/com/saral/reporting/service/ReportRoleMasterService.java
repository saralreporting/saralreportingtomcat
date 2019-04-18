package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.ReportRoleMaster;

public interface ReportRoleMasterService {
	List<ReportRoleMaster> findAll();
	
	 public List<ReportRoleMaster> getAllReportRoleMaster();
	 
	 public ReportRoleMaster getReportRoleMasterById(long id);
	 
	 public void saveOrUpdate(ReportRoleMaster reportRoleMaster);
	 
	 public void deleteReportRoleMaster(long id);

	 ReportRoleMaster save(ReportRoleMaster reportRoleMaster);
}
