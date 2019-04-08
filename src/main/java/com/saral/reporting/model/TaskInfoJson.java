package com.saral.reporting.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tbl_taskinfo_json", schema = "saral1")
public class TaskInfoJson {

	@Id
	@GeneratedValue(generator = "tbl_taskinfo_json_id_seq")
	@SequenceGenerator(name = "tbl_taskinfo_json_id_seq", sequenceName = "tbl_taskinfo_json_id_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "a_id")
	private Long aid;

	@Column(name = "appl_id")
	private Long applId;
	@Column(name = "process_id")
	private Long currentProcessId;
	@Column(name = "task_info")
	@Type(type = "JsonDataUserType")
	private Map<String, Object> taskInfo;

	@Column(name = "official_form_attributes")
	@Type(type = "JsonDataUserType")
	private Map<String, Object> officialFormAttributes;

	@Column(name = "applicant_task_data")
	@Type(type = "JsonDataUserType")
	private Map<String, Object> applicantTaskData;

	@Column(name = "user_detail")
	@Type(type = "JsonDataUserType")
	private Map<String, Object> userDetail;

	@Column(name = "combinedtask_json")
	@Type(type = "JsonDataUserType")
	private Map<String, Object> combinedtaskJson;
	

	@Column(name = "task_id")
	
	private Long taskId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Map<String, Object> getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(Map<String, Object> taskInfo) {
		this.taskInfo = taskInfo;
	}

	public Map<String, Object> getOfficialFormAttributes() {
		return officialFormAttributes;
	}

	public void setOfficialFormAttributes(Map<String, Object> officialFormAttributes) {
		this.officialFormAttributes = officialFormAttributes;
	}

	public Map<String, Object> getApplicantTaskData() {
		return applicantTaskData;
	}

	public void setApplicantTaskData(Map<String, Object> applicantTaskData) {
		this.applicantTaskData = applicantTaskData;
	}

	public Map<String, Object> getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(Map<String, Object> userDetail) {
		this.userDetail = userDetail;
	}

	public Map<String, Object> getCombinedtaskJson() {
		return combinedtaskJson;
	}

	public void setCombinedtaskJson(Map<String, Object> combinedtaskJson) {
		this.combinedtaskJson = combinedtaskJson;
	}

	public Long getCurrentProcessId() {
		return currentProcessId;
	}

	public void setCurrentProcessId(Long currentProcessId) {
		this.currentProcessId = currentProcessId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
