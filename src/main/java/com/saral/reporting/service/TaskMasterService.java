package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.TaskMaster;

public interface TaskMasterService {
	
	Long countByServiceIdAndVersionNo(String serviceId, String versionNo);
	
	List<TaskMaster> findByServiceIdAndVersionNo(String serviceId, String versionNo);

}
