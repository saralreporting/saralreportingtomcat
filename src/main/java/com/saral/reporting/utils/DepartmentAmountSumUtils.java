package com.saral.reporting.utils;

import java.math.BigInteger;

public class DepartmentAmountSumUtils {

	private String serviceName;
	private BigInteger sum;

	
	
	public DepartmentAmountSumUtils(String serviceName, BigInteger sum) {
		super();
		this.serviceName = serviceName;
		this.sum = sum;
	}

	public DepartmentAmountSumUtils() {
		// TODO Auto-generated constructor stub
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigInteger getSum() {
		return sum;
	}

	public void setSum(BigInteger bigInteger) {
		this.sum = bigInteger;
	}

	@Override
	public String toString() {
		return "DepartmentAmountSumUtils [serviceName=" + serviceName + ", sum=" + sum + "]";
	}

}
