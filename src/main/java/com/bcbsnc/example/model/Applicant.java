package com.bcbsnc.example.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Applicant implements Serializable {


	@NotNull protected String personId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	@NotNull protected DateTime birthDate;
	@NotNull protected String gender;
	@NotNull protected String riskTier;
	@NotNull protected String relationshipCode;
    protected Boolean maternityCoverageRequestedIndicator;
    protected BigInteger birthSequenceNumber;
    protected BigInteger dependentNumber;
    protected Boolean fulltimeStudentIndicator;
    protected Boolean disabledDependentIndicator;
    protected Boolean requestMemberBeExcludedFromBillingIndicator;
	
    public Applicant() {
	}
	public void init() {
        System.out.println("Applicant initialization logic");
    }

	@NotNull (message = "blah blah")
	@NotEmpty (message = "is empty")
	public String getPersonId() {
		return personId;
	}
	
	@NotNull (message = "blah blah")
	public DateTime getBirthDate() {
		return birthDate;
	}
	public String getGender() {
		return gender;
	}
	public String getRiskTier() {
		return riskTier;
	}
	public String getRelationshipCode() {
		return relationshipCode;
	}
	public Boolean getMaternityCoverageRequestedIndicator() {
		return maternityCoverageRequestedIndicator;
	}
	public BigInteger getBirthSequenceNumber() {
		return birthSequenceNumber;
	}
	public BigInteger getDependentNumber() {
		return dependentNumber;
	}
	public Boolean getFulltimeStudentIndicator() {
		return fulltimeStudentIndicator;
	}
	public Boolean getDisabledDependentIndicator() {
		return disabledDependentIndicator;
	}
	public Boolean getRequestMemberBeExcludedFromBillingIndicator() {
		return requestMemberBeExcludedFromBillingIndicator;
	}
	
	public void setPersonId(String personId) {
		System.out.println("Applicant.setPersonId("+personId+")");
		this.personId = personId;
	}
	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setRiskTier(String riskTier) {
		this.riskTier = riskTier;
	}
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}
	
	public void setMaternityCoverageRequestedIndicator(Boolean maternityCoverageRequestedIndicator) {
		this.maternityCoverageRequestedIndicator = maternityCoverageRequestedIndicator;
	}
	
	public void setBirthSequenceNumber(BigInteger birthSequenceNumber) {
		this.birthSequenceNumber = birthSequenceNumber;
	}
	
	public void setDependentNumber(BigInteger dependentNumber) {
		this.dependentNumber = dependentNumber;
	}
	
	public void setFulltimeStudentIndicator(Boolean fulltimeStudentIndicator) {
		this.fulltimeStudentIndicator = fulltimeStudentIndicator;
	}
	
	public void setDisabledDependentIndicator(Boolean disabledDependentIndicator) {
		this.disabledDependentIndicator = disabledDependentIndicator;
	}
	
	public void setRequestMemberBeExcludedFromBillingIndicator(Boolean requestMemberBeExcludedFromBillingIndicator) {
		this.requestMemberBeExcludedFromBillingIndicator = requestMemberBeExcludedFromBillingIndicator;
	}
	
	
	@Override
	public String toString() {
		return "Applicant [personId=" + personId + ", birthDate=" + birthDate + ", gender=" + gender + ", riskTier="
				+ riskTier + ", relationshipCode=" + relationshipCode + ", maternityCoverageRequestedIndicator="
				+ maternityCoverageRequestedIndicator + ", birthSequenceNumber=" + birthSequenceNumber
				+ ", dependentNumber=" + dependentNumber + ", fulltimeStudentIndicator=" + fulltimeStudentIndicator
				+ ", disabledDependentIndicator=" + disabledDependentIndicator
				+ ", requestMemberBeExcludedFromBillingIndicator=" + requestMemberBeExcludedFromBillingIndicator+"]";
	}
	
    
}

