package com.saral.reporting.utils;

public class DeparmentAdminCountUtills {
	
	
	 private String departmentName;
	  private Long   cnt;

	 public DeparmentAdminCountUtills(String departmentName, Long cnt) {
	    this.departmentName = departmentName;
	    this.cnt  = cnt;
	  }

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getCnt() {
		return cnt;
	}

	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "DeparmentAdminCountUtills [departmentId=" + departmentName + ", cnt=" + cnt + "]";
	}
	  
	  
	

}
