package com.saral.reporting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.HrOrgLocatedAtLevels;

public interface HrOrgLocatedAtLevelsRepo extends JpaRepository<HrOrgLocatedAtLevels, Long>{

	HrOrgLocatedAtLevels findByOlcAndOrgLocatedLevelCodeOrderBySortOrderAsc(Long olc, Long orgLocatedLevelCode);

	int countByOlc(Long departmentId);

	HrOrgLocatedAtLevels findByOrgLocatedLevelCode(Long orgLocatedLevelCode);

}
