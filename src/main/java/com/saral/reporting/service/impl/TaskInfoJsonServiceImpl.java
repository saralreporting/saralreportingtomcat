package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.TaskInfoJson;
import com.saral.reporting.repo.TaskInfoJsonRepository;
import com.saral.reporting.service.TaskInfoJsonService;

@Service
public class TaskInfoJsonServiceImpl implements TaskInfoJsonService{

	@Autowired
	TaskInfoJsonRepository taskInfoJsonRepo;
	
	@Override
	public List<TaskInfoJson> findByApplIdOrderByCurrentProcessId(Long applId) {
		// TODO Auto-generated method stub
		return taskInfoJsonRepo.findByApplIdOrderByCurrentProcessId(applId);
	}

}
