package com.saral.reporting.service;
import java.util.List;

import com.saral.reporting.model.ReportDistrict;
import com.saral.reporting.model.ReportDomainMaster;
import com.saral.reporting.model.ReportOrganizations;

public interface ReportDomainService {
	List<ReportDomainMaster> findAll();
	
	 public List<ReportDomainMaster> getAllReportDomainMaster();
	 
	 public ReportDomainMaster getReportDomainId(long reportDomainId);
	 public List<ReportDomainMaster> getDomainUserId(long reportDomainId);
	
	 public void saveOrUpdate(ReportDomainMaster reportDomainMaster);
	 
	 public void deleteReportDomainMaster(long id);
	 public void deleteReportDomainMasterDetail(long id,long id2);


	 ReportDomainMaster save(ReportDomainMaster reportDomainMaster);
	 
	 public List<ReportOrganizations> getAllReportOrganizations(Long slc);
	 
	 public ReportOrganizations findByOrgCode(Long orgCode);
	 
	 public List<ReportDistrict> getAllReportDistrict(Long slc,boolean isActive);

	 public List<ReportDomainMaster> getDomainUserIdAndDomainRoleId(long domainUserId, long domainRoleId);

}
