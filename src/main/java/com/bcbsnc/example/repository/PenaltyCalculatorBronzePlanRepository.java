package com.bcbsnc.example.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bcbsnc.example.entity.PnltBronzePlanPrem;

public interface PenaltyCalculatorBronzePlanRepository extends JpaRepository <PnltBronzePlanPrem, Long> {
	@Query
	List<PnltBronzePlanPrem> findByDateandFamilySize(java.util.Date requestDate, int familySize) ;
	
	@Query
	List<PnltBronzePlanPrem> findByDateandFamilySize2(Date date, BigDecimal t);

	//List<PnltBronzePlanPrem> findByDateandFamilySize(java.util.Date requestDate, BigDecimal familySize) ;

}
