package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.ReportDomainMaster;


public interface ReportDomainRepository extends JpaRepository<ReportDomainMaster,Long>  {

	ReportDomainMaster findByReportDomainId(Long reportDomainId);

	void deleteByReportDomainId(Long reportDomainId);
	List<ReportDomainMaster> findByDomainUserId(Long domainUserId);
	void deleteByDomainUserIdAndDomainRoleId(Long domainUserId, Long domainRoleId);

	List<ReportDomainMaster> findByDomainUserIdAndDomainRoleId(long domainUserId, long domainRoleId);
}
