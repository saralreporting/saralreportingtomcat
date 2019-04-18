package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.TaskInfo;

public interface TaskInfoRepository extends JpaRepository<TaskInfo, Long> {

	List<TaskInfo> findByApplIdOrderByCurrentProcessId(Long applId);
	Long countDistinctTaskIdByApplId(Long applId);

}
