package com.bcbsnc.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PnltBronzePlanPremPK implements Serializable {

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EFFECT_DT")	
    private java.util.Date effectDt;
	
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EXPIRE_DT")
    private java.util.Date expireDt;
	
	@Column(name="FMLY_QNT_FROM")
	private java.math.BigDecimal fmlyQntFrom;
	
	@Column(name="FMLY_QNT_TO")
	private java.math.BigDecimal fmlyQntTo;

    @Column(name="FMLY_PREM_AMT")
	private java.math.BigDecimal fmlyPremAmt;
	
    @Column(name="WORKSHEETA_MULTIPLIER")
	private java.math.BigDecimal worksheetAMultiplier;

	public java.util.Date getEffectDt() {
		return effectDt;
	}

	public java.util.Date getExpireDt() {
		return expireDt;
	}

	public java.math.BigDecimal getFmlyQntFrom() {
		return fmlyQntFrom;
	}

	public java.math.BigDecimal getFmlyQntTo() {
		return fmlyQntTo;
	}

	public java.math.BigDecimal getFmlyPremAmt() {
		return fmlyPremAmt;
	}

	public java.math.BigDecimal getWorksheetAMultiplier() {
		return worksheetAMultiplier;
	}

	public void setEffectDt(java.util.Date effectDt) {
		this.effectDt = effectDt;
	}

	public void setExpireDt(java.util.Date expireDt) {
		this.expireDt = expireDt;
	}

	public void setFmlyQntFrom(java.math.BigDecimal fmlyQntFrom) {
		this.fmlyQntFrom = fmlyQntFrom;
	}

	public void setFmlyQntTo(java.math.BigDecimal fmlyQntTo) {
		this.fmlyQntTo = fmlyQntTo;
	}

	public void setFmlyPremAmt(java.math.BigDecimal fmlyPremAmt) {
		this.fmlyPremAmt = fmlyPremAmt;
	}

	public void setWorksheetAMultiplier(java.math.BigDecimal worksheetAMultiplier) {
		this.worksheetAMultiplier = worksheetAMultiplier;
	}

	@Override
	public String toString() {
		return "PnltBronzePlanPremPK [effectDt=" + effectDt + ", expireDt=" + expireDt + ", fmlyQntFrom=" + fmlyQntFrom
				+ ", fmlyQntTo=" + fmlyQntTo + ", fmlyPremAmt=" + fmlyPremAmt + ", worksheetAMultiplier="
				+ worksheetAMultiplier + "]";
	}
	
}
