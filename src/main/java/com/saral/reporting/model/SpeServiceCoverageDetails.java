package com.saral.reporting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "spe_service_coverage_details")
public class SpeServiceCoverageDetails {

	@Id
	@Column(name = "spdi_coverage_location_id")
	private Long spdiCoverageLocationId;
	
	@Column(name = "spdi_service_id")
	private Long spdiServiceId;
	
	@Column(name = "spdi_location_type_id")
	private Long spdiLocationTypeId;
	
	@Column(name = "spdi_location_id")
	private Long spdiLocationId;
	
	@Column(name = "spdi_service_status")
	private Long spdiServiceStatus;
	
	@Column(name = "spdi_contact_person_add_dist")
	private Long spdiContactPersonAddDist;
	
	@Column(name = "spdi_contact_person_add_state")
	private Long spdiContactPersonAddState;
	
	@Column(name = "spdv_contact_person_add_line1")
	private String spdvContactPersonAddLine1;
	
	@Column(name = "spdv_contact_person_add_line2")
	private String spdvContactPersonAddLine2;
	
	@Column(name = "spdv_contact_person_add_line3")
	private String spdvContactPersonAddLine3;
	
	@Column(name = "spdv_contact_person_add_pin")
	private String spdvContactPersonAddPin;
	
	@Column(name = "spdv_contact_person_email")
	private String spdvContactPersonEmail;
	
	@Column(name = "spdv_contact_person_name")
	private String spdvContactPersonName;
	
	@Column(name = "spdv_contact_person_phone")
	private String spdvContactPersonPhone;
	
	@Column(name = "spdi_adopt_flag")
	private Long spdiAdoptFlag;
	
	@Column(name = "spdv_location_level")
	private String spdvLocationLevel;
	
	@Column(name = "spdv_location_level_id")
	private String spdvLocationLevelId;
	
	@Column(name = "spdi_desig_ofcr_userid")
	private Long spdiDesigOfcrUserid;

	@Temporal(TemporalType.DATE)
	@Column(name = "spdv_adopt_on")
	private Date spdvAdoptOn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "spdv_activated_on")
	private Date spdvActivatedOn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "spdv_deactivated_on")
	private Date spdvDeactivatedOn;
	
	@Column(name = "spdi_state_id")
	private Long spdiStateId;
	
	@Column(name = "spdi_updated_flag")
	private Character spdiUpdatedFlag;
	
	@Column(name = "assign_by_user_id")
	private Long assignByUserId;
	
	@Column(name = "ld_type")
	private Character ldType;
	
	@Column(name = "version_no")
	private Long versionNo;

	public Long getSpdiCoverageLocationId() {
		return spdiCoverageLocationId;
	}

	public void setSpdiCoverageLocationId(Long spdiCoverageLocationId) {
		this.spdiCoverageLocationId = spdiCoverageLocationId;
	}

	public Long getSpdiServiceId() {
		return spdiServiceId;
	}

	public void setSpdiServiceId(Long spdiServiceId) {
		this.spdiServiceId = spdiServiceId;
	}

	public Long getSpdiLocationTypeId() {
		return spdiLocationTypeId;
	}

	public void setSpdiLocationTypeId(Long spdiLocationTypeId) {
		this.spdiLocationTypeId = spdiLocationTypeId;
	}

	public Long getSpdiLocationId() {
		return spdiLocationId;
	}

	public void setSpdiLocationId(Long spdiLocationId) {
		this.spdiLocationId = spdiLocationId;
	}

	public Long getSpdiServiceStatus() {
		return spdiServiceStatus;
	}

	public void setSpdiServiceStatus(Long spdiServiceStatus) {
		this.spdiServiceStatus = spdiServiceStatus;
	}

	public Long getSpdiContactPersonAddDist() {
		return spdiContactPersonAddDist;
	}

	public void setSpdiContactPersonAddDist(Long spdiContactPersonAddDist) {
		this.spdiContactPersonAddDist = spdiContactPersonAddDist;
	}

	public Long getSpdiContactPersonAddState() {
		return spdiContactPersonAddState;
	}

	public void setSpdiContactPersonAddState(Long spdiContactPersonAddState) {
		this.spdiContactPersonAddState = spdiContactPersonAddState;
	}

	public String getSpdvContactPersonAddLine1() {
		return spdvContactPersonAddLine1;
	}

	public void setSpdvContactPersonAddLine1(String spdvContactPersonAddLine1) {
		this.spdvContactPersonAddLine1 = spdvContactPersonAddLine1;
	}

	public String getSpdvContactPersonAddLine2() {
		return spdvContactPersonAddLine2;
	}

	public void setSpdvContactPersonAddLine2(String spdvContactPersonAddLine2) {
		this.spdvContactPersonAddLine2 = spdvContactPersonAddLine2;
	}

	public String getSpdvContactPersonAddLine3() {
		return spdvContactPersonAddLine3;
	}

	public void setSpdvContactPersonAddLine3(String spdvContactPersonAddLine3) {
		this.spdvContactPersonAddLine3 = spdvContactPersonAddLine3;
	}

	public String getSpdvContactPersonAddPin() {
		return spdvContactPersonAddPin;
	}

	public void setSpdvContactPersonAddPin(String spdvContactPersonAddPin) {
		this.spdvContactPersonAddPin = spdvContactPersonAddPin;
	}

	public String getSpdvContactPersonEmail() {
		return spdvContactPersonEmail;
	}

	public void setSpdvContactPersonEmail(String spdvContactPersonEmail) {
		this.spdvContactPersonEmail = spdvContactPersonEmail;
	}

	public String getSpdvContactPersonName() {
		return spdvContactPersonName;
	}

	public void setSpdvContactPersonName(String spdvContactPersonName) {
		this.spdvContactPersonName = spdvContactPersonName;
	}

	public String getSpdvContactPersonPhone() {
		return spdvContactPersonPhone;
	}

	public void setSpdvContactPersonPhone(String spdvContactPersonPhone) {
		this.spdvContactPersonPhone = spdvContactPersonPhone;
	}

	public Long getSpdiAdoptFlag() {
		return spdiAdoptFlag;
	}

	public void setSpdiAdoptFlag(Long spdiAdoptFlag) {
		this.spdiAdoptFlag = spdiAdoptFlag;
	}

	public String getSpdvLocationLevel() {
		return spdvLocationLevel;
	}

	public void setSpdvLocationLevel(String spdvLocationLevel) {
		this.spdvLocationLevel = spdvLocationLevel;
	}

	public String getSpdvLocationLevelId() {
		return spdvLocationLevelId;
	}

	public void setSpdvLocationLevelId(String spdvLocationLevelId) {
		this.spdvLocationLevelId = spdvLocationLevelId;
	}

	public Long getSpdiDesigOfcrUserid() {
		return spdiDesigOfcrUserid;
	}

	public void setSpdiDesigOfcrUserid(Long spdiDesigOfcrUserid) {
		this.spdiDesigOfcrUserid = spdiDesigOfcrUserid;
	}

	public Date getSpdvAdoptOn() {
		return spdvAdoptOn;
	}

	public void setSpdvAdoptOn(Date spdvAdoptOn) {
		this.spdvAdoptOn = spdvAdoptOn;
	}

	public Date getSpdvActivatedOn() {
		return spdvActivatedOn;
	}

	public void setSpdvActivatedOn(Date spdvActivatedOn) {
		this.spdvActivatedOn = spdvActivatedOn;
	}

	public Date getSpdvDeactivatedOn() {
		return spdvDeactivatedOn;
	}

	public void setSpdvDeactivatedOn(Date spdvDeactivatedOn) {
		this.spdvDeactivatedOn = spdvDeactivatedOn;
	}

	public Long getSpdiStateId() {
		return spdiStateId;
	}

	public void setSpdiStateId(Long spdiStateId) {
		this.spdiStateId = spdiStateId;
	}

	public Character getSpdiUpdatedFlag() {
		return spdiUpdatedFlag;
	}

	public void setSpdiUpdatedFlag(Character spdiUpdatedFlag) {
		this.spdiUpdatedFlag = spdiUpdatedFlag;
	}

	public Long getAssignByUserId() {
		return assignByUserId;
	}

	public void setAssignByUserId(Long assignByUserId) {
		this.assignByUserId = assignByUserId;
	}

	public Character getLdType() {
		return ldType;
	}

	public void setLdType(Character ldType) {
		this.ldType = ldType;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "SpeServiceCoverageDetails [spdiCoverageLocationId=" + spdiCoverageLocationId + ", spdiServiceId="
				+ spdiServiceId + ", spdiLocationTypeId=" + spdiLocationTypeId + ", spdiLocationId=" + spdiLocationId
				+ ", spdiServiceStatus=" + spdiServiceStatus + ", spdiContactPersonAddDist=" + spdiContactPersonAddDist
				+ ", spdiContactPersonAddState=" + spdiContactPersonAddState + ", spdvContactPersonAddLine1="
				+ spdvContactPersonAddLine1 + ", spdvContactPersonAddLine2=" + spdvContactPersonAddLine2
				+ ", spdvContactPersonAddLine3=" + spdvContactPersonAddLine3 + ", spdvContactPersonAddPin="
				+ spdvContactPersonAddPin + ", spdvContactPersonEmail=" + spdvContactPersonEmail
				+ ", spdvContactPersonName=" + spdvContactPersonName + ", spdvContactPersonPhone="
				+ spdvContactPersonPhone + ", spdiAdoptFlag=" + spdiAdoptFlag + ", spdvLocationLevel="
				+ spdvLocationLevel + ", spdvLocationLevelId=" + spdvLocationLevelId + ", spdiDesigOfcrUserid="
				+ spdiDesigOfcrUserid + ", spdvAdoptOn=" + spdvAdoptOn + ", spdvActivatedOn=" + spdvActivatedOn
				+ ", spdvDeactivatedOn=" + spdvDeactivatedOn + ", spdiStateId=" + spdiStateId + ", spdiUpdatedFlag="
				+ spdiUpdatedFlag + ", assignByUserId=" + assignByUserId + ", ldType=" + ldType + ", versionNo="
				+ versionNo + "]";
	}
	
}
