package com.application.backend.services;

import java.io.IOException;
import java.util.List;

import com.application.backend.constants.DomainConstants;
import com.application.backend.domains.defenses.Army;
import com.application.backend.dto.ParticipatingArmies;
import com.application.backend.exceptions.InvalidInputException;
import com.application.backend.exceptions.InvalidQuantityException;

public class DefaultWarSituationServiceImpl implements DefaultWarSituationService {
	
	@Override
	public ParticipatingArmies getDefaultOpponentArmies() {
		DomainService domainService = new DomainServiceImpl();

		Army defenderArmy = domainService.initialisePlanetForWar(DomainConstants.DEFAULT_GALAXY,
				DomainConstants.DEFAULT_DEFENDER_PLANET, DomainConstants.DEFAULT_RULER_OF_DEFENDER_PLANET);
		Army offenderArmy = domainService.initialisePlanetForWar(DomainConstants.DEFAULT_GALAXY,
				DomainConstants.DEFAULT_OFFENDER_PLANET, DomainConstants.DEFAULT_RULER_OF_OFFENDER_PLANET);

		return new ParticipatingArmies(defenderArmy, offenderArmy);
	}
	
	@Override
	public ParticipatingArmies initiateDefaultWarSetup(ParticipatingArmies participatingArmies)
			throws IOException, InvalidInputException, InvalidQuantityException {
		ArmyService armyService = new ArmyServiceImpl();
		Army defenderArmy = armyService.mobiliseDefaultDefenderArmy(participatingArmies.getDefenderArmy());
		Army offenderArmy = armyService.mobiliseDefaultOffenderArmy(participatingArmies.getOffenderArmy());
		return new ParticipatingArmies(defenderArmy, offenderArmy);
	}

	@Override
	public ParticipatingArmies initiateDefaultWarSetup(ParticipatingArmies participatingArmies, String filePath)
			throws IOException, InvalidInputException, InvalidQuantityException {
		ArmyService armyService = new ArmyServiceImpl();
		Army defenderArmy = armyService.mobiliseDefaultDefenderArmy(participatingArmies.getDefenderArmy());
		Army offenderArmy = armyService.mobiliseDefaultOffenderArmy(participatingArmies.getOffenderArmy(), filePath);
		return new ParticipatingArmies(defenderArmy, offenderArmy);
	}
	
	@Override
	public String getDefaultWarOutcome(ParticipatingArmies participatingArmies) throws IOException {
		ArmyService armyService = new ArmyServiceImpl();
		WarService warService = new WarServiceImpl();
		InputOutputService inputOutputService = new InputOutputServiceImpl();

		List<Integer> initialStrengthRankwiseOfDefenderArmy = armyService
				.getBattalionStrengths(participatingArmies.getDefenderArmy());
		participatingArmies = warService.startWar(participatingArmies.getDefenderArmy(),
				participatingArmies.getOffenderArmy());
		List<Integer> finalStrengthRankwiseOfDefenderArmy = armyService
				.getBattalionStrengths(participatingArmies.getDefenderArmy());

		List<String> categories = armyService.getCategoriesRankwise(participatingArmies.getDefenderArmy());

		return inputOutputService.getOutcomeWithStats(initialStrengthRankwiseOfDefenderArmy,
				finalStrengthRankwiseOfDefenderArmy, categories, participatingArmies.getOffenderArmy());
	}
	
}
