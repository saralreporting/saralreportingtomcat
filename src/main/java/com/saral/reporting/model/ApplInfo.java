package com.saral.reporting.model;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity

@Table(name = "r_applinfo",schema="saral1")
public class ApplInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "r_app_generator", sequenceName = "r_app_generator_sequence", initialValue = 1)
	@Column(name = "aid")
	private Long aid;

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "service_id")
	private Long serviceId;

	@Column(name = "service_name")
	private String serviceName;

	@Column(name = "appl_id")
	private Long applId;

	@Column(name = "appl_ref_no")
	private String applRefNo;

	@Column(name = "no_of_attachment")
	private Long numberAttachment;

	@Column(name = "submission_mode")
	private String submmisionMode;

	@Column(name = "applied_by")
	private String appliedBy;

	@Column(name = "submission_location")
	private String submissionLocation;

	@Column(name = "submission_date")
	@Temporal(TemporalType.DATE)
	private Date submissionDate;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "payment_date")
	@Temporal(TemporalType.DATE)
	private Date payment_date;

	@Column(name = "reference_no")
	private String reference_no;

	@Column(name = "amount")
	private Long amount;

	@Column(name = "registration_id")
	private String registrationId;

	@Column(name = "base_service_id")
	private Long baseServiceId;

	@Column(name = "version_no")
	private Long versionNo;

	@Column(name = "sub_version")
	private Long subVersion;

	@Transient
	private String  view;
	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public String getApplRefNo() {
		return applRefNo;
	}

	public void setApplRefNo(String applRefNo) {
		this.applRefNo = applRefNo;
	}

	public Long getNumberAttachment() {
		return numberAttachment;
	}

	public void setNumberAttachment(Long numberAttachment) {
		this.numberAttachment = numberAttachment;
	}

	public String getSubmmisionMode() {
		return submmisionMode;
	}

	public void setSubmmisionMode(String submmisionMode) {
		this.submmisionMode = submmisionMode;
	}

	public String getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}

	public String getSubmissionLocation() {
		return submissionLocation;
	}

	public void setSubmissionLocation(String submissionLocation) {
		this.submissionLocation = submissionLocation;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public String getReference_no() {
		return reference_no;
	}

	public void setReference_no(String reference_no) {
		this.reference_no = reference_no;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public Long getBaseServiceId() {
		return baseServiceId;
	}

	public void setBaseServiceId(Long baseServiceId) {
		this.baseServiceId = baseServiceId;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public Long getSubVersion() {
		return subVersion;
	}

	public void setSubVersion(Long subVersion) {
		this.subVersion = subVersion;
	}
	

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}



	@Override
	public String toString() {
		return "ApplInfo [aid=" + aid + ", departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", serviceId=" + serviceId + ", serviceName=" + serviceName + ", applId=" + applId + ", applRefNo="
				+ applRefNo + ", numberAttachment=" + numberAttachment + ", submmisionMode=" + submmisionMode
				+ ", appliedBy=" + appliedBy + ", submissionLocation=" + submissionLocation + ", submissionDate="
				+ submissionDate + ", paymentMode=" + paymentMode + ", payment_date=" + payment_date + ", reference_no="
				+ reference_no + ", amount=" + amount + ", registrationId=" + registrationId + ", baseServiceId="
				+ baseServiceId + ", versionNo=" + versionNo + ", subVersion=" + subVersion + ", view=" + view + "]";
	}

	public Map<String, String> getColumnNames() {
		Map<String, String> list = new HashMap<>();
		for (Field field : this.getClass().getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				
				if ((!column.name().equals("department_id")) && (!column.name().equals("department_name"))) {
					list.put(column.name(), column.name());
				}
			}
		}
		list.remove("aid");
		list.remove("appl_id");
		return list;
	}

	public Map<String, Object> getColumnNamesWithPojoVariables() {
		Map<String, Object> list = new HashMap<>();
		for (Field field : ApplInfo.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);

			
			if (column != null) {
				if ((!column.name().equals("department_id")) && (!column.name().equals("department_name"))) {
					list.put(column.name(), field.getName());
				}

			}
		}
		list.remove("aid");
		list.remove("appl_id");
		return list;
	}
	
	public static String returnColumnPojoName(String col) {
		String s = "";
		for (Field field : ApplInfo.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);

			
			if (column.name().equalsIgnoreCase(col) ){
				s = (field.getName());
				}

			}
		return s;
		}
	}

