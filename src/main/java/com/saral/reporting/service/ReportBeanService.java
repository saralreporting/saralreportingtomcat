package com.saral.reporting.service;

import java.util.List;

import com.saral.reporting.model.ReportBean;

public interface ReportBeanService {
	
	ReportBean save(ReportBean reportBean);
	
	List<ReportBean> findBySignNo(String sign_no);
	
	List<ReportBean> findByDepartmentId(Long department_id);
	
	ReportBean findByReportId(Long reportId);
	
	void deleteReportBean(Long reportId);

	List<ReportBean> findByIsAdminReport(Character x);

	List<ReportBean> findByDepartmentIdAndIsAdminReport(Long department_id, char c);

}
