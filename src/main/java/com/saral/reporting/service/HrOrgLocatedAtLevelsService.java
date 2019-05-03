package com.saral.reporting.service;

import com.saral.reporting.model.HrOrgLocatedAtLevels;

public interface HrOrgLocatedAtLevelsService {

	HrOrgLocatedAtLevels findByOlcAndOrgLocatedLevelCode(Long Olc, Long OrgLocatedLevelCode);

	int countByOlc(Long departmentId);
	
	HrOrgLocatedAtLevels findByOrgLocatedLevelCode(Long orgLocatedLevelCode);
}
