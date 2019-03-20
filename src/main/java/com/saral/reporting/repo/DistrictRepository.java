package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saral.reporting.model.ReportDistrict;

public interface DistrictRepository extends JpaRepository<ReportDistrict,Long>  {

	List<ReportDistrict> findByslcAndIsActive(Long slc,boolean isActive);
		
	
}
