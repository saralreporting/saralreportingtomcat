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

@Table(name = "reportFilterMaster")
public class ReportFilterMaster {@Id
	@GeneratedValue(generator = "report_Filter_generator")
	@SequenceGenerator(name = "report_Filter_generator", sequenceName = "report_Filter_generator_sequence", initialValue = 1)
	@Column(name = "reportFilterId")
	private Long reportFilterId;

	@Column(name = "reportFilterName")
	private String reportFilterName;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany (mappedBy="reportFilterMaster", targetEntity = ReportDomainMaster.class, cascade = {CascadeType.ALL})
	private List<ReportDomainMaster> reportDomainMaster;
	
	
	public Long getReportFilterId() {
		return reportFilterId;
	}

	public void setReportFilterId(Long reportFilterId) {
		this.reportFilterId = reportFilterId;
	}

	public String getReportFilterName() {
		return reportFilterName;
	}

	public void setReportFilterName(String reportFilterName) {
		this.reportFilterName = reportFilterName;
	}

	
	
	
	public List<ReportDomainMaster> getReportDomainMaster() {
		return reportDomainMaster;
	}

	public void setReportDomainMaster(List<ReportDomainMaster> reportDomainMaster) {
		this.reportDomainMaster = reportDomainMaster;
	}

	@Override
	public String toString() {
		return "ReportFilterMaster [reportFilterId=" + reportFilterId + ", reportFilterName=" + reportFilterName + "]";
	}

	

	
	

}
