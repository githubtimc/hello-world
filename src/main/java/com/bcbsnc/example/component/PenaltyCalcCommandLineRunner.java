package com.bcbsnc.example.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bcbsnc.example.entity.PnltCalc;
import com.bcbsnc.example.repository.PenaltyCalculatorRepository;

@Component
public class PenaltyCalcCommandLineRunner implements CommandLineRunner{

	@Autowired
	PenaltyCalculatorRepository penaltyCalculatorRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("HELLO FROM PenaltyCalcCommandLineRunner");
		for (PnltCalc b: this.penaltyCalculatorRepository.findAll() ) {
			System.out.println(b.toString());
		}
	}
}
