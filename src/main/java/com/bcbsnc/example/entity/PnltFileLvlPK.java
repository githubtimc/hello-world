package com.bcbsnc.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PnltFileLvlPK implements Serializable {

	@Column(name="FILE_STATUS_CD")
	private int filingStatusCode;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EFFECT_DT")	
	private java.util.Date effectDt;
	
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EXPIRE_DT")	
	private java.util.Date expireDt;
	
    @Column(name="MIN_INCOME_AMT")
	private BigDecimal minIncomeAmt;

	public PnltFileLvlPK() {
		super();
	}

	public int getFilingStatusCode() {
		return filingStatusCode;
	}

	public java.util.Date getEffectDt() {
		return effectDt;
	}

	public java.util.Date getExpireDt() {
		return expireDt;
	}

	public BigDecimal getMinIncomeAmt() {
		return minIncomeAmt;
	}

	public void setFilingStatusCode(int filingStatusCode) {
		this.filingStatusCode = filingStatusCode;
	}

	public void setEffectDt(java.util.Date effectDt) {
		this.effectDt = effectDt;
	}

	public void setExpireDt(java.util.Date expireDt) {
		this.expireDt = expireDt;
	}

	public void setMinIncomeAmt(BigDecimal minIncomeAmt) {
		this.minIncomeAmt = minIncomeAmt;
	}
    
	
}
