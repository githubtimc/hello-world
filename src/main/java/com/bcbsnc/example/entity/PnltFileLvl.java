package com.bcbsnc.example.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PNLT_FILE_LVL")
public class PnltFileLvl implements Serializable {

	@EmbeddedId
	private PnltFileLvlPK id;

	public PnltFileLvlPK getId() {
		return id;
	}

	public void setId(PnltFileLvlPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PnltFileLvl [id=" + id + "]";
	}

}
