package com.bcbsnc.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bcbsnc.example.entity.PnltFileLvl;

public interface PenaltyFilingLevelRepository extends JpaRepository <PnltFileLvl, Long> {
	@Query
	List<PnltFileLvl> findByDateAndFilingStatus(java.util.Date requestDate, int fileStatusCd);

}
