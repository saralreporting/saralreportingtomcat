package com.saral.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "taskmaster", schema = "lgd")
public class TaskMaster {

	@Id
	@GeneratedValue(generator = "taskmaster_id_seq")
	@SequenceGenerator(name = "taskmaster_id_seq", sequenceName = "taskmaster_id_seq", initialValue = 1)
	@Column(name = "tid")
	private Long id;

	@Column(name = "service_id")
	private Long serviceId;
	
	@Column(name = "Service_name")
	private String serviceName;
	
	@Column(name = "version_no")
	private Long versionNo;
	
	
	
	@Column(name = "task_name")
	private String taskName;
	
	@Column(name = "task_id")
	private Long taskId;
	

	
	@Column(name = "task_type_id")
	private Long taskTypeId;
	
	
	@Column(name = "task_type_name")
	private String taskTypeName;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getServiceId() {
		return serviceId;
	}


	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public Long getVersionNo() {
		return versionNo;
	}


	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public Long getTaskId() {
		return taskId;
	}


	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	public Long getTaskTypeId() {
		return taskTypeId;
	}


	public void setTaskTypeId(Long taskTypeId) {
		this.taskTypeId = taskTypeId;
	}


	public String getTaskType() {
		return taskTypeName;
	}


	public void setTaskType(String taskType) {
		this.taskTypeName = taskType;
	}


	@Override
	public String toString() {
		return "TaskMaster [id=" + id + ", serviceId=" + serviceId + ", serviceName=" + serviceName + ", versionNo="
				+ versionNo + ", taskName=" + taskName + ", taskId=" + taskId + ", taskTypeId=" + taskTypeId
				+ ", taskType=" + taskTypeName + "]";
	}
	



}
