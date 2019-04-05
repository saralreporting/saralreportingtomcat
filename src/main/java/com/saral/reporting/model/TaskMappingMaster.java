package com.saral.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taskmappingmaster", schema = "saral", catalog = "saral")
public class TaskMappingMaster {
	
	@Column(name = "service_id")
	private Long serviceId;
	
	@Column(name = "Service_name")
	private String serviceName;
	
	@Column(name = "version_no")
	private Long versionNo;
	
	@Column(name = "from_task_id")
	private Long fromTaskId;
	
	@Column(name = "from_task_name")
	private String fromTaskName;
	
	@Column(name = "to_task_id")
	private Long toTaskId;
	
	@Column(name = "to_task_name")
	private String toTaskName;
	
	@Id
	@Column(name = "id")
	private Long id;

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

	public Long getFromTaskId() {
		return fromTaskId;
	}

	public void setFromTaskId(Long fromTaskId) {
		this.fromTaskId = fromTaskId;
	}

	public String getFromTaskName() {
		return fromTaskName;
	}

	public void setFromTaskName(String fromTaskName) {
		this.fromTaskName = fromTaskName;
	}

	public Long getToTaskId() {
		return toTaskId;
	}

	public void setToTaskId(Long toTaskId) {
		this.toTaskId = toTaskId;
	}

	public String getToTaskName() {
		return toTaskName;
	}

	public void setToTaskName(String toTaskName) {
		this.toTaskName = toTaskName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TaskMappingMaster [serviceId=" + serviceId + ", serviceName=" + serviceName + ", versionNo=" + versionNo
				+ ", fromTaskId=" + fromTaskId + ", fromTaskName=" + fromTaskName + ", toTaskId=" + toTaskId
				+ ", toTaskName=" + toTaskName + ", id=" + id + "]";
	}
	
}
