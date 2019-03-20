package com.saral.reporting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.saral.reporting.model.ApplInfoJson;

public interface ApplInfoJsonService {

	
	
	//Page<ApplInfoJson> findByServiceId(Long serviceId, Pageable pageable);
	
	List<ApplInfoJson> findByServiceIdAndLocationValue(Long serviceId,Long locationvalue);
	
	Long countByServiceId(Long serviceId);
	
	List<ApplInfoJson> findByServiceIdForExcel(Long serviceId);

	Page<ApplInfoJson> findAll(Pageable pageable);

	long count(); 
}
