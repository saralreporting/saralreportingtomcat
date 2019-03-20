package com.saral.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.SpeServiceCoverageDetails;
import com.saral.reporting.repo.SpeServiceCoverageDetailsRepo;
import com.saral.reporting.service.SpeServiceCoverageDetailsService;

@Service
public class SpeServiceCoverageDetailsServiceImpl implements SpeServiceCoverageDetailsService{

	@Autowired
	SpeServiceCoverageDetailsRepo speServiceCoverageDetailsRepo;
	@Override
	public List<SpeServiceCoverageDetails> findBySpdiDesigOfcrUserid(Long user_id) {
		// TODO Auto-generated method stub
		return speServiceCoverageDetailsRepo.findBySpdiDesigOfcrUserid(user_id);
	}

}
