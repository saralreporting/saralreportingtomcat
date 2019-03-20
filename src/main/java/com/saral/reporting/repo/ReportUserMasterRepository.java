package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saral.reporting.model.ReportUserMaster;


public interface ReportUserMasterRepository extends JpaRepository<ReportUserMaster,Long>  {

	ReportUserMaster findByuserDataId(Long userDataId);

	void deleteByuserDataId(Long userDataId);

	ReportUserMaster findBySignNOAndSecuredPassword(String signNO, String securedPassword);
	
	ReportUserMaster findBySignNO(String signNO);
	
	Long countBySignNO(String SignNO);

	List<ReportUserMaster> findByUserDataId(Long userDataId);
}
