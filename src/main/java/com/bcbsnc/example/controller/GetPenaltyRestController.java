package com.bcbsnc.example.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.validation.Validation;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bcbsnc.example.entity.PnltCalc;
import com.bcbsnc.example.manager.PenaltyCalculatorManager;
import com.bcbsnc.example.model.Applicant;
import com.bcbsnc.example.model.Household;
import com.bcbsnc.example.model.Penalty;
import com.bcbsnc.example.repository.PenaltyCalculatorRepository;
import com.bcbsnc.example.util.FilingStatusEnum;

@RestController
@RequestMapping("/getPenalty")
public class GetPenaltyRestController {

	@Autowired
	private PenaltyCalculatorRepository penaltyCalculatorRepository;
	
	@Autowired
	private PenaltyCalculatorManager penaltyCalculatorManager;
	
	/**
	 * Doesn't do any real work yet. Just returns the rows of the Pnlt_Calc table
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	Collection<PnltCalc> getCounties() {
		return this.penaltyCalculatorRepository.findAll();
	}
	
	
	/**
	 * Calculate the penalty for the household.
	 * @param household
	 * @return URI for making a GET request that will return the data
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody @Valid Household household)
	//ResponseEntity<?> add(@RequestBody @Valid Household household, BindingResult bindingResult)
	{
		System.out.println("getPenalty household: "+household);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(household.getHouseholdId()).toUri();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);
		responseHeaders.set("MyResponseHeader", "MyValue");

		Penalty penalty = penaltyCalculatorManager.calculatePenalty(household);
		household.setFilingStatusCode(Integer.toString(penalty.getFilingStatus().getWebServiceCode()));
		household.setFilingStatusDesc(penalty.getFilingStatus().getWebServiceDescription());
		household.setPenaltyExempt(penalty.getFilingStatus().getWebServiceCode() == FilingStatusEnum.Exempt.getWebServiceCode());
		
		if (household.isPenaltyExempt()) {
			household.setPenaltyAmt(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP));
		}
		else {
			household.setPenaltyAmt(penalty.getPenaltyAmt().setScale(2,RoundingMode.HALF_UP));
			System.out.println("household.getPenaltyAmt == "+household.getPenaltyAmt());
		}
		System.out.println("getPenalty household: "+household);
		
		//use this to return the json for testing
		return new ResponseEntity<Household>(household, responseHeaders, HttpStatus.CREATED);
		
		//use this in real life
		//return ResponseEntity.created(location).build();
	}

	
	
	
	private void validate(Applicant applicant) {
		System.out.println("validate(Applicant BOJ");
    
		javax.validation.Validator messageValidator = Validation.buildDefaultValidatorFactory().getValidator();
		SpringValidatorAdapter validatorAdapter = new SpringValidatorAdapter(messageValidator); 
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(applicant, applicant.getClass().getName());
		  
//		BeanPropertyBindingResult result = new BeanPropertyBindingResult(applicant, applicant.getClass().getName());
//		validatorAdapter.validate(messageValidator, result);
//		if (result == null || result.getAllErrors().isEmpty()) {
//			System.out.println("validate(): validatorAdapter.validate(messageValidator, result) did not return an error");
//			System.out.println("validate(): result.getAllErrors().isEmpty()");
//		}
//		else {
//			System.out.println("validate(): validatorAdapter.validate(messageValidator, result) did return an error");
//			System.out.println("validate(): result.getAllErrors().size() == "+result.getAllErrors().size());
//			Iterator<ObjectError> i = result.getAllErrors().iterator();
//			while (i.hasNext()) {
//				ObjectError oe = i.next();
//				System.out.println(oe.getDefaultMessage());
//				System.out.println(oe);
//			}
//		}

		errors = new BindException(applicant, applicant.getClass().getName());
		validatorAdapter.validate(applicant, errors);
		System.out.println("validate(): errors: "+errors);
    
		if (!errors.getAllErrors().isEmpty())
		{
			Iterator<ObjectError> i = errors.getAllErrors().iterator();
			while (i.hasNext()) {
				ObjectError oe = i.next();
				System.out.println(oe);
				System.out.println(oe.getObjectName()+" has error on field. Message "+oe.getDefaultMessage());
			}
		}
//		    Validator validator = new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
//		    //LazyValidatorFactory lvf = new LazyValidatorFactory();
//		    //Validator validator = new SpringValidatorAdapter(lvf.getValidator());
//		    ValidationUtils.invokeValidator(validator, applicant, errors);
//		    //return errors;
		System.out.println("validate(Applicant EOJ");
	}
}


//version 1
//@RequestMapping(method = RequestMethod.POST)
//ResponseEntity<List<Applicant>> add(@RequestBody @Valid Applicant input, BindingResult bindingResult)
//{
//	StringBuilder builder = new StringBuilder();
//    List<FieldError> errors = bindingResult.getFieldErrors();
//    List<String>errorMessages = new ArrayList<String>();
//    
//	List<Applicant> applicantList = new ArrayList<Applicant>();
//	applicantList.add(input);
//
//	if (!errors.isEmpty())
//	{
//		for (FieldError error : errors ) {
//	    	String errorMessage = error.getField() + " : " + error.getDefaultMessage();
//	    	System.out.println(errorMessage);
//	    	if (!errorMessages.contains(errorMessage)) {
//	    		errorMessages.add(errorMessage);
//	    		builder.append(errorMessage).append(". ");
//	    	}
//	    } 
//	    System.out.println("<<<<>>>");
//	    System.out.println(builder.toString().trim());
//	    System.out.println("<<<<>>>");
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(input.getPersonId()).toUri();
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.setLocation(location);
//		responseHeaders.set("Error", builder.toString().trim());
//		return new ResponseEntity<List<Applicant>>(applicantList, responseHeaders, HttpStatus.BAD_REQUEST);
//	}
//	
//	//this might be useful way to validate too somehow
//	try
//    {
//    	validate(input);
//    }
//    catch (Exception e) {
//    	e.printStackTrace();
//	    return new ResponseEntity<List<Applicant>>(HttpStatus.BAD_REQUEST);
//    }
//			
//	System.out.println("getPenalty applicant: "+input);
//	//return ResponseEntity.ok().build();
//	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(input.getPersonId()).toUri();
//	HttpHeaders responseHeaders = new HttpHeaders();
//	responseHeaders.setLocation(location);
//	responseHeaders.set("MyResponseHeader", "MyValue");
//	
//	DateTime effect = new DateTime(2017,  1,  1, 00, 00, 00, 000);
//	DateTime expire = new DateTime(2017, 12, 31, 23, 59, 59, 999);
//	System.out.println("effect: "+effect);
//	System.out.println("expire: "+expire);
//	this.penaltyCalculatorRepository.findByDate(effect.toDate(), expire.toDate());
//	
//	return new ResponseEntity<List<Applicant>>(applicantList, responseHeaders, HttpStatus.CREATED);
//
//}


//version 2
//@RequestMapping(method = RequestMethod.POST)
//ResponseEntity<?> add(@RequestBody @Valid Household household)
////ResponseEntity<?> add(@RequestBody @Valid Household household, BindingResult bindingResult)
//{
//	System.out.println("getPenalty household: "+household);
//	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(household.getHouseholdId()).toUri();
//	HttpHeaders responseHeaders = new HttpHeaders();
//	responseHeaders.setLocation(location);
//	responseHeaders.set("MyResponseHeader", "MyValue");
//
//	StringBuilder builder = new StringBuilder();
//    List<FieldError> errors = bindingResult.getFieldErrors();
//    List<String>errorMessages = new ArrayList<String>();
//    
//	List<Applicant> applicantList = new ArrayList<Applicant>();
//	applicantList.add(household.getApplicants().get(0));
//
//	if (!errors.isEmpty())
//	{
//		for (FieldError error : errors ) {
//	    	String errorMessage = error.getField() + " : " + error.getDefaultMessage();
//	    	System.out.println(errorMessage);
//	    	if (!errorMessages.contains(errorMessage)) {
//	    		errorMessages.add(errorMessage);
//	    		builder.append(errorMessage).append(". ");
//	    	}
//	    } 
//	    System.out.println("<<<<>>>");
//	    System.out.println(builder.toString().trim());
//	    System.out.println("<<<<>>>");
//		URI errorLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(household.getHouseholdId()).toUri();
//		HttpHeaders errorResponseHeaders = new HttpHeaders();
//		errorResponseHeaders.setLocation(errorLocation);
//		errorResponseHeaders.set("Error", builder.toString().trim());
//		return new ResponseEntity<List<Applicant>>(applicantList, errorResponseHeaders, HttpStatus.BAD_REQUEST);
//	}
//	
////	//this might be useful way to validate too somehow
////	try
////    {
////    	validate(input);
////    }
////    catch (Exception e) {
////    	e.printStackTrace();
////	    return new ResponseEntity<List<Applicant>>(HttpStatus.BAD_REQUEST);
////    }
//			
//	
//	DateTime effect = new DateTime(2017,  1,  1, 00, 00, 00, 000);
//	DateTime expire = new DateTime(2017, 12, 31, 23, 59, 59, 999);
//	
//	
//	System.out.println("effect   : "+effect+"("+effect.toDate());
//	System.out.println("expire   : "+expire+"("+expire.toDate());
//	System.out.println("Requested: "+household.getRequestedEffectiveDate()+"("+household.getRequestedEffectiveDate().toDate());		
//	
//	//Penalty penalty = penaltyCalculatorManager.createNewPenalty(household.getRequestedEffectiveDate(), household.getApplicants().size());
//	Penalty penalty = penaltyCalculatorManager.calculatePenalty(household);
//	household.setFilingStatusCode(Integer.toString(penalty.getFilingStatus().getWebServiceCode()));
//	household.setFilingStatusDesc(penalty.getFilingStatus().getWebServiceDescription());
//	household.setPenaltyExempt(penalty.getFilingStatus().getWebServiceCode() == FilingStatusEnum.Exempt.getWebServiceCode());
//	if (household.isPenaltyExempt()) {
//		household.setPenaltyAmt(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP));
//	}
//	else {
//		household.setPenaltyAmt(penalty.getPenaltyAmt().setScale(2,RoundingMode.HALF_UP));
//		System.out.println("household.getPenaltyAmt == "+household.getPenaltyAmt());
//		//household.setPenaltyAmt(new BigDecimal("123.45"));
//	}
//	System.out.println("getPenalty household: "+household);
//	//use this to return the json for testing
//	return new ResponseEntity<Household>(household, responseHeaders, HttpStatus.CREATED);
//	//use this in real life
//	//return ResponseEntity.created(location).build();
//}

