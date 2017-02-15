package com.bcbsnc.example.manager;

import com.bcbsnc.example.model.Household;
import com.bcbsnc.example.model.Penalty;

public interface PenaltyCalculatorManager {

	//public Penalty createNewPenalty(DateTime requestedDate, int familySize);
	public Penalty calculatePenalty(Household household);
}
