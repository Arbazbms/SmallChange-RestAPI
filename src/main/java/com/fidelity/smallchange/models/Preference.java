package com.fidelity.smallchange.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Preference {
	  private String clientId;
      private String investmentPurpose;
      private String riskTolerance;
      private String incomeCategory;
      private String lengthOfInvestment;
      
	public Preference(String clientId, String investmentPurpose, String riskTolerance, String incomeCategory,
			String lengthOfInvestment) {
		
		this.clientId = clientId;
		this.investmentPurpose = investmentPurpose;
		this.riskTolerance = riskTolerance;
		this.incomeCategory = incomeCategory;
		this.lengthOfInvestment = lengthOfInvestment;
	}
	public Preference() {}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getInvestmentPurpose() {
		return investmentPurpose;
	}
	public void setInvestmentPurpose(String investmentPurpose) {
		this.investmentPurpose = investmentPurpose;
	}
	public String getRiskTolerance() {
		return riskTolerance;
	}
	public void setRiskTolerance(String riskTolerance) {
		this.riskTolerance = riskTolerance;
	}
	public String getIncomeCategory() {
		return incomeCategory;
	}
	public void setIncomeCategory(String incomeCategory) {
		this.incomeCategory = incomeCategory;
	}
	public String getLengthOfInvestment() {
		return lengthOfInvestment;
	}
	public void setLengthOfInvestment(String lengthOfInvestment) {
		this.lengthOfInvestment = lengthOfInvestment;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientId, incomeCategory, investmentPurpose, lengthOfInvestment, riskTolerance);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preference other = (Preference) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(incomeCategory, other.incomeCategory)
				&& Objects.equals(investmentPurpose, other.investmentPurpose)
				&& Objects.equals(lengthOfInvestment, other.lengthOfInvestment)
				&& Objects.equals(riskTolerance, other.riskTolerance);
	}
	@Override
	public String toString() {
		return "Preference [clientId=" + clientId + ", investmentPurpose=" + investmentPurpose + ", riskTolerance="
				+ riskTolerance + ", incomeCategory=" + incomeCategory + ", lengthOfInvestment=" + lengthOfInvestment
				+ "]";
	}
	
	
      
      
}
