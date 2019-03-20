package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.DashBoardReportData;
import com.saral.reporting.repo.DashBoardReportDataRepository;
import com.saral.reporting.service.DashBoardReportDataService;

@Service
public class DashBoardReportDataServiceImpl implements DashBoardReportDataService {

	@Autowired
	DashBoardReportDataRepository dashBoardReportDataRepository;
	@Override
	public DashBoardReportData save(DashBoardReportData dashBoardReportData) {
		// TODO Auto-generated method stub
		return dashBoardReportDataRepository.save(dashBoardReportData);
	}

	@Override
	public DashBoardReportData findByReportIdAndDepartmentId(Long reportId,Long departmentId) {
		// TODO Auto-generated method stub
		return dashBoardReportDataRepository.findByReportIdAndDepartmentId(reportId,departmentId);
	}

	@Override
	public List<DashBoardReportData> findDistinctTop5ByDepartmentIdOrderByCountDesc(Long departmentId) {
		// TODO Auto-generated method stub
		return dashBoardReportDataRepository.findDistinctTop5ByDepartmentIdOrderByCountDesc(departmentId);
	}

	@Override
	public List<DashBoardReportData> findDistinctTop5OrderByCountDesc() {
		// TODO Auto-generated method stub
		return dashBoardReportDataRepository.findDistinctTop5ByOrderByCountDesc();
	}

	@Override
	public void deleteByReportId(Long reportId) {
		dashBoardReportDataRepository.deleteByReportId( reportId );
		
	}

}
