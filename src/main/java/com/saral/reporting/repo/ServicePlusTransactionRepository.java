package com.saral.reporting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saral.reporting.model.ServicePlusTransaction;
import com.saral.reporting.utils.DepartmentAmountSumUtils;

public interface ServicePlusTransactionRepository extends JpaRepository<ServicePlusTransaction, Long>{
	
	 @Query(value="   select sum(a.amount),b.service_name from saral1.serviceplustransaction a, saral1.service_master b where \r\n" + 
	 		"         b.service_code =  left(cast(a.servicecode as varchar) ,length(cast(servicecode as varchar)) - 4)\r\n" + 
	 		"         and a.deptcode = ?1 and a.submission_date > current_timestamp - interval '31 day'\r\n" + 
	 		"         group by 2" ,nativeQuery=true)
	  List<Object[]> findSumCount30days(Long deptcode);
	  
	  @Query(value="   select sum(a.amount),b.service_name from saral1.serviceplustransaction a, saral1.service_master b where \r\n" + 
		 		"         b.service_code =  left(cast(a.servicecode as varchar) ,length(cast(servicecode as varchar)) - 4)\r\n" + 
		 		"         and a.deptcode = ?1 and a.submission_date > current_timestamp - interval '8 day'\r\n" + 
		 		"         group by 2" ,nativeQuery=true)
		  List<Object[]> findSumCount7days(Long deptcode);
		  
		  
		  @Query(value="   select sum(a.amount),b.service_name from saral1.serviceplustransaction a, saral1.service_master b where \r\n" + 
			 		"         b.service_code =  left(cast(a.servicecode as varchar) ,length(cast(servicecode as varchar)) - 4)\r\n" + 
			 		"         and a.deptcode = ?1 and a.submission_date > current_timestamp - interval '2 day'\r\n" + 
			 		"         group by 2" ,nativeQuery=true)
			  List<Object[]> findSumCount1days(Long deptcode);

	 
}
