package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.SpeServiceCoverageDetails;

public interface SpeServiceCoverageDetailsRepo extends JpaRepository<SpeServiceCoverageDetails, Long>{

	List<SpeServiceCoverageDetails> findBySpdiDesigOfcrUserid(Long user_id);

}
