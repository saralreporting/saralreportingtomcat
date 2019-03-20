package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.ReportOrganizations;


public interface ReportOrganizationMasterRepository extends JpaRepository<ReportOrganizations,Long>  {

	ReportOrganizations findByOrgCode(Long orgCode);
	
	List<ReportOrganizations> findBySlc(Long slc);

	void deleteByOrgCode(Long orgCode);
}
