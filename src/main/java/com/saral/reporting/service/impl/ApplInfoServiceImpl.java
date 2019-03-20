package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.saral.reporting.repo.ApplInfoRepository;
import com.saral.reporting.service.ApplInfoService;
import com.saral.reporting.utils.DeparmentAdminCountUtills;

@Service
public class ApplInfoServiceImpl implements ApplInfoService {
	
	@Autowired
	ApplInfoRepository applInfoRepository; 

	@Override
	public List<DeparmentAdminCountUtills> findTop5ApplInfoCount(Pageable pagebale) {
		// TODO Auto-generated method stub
		return applInfoRepository.findTop5ApplInfoCount(new PageRequest(0, 5));
	}

	
	
}
