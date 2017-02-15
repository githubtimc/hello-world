package com.bcbsnc.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bcbsnc.example.entity.Cnty;


public interface CntyRepository extends JpaRepository <Cnty, Long> {
	
		Collection<Cnty> findByCntyNm(String cntyNm) ;
	
		public Cnty findBy(long fips);
		
		@Query
		Cnty findByFipsCd(long fips_cnty_cd);
}
