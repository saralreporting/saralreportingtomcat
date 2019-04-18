package com.saral.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "district_hr",schema="lgd", catalog="lgd")
public class ReportDistrict {
	
	@Id
	@Column(name="district_code")
	private Long districtCode;
	
	@Column(name="district_name_english")
	private String districtNameEnglish;
	
	@Column(name="district_name_local")
	private String districtNameLocal;
	
	@Column(name="dlc")
	private Long dlc;
	
	@Column(name="slc")
	private Long slc;
	
	@Column(name="isactive")
	private boolean isActive;

	public Long getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public String getDistrictNameLocal() {
		return districtNameLocal;
	}

	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
	}

	public Long getDlc() {
		return dlc;
	}

	public void setDlc(Long dlc) {
		this.dlc = dlc;
	}

	public Long getSlc() {
		return slc;
	}

	public void setSlc(Long slc) {
		this.slc = slc;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	

	
}