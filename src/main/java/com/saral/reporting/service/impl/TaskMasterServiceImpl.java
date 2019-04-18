package com.saral.reporting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.repo.TaskMasterRepository;
import com.saral.reporting.service.TaskMasterService;

@Service
public class TaskMasterServiceImpl implements TaskMasterService {
	@Autowired
	TaskMasterRepository taskMappingMasterService;
	

	@Override
	public Long countByServiceIdAndVersionNo(String serviceId, Long versionNo) {
		// TODO Auto-generated method stub
		return taskMappingMasterService.countByServiceIdAndVersionNo(serviceId, versionNo);
	}
	
 
	
}
