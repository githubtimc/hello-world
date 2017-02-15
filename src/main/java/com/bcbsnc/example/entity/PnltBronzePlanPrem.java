package com.bcbsnc.example.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PNLT_BRONZE_PLAN_PREM")
public class PnltBronzePlanPrem implements Serializable {

	@EmbeddedId
	private PnltBronzePlanPremPK id;

	public PnltBronzePlanPremPK getId() {
		return id;
	}

	public void setId(PnltBronzePlanPremPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PnltBronzePlanPrem [id=" + id + "]";
	}
	
}
