package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.TaskMaster;
import com.saral.reporting.repo.TaskMasterRepository;
import com.saral.reporting.service.TaskMasterService;

@Service
public class TaskMasterServiceImpl implements TaskMasterService {
	@Autowired
	TaskMasterRepository taskMappingMasterService;
	

	@Override
	public Long countByServiceIdAndVersionNo(String serviceId, String versionNo) {
		// TODO Auto-generated method stub
		return taskMappingMasterService.countByServiceIdAndVersionNo(serviceId, versionNo);
	}


	@Override
	public List<TaskMaster> findByServiceIdAndVersionNo(String serviceId, String versionNo) {
		// TODO Auto-generated method stub
		return taskMappingMasterService.findByServiceIdAndVersionNo(serviceId, versionNo);
	}
	
 
	
}
