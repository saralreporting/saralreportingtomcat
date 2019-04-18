package com.saral.reporting.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity

@Table(name = "reportRoleMaster")
public class ReportRoleMaster {
	@Id
	@GeneratedValue(generator = "report_role_generator")
	@SequenceGenerator(name = "report_role_generator", sequenceName = "report_role_generator_sequence", initialValue = 1)
	@Column(name = "reportRoleId")
	private Long reportRoleId;

	@Column(name = "reportRoleName")
	private String reportRoleName;
	
	@Column(name = "reporttype")
	private int reporttype;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany (mappedBy="reportRoleMaster", targetEntity = ReportDomainMaster.class, cascade = {CascadeType.ALL})
	private List<ReportDomainMaster> reportDomainMaster;
	

	public Long getReportRoleId() {
		return reportRoleId;
	}

	public void setReportRoleId(Long reportRoleId) {
		this.reportRoleId = reportRoleId;
	}

	public String getReportRoleName() {
		return reportRoleName;
	}

	public void setReportRoleName(String reportRoleName) {
		this.reportRoleName = reportRoleName;
	}

	public int getReporttype() {
		return reporttype;
	}

	public void setReporttype(int reporttype) {
		this.reporttype = reporttype;
	}
	
	

	public List<ReportDomainMaster> getReportDomainMaster() {
		return reportDomainMaster;
	}

	public void setReportDomainMaster(List<ReportDomainMaster> reportDomainMaster) {
		this.reportDomainMaster = reportDomainMaster;
	}

	@Override
	public String toString() {
		return "ReportRoleMaster [reportRoleId=" + reportRoleId + ", reportRoleName=" + reportRoleName + ", reporttype="
				+ reporttype + ", reportDomainMaster=" + reportDomainMaster + "]";
	}




	


}
