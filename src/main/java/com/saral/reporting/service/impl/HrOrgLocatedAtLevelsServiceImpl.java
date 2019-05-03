package com.saral.reporting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.HrOrgLocatedAtLevels;
import com.saral.reporting.repo.HrOrgLocatedAtLevelsRepo;
import com.saral.reporting.service.HrOrgLocatedAtLevelsService;

@Service
public class HrOrgLocatedAtLevelsServiceImpl implements HrOrgLocatedAtLevelsService {

	@Autowired
	HrOrgLocatedAtLevelsRepo hrOrgLocatedAtLevelsRepo;
	
	@Override
	public HrOrgLocatedAtLevels findByOlcAndOrgLocatedLevelCode(Long Olc, Long OrgLocatedLevelCode) {
		// TODO Auto-generated method stub
		return hrOrgLocatedAtLevelsRepo.findByOlcAndOrgLocatedLevelCodeOrderBySortOrderAsc( Olc, OrgLocatedLevelCode);
	}

	@Override
	public int countByOlc(Long departmentId) {
		// TODO Auto-generated method stub
		return hrOrgLocatedAtLevelsRepo.countByOlc(departmentId);
	}

	@Override
	public HrOrgLocatedAtLevels findByOrgLocatedLevelCode(Long orgLocatedLevelCode) {
		// TODO Auto-generated method stub
		return hrOrgLocatedAtLevelsRepo.findByOrgLocatedLevelCode(orgLocatedLevelCode);
	}

	
}
