package com.bcbsnc.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bcbsnc.example.entity.PnltCalc;


public interface PenaltyCalculatorRepository extends JpaRepository <PnltCalc, Long> {
	@Query
	List<PnltCalc> findByDate(java.util.Date requestDate) ;
	
}
