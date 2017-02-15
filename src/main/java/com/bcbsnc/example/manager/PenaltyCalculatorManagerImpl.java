package com.bcbsnc.example.manager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcbsnc.example.entity.PnltBronzePlanPrem;
import com.bcbsnc.example.entity.PnltCalc;
import com.bcbsnc.example.entity.PnltFileLvl;
import com.bcbsnc.example.model.Applicant;
import com.bcbsnc.example.model.Household;
import com.bcbsnc.example.model.Penalty;
import com.bcbsnc.example.repository.PenaltyCalculatorBronzePlanRepository;
import com.bcbsnc.example.repository.PenaltyCalculatorRepository;
import com.bcbsnc.example.repository.PenaltyFilingLevelRepository;
import com.bcbsnc.example.util.AgeCalculator;
import com.bcbsnc.example.util.FilingStatusCalculator;
import com.bcbsnc.example.util.FilingStatusEnum;

@Service
public class PenaltyCalculatorManagerImpl implements PenaltyCalculatorManager
{	
	private static final BigDecimal HALF = new BigDecimal(0.5).setScale(1,RoundingMode.HALF_UP);
	
	@Autowired
	private PenaltyCalculatorRepository penaltyCalculatorRepository;
	@Autowired
	private PenaltyCalculatorBronzePlanRepository penaltyCalculatorBronzePlanRepository;
	@Autowired
	private PenaltyFilingLevelRepository penaltyFilingLevelRepository;
		
	@Override
	public Penalty calculatePenalty(Household household) {
		SharedResponsibilityPaymentWorksheet sharedResponsibilityPaymentWorksheet = new SharedResponsibilityPaymentWorksheet();
		Penalty penalty = createNewPenalty(household);
		BigDecimal percentageIncomeAmt = percentageIncomeCalculator(household, penalty);
		
		System.out.println("Income              == "+household.getIncomeAmt());
		System.out.println("percentageIncomeAmt == "+percentageIncomeAmt);
		
		boolean isAnyOneTurning18 = AgeCalculator.isAnyoneTurning18(household.getApplicants(), household.getRequestedEffectiveDate().getYear());
		if (isAnyOneTurning18) {
			WorksheetA worksheetA = worksheetA(household, penalty);
			WorksheetB worksheetB = new WorksheetB(penalty.getWorksheetA().getLine5ByMonth(), percentageIncomeAmt);
			
			sharedResponsibilityPaymentWorksheet.setLine1(worksheetA.getFlatDollarAmount());	
			sharedResponsibilityPaymentWorksheet.setLine2(worksheetB.getPercentageIncomeAmt());
			sharedResponsibilityPaymentWorksheet.setLine3(worksheetB.getPercentageIncomeAmt());
			sharedResponsibilityPaymentWorksheet.setLine4(step5(household, penalty, worksheetA));
		}
		else {
			sharedResponsibilityPaymentWorksheet.setLine1(step2(household, penalty));
			sharedResponsibilityPaymentWorksheet.setLine2(percentageIncomeAmt);
			sharedResponsibilityPaymentWorksheet.setLine3(percentageIncomeAmt.max(sharedResponsibilityPaymentWorksheet.getLine1()));
			sharedResponsibilityPaymentWorksheet.setLine4(step5(household, penalty, null));			
		}
		sharedResponsibilityPaymentWorksheet.setLine5(sharedResponsibilityPaymentWorksheet.getLine3().min(sharedResponsibilityPaymentWorksheet.getLine4().setScale(2,RoundingMode.HALF_UP)));
		penalty.setPenaltyAmt(sharedResponsibilityPaymentWorksheet.getLine5());
		System.out.println("sharedResponsibilityPaymentWorksheet line1: "+sharedResponsibilityPaymentWorksheet.getLine1());
		System.out.println("sharedResponsibilityPaymentWorksheet line2: "+sharedResponsibilityPaymentWorksheet.getLine2());
		System.out.println("sharedResponsibilityPaymentWorksheet line3: "+sharedResponsibilityPaymentWorksheet.getLine3());
		System.out.println("sharedResponsibilityPaymentWorksheet line4: "+sharedResponsibilityPaymentWorksheet.getLine4());
		System.out.println("sharedResponsibilityPaymentWorksheet line5: "+sharedResponsibilityPaymentWorksheet.getLine5());

//		BigDecimal percentageIncomeAmt = percentageIncomeCalculator(household, penalty);
//		if (penalty.getWorksheetA()!=null) {
//			WorksheetB worksheetB = new WorksheetB(penalty.getWorksheetA().getLine5ByMonth(), percentageIncomeAmt);
//		}
//		else {
//			sharedResponsibilityPaymentWorksheet.setLine2(percentageIncomeAmt);
//		}
		System.out.println("Penalty == "+penalty);
		return penalty;
	}
		
	
	private Penalty createNewPenalty(Household household) {
		Penalty penalty = new Penalty();
		
		//get data from Pnlt_Calc table
		List<PnltCalc> pnltCalcTableData = this.penaltyCalculatorRepository.findByDate(household.getRequestedEffectiveDate().toDate());
		System.out.println("pnlty_calc table data size: "+pnltCalcTableData.size()); 
		if (pnltCalcTableData.size()==1) {
			penalty.setEffectDt(new DateTime(pnltCalcTableData.get(0).getId().getEffectDt()));
			penalty.setExpireDt(new DateTime(pnltCalcTableData.get(0).getId().getEffectDt()));
			penalty.setPctIncomeQnt(pnltCalcTableData.get(0).getId().getPctIncomeQnt());
			penalty.setPerAdultAmt(pnltCalcTableData.get(0).getId().getPerAdultAmt());
			penalty.setPerChildAmt(pnltCalcTableData.get(0).getId().getPerChildAmt());
			penalty.setPerHouseholdMaxAmt(pnltCalcTableData.get(0).getId().getPerHouseholdMax());
		}
		
		System.out.println(penalty);
		
		//get filing status data from Pnlt_File_lvl table
		FilingStatusEnum filingStatus = FilingStatusCalculator.determineFilingStatus(household, household.getRequestedEffectiveDate());
		System.out.println("Filing status["+filingStatus.getWebServiceCode()+"] == "+filingStatus.getWebServiceDescription());
		penalty.setFilingStatus(filingStatus);
		
		List<PnltFileLvl> pnltFileLvlTableData = this.penaltyFilingLevelRepository.findByDateAndFilingStatus(household.getRequestedEffectiveDate().toDate(), filingStatus.getWebServiceCode());
		System.out.println("pnlt_file_lvl table data size:"+pnltFileLvlTableData.size());
		if (pnltFileLvlTableData.size()==1) {
			penalty.setFilingStatusAmt(pnltFileLvlTableData.get(0).getId().getMinIncomeAmt());
		}
		System.out.println(penalty);
		
		return penalty;
	}
	
	/**
	 * This equates to step5 in the 2016 Form 8965 tax document.
	 * It is the National Average Bronze Plan Premium calculation
	 * @param household
	 * @param penalty
	 * @param worksheetA
	 * @return
	 */
	private BigDecimal step5(Household household, Penalty penalty, WorksheetA worksheetA) 
	{
		//get data from Pnlt_Bronze_Plan_Prem table
		List<PnltBronzePlanPrem> pnltBronzePlanPremTableData = this.penaltyCalculatorBronzePlanRepository.findByDateandFamilySize2(household.getRequestedEffectiveDate().toDate(), new BigDecimal(household.getApplicants().size()));

		if (pnltBronzePlanPremTableData.size()==1) {
			if (worksheetA == null) {
				System.out.println("Use the family size for bronze plan premium: "+pnltBronzePlanPremTableData.get(0).getId().getFmlyPremAmt()); 
				penalty.setBronzePremiumAmt(pnltBronzePlanPremTableData.get(0).getId().getFmlyPremAmt());
				return penalty.getBronzePremiumAmt();
			}
			else {
				System.out.println("Use the worksheetA line8 value for bronze plan premium: "+pnltBronzePlanPremTableData.get(0).getId().getFmlyPremAmt()); 
				BigDecimal worksheetA_line8 = new BigDecimal(worksheetA.getLine8()).setScale(2,RoundingMode.HALF_UP);
				BigDecimal bronzePlanMultiplier = pnltBronzePlanPremTableData.get(0).getId().getWorksheetAMultiplier();
				penalty.setBronzePremiumAmt(bronzePlanMultiplier.multiply(worksheetA_line8).setScale(2,RoundingMode.HALF_UP));
				return penalty.getBronzePremiumAmt();				
			}
		}
		return BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	}

	/**
	 * This equates to step2 in the 2016 Form 8965 tax document.
	 * It is the Flat Dollar Amount calculation
	 * @param household
	 * @param penalty
	 * @return
	 */
	private BigDecimal step2(Household household, Penalty penalty)
	{	
		BigDecimal numberOfApplicantsOver18 = BigDecimal.ZERO;
		BigDecimal numberOfApplicantsUnder18 = BigDecimal.ZERO;
		int year = household.getRequestedEffectiveDate().getYear();
		
		Iterator<Applicant> applicantIterator = household.getApplicants().iterator();
		System.out.println("under 18:"+numberOfApplicantsUnder18);
		System.out.println("18 +    :"+numberOfApplicantsOver18);
		while (applicantIterator.hasNext()) 
		{
			Applicant applicant = applicantIterator.next();
			int applicantAge = AgeCalculator.getAgeAsOfRequestedDate(applicant.getBirthDate().toLocalDate(), new LocalDate(year, DateTimeConstants.JANUARY, 1));
			if (applicantAge>=18) {
				numberOfApplicantsOver18 = numberOfApplicantsOver18.add(BigDecimal.ONE);
				System.out.println("18 +    :"+numberOfApplicantsOver18);
			}
			else {
				numberOfApplicantsUnder18 = numberOfApplicantsUnder18.add(BigDecimal.ONE);
				System.out.println("under 18:"+numberOfApplicantsUnder18);
			}
		}
		System.out.println("under 18:"+numberOfApplicantsUnder18);
		System.out.println("18 +    :"+numberOfApplicantsOver18);

		BigDecimal over18cost  = numberOfApplicantsOver18.multiply(penalty.getPerAdultAmt()).setScale(2,RoundingMode.HALF_UP);
		BigDecimal under18cost = numberOfApplicantsUnder18.multiply(penalty.getPerChildAmt()).setScale(2,RoundingMode.HALF_UP);	
		BigDecimal totalCost   = over18cost.add(under18cost).setScale(2,RoundingMode.HALF_UP);
		System.out.println("totalCost:"+totalCost);
		System.out.println("totalCost:"+totalCost);
		return totalCost.min(penalty.getPerHouseholdMaxAmt());
	}

	private WorksheetA worksheetA(Household household, Penalty penalty)
	{	
		WorksheetA worksheetA = new WorksheetA();
		int year = household.getRequestedEffectiveDate().getYear();
		worksheetA.setUseWorksheetA(true);
		
		Iterator<Applicant> applicantIterator = household.getApplicants().iterator();
		while (applicantIterator.hasNext()) 
		{
			Applicant applicant = applicantIterator.next();
			System.out.print(applicant.getPersonId()+" is age ");
			for (Integer month=1; month<=12; month++) 
			{
				
				LocalDate startofmonth = new LocalDate(year, month, 1);
				int applicantAge = AgeCalculator.getAgeAsOfRequestedDate(applicant.getBirthDate().toLocalDate(), startofmonth);
				System.out.print(applicantAge+". ");
				
				worksheetA.addToLine1ByMonth(month);
				
				if (applicantAge >=18 ) {
					worksheetA.addToLine2ByMonth(month);
				}
				else { //applicantAge <18
					worksheetA.addToLine3ByMonth(month);
				}				
			}
			System.out.println();
			System.out.println("LINE1:"+worksheetA.getLine1ByMonth());
			System.out.println("LINE2:"+worksheetA.getLine2ByMonth());
			System.out.println("LINE3:"+worksheetA.getLine3ByMonth());			
			System.out.println(worksheetA);
		}//end applicant iteration
		worksheetA.calculateLine4ByMonth();
		System.out.println("LINE4:"+worksheetA.getLine4ByMonth());
		
		//calculate the value for each month
		worksheetA.calculateLine5ByMonth(penalty.getPerAdultAmt(), penalty.getPerHouseholdMaxAmt());
		System.out.println("LINE5:"+worksheetA.getLine5ByMonth());

		worksheetA.calculateLine6();
		worksheetA.calculateFlatDollarAmount();
		System.out.println("LINE6:"+worksheetA.getLine6());
		System.out.println("LINE7:"+worksheetA.getFlatDollarAmount());
		System.out.println("LINE8:"+worksheetA.getLine8());
		penalty.setWorksheetA(worksheetA);
		return worksheetA;
	}


	private BigDecimal percentageIncomeCalculator(Household household, Penalty penalty) {
		FilingStatusEnum fse = FilingStatusCalculator.determineFilingStatus(household, household.getRequestedEffectiveDate());

		BigDecimal line1 = household.getIncomeAmt();
		BigDecimal line3 = penalty.getFilingStatusAmt();
		
		BigDecimal line5 = line1.subtract(line3);
		if (line5.compareTo(BigDecimal.ZERO)<=0) {
			penalty.setFilingStatus(FilingStatusEnum.Exempt);
			return BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
		}
		
		BigDecimal line7 = line5.multiply(penalty.getPctIncomeQnt()).setScale(2,RoundingMode.HALF_UP);
		return line7;
	}

}
