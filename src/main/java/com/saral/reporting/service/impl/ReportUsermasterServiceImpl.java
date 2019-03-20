package com.saral.reporting.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.ReportUserMaster;
import com.saral.reporting.repo.ReportUserMasterRepository;
import com.saral.reporting.service.ReportUserMasterService;

@Service
public class ReportUsermasterServiceImpl implements ReportUserMasterService{
	@Autowired
	ReportUserMasterRepository reportUserMasterRepository; 
	@Override
	public List<ReportUserMaster> findAll() {
		List<ReportUserMaster> list = reportUserMasterRepository.findAll();
		return list;
	}
	@Override
	public List<ReportUserMaster> getAllReportUserMaster() {
		return (List<ReportUserMaster>) reportUserMasterRepository.findAll();
	}
	@Override
	public ReportUserMaster getReportUserMasterById(long id) {
		return (ReportUserMaster) reportUserMasterRepository.findByuserDataId(id);
	}
	@Override
	public void saveOrUpdate(ReportUserMaster reportUserMaster) {
		reportUserMasterRepository.save(reportUserMaster);		
	}
	@Override
	public void deleteReportUserMaster(long id) {
		reportUserMasterRepository.deleteByuserDataId(id);
		
	}
	@Override
	public ReportUserMaster save(ReportUserMaster reportUserMaster) {
		return reportUserMasterRepository.save(reportUserMaster);
	}
	@Override
	public ReportUserMaster findBySignNOAndSecuredPassword(String signNO, String securedPassword) {
		// TODO Auto-generated method stub
		return reportUserMasterRepository.findBySignNOAndSecuredPassword(signNO,securedPassword);
	}
	
	@Override
	public ReportUserMaster findBySignNO(String signNO) {
		// TODO Auto-generated method stub
		return reportUserMasterRepository.findBySignNO(signNO);
	}
	
	@Override
	public List<ReportUserMaster> findByUserDataId(Long userDataId) {
		// TODO Auto-generated method stub
		return reportUserMasterRepository.findByUserDataId(userDataId);
	}
	
	@Override
	public Long countBySignNO(String signNO) {
		// TODO Auto-generated method stub
		return reportUserMasterRepository.countBySignNO(signNO);
	}

}
