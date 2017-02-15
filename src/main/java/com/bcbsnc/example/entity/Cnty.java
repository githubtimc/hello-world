package com.bcbsnc.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CNTY")

public class Cnty {
	
	@Id
	private long fipsCntyCd;
	private String cntyNm;
	private String stNm;
	//private Date date;
	
	public Cnty() {
	}

	public long getFipsCntyCd() {
		return fipsCntyCd;
	}

	public void setFipsCntyCd(long fipsCntyCd) {
		this.fipsCntyCd = fipsCntyCd;
	}

	public String getCntyNm() {
		return cntyNm;
	}

	public void setCntyNm(String cntyNm) {
		this.cntyNm = cntyNm;
	}

	public String getStNm() {
		return stNm;
	}

	public void setStNm(String stNm) {
		this.stNm = stNm;
	}

	@Override
	public String toString() {
		return "Cnty [fipsCntyCd=" + fipsCntyCd + ", cntyNm=" + cntyNm + ", stNm=" + stNm + "]";
	}

//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

	
}
