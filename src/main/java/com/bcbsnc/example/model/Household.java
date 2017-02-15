package com.bcbsnc.example.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Household {
	
	@NotNull private String householdId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
    @NotNull private DateTime requestedEffectiveDate;
    
    @NotNull private BigDecimal incomeAmt;
    private boolean penaltyExempt;
    private BigDecimal penaltyAmt;
    private String filingStatusCode;
    private String filingStatusDesc;
    @NotNull protected List<Applicant> applicants;

    public String getHouseholdId() {
		return householdId;
	}

	public DateTime getRequestedEffectiveDate() {
		return requestedEffectiveDate;
	}

	public BigDecimal getIncomeAmt() {
		return incomeAmt;
	}
    
    
	public boolean isPenaltyExempt() {
		return penaltyExempt;
	}

	public BigDecimal getPenaltyAmt() {
		return penaltyAmt;
	}

	public String getFilingStatusCode() {
		return filingStatusCode;
	}

	public String getFilingStatusDesc() {
		return filingStatusDesc;
	}

	public List<Applicant> getApplicants() {
		return applicants;
	}

	public void setHouseholdId(String householdId) {
		this.householdId = householdId;
	}

	public void setIncomeAmt(BigDecimal incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public void setRequestedEffectiveDate(DateTime requestedEffectiveDate) {
		this.requestedEffectiveDate = requestedEffectiveDate;
	}

	public void setPenaltyExempt(boolean penaltyExempt) {
		this.penaltyExempt = penaltyExempt;
	}

	public void setPenaltyAmt(BigDecimal penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}

	public void setFilingStatusCode(String filingStatusCode) {
		this.filingStatusCode = filingStatusCode;
	}

	public void setFilingStatusDesc(String filingStatusDesc) {
		this.filingStatusDesc = filingStatusDesc;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}


	@Override
	public String toString() {
		return "Household [householdId=" + householdId + ", requestedEffectiveDate=" + requestedEffectiveDate
				+ ", incomeAmt=" + incomeAmt + ", penaltyExempt=" + penaltyExempt + ", penaltyAmt=" + penaltyAmt
				+ ", filingStatusCode=" + filingStatusCode + ", filingStatusDesc=" + filingStatusDesc + ", applicants="
				+ applicants + "]";
	}
	
	
}
