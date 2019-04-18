package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saral.reporting.model.ApplInfoJson;



public interface ApplInfoJsonRepository extends JpaRepository<ApplInfoJson, Long> {
@Query(value="SELECT * FROM saral1.r_app_json WHERE  combined_json @> '{\"version_no\":4, \"17942\": \"238\"}'  /*#pageable*/",nativeQuery=true)
	
	Page<ApplInfoJson> findAll(Pageable pageable);

	Long countByServiceId(Long serviceId);

	List<ApplInfoJson> findByServiceId(Long serviceId);

	
}
