package com.saral.reporting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@Table(name = "serviceplustransaction",schema="saral1")
public class ServicePlusTransaction {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "servicecode")
	private Long servicecode;
	
	@Column(name = "applicationrefno")
	private String applicationrefno;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "amount")
	private Long amount;
	
	@Column(name = "payment_mode_name")
	private String paymentModeName;
	
	@Column(name = "registrationid")
	private String registrationid;
	
	@Column(name = "appliedby")
	private String appliedby;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "submission_date")
	@Temporal(TemporalType.DATE)
	private Date submissionDate;
	
	@Column(name = "deptcode")
	private Long deptcode;
	
	@Column(name = "entrydate")
	@Temporal(TemporalType.DATE)
	private Date entrydate;
	
	@Column(name = "token_no")
	private String tokenNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServicecode() {
		return servicecode;
	}

	public void setServicecode(Long servicecode) {
		this.servicecode = servicecode;
	}

	public String getApplicationrefno() {
		return applicationrefno;
	}

	public void setApplicationrefno(String applicationrefno) {
		this.applicationrefno = applicationrefno;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPaymentModeName() {
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}

	public String getRegistrationid() {
		return registrationid;
	}

	public void setRegistrationid(String registrationid) {
		this.registrationid = registrationid;
	}

	public String getAppliedby() {
		return appliedby;
	}

	public void setAppliedby(String appliedby) {
		this.appliedby = appliedby;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Long getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(Long deptcode) {
		this.deptcode = deptcode;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	@Override
	public String toString() {
		return "ServicePlusTransaction [id=" + id + ", servicecode=" + servicecode + ", applicationrefno="
				+ applicationrefno + ", date=" + date + ", amount=" + amount + ", paymentModeName=" + paymentModeName
				+ ", registrationid=" + registrationid + ", appliedby=" + appliedby + ", userid=" + userid
				+ ", submissionDate=" + submissionDate + ", deptcode=" + deptcode + ", entrydate=" + entrydate
				+ ", tokenNo=" + tokenNo + "]";
	}

}
