package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saral.reporting.model.TaskInfoJson;

public interface TaskInfoJsonRepository extends JpaRepository<TaskInfoJson, Long> {

	List<TaskInfoJson> findByApplIdOrderByCurrentProcessId(Long applId);

}
