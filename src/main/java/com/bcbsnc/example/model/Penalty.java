package com.bcbsnc.example.model;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.bcbsnc.example.manager.WorksheetA;
import com.bcbsnc.example.util.FilingStatusEnum;

public class Penalty {

    private DateTime effectDt;
    private DateTime expireDt;
	private BigDecimal pctIncomeQnt;
	private BigDecimal perAdultAmt;
	private BigDecimal perChildAmt;
	private BigDecimal perHouseholdMaxAmt;
	private BigDecimal bronzePremiumAmt;
	private BigDecimal filingStatusAmt;
	private FilingStatusEnum filingStatus;
	private BigDecimal penaltyAmt;
	private WorksheetA worksheetA;
	private WorksheetA worksheetB;
	private boolean useWorksheetA;
	
	
	public Penalty() {
		super();
		this.useWorksheetA = false;
		this.useWorksheetB = false;
	}
	public WorksheetA getWorksheetA() {
		return worksheetA;
	}
	public WorksheetA getWorksheetB() {
		return worksheetB;
	}
	public boolean isUseWorksheetA() {
		return useWorksheetA;
	}
	public boolean isUseWorksheetB() {
		return useWorksheetB;
	}
	public void setWorksheetA(WorksheetA worksheetA) {
		this.worksheetA = worksheetA;
	}
	public void setWorksheetB(WorksheetA worksheetB) {
		this.worksheetB = worksheetB;
	}
	public void setUseWorksheetA(boolean useWorksheetA) {
		this.useWorksheetA = useWorksheetA;
	}
	public void setUseWorksheetB(boolean useWorksheetB) {
		this.useWorksheetB = useWorksheetB;
	}
	private boolean useWorksheetB;
	
	public DateTime getEffectDt() {
		return effectDt;
	}
	public DateTime getExpireDt() {
		return expireDt;
	}
	public BigDecimal getPctIncomeQnt() {
		return pctIncomeQnt;
	}
	public BigDecimal getPerAdultAmt() {
		return perAdultAmt;
	}
	public BigDecimal getPerChildAmt() {
		return perChildAmt;
	}
	public BigDecimal getPerHouseholdMaxAmt() {
		return perHouseholdMaxAmt;
	}
	public BigDecimal getBronzePremiumAmt() {
		return bronzePremiumAmt;
	}
	public BigDecimal getFilingStatusAmt() {
		return filingStatusAmt;
	}
	public FilingStatusEnum getFilingStatus() {
		return filingStatus;
	}
	public BigDecimal getPenaltyAmt() {
		return penaltyAmt;
	}
	public void setEffectDt(DateTime effectDt) {
		this.effectDt = effectDt;
	}
	public void setExpireDt(DateTime expireDt) {
		this.expireDt = expireDt;
	}
	public void setPctIncomeQnt(BigDecimal pctIncomeQnt) {
		this.pctIncomeQnt = pctIncomeQnt;
	}
	public void setPerAdultAmt(BigDecimal perAdultAmt) {
		this.perAdultAmt = perAdultAmt;
	}
	public void setPerChildAmt(BigDecimal perChildAmt) {
		this.perChildAmt = perChildAmt;
	}
	public void setPerHouseholdMaxAmt(BigDecimal perHouseholdMaxAmt) {
		this.perHouseholdMaxAmt = perHouseholdMaxAmt;
	}
	public void setBronzePremiumAmt(BigDecimal bronzePremiumAmt) {
		this.bronzePremiumAmt = bronzePremiumAmt;
	}
	public void setFilingStatusAmt(BigDecimal filingStatusAmt) {
		this.filingStatusAmt = filingStatusAmt;
	}
	public void setFilingStatus(FilingStatusEnum filingStatus) {
		this.filingStatus = filingStatus;
	}
	public void setPenaltyAmt(BigDecimal penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}
	@Override
	public String toString() {
		return "Penalty [effectDt=" + effectDt + ", expireDt=" + expireDt + ", pctIncomeQnt=" + pctIncomeQnt
				+ ", perAdultAmt=" + perAdultAmt + ", perChildAmt=" + perChildAmt + ", perHouseholdMaxAmt="
				+ perHouseholdMaxAmt + ", bronzePremiumAmt=" + bronzePremiumAmt + ", filingStatusAmt=" + filingStatusAmt
				+ ", filingStatus=" + filingStatus + ", penaltyAmt=" + penaltyAmt + "]";
	}


	
	
	
}
