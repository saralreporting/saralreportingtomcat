package com.saral.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state",schema="lgd", catalog="lgd")

public class ReportState {
	
	@Id
	@Column(name="state_code")
	private Long stateCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="state_name_local")
	private String stateNameLocal;
	
	@Column(name="alias_english")
	private String aliasEnglish;
	
	@Column(name="slc")
	private Long slc;
	
	@Column(name="isactive")
	private Boolean isActive=true;

	public Long getStateCode() {
		return stateCode;
	}

	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public String getStateNameLocal() {
		return stateNameLocal;
	}

	public void setStateNameLocal(String stateNameLocal) {
		this.stateNameLocal = stateNameLocal;
	}

	public String getAliasEnglish() {
		return aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
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
		return "ReportState [stateCode=" + stateCode + ", stateNameEnglish=" + stateNameEnglish + ", stateNameLocal="
				+ stateNameLocal + ", aliasEnglish=" + aliasEnglish + ", slc=" + slc + ", isActive=" + isActive + "]";
	}
	
	
}