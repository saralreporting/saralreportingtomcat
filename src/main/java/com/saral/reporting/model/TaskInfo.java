package com.saral.reporting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="tbl_taskinfo",schema="saral1")
public class TaskInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "tbl_taskinfo_a_id_seq", sequenceName = "tbl_taskinfo_a_id_seq", initialValue = 1)
	@Column(name = "a_id")
	private Long aid;
	
	@Column(name = "appl_id")
	private Long applId;
	
	@Column(name = "task_id")
	private Long taskId;
	
	@Column(name = "task_name")
	private String taskName;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "task_type")
	private String taskType;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "payment_ref_no")
	private String paymentRefNo;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "current_process_id")
	private Long currentProcessId;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "service_id")
	private Long serviceId;
	
	@Column(name = "received_time")
	private Date receivedTime;
	
	@Column(name = "executed_time")
	private Date executedTime;
	
	@Column(name = "user_detail")
	private String userDetail;
	
	@Column(name = "pull_user_id")
	private Long pullUserId;
	
	@Column(name = "department_level")
	private String departmentLevel;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "location_name")
	private String locationName;
	
	@Column(name = "p_user_name")
	private String pUserName;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentRefNo() {
		return paymentRefNo;
	}

	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getCurrentProcessId() {
		return currentProcessId;
	}

	public void setCurrentProcessId(Long currentProcessId) {
		this.currentProcessId = currentProcessId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public Date getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(Date executedTime) {
		this.executedTime = executedTime;
	}

	public String getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
	}

	public Long getPullUserId() {
		return pullUserId;
	}

	public void setPullUserId(Long pullUserId) {
		this.pullUserId = pullUserId;
	}

	public String getDepartmentLevel() {
		return departmentLevel;
	}

	public void setDepartmentLevel(String departmentLevel) {
		this.departmentLevel = departmentLevel;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getpUserName() {
		return pUserName;
	}

	public void setpUserName(String pUserName) {
		this.pUserName = pUserName;
	}

	@Override
	public String toString() {
		return "TaskInfo [aid=" + aid + ", applId=" + applId + ", taskId=" + taskId + ", taskName=" + taskName
				+ ", userName=" + userName + ", taskType=" + taskType + ", paymentMode=" + paymentMode
				+ ", paymentRefNo=" + paymentRefNo + ", paymentDate=" + paymentDate + ", amount=" + amount
				+ ", currentProcessId=" + currentProcessId + ", remarks=" + remarks + ", serviceId=" + serviceId
				+ ", receivedTime=" + receivedTime + ", executedTime=" + executedTime + ", userDetail=" + userDetail
				+ ", pullUserId=" + pullUserId + ", departmentLevel=" + departmentLevel + ", designation=" + designation
				+ ", locationName=" + locationName + ", pUserName=" + pUserName + "]";
	}

	
	

}
