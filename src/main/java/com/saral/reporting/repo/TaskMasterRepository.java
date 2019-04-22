package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saral.reporting.model.TaskMaster;

public interface TaskMasterRepository extends JpaRepository<TaskMaster, Long> {

	@Query(value = "SELECT   count(distinct(task_id)) from lgd.taskmaster where left(cast(service_id as varchar) ,LENgth(cast(service_id as varchar)) - 4) =?1 "
			+ "and (right(cast(service_id as varchar) ,2) =?2 or  right(cast(service_id as varchar) ,1) =?2)", nativeQuery = true)
	Long countByServiceIdAndVersionNo(String serviceId, String versionNo);

	@Query(value = "SELECT  * from lgd.taskmaster where left(cast(service_id as varchar) ,LENgth(cast(service_id as varchar)) - 4) =?1 "
			+ "and (right(cast(service_id as varchar) ,2) =?2 or  right(cast(service_id as varchar) ,1) =?2)", nativeQuery = true)
	List<TaskMaster> findByServiceIdAndVersionNo(String serviceId, String versionNo);
}
