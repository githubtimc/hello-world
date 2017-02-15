package com.bcbsnc.example.util;

import java.util.Iterator;
import java.util.List;

import com.bcbsnc.example.model.Applicant;
import com.bcbsnc.svc.party.individualratingservice_v1.dxf.RelationshipTypeEnum;

public class HouseholdUtilities {

	public static Applicant getPrimaryApplicant(List<Applicant> applicants) {
		Iterator<Applicant> applicantIterator = applicants.iterator();
		while (applicantIterator.hasNext()) {
			Applicant applicant = applicantIterator.next();
			if (applicant.getRelationshipCode().equalsIgnoreCase(RelationshipTypeEnum.SELF.name())) {
				return applicant;
			}
		}
		return null;
	}
	
	public static Applicant getSpouseApplicant(List<Applicant> applicants) {
		Iterator<Applicant> applicantIterator = applicants.iterator();
		while (applicantIterator.hasNext()) {
			Applicant applicant = applicantIterator.next();
			if (applicant.getRelationshipCode().equalsIgnoreCase(RelationshipTypeEnum.SPOUSE.name())) {
				return applicant;
			}
		}
		return null;
	}
	
	public static Applicant getDomesticPartnerApplicant(List<Applicant> applicants) {
		Iterator<Applicant> applicantIterator = applicants.iterator();
		while (applicantIterator.hasNext()) {
			Applicant applicant = applicantIterator.next();
			if (applicant.getRelationshipCode().equalsIgnoreCase(RelationshipTypeEnum.DOMESTIC_PARTNER.name())) {
				return applicant;
			}
		}
		return null;
	}

}
