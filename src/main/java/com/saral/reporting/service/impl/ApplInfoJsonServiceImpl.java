package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.ApplInfoJson;
import com.saral.reporting.repo.ApplInfoJsonRepository;
import com.saral.reporting.service.ApplInfoJsonService;

@Service
public class ApplInfoJsonServiceImpl implements ApplInfoJsonService {

	@Autowired
	ApplInfoJsonRepository applInfoJsonRepository;

	@Override
	public Page<ApplInfoJson> findAll(Pageable pageable) {

		return applInfoJsonRepository.findAll( new PageRequest(pageable.getPageNumber() - 1, 150));
	}

	@Override
	public List<ApplInfoJson> findByServiceIdForExcel(Long serviceId) {

		return applInfoJsonRepository.findByServiceId(serviceId);
	}

	@Override
	public Long countByServiceId(Long serviceId) {
		// TODO Auto-generated method stub
		return applInfoJsonRepository.countByServiceId(serviceId);
	}

	@Override
	public List<ApplInfoJson> findByServiceIdAndLocationValue(Long serviceId, Long locationvalue) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return applInfoJsonRepository.count();
	}
	
}
