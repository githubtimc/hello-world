package com.bcbsnc.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Embeddable
public class PlanPK implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Column(name="PLAN_ID")
//	private String planId;
//
//	@Column(name="PLAN_VARNT_ID")
//	private int planVarntId;
//
//    @Temporal(TemporalType.TIMESTAMP)
//	@Column(name="EFFECT_DT")
//	private java.util.Date effectDt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//	@Column(name="EXPIRE_DT")
//	private java.util.Date expireDt;
//
//	public PlanPK() {
//		super();
//	}
//
//	public String getPlanId() {
//		return planId;
//	}
//
//	public void setPlanId(String planId) {
//		this.planId = planId;
//	}
//
//	public int getPlanVarntId() {
//		return planVarntId;
//	}
//
//	public void setPlanVarntId(int planVarntId) {
//		this.planVarntId = planVarntId;
//	}
//
//	public java.util.Date getEffectDt() {
//		return effectDt;
//	}
//
//	public void setEffectDt(java.util.Date effectDt) {
//		this.effectDt = effectDt;
//	}
//
//	public java.util.Date getExpireDt() {
//		return expireDt;
//	}
//
//	public void setExpireDt(java.util.Date expireDt) {
//		this.expireDt = expireDt;
//	}
//
//	@Override
//	public String toString() {
//		return "PlanPK [planId=" + planId + ", planVarntId=" + planVarntId + ", effectDt=" + effectDt + ", expireDt="
//				+ expireDt + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((effectDt == null) ? 0 : effectDt.hashCode());
//		result = prime * result + ((expireDt == null) ? 0 : expireDt.hashCode());
//		result = prime * result + ((planId == null) ? 0 : planId.hashCode());
//		result = prime * result + (int) (planVarntId ^ (planVarntId >>> 32));
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		PlanPK other = (PlanPK) obj;
//		if (effectDt == null) {
//			if (other.effectDt != null)
//				return false;
//		} 
//		else if (!effectDt.equals(other.effectDt))
//			return false;
//		if (expireDt == null) {
//			if (other.expireDt != null)
//				return false;
//		} else if (!expireDt.equals(other.expireDt))
//			return false;
//		if (planId == null) {
//			if (other.planId != null)
//				return false;
//		} else if (!planId.equals(other.planId))
//			return false;
//		if (planVarntId != other.planVarntId)
//			return false;
//		return true;
//	}
	

}
