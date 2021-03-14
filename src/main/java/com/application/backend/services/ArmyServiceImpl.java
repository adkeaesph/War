package com.application.backend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.application.backend.domains.defenses.Army;
import com.application.backend.domains.defenses.Battalion;
import com.application.backend.exceptions.InvalidInputException;
import com.application.backend.exceptions.InvalidQuantityException;
import com.application.backend.constants.DefenceConstants;
import com.application.backend.constants.ExceptionMessages;
import com.application.backend.constants.InputOutputConstants;
import com.application.backend.constants.CommonConstants;

public class ArmyServiceImpl implements ArmyService {

	private InputOutputService inputOutputService;
	private ParserService parserService;

	@Override
	public void displayArmyStats(Army defenderArmy, Army offenderArmy) {
		System.out.println("---------Defender Army-----------");
		displayArmyStats(defenderArmy);
		System.out.println("---------Offender Army-----------");
		displayArmyStats(offenderArmy);
	}

	@Override
	public Army mobiliseDefaultDefenderArmy(Army defenderArmy) {
		defenderArmy.addBattalion(DefenceConstants.CATEGORY_HORSE,
				new Battalion(DefenceConstants.DEFAULT_STRENGTH_OF_LENGABOREAN_HORSES,
						DefenceConstants.LENGABOREAN_UNIT_STRENGTH, DefenceConstants.DEFAULT_HORSE_RANKING));
		defenderArmy.addBattalion(DefenceConstants.CATEGORY_ELEPHANT,
				new Battalion(DefenceConstants.DEFAULT_STRENGTH_OF_LENGABOREAN_ELEPHANTS,
						DefenceConstants.LENGABOREAN_UNIT_STRENGTH, DefenceConstants.DEFAULT_ELEPHANT_RANKING));
		defenderArmy.addBattalion(DefenceConstants.CATEGORY_SLING_GUN,
				new Battalion(DefenceConstants.DEFAULT_STRENGTH_OF_LENGABOREAN_SLING_GUNS,
						DefenceConstants.LENGABOREAN_UNIT_STRENGTH, DefenceConstants.DEFAULT_SLING_GUN_RANKING));
		defenderArmy.addBattalion(DefenceConstants.CATEGORY_ARMOURED_TANK,
				new Battalion(DefenceConstants.DEFAULT_STRENGTH_OF_LENGABOREAN_ARMOURED_TANKS,
						DefenceConstants.LENGABOREAN_UNIT_STRENGTH, DefenceConstants.DEFAULT_ARMOURED_TANK_RANKING));
		defenderArmy.sortBattalionsByRanking();
		return defenderArmy;
	}

	@Override
	public Army mobiliseDefaultOffenderArmy(Army offenderArmy)
			throws IOException, InvalidInputException, InvalidQuantityException {
		inputOutputService = new InputOutputServiceImpl();
		return populateArmy(offenderArmy, inputOutputService.extractIndividualBattalionStrengthsFromInputFile());
	}

	@Override
	public Army mobiliseDefaultOffenderArmy(Army offenderArmy, String filePath)
			throws IOException, InvalidInputException, InvalidQuantityException {
		inputOutputService = new InputOutputServiceImpl();
		return populateArmy(offenderArmy,
				inputOutputService.extractIndividualBattalionStrengthsFromInputFile(filePath));
	}

	@Override
	public List<Integer> getBattalionStrengths(Army army) {
		Set<Map.Entry<String, Battalion>> battalionEntries = army.getBattalionSets();
		Iterator<Map.Entry<String, Battalion>> iteratorForBattalionEntries = battalionEntries.iterator();

		List<Integer> strengthOfArmyBattalionWise = new ArrayList<>();
		while (iteratorForBattalionEntries.hasNext()) {
			Map.Entry<String, Battalion> currentMapEntry = iteratorForBattalionEntries.next();
			Battalion currentBattalion = currentMapEntry.getValue();
			strengthOfArmyBattalionWise.add(currentBattalion.getSize());
		}
		return strengthOfArmyBattalionWise;
	}

	@Override
	public List<String> getCategoriesRankwise(Army army) {
		Set<Map.Entry<String, Battalion>> battalionEntries = army.getBattalionSets();
		Iterator<Map.Entry<String, Battalion>> iteratorForBattalionEntries = battalionEntries.iterator();

		List<String> categories = new ArrayList<>();
		while (iteratorForBattalionEntries.hasNext()) {
			Map.Entry<String, Battalion> currentBattalion = iteratorForBattalionEntries.next();
			categories.add(currentBattalion.getKey());
		}
		return categories;
	}

	@Override
	public boolean isTheWholeArmyNeutralised(Army army) {
		Set<Map.Entry<String, Battalion>> battalionEntries = army.getBattalionSets();
		Iterator<Map.Entry<String, Battalion>> iteratorForBattalionEntries = battalionEntries.iterator();

		while (iteratorForBattalionEntries.hasNext()) {
			Map.Entry<String, Battalion> currentMapEntry = iteratorForBattalionEntries.next();
			Battalion currentBattalion = currentMapEntry.getValue();
			if (currentBattalion.getSize() != 0)
				return false;
		}
		return true;
	}

	private Army populateArmy(Army army, String[] arrayOfBattalionStrengths)
			throws InvalidInputException, InvalidQuantityException {
		parserService = new ParserServiceImpl();
		if (arrayOfBattalionStrengths[0].equals(InputOutputConstants.DEFAULT_INPUT_PREFIX)) {
			try {
				army = parserService.extractAndIncludeDefaultBattalion(army, arrayOfBattalionStrengths[1],
						DefenceConstants.DEFAULT_HORSE_RANKING);
				army = parserService.extractAndIncludeDefaultBattalion(army, arrayOfBattalionStrengths[2],
						DefenceConstants.DEFAULT_ELEPHANT_RANKING);
				army = parserService.extractAndIncludeDefaultBattalion(army, arrayOfBattalionStrengths[3],
						DefenceConstants.DEFAULT_ARMOURED_TANK_RANKING);
				army = parserService.extractAndIncludeDefaultBattalion(army, arrayOfBattalionStrengths[4],
						DefenceConstants.DEFAULT_SLING_GUN_RANKING);
				army.sortBattalionsByRanking();
			} catch (NumberFormatException exception) {
				throw new InvalidInputException(ExceptionMessages.INVALID_INPUT_FORMAT_MESSAGE);
			}
		} else {
			throw new InvalidInputException(ExceptionMessages.INVALID_INPUT_FORMAT_MESSAGE);
		}

		return army;
	}

	private void displayArmyStats(Army army) {
		Set<Map.Entry<String, Battalion>> battaionEntries = army.getBattalionSets();
		Iterator<Map.Entry<String, Battalion>> iteratorForBattalionEntries = battaionEntries.iterator();
		while (iteratorForBattalionEntries.hasNext()) {
			Map.Entry<String, Battalion> currentMapEntry = iteratorForBattalionEntries.next();
			String currentCategory = currentMapEntry.getKey();
			Battalion currentBattalion = currentMapEntry.getValue();
			System.out.println(currentCategory + CommonConstants.HYPHEN_DELIMETER + currentBattalion.getSize());
		}
	}

}
