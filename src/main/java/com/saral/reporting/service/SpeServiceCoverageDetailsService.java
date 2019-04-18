package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.SpeServiceCoverageDetails;

public interface SpeServiceCoverageDetailsService {

	List<SpeServiceCoverageDetails> findBySpdiDesigOfcrUserid(Long user_id);

	
}
