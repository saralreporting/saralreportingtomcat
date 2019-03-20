package com.saral.reporting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.ReportRoleMaster;

public interface ReportRoleMasterRepository extends JpaRepository<ReportRoleMaster,Long>  {

	ReportRoleMaster findByReportRoleId(Long reportRoleId);

	void deleteByReportRoleId(Long reportRoleId);
}
