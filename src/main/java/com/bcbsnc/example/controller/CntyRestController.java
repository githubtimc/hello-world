package com.bcbsnc.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcbsnc.example.entity.Cnty;
import com.bcbsnc.example.repository.CntyRepository;

@RestController
public class CntyRestController {

	@Autowired
	CntyRepository cntyRepository;
	
	@RequestMapping("/counties")
	Collection<Cnty> getCounties() {
		return this.cntyRepository.findAll();
	}
	
	@RequestMapping("/fipCode")
	Cnty findByFipsCd() {
		return this.cntyRepository.findByFipsCd(3);
	}
	
}
