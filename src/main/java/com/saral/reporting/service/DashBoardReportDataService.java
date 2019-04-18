package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.DashBoardReportData;

public interface DashBoardReportDataService {
	
	
	DashBoardReportData save(DashBoardReportData dashBoardReportData);
	
	DashBoardReportData findByReportIdAndDepartmentId(Long reportId, Long departmentId);
	List<DashBoardReportData> findDistinctTop5ByDepartmentIdOrderByCountDesc(Long departmentId);
	List<DashBoardReportData> findDistinctTop5OrderByCountDesc();
	void deleteByReportId(Long reportId);
}
