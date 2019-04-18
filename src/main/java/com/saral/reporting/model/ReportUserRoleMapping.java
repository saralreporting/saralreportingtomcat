package com.saral.reporting.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reportM_User_RoleMapping")
public class ReportUserRoleMapping {
	
	@Id
	@GeneratedValue(generator = "user_Data_Role_generator")
	@SequenceGenerator(name = "user_Data_Role_generator", sequenceName = "user_Data_Role_generator", initialValue = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Role_id", insertable = true, updatable = false, nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private ReportUserMaster reportUserMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReportUserMaster getReportUserMaster() {
		return reportUserMaster;
	}

	public void setReportUserMaster(ReportUserMaster reportUserMaster) {
		this.reportUserMaster = reportUserMaster;
	}

}
