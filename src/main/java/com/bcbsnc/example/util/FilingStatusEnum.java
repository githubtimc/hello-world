package com.bcbsnc.example.util;

import java.util.HashMap;
import java.util.Map;

public enum FilingStatusEnum {

	Exempt("Exempt", 0),
	SingleUnder65("Single ", 1),
	Single65Plus("Single", 2),
	HeadOfHouseholdUnder65("Head of Household", 3),
	HeadOfHousehold65Plus("Head of Household", 4),
	MarriedFilingJointlyBothUnder65("Married filing jointly", 5),
	MarriedFilingJointlyOne65Plus("Married filing jointly", 6),
	MarriedFilingJointlyBoth65Plus("Married filing jointly", 7),
	MarriedFilingSeparately("Married filing separately", 8),
	WidowUnder65WithDependent("Qualifying widow(er) with dependent child", 9),
	Widow65PlusWithDependent("Qualifying widow(er) with dependent child", 10);
	
	private String webServiceDescription;
	private int webServiceCode;

	private static Map<Integer, FilingStatusEnum> codeMapping;
	
	private FilingStatusEnum(String webServiceDescription, int webServiceCode) {
		this.webServiceDescription = webServiceDescription;
		this.webServiceCode = webServiceCode;
	}
	
	private static void initMapping() {
		codeMapping = new HashMap<Integer,FilingStatusEnum>();
		for (FilingStatusEnum codes : values()) {
			codeMapping.put(codes.webServiceCode, codes);
		}
	}
	
	public static FilingStatusEnum getFilingStatus(String webServiceCode) {
		if (codeMapping==null) {
			initMapping();
		}
		return codeMapping.get(webServiceCode);
	}
	
	public int getWebServiceCode() {
		return webServiceCode;
	}
	
	public String getWebServiceDescription() {
		return webServiceDescription;
	}

	public static boolean isFilingJointly(int filingStatusCode) {
		return (filingStatusCode==FilingStatusEnum.MarriedFilingJointlyBoth65Plus.webServiceCode ||
				filingStatusCode==FilingStatusEnum.MarriedFilingJointlyBothUnder65.webServiceCode ||
				filingStatusCode==FilingStatusEnum.MarriedFilingJointlyOne65Plus.webServiceCode);
	}
}

