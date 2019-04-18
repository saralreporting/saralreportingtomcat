package com.saral.reporting.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.ReportRoleMaster;
import com.saral.reporting.repo.ReportRoleMasterRepository;
import com.saral.reporting.service.ReportRoleMasterService;

@Service
public class ReportRoleMasterServiceImpl implements ReportRoleMasterService {
	@Autowired
	ReportRoleMasterRepository reportRoleMasterRepository; 
	@Override
	public List<ReportRoleMaster> findAll() {
		
		List<ReportRoleMaster> list = reportRoleMasterRepository.findAll();
		return list;
	}
	@Override
	public List<ReportRoleMaster> getAllReportRoleMaster() {
		// TODO Auto-generated method stub
		return (List<ReportRoleMaster>) reportRoleMasterRepository.findAll();
	}
	@Override
	public ReportRoleMaster getReportRoleMasterById(long id) {
			
	 return (ReportRoleMaster) reportRoleMasterRepository.findByReportRoleId(id);
	}
	@Override
	public void saveOrUpdate(ReportRoleMaster reportRoleMaster) {
	
		reportRoleMasterRepository.save(reportRoleMaster);
	}
	@Override
	public void deleteReportRoleMaster(long id) {
		// TODO Auto-generated method stub
		reportRoleMasterRepository.deleteByReportRoleId(id);
	}
	@Override
	public ReportRoleMaster save(ReportRoleMaster reportRoleMaster) {
		// TODO Auto-generated method stub
		return reportRoleMasterRepository.save(reportRoleMaster);
	}
	


}
