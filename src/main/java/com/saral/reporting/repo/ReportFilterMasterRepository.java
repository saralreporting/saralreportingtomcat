package com.saral.reporting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.ReportFilterMaster;


public interface ReportFilterMasterRepository extends JpaRepository<ReportFilterMaster,Long>  {

	ReportFilterMaster findByReportFilterId(Long reportRoleId);

	void deleteByReportFilterId(Long reportRoleId);
}
