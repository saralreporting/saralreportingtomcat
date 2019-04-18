package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.TaskInfo;

public interface TaskInfoService {

	public List<TaskInfo>  findByApplIdOrderByCurrentProcessId(Long applId);
	
	Long countDistinctTaskIdByApplId(Long applId);
}
