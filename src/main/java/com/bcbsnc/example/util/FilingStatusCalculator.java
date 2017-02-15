package com.bcbsnc.example.util;

import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;

import com.bcbsnc.example.model.Applicant;
import com.bcbsnc.example.model.Household;

public class FilingStatusCalculator {

//	private static final int ADULT = 1;
//	private static final int STANDALONE = 2;
//	private static final int DEPENDENT = 3;
//	
//	private static final String SELF = "Self";
//	private static final String SPOUSE = "Spouse";
//	private static final String DOMESTIC_PARTNER = "Domestic Partner";
//	private static final String STANDALONE_CHILD = "Standalone Child";
//	private static final String CHILD = "Child";

	private static final int EXEMPT 								= 0;
	private static final int FILING_AS_SINGLE_UNDER_65 				= 1;
	private static final int FILING_AS_SINGLE_65_PLUS 				= 2;
	private static final int FILING_AS_HEAD_OF_HOUSEHOLD_UNDER_65 	= 3;
	private static final int FILING_AS_HEAD_OF_HOUSEHOLD_65_PLUS 	= 4;
	private static final int MARRIED_FILING_JOINTLY_BOTH_UNDER_65	= 5;
	private static final int MARRIED_FILING_JOINTLY_ONE_65_PLUS_	= 6;
	private static final int MARRIED_FILING_JOINTLY_BOTH_65_PLUS	= 7;
	private static final int MARRIED_FILING_SEPARATELY				= 8;
	private static final int WIDOW_WITH_DEPENDENT_UNDER_65			= 9;
	private static final int WIDOW_WITH_DEPENDENT_65_PLUS			= 10;
	private static final int WIDOWER_WITH_DEPENDENT_UNDER_65		= 9;
	private static final int WIDOWER_WITH_DEPENDENT_65_PLUS			= 10;


	/**
	 * If single applicant 
	 * 	A - if standalone child then penalty exempt
	 * 	B - else filing status is single
	 * If two+ applicants
	 * 	A - If one is a spouse then filing status is joint
	 *  B - else filing status is head of household
	 *  
	 * @param applicants
	 * @return
	 */
	public static FilingStatusEnum determineFilingStatus(Household household, DateTime requestedDate) {
		int numberOfApplicants = household.getApplicants().size();
		
		//there has to be a primary applicant - has a relationship code of SELF
		Applicant primaryApplicant = HouseholdUtilities.getPrimaryApplicant(household.getApplicants());
		boolean isPrimaryApplicantOver65 = AgeCalculator.isOver65(primaryApplicant.getBirthDate(), requestedDate); 
		
		
		switch(numberOfApplicants) {
		case 1:
				if (isPrimaryApplicantOver65) {
					return FilingStatusEnum.Single65Plus;
				}
				else {
					return FilingStatusEnum.SingleUnder65;
				}
			
		default:
			//determine if there is a spouse in the household and ages of spouses to get filing status
			Applicant spouseApplicant = HouseholdUtilities.getSpouseApplicant(household.getApplicants());
			if (spouseApplicant!=null) 
			{	
				boolean isSpouseApplicantOver65 = AgeCalculator.isOver65(spouseApplicant.getBirthDate(), requestedDate);
			
				if (isPrimaryApplicantOver65 && isSpouseApplicantOver65) {
					return FilingStatusEnum.MarriedFilingJointlyBoth65Plus;
				}
				else if (!isPrimaryApplicantOver65 && !isSpouseApplicantOver65) {
					return FilingStatusEnum.MarriedFilingJointlyBothUnder65;
				}
				else {
					return FilingStatusEnum.MarriedFilingJointlyOne65Plus;
				}
			}
			
			//there is no spouse. Treat as head of household
			if (AgeCalculator.isOver65(primaryApplicant.getBirthDate(), requestedDate)) {
				return FilingStatusEnum.HeadOfHousehold65Plus;
			}
			else {
				return FilingStatusEnum.HeadOfHouseholdUnder65;
			}		
		}
	}

}
