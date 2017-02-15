package com.bcbsnc.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PnltCalcPK implements Serializable {

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EFFECT_DT")	
    private java.util.Date effectDt;
	
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EXPIRE_DT")
    private java.util.Date expireDt;
	
    @Column(name="PCT_INCOME_QNT")
	private java.math.BigDecimal pctIncomeQnt;
	
	@Column(name="PER_ADULT_AMT")
	private java.math.BigDecimal perAdultAmt;
	
	@Column(name="PER_CHILD_AMT")
	private java.math.BigDecimal perChildAmt;
	
	@Column(name="PER_HOUSEHOLD_MAX")
	private java.math.BigDecimal perHouseholdMax;

	
	public PnltCalcPK() {
	}


	public java.util.Date getEffectDt() {
		return effectDt;
	}


	public java.util.Date getExpireDt() {
		return expireDt;
	}


	public java.math.BigDecimal getPctIncomeQnt() {
		return pctIncomeQnt;
	}


	public java.math.BigDecimal getPerAdultAmt() {
		return perAdultAmt;
	}


	public java.math.BigDecimal getPerChildAmt() {
		return perChildAmt;
	}


	public java.math.BigDecimal getPerHouseholdMax() {
		return perHouseholdMax;
	}


	public void setEffectDt(java.util.Date effectDt) {
		this.effectDt = effectDt;
	}


	public void setExpireDt(java.util.Date expireDt) {
		this.expireDt = expireDt;
	}


	public void setPctIncomeQnt(java.math.BigDecimal pctIncomeQnt) {
		this.pctIncomeQnt = pctIncomeQnt;
	}


	public void setPerAdultAmt(java.math.BigDecimal perAdultAmt) {
		this.perAdultAmt = perAdultAmt;
	}


	public void setPerChildAmt(java.math.BigDecimal perChildAmt) {
		this.perChildAmt = perChildAmt;
	}


	public void setPerHouseholdMax(java.math.BigDecimal perHouseholdMax) {
		this.perHouseholdMax = perHouseholdMax;
	}

	@Override
	public String toString() {
		return "PnltCalcPK [effectDt=" + effectDt + ", expireDt=" + expireDt + ", pctIncomeQnt=" + pctIncomeQnt
				+ ", perAdultAmt=" + perAdultAmt + ", perChildAmt=" + perChildAmt + ", perHouseholdMax="
				+ perHouseholdMax + "]";
	}
	

}
