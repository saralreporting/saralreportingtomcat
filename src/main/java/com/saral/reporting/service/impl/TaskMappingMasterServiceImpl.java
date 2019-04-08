package com.saral.reporting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.repo.TaskMappingMasterRepository;
import com.saral.reporting.service.TaskMappingMasterService;

@Service
public class TaskMappingMasterServiceImpl implements TaskMappingMasterService {
	@Autowired
	TaskMappingMasterRepository taskMappingMasterService;
	

	@Override
	public Long countByServiceIdAndVersionNo(String serviceId, Long versionNo) {
		// TODO Auto-generated method stub
		return taskMappingMasterService.countByServiceIdAndVersionNo(serviceId, versionNo);
	}
	
 
	
}
