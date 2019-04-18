package com.saral.reporting.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.ReportDistrict;
import com.saral.reporting.model.ReportDomainMaster;
import com.saral.reporting.model.ReportOrganizations;
import com.saral.reporting.repo.DistrictRepository;
import com.saral.reporting.repo.ReportDomainRepository;
import com.saral.reporting.repo.ReportOrganizationMasterRepository;
import com.saral.reporting.service.ReportDomainService;


@Service
public class ReportDomainServiceImpl implements ReportDomainService{
	@Autowired
	ReportDomainRepository reportDomainRepository; 
	
	@Autowired
	ReportOrganizationMasterRepository reportOrganizationMasterRepository;
	
	@Autowired
	DistrictRepository districtRepository;
		
	@Override
	public List<ReportDomainMaster> findAll() {
		List<ReportDomainMaster> list = reportDomainRepository.findAll();
		return list;
	}
	
	@Override
	public List<ReportDomainMaster> getAllReportDomainMaster() {
		return  reportDomainRepository.findAll();
	}
	
	@Override
	public ReportDomainMaster getReportDomainId(long reportDomainId) {
		
	 return reportDomainRepository.findByReportDomainId(reportDomainId);
	}
	
	@Override
	public List<ReportDomainMaster> getDomainUserId(long reportDomainId) {
		
	 return reportDomainRepository.findByDomainUserId(reportDomainId);
	}
	@Override
	public void saveOrUpdate(ReportDomainMaster ReportDomainMaster) {
		reportDomainRepository.save(ReportDomainMaster);		
	}
	@Override
	public void deleteReportDomainMaster(long id) {
		reportDomainRepository.deleteByReportDomainId(id);
		
	}
	@Override
	public void deleteReportDomainMasterDetail(long id,long id2) {
		reportDomainRepository.deleteByDomainUserIdAndDomainRoleId(id,id2);
		
	}
	@Override
	public ReportDomainMaster save(ReportDomainMaster ReportDomainMaster) {
		return reportDomainRepository.save(ReportDomainMaster);
	}
	
	@Override
	public List<ReportOrganizations> getAllReportOrganizations(Long slc) {
		return reportOrganizationMasterRepository.findBySlc(slc);
	}
	
	

	@Override
	public List<ReportDistrict> getAllReportDistrict(Long slc, boolean isActive) {
		return districtRepository.findByslcAndIsActive(slc, isActive);
	}
	
	@Override
	public List<ReportDomainMaster> getDomainUserIdAndDomainRoleId(long domainUserId, long domainRoleId) {
		// TODO Auto-generated method stub
		 return reportDomainRepository.findByDomainUserIdAndDomainRoleId(domainUserId, domainRoleId);
	}

	@Override
	public ReportOrganizations findByOrgCode(Long orgCode) {
		// TODO Auto-generated method stub
		return reportOrganizationMasterRepository.findByOrgCode(orgCode);
	}

	

}
