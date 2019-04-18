package com.saral.reporting.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saral.reporting.model.ReportFilterMaster;
import com.saral.reporting.repo.ReportFilterMasterRepository;
import com.saral.reporting.service.ReportFilterMasterService;

@Service
public class ReportFiltermasterServiceImpl implements ReportFilterMasterService{
	@Autowired
	ReportFilterMasterRepository reportFilterMasterRepository; 
	@Override
	public List<ReportFilterMaster> findAll() {
		List<ReportFilterMaster> list = reportFilterMasterRepository.findAll();
		return list;
	}
	@Override
	public List<ReportFilterMaster> getAllReportFilterMaster() {
		return (List<ReportFilterMaster>) reportFilterMasterRepository.findAll();
	}
	@Override
	public ReportFilterMaster getReportFilterMasterById(long id) {
		
	 return (ReportFilterMaster) reportFilterMasterRepository.findByReportFilterId(id);
	}
	@Override
	public void saveOrUpdate(ReportFilterMaster reportFilterMaster) {
		reportFilterMasterRepository.save(reportFilterMaster);		
	}
	@Override
	public void deleteReportFilterMaster(long id) {
		reportFilterMasterRepository.deleteByReportFilterId(id);
		
	}
	@Override
	public ReportFilterMaster save(ReportFilterMaster reportFilterMaster) {
		return reportFilterMasterRepository.save(reportFilterMaster);
	}

	

}
