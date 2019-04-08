package com.saral.reporting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saral.reporting.model.TaskMappingMaster;
import com.saral.reporting.model.TaskMaster;



public interface TaskMasterRepository extends JpaRepository<TaskMaster, Long> {

	@Query(value="SELECT   count(distinct(task_id)) from saral.taskmaster where left(cast(service_id as varchar) ,LENgth(cast(service_id as varchar)) - 4) =?1 and version_no =?2", nativeQuery =true)

	Long countByServiceIdAndVersionNo(String serviceId, Long versionNo);
}
