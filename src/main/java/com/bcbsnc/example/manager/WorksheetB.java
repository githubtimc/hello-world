package com.bcbsnc.example.manager;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class WorksheetB {

	private static final BigDecimal TWELVE = new BigDecimal(12).setScale(0, RoundingMode.HALF_UP);
	
	private Map<Integer, BigDecimal> columnAByMonth = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> columnBByMonth = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> columnCByMonth = new HashMap<Integer, BigDecimal>();
	private BigDecimal line13 = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	private BigDecimal percentageIncomeAmt = BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	
	public WorksheetB() {
		
		for (Integer month=1; month<=12; month++) {
			columnAByMonth.put(month, BigDecimal.ZERO);
			columnBByMonth.put(month, BigDecimal.ZERO);
			columnCByMonth.put(month, BigDecimal.ZERO);
		}
	}

	public WorksheetB(Map<Integer, BigDecimal> worksheetA_line5ByMonth, BigDecimal percentageIncomeAmt) {
		for (Integer month=1; month<=12; month++) {
			columnAByMonth.put(month, worksheetA_line5ByMonth.get(month));
			columnBByMonth.put(month, percentageIncomeAmt);
			columnCByMonth.put(month, worksheetA_line5ByMonth.get(month).max(percentageIncomeAmt));
			
			line13 = line13.add(columnCByMonth.get(month));
		}
		this.percentageIncomeAmt = line13.divide(TWELVE, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP);
	}

	public Map<Integer, BigDecimal> getColumnAByMonth() {
		return columnAByMonth;
	}

	public Map<Integer, BigDecimal> getColumnBByMonth() {
		return columnBByMonth;
	}

	public Map<Integer, BigDecimal> getColumnCByMonth() {
		return columnCByMonth;
	}

	public BigDecimal getLine13() {
		return line13;
	}

	public BigDecimal getPercentageIncomeAmt() {
		return percentageIncomeAmt;
	}

	public void setColumnAByMonth(Map<Integer, BigDecimal> columnAByMonth) {
		this.columnAByMonth = columnAByMonth;
	}

	public void setColumnBByMonth(Map<Integer, BigDecimal> columnBByMonth) {
		this.columnBByMonth = columnBByMonth;
	}

	public void setColumnCByMonth(Map<Integer, BigDecimal> columnCByMonth) {
		this.columnCByMonth = columnCByMonth;
	}

	public void setLine13(BigDecimal line13) {
		this.line13 = line13;
	}

	public void setPercentageIncomeAmt(BigDecimal percentageIncomeAmt) {
		this.percentageIncomeAmt = percentageIncomeAmt;
	}

	
}
