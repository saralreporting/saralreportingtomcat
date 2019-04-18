package com.saral.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "hr_org_located_at_levels", schema = "lgd", catalog = "lgd")
public class HrOrgLocatedAtLevels {

	@Id
	@Column(name = "org_located_level_code")
	private Long orgLocatedLevelCode;

	@Column(name = "olc")
	private Long olc;

	@Column(name = "located_at_level")
	private Long locatedAtLevel;

	@Column(name = "isactive")
	private Boolean isactive;

	@Column(name = "parent_org_located_level_code")
	private Long parentOrgLocatedLevelCode;

	@Column(name = "org_level_specific_name")
	private String orgLevelSpecificName;

	@Column(name = "org_unt_done")
	private Boolean orgUntDone;

	@Column(name = "sort_order")
	private Long sortOrder;

	@Column(name = "org_located_level_version")
	private Long orgLocatedLevelVersion;

	public Long getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Long orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	public Long getOlc() {
		return olc;
	}

	public void setOlc(Long olc) {
		this.olc = olc;
	}

	public Long getLocatedAtLevel() {
		return locatedAtLevel;
	}

	public void setLocatedAtLevel(Long locatedAtLevel) {
		this.locatedAtLevel = locatedAtLevel;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Long getParentOrgLocatedLevelCode() {
		return parentOrgLocatedLevelCode;
	}

	public void setParentOrgLocatedLevelCode(Long parentOrgLocatedLevelCode) {
		this.parentOrgLocatedLevelCode = parentOrgLocatedLevelCode;
	}

	public String getOrgLevelSpecificName() {
		return orgLevelSpecificName;
	}

	public void setOrgLevelSpecificName(String orgLevelSpecificName) {
		this.orgLevelSpecificName = orgLevelSpecificName;
	}

	public Boolean getOrgUntDone() {
		return orgUntDone;
	}

	public void setOrgUntDone(Boolean orgUntDone) {
		this.orgUntDone = orgUntDone;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getOrgLocatedLevelVersion() {
		return orgLocatedLevelVersion;
	}

	public void setOrgLocatedLevelVersion(Long orgLocatedLevelVersion) {
		this.orgLocatedLevelVersion = orgLocatedLevelVersion;
	}

	@Override
	public String toString() {
		return "HrOrgLocatedAtLevels [orgLocatedLevelCode=" + orgLocatedLevelCode + ", olc=" + olc + ", locatedAtLevel="
				+ locatedAtLevel + ", isactive=" + isactive + ", parentOrgLocatedLevelCode=" + parentOrgLocatedLevelCode
				+ ", orgLevelSpecificName=" + orgLevelSpecificName + ", orgUntDone=" + orgUntDone + ", sortOrder="
				+ sortOrder + ", orgLocatedLevelVersion=" + orgLocatedLevelVersion + "]";
	}

}
