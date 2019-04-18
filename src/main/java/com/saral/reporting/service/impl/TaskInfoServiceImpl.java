package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.TaskInfo;
import com.saral.reporting.repo.TaskInfoRepository;
import com.saral.reporting.service.TaskInfoService;

@Service
public class TaskInfoServiceImpl implements TaskInfoService{

	@Autowired
	TaskInfoRepository taskInfoJsonRepo;
	
	@Override
	public List<TaskInfo> findByApplIdOrderByCurrentProcessId(Long applId) {
		// TODO Auto-generated method stub
		return taskInfoJsonRepo.findByApplIdOrderByCurrentProcessId(applId);
	}

	@Override
	public Long countDistinctTaskIdByApplId(Long applId) {
		// TODO Auto-generated method stub
		return taskInfoJsonRepo.countDistinctTaskIdByApplId(applId);
	}

}
