package com.saral.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dashboard_reportdata")
public class DashBoardReportData {
	@Id

	@GeneratedValue(generator = "tbl_app_generator")
	@SequenceGenerator(name = "tbl_app_generator", sequenceName = "tbl_app_generator_sequence", initialValue = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "report_id")
	private Long reportId;
	
	@Column(name = "report_name")
	private String reportName;
	
	@Column(name = "department_id")
	private Long departmentId;
	
	@Column(name = "count_seen")
	private Long count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DashBoardReportData [id=" + id + ", reportId=" + reportId + ", reportName=" + reportName
				+ ", departmentId=" + departmentId + ", count=" + count + "]";
	}

	
	
}
