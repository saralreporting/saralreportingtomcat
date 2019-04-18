package com.saral.reporting.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "reportM_User_Data")

public class ReportUserMaster {
	@Id
	@GeneratedValue(generator = "user_Data_generator")
	@SequenceGenerator(name = "user_Data_generator", sequenceName = "user_Data_generator", initialValue = 1)
	@Column(name = "UserDataId")
	private Long userDataId;

	
	@Column(name = "Sign_No")
	private String signNO;

	@Column(name = "Password")
	private String password;

	@Column(name = "Role_Id", columnDefinition = "smallint[]")
	@Type(type = "com.saral.reporting.hibernate.ShortArrayType")
	private List<Integer> roleId;

	@Column(name = "Role_Name", columnDefinition = "text[]")
	@Type(type = "com.saral.reporting.hibernate.StringArrayType")
	private List<String> roleName;

	@Column(name = "Designation")
	private String designation;

	@Column(name = "User_Detail")
	private String userDetail;

	@Column(name = "Mobilenumber")
	private String mobilenumber;
	 

	@Column(name = "Email")
	private String email;

	@Column(name = "Last_modfied_By")
	private String lastModfiedBy;

	@Column(name = "Last_Modified_Date")
	private Date lastModifiedDate;

	@Column(name = "State_Id")
	private int stateId;

	@Column(name = "Change_Password_Date")
	private Date changePasswordDate;

	@Column(name = "SMS_Send_Flag")
	private boolean smsSendFlag;

	@Column(name = "User_Flag")
	private boolean userFlag;

	@Column(name = "Last_Activated_Date")
	private Date lastActivatedDate;

	@Column(name = "Email_Send_Flag")
	private boolean emailSendFlag;


	@Transient
    private String retypePassword;
        // getters setters
	
	

@Column(name = "PasswordMD")
	private String securedPassword;
	

public String getRetypePassword() {
	return retypePassword;
}

public void setRetypePassword(String retypePassword) {
	this.retypePassword = retypePassword;
}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "reportUserMaster", targetEntity = ReportDomainMaster.class, cascade = { CascadeType.ALL })
	private List<ReportDomainMaster> reportDomainMaster;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "reportUserMaster", cascade = { CascadeType.ALL })
	private List<ReportUserRoleMapping> reportUserRoleMappingList;

	public List<ReportUserRoleMapping> getReportUserRoleMappingList() {
		return reportUserRoleMappingList;
	}

	public void setReportUserRoleMappingList(List<ReportUserRoleMapping> reportUserRoleMappingList) {
		this.reportUserRoleMappingList = reportUserRoleMappingList;
	}

	
	public String getSecuredPassword() {
		return securedPassword;
	}

	public void setSecuredPassword(String securedPassword) {
		this.securedPassword = securedPassword;
	}
	public Long getUserDataId() {
		return userDataId;
	}

	public void setUserDataId(Long userDataId) {
		this.userDataId = userDataId;
	}

	public String getSignNO() {
		return signNO;
	}

	public void setSignNO(String signNO) {
		this.signNO = signNO;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastModfiedBy() {
		return lastModfiedBy;
	}

	public void setLastModfiedBy(String lastModfiedBy) {
		this.lastModfiedBy = lastModfiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public Date getChangePasswordDate() {
		return changePasswordDate;
	}

	public void setChangePasswordDate(Date changePasswordDate) {
		this.changePasswordDate = changePasswordDate;
	}

	public boolean isSmsSendFlag() {
		return smsSendFlag;
	}

	public void setSmsSendFlag(boolean smsSendFlag) {
		this.smsSendFlag = smsSendFlag;
	}

	public boolean isUserFlag() {
		return userFlag;
	}

	public void setUserFlag(boolean userFlag) {
		this.userFlag = userFlag;
	}

	public Date getLastActivatedDate() {
		return lastActivatedDate;
	}

	public void setLastActivatedDate(Date lastActivatedDate) {
		this.lastActivatedDate = lastActivatedDate;
	}

	public boolean isEmailSendFlag() {
		return emailSendFlag;
	}

	public void setEmailSendFlag(boolean emailSendFlag) {
		this.emailSendFlag = emailSendFlag;
	}

	public List<ReportDomainMaster> getReportDomainMaster() {
		return reportDomainMaster;
	}

	public void setReportDomainMaster(List<ReportDomainMaster> reportDomainMaster) {
		this.reportDomainMaster = reportDomainMaster;
	}

	public List<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}


	public void addReportUserRoleMapping(ReportUserRoleMapping reportUserRoleMapping) {
		if (reportUserRoleMapping != null) {
			if (reportUserRoleMappingList == null) {
				reportUserRoleMappingList = new ArrayList<ReportUserRoleMapping>();
			}
			reportUserRoleMappingList.add(reportUserRoleMapping);
			reportUserRoleMapping.setReportUserMaster(this);
		}
	}

	@Override
	public String toString() {
		return "ReportUserMaster [userDataId=" + userDataId + ", signNO=" + signNO + ", password=" + password
				+ ", roleId=" + roleId + ", designation=" + designation + ", userDetail=" + userDetail
				+ ", mobilenumber=" + mobilenumber + ", email=" + email + ", lastModfiedBy=" + lastModfiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", stateId=" + stateId + ", changePasswordDate="
				+ changePasswordDate + ", smsSendFlag=" + smsSendFlag + ", userFlag=" + userFlag
				+ ", lastActivatedDate=" + lastActivatedDate + ", emailSendFlag=" + emailSendFlag
				+ ", reportDomainMaster=" + reportDomainMaster + ", reportUserRoleMappingList="
				+ reportUserRoleMappingList + ", getUserDataId()=" + getUserDataId() + ", getSignNO()=" + getSignNO()
				+ ", getPassword()=" + getPassword() + ", getRoleId()=" + getRoleId() + ", getDesignation()="
				+ getDesignation() + ", getUserDetail()=" + getUserDetail() + ", getMobilenumber()=" + getMobilenumber()
				+ ", getEmail()=" + getEmail() + ", getLastModfiedBy()=" + getLastModfiedBy()
				+ ", getLastModifiedDate()=" + getLastModifiedDate() + ", getStateId()=" + getStateId()
				+ ", getChangePasswordDate()=" + getChangePasswordDate() + ", isSmsSendFlag()=" + isSmsSendFlag()
				+ ", isUserFlag()=" + isUserFlag() + ", getLastActivatedDate()=" + getLastActivatedDate()
				+ ", isEmailSendFlag()=" + isEmailSendFlag() + ", getReportDomainMaster()=" + getReportDomainMaster()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
