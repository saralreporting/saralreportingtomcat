package com.saral.reporting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saral.reporting.model.TaskMappingMaster;



public interface TaskMappingMasterRepository extends JpaRepository<TaskMappingMaster, Long> {

	@Query(value="SELECT   count(*) from lgd.taskmappingmaster where left(cast(service_id as varchar) ,LENgth(cast(service_id as varchar)) - 4) =?1 and version_no =?2", nativeQuery =true)

	Long countByServiceIdAndVersionNo(String serviceId, Long versionNo);
}
