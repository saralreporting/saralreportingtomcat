package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.ReportState;

public interface StateRepository extends JpaRepository<ReportState, Long> {

	ReportState findByslcAndIsActive(Long slc, boolean isActive);

	List<ReportState> findByIsActive(Boolean isActive);

}
