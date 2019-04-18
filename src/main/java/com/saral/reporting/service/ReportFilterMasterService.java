package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.ReportFilterMaster;



public interface ReportFilterMasterService {
	List<ReportFilterMaster> findAll();
	
	 public List<ReportFilterMaster> getAllReportFilterMaster();
	 
	 public ReportFilterMaster getReportFilterMasterById(long id);
	 
	 public void saveOrUpdate(ReportFilterMaster reportRoleMaster);
	 
	 public void deleteReportFilterMaster(long id);

	 ReportFilterMaster save(ReportFilterMaster reportRoleMaster);

}
