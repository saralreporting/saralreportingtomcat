package com.saral.reporting.service;

import java.util.List;

public interface ServicePlusTransactionService {

	  List<Object[]> findSumCount30days(Long deptcode);
	  
	  List<Object[]> findSumCount7days(Long deptcode);
	  
	  List<Object[]> findSumCount1days(Long deptcode);
}
