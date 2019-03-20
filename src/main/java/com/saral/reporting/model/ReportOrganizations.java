package com.saral.reporting.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hr_organization",schema="lgd", catalog="lgd")

public class ReportOrganizations implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="org_code")
	private Long orgCode;
	
	@Column(name="org_version")
	private String orgVersion;
	
	@Column(name="org_name")
	private String orgName;
	
	@Column(name="org_level")
	private String orgLevel;
	
	@Column(name="slc")
	private Long slc;
	
	@Column(name="isactive")
	private Boolean isActive=true;

	public Long getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgVersion() {
		return orgVersion;
	}

	public void setOrgVersion(String orgVersion) {
		this.orgVersion = orgVersion;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Long getSlc() {
		return slc;
	}

	public void setSlc(Long slc) {
		this.slc = slc;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ReportOrganizations [orgCode=" + orgCode + ", orgVersion=" + orgVersion + ", orgName=" + orgName
				+ ", orgLevel=" + orgLevel + ", slc=" + slc + ", isActive=" + isActive + "]";
	}

	
	
	
}