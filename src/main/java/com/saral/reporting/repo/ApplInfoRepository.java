package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.utils.DeparmentAdminCountUtills;



public interface ApplInfoRepository extends JpaRepository<ApplInfo, Long> {

	
	
 @Query("SELECT  " +
	           "    new com.saral.reporting.utils.DeparmentAdminCountUtills(v.departmentName, COUNT(v)) " +
	           "FROM " +
	           "    ApplInfo v " +
	           "GROUP BY " +
	           "    v.departmentName order by  COUNT(v) desc ")
	  List<DeparmentAdminCountUtills> findTop5ApplInfoCount(Pageable pagebale);
 
	Long countByServiceId(Long serviceId);
	long count(); 

	//ApplInfo findBySubmissionDate(Long applId);

	ApplInfo findByApplId(Long applId);
}
