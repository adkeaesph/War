package com.application.backend.services;

import com.application.backend.domains.defenses.Army;
import com.application.backend.exceptions.InvalidQuantityException;

public interface ParserService {
	Army extractAndIncludeBattalion(Army army, String battalionString, int powerPerBattalionUnit, int rankingOfBatttalion)
			throws NumberFormatException, InvalidQuantityException;

	Army extractAndIncludeDefaultBattalion(Army army, String battalionString, int ranking)
			throws NumberFormatException, InvalidQuantityException;
}
