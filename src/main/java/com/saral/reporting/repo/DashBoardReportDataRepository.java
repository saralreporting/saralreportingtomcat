package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.DashBoardReportData;

public interface DashBoardReportDataRepository extends JpaRepository<DashBoardReportData, Long>{

	//DashBoardReportData findByReportId(Long reportId, Long departmentId);

	DashBoardReportData findByReportIdAndDepartmentId(Long reportId, Long departmentId);
	
	List<DashBoardReportData> findDistinctTop5ByDepartmentIdOrderByCountDesc(Long departmentId);
	
	//List<DashBoardReportData> findDistinctTop5OrderByCountDesc();

	List<DashBoardReportData> findDistinctTop5ByOrderByCountDesc();

	void deleteByReportId(Long reportId);
	



}
