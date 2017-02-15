package com.bcbsnc.example.manager;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class SharedResponsibilityPaymentWorksheet {

	private BigDecimal line1 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private BigDecimal line2 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private BigDecimal line3 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private BigDecimal line4 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private BigDecimal line5 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	
	public SharedResponsibilityPaymentWorksheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getLine1() {
		return line1;
	}

	public BigDecimal getLine2() {
		return line2;
	}

	public BigDecimal getLine3() {
		return line3;
	}

	public BigDecimal getLine4() {
		return line4;
	}

	public BigDecimal getLine5() {
		return line5;
	}

	public void setLine1(BigDecimal line1) {
		this.line1 = line1;
	}

	public void setLine2(BigDecimal line2) {
		this.line2 = line2;
	}

	public void setLine3(BigDecimal line3) {
		this.line3 = line3;
	}

	public void setLine4(BigDecimal line4) {
		this.line4 = line4;
	}

	public void setLine5(BigDecimal line5) {
		this.line5 = line5;
	}

	public boolean isLine1LargerThanLine2() {
		System.out.println("compare line1: "+line1+" to line2: "+line2+". Retrun "+(line1.compareTo(line2) >0));
		return line1.compareTo(line2) >0;
	}
	
	@Override
	public String toString() {
		return "SharedResponsibilityPaymentWorksheet [line1=" + line1 + ", line2=" + line2 + ", line3=" + line3
				+ ", line4=" + line4 + ", line5=" + line5 + "]";
	}
	
	
	
}
