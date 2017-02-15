package com.bcbsnc.example.util;

import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.bcbsnc.example.model.Applicant;

public class AgeCalculator {

	
	public static boolean isOver65(DateTime birthDate, DateTime compareToDate)
	{
		//System.out.println("Comparing:"+birthDate.toDate());
		//System.out.println("With     :"+compareToDate.toDate());
		int birthDateYear = birthDate.getYear();
		int compareToDateYear = compareToDate.getYear();

		int birthDateDayOfYear = birthDate.getDayOfYear();
		int compareToDateDayOfYear = compareToDate.getDayOfYear();
		
		boolean birthdayOccurredThisYear = false;
		if (birthDateDayOfYear <= compareToDateDayOfYear) {
			//System.out.println("Birthday has occurred: "+birthDate);
			birthdayOccurredThisYear = true;
		}
		
		if (birthdayOccurredThisYear) {
			//System.out.println("Age: "+(compareToDateYear - birthDateYear));
			return (compareToDateYear - birthDateYear) > 65;
		}
		else {
			//System.out.println("Age: "+((compareToDateYear - birthDateYear)-1));
			return (compareToDateYear - birthDateYear)-1 > 65;
		}
	}
	
	
	public static int getAgeAsOfRequestedDate(DateTime birthDate, DateTime compareToDateTime)
	{
		//System.out.println("Comparing:"+birthDate.toDate());
		//System.out.println("With     :"+compareToDate.toDate());
		return getAgeAsOfRequestedDate(birthDate.toLocalDate(), compareToDateTime.toLocalDate());
	}
	
	public static int getAgeAsOfRequestedDate(LocalDate birthDate, LocalDate compareToDate)
	{
		//System.out.println("Comparing:"+birthDate.toDate());
		//System.out.println("With     :"+compareToDate.toDate());
		
		//System.out.println("Comparing:"+birthDate.toDate());
		//System.out.println("With     :"+compareToDate.toDate());

		int birthDateYear = birthDate.getYear();
		int compareToDateYear = compareToDate.getYear();

		if (birthDateYear >= compareToDateYear) {
			return 0;
		}
		
		int birthDateDayOfYear = birthDate.getDayOfYear();
		int compareToDateDayOfYear = compareToDate.getDayOfYear();
		
		boolean birthdayOccurredThisYear = false;
		if (birthDateDayOfYear <= compareToDateDayOfYear) {
			//System.out.println("Birthday has occurred: "+birthDate);
			birthdayOccurredThisYear = true;
		}
		
		if (birthdayOccurredThisYear) {
			//System.out.println("Age: "+(compareToDateYear - birthDateYear));
			return (compareToDateYear - birthDateYear);
		}
		else {
			//System.out.println("Age: "+((compareToDateYear - birthDateYear)-1));
			return (compareToDateYear - birthDateYear)-1;
		}
	}
	
	public static boolean isAnyoneTurning18(List<Applicant> applicants, int year)
	{
		Iterator<Applicant> applicantIterator = applicants.iterator();
		while (applicantIterator.hasNext()) {
			Applicant applicant = applicantIterator.next();
			int applicantStartAge = AgeCalculator.getAgeAsOfRequestedDate(applicant.getBirthDate().toLocalDate(), new LocalDate(year, DateTimeConstants.JANUARY, 1));
			
			if (applicantStartAge == 17) {
				return true;
			}
		}
		return false;
	}
}
