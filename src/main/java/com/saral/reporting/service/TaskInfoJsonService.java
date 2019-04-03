package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.TaskInfoJson;

public interface TaskInfoJsonService {

	public List<TaskInfoJson>  findByApplIdOrderByCurrentProcessId(Long applId);
}
