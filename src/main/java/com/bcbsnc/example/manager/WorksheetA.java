package com.bcbsnc.example.manager;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class WorksheetA {

	private static final BigDecimal TWELVE = new BigDecimal(12).setScale(0, RoundingMode.HALF_UP);
	private static final BigDecimal HALF = new BigDecimal(0.5).setScale(1,RoundingMode.HALF_UP);
	
	private boolean useWorksheetA;
	private BigDecimal numberOfApplicantsOver18;//technically not part of the worksheet, but I needed someplace to keep a record
	private BigDecimal numberOfApplicantsUnder18;////technically not part of the worksheet, but I needed someplace to keep a record
	
	private Map<Integer, Integer>    line1ByMonth = new HashMap<Integer, Integer>();
	private Map<Integer, BigDecimal> line2ByMonth = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> line3ByMonth = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> line4ByMonth = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> line5ByMonth = new HashMap<Integer, BigDecimal>();
	private BigDecimal line6 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private BigDecimal flatDollarAmount = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private int line8;
	
	public WorksheetA() {
		useWorksheetA = false;
		
		for (Integer month=1; month<=12; month++) {
			line1ByMonth.put(month, 0);
			line2ByMonth.put(month, BigDecimal.ZERO);
			line3ByMonth.put(month, BigDecimal.ZERO);
			line4ByMonth.put(month, BigDecimal.ZERO);
			line5ByMonth.put(month, BigDecimal.ZERO);
		}
	}

	public boolean isUseWorksheetA() {
		return useWorksheetA;
	}

	public BigDecimal getNumberOfApplicantsOver18() {
		return numberOfApplicantsOver18;
	}

	public BigDecimal getNumberOfApplicantsUnder18() {
		return numberOfApplicantsUnder18;
	}

	public Map<Integer, Integer> getLine1ByMonth() {
		return line1ByMonth;
	}

	public Map<Integer, BigDecimal> getLine2ByMonth() {
		return line2ByMonth;
	}

	public Map<Integer, BigDecimal> getLine3ByMonth() {
		return line3ByMonth;
	}

	public Map<Integer, BigDecimal> getLine4ByMonth() {
		return line4ByMonth;
	}

	public Map<Integer, BigDecimal> getLine5ByMonth() {
		return line5ByMonth;
	}

	public BigDecimal getLine6() {
		return line6;
	}

	public BigDecimal getFlatDollarAmount() {
		return flatDollarAmount;
	}

	public int getLine8() {
		return line8;
	}

	public void setUseWorksheetA(boolean useWorksheetA) {
		this.useWorksheetA = useWorksheetA;
	}

	public void setNumberOfApplicantsOver18(BigDecimal numberOfApplicantsOver18) {
		this.numberOfApplicantsOver18 = numberOfApplicantsOver18;
	}

	public void setNumberOfApplicantsUnder18(BigDecimal numberOfApplicantsUnder18) {
		this.numberOfApplicantsUnder18 = numberOfApplicantsUnder18;
	}

	public void setLine1ByMonth(Map<Integer, Integer> line1ByMonth) {
		this.line1ByMonth = line1ByMonth;
	}

	public void setLine2ByMonth(Map<Integer, BigDecimal> line2ByMonth) {
		this.line2ByMonth = line2ByMonth;
	}

	public void setLine3ByMonth(Map<Integer, BigDecimal> line3ByMonth) {
		this.line3ByMonth = line3ByMonth;
	}

	public void setLine4ByMonth(Map<Integer, BigDecimal> line4ByMonth) {
		this.line4ByMonth = line4ByMonth;
	}

	public void setLine5ByMonth(Map<Integer, BigDecimal> line5ByMonth) {
		this.line5ByMonth = line5ByMonth;
	}

	public void setLine6(BigDecimal line6) {
		this.line6 = line6;
	}

	public void setFlatDollarAmount(BigDecimal flatDollarAmount) {
		this.flatDollarAmount = flatDollarAmount;
	}

	public void setLine8(int line8) {
		this.line8 = line8;
	}

	//total number of applicants in a month. capped at 5
	public void addToLine1ByMonth(Integer month) {
		if (1<=month && month<=12) {
			Integer current = line1ByMonth.get(month);
			if (current < 5) {
				line1ByMonth.put(month, ++current);
			}
		}
	}
	
	//Add the total number of Xs in a month for individuals 18 or over (18 on the month after their birthday)
	public void addToLine2ByMonth(Integer month) {
		if (1<=month && month<=12) {
			BigDecimal current = line2ByMonth.get(month);
			line2ByMonth.put(month, current.add(BigDecimal.ONE));
		}
	}
	
	//Add one half the number of Xs in a month for individuals under 18
	public void addToLine3ByMonth(Integer month) {
		if (1<=month && month<=12) {
			BigDecimal current = line3ByMonth.get(month);
			line3ByMonth.put(month, current.add(HALF));
		}
	}
	
	public void calculateLine4ByMonth() {
		for (Integer month=1; month<=12; month++) {
			BigDecimal numberXsOver18  =  line2ByMonth.get(month);
			BigDecimal numberXsUnder18 =  line3ByMonth.get(month);
			line4ByMonth.put(month, numberXsOver18.add(numberXsUnder18));
		}
	}
	
	public void calculateLine5ByMonth(BigDecimal multiplier, BigDecimal maxAllowed) {
		for (Integer month=1; month<=12; month++) {
			BigDecimal numberXs  =  line4ByMonth.get(month);
			line5ByMonth.put(month, (numberXs.multiply(multiplier).setScale(2, RoundingMode.HALF_UP)).min(maxAllowed));
		}
	}
	
	public void calculateLine6() {
		for (Integer month=1; month<=12; month++) {
			Integer line1Value     = line1ByMonth.get(month);
			BigDecimal line5Value  = line5ByMonth.get(month);
			
			line6 = this.line6.add(line5Value).setScale(2,RoundingMode.HALF_UP);
			line8 = this.line8+line1Value;
		}
	}
	
	public void calculateFlatDollarAmount() {
		this.flatDollarAmount = this.line6.divide(TWELVE, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "WorksheetA [useWorksheetA=" + useWorksheetA + ", numberOfApplicantsOver18=" + numberOfApplicantsOver18
				+ ", numberOfApplicantsUnder18=" + numberOfApplicantsUnder18 + ", line1ByMonth=" + line1ByMonth
				+ ", line2ByMonth=" + line2ByMonth + ", line3ByMonth=" + line3ByMonth + ", line4ByMonth=" + line4ByMonth
				+ ", line5ByMonth=" + line5ByMonth + ", line6=" + line6 + ", flatDollarAmount=" + flatDollarAmount
				+ ", line8=" + line8 + "]";
	}
	
	
	
}
