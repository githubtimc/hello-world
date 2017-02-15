package com.bcbsnc.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bcbsnc.example.entity.Plan;

//@Transactional
public interface PlanRepository {//extends CrudRepository<Plan, Long> {
	//@Query
	//public List<Plan> findTest1(String planId, int planVarntId);
	//@Query
	//public List<Plan> findTest2(Date saleExpireDt);
}
