package com.application.backend.services;

import java.io.IOException;
import java.util.List;

import com.application.backend.domains.defenses.Army;
import com.application.backend.exceptions.InvalidInputException;
import com.application.backend.exceptions.InvalidQuantityException;

public interface ArmyService {
	public void displayArmyStats(Army defenderArmy, Army offenderArmy);

	public Army mobiliseDefaultDefenderArmy(Army defenderArmy);

	public Army mobiliseDefaultOffenderArmy(Army offenderArmy)
			throws IOException, InvalidInputException, InvalidQuantityException;

	public Army mobiliseDefaultOffenderArmy(Army offenderArmy, String filePath)
			throws IOException, InvalidInputException, InvalidQuantityException;

	public List<Integer> getBattalionStrengths(Army army);

	public List<String> getCategoriesRankwise(Army army);

	boolean isTheWholeArmyNeutralised(Army army);

}
