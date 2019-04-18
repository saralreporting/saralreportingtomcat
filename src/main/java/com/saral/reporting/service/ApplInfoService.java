package com.saral.reporting.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.utils.DeparmentAdminCountUtills;

public interface ApplInfoService {

	
	List<DeparmentAdminCountUtills> findTop5ApplInfoCount(Pageable pagebale);
	Long countByServiceId(Long serviceId);
	long count(); 
	
	//ApplInfo findBySubmissionDate(Long applId);
	ApplInfo findByApplId(Long applId);

}
