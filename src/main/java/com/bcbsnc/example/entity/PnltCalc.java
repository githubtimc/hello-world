package com.bcbsnc.example.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PNLT_CALC")
public class PnltCalc implements Serializable {

	@EmbeddedId
	private PnltCalcPK id;

	public PnltCalcPK getId() {
		return id;
	}

	public void setId(PnltCalcPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PnltCalc [id=" + id + "]";
	}
	
	
	
}
