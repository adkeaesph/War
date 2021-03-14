package com.application.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.application.backend.domains.defenses.Army;
import com.application.backend.domains.defenses.Battalion;
import com.application.backend.dto.ParticipatingArmies;

public class WarServiceImpl implements WarService {

	@Override
	public ParticipatingArmies startWar(Army defenderArmy, Army offenderArmy) {
		List<Map.Entry<String, Battalion>> offenderBattalions = new ArrayList<>(offenderArmy.getBattalionSets());
		List<Map.Entry<String, Battalion>> defenderBattalions = new ArrayList<>(defenderArmy.getBattalionSets());

		for (int battalionIndex = 0; battalionIndex < defenderBattalions.size(); battalionIndex++)
			neutralise(defenderBattalions.get(battalionIndex), offenderBattalions.get(battalionIndex));

		int battalionIndex = 0;
		while (battalionIndex < offenderBattalions.size()) {
			Map.Entry<String, Battalion> battalionEntry = offenderBattalions.get(battalionIndex);
			Battalion offenderBattalion = battalionEntry.getValue();
			if (offenderBattalion.getSize() != 0) {
				if (battalionIndex == 0)
					neutraliseBySubstitutionFromHigherToLowerRank(defenderArmy, battalionIndex + 1, battalionIndex,
							defenderBattalions, offenderBattalions);
				else if (battalionIndex == offenderBattalions.size() - 1)
					neutraliseBySubstitutionFromLowerToHigherRank(defenderArmy, battalionIndex - 1, battalionIndex,
							defenderBattalions, offenderBattalions);
				else {
					neutraliseBySubstitutionFromLowerToHigherRank(defenderArmy, battalionIndex - 1, battalionIndex,
							defenderBattalions, offenderBattalions);
					if (offenderBattalion.getSize() != 0)
						neutraliseBySubstitutionFromHigherToLowerRank(defenderArmy, battalionIndex + 1, battalionIndex,
								defenderBattalions, offenderBattalions);
				}
				break;
			}
			battalionIndex++;
		}
		return new ParticipatingArmies(defenderArmy, offenderArmy);
	}

	private void neutraliseBySubstitutionFromHigherToLowerRank(Army defenderArmy, int substitutableBattalionIndex,
			int intendedBattalionIndex, List<Map.Entry<String, Battalion>> defenderBattalions,
			List<Map.Entry<String, Battalion>> offenderBattalions) {
		defenderArmy.convertHigherToLowerRankedAndViceVersa(substitutableBattalionIndex, intendedBattalionIndex);
		neutralise(defenderBattalions, offenderBattalions, intendedBattalionIndex);
		defenderArmy.convertHigherToLowerRankedAndViceVersa(intendedBattalionIndex, substitutableBattalionIndex);
	}

	private void neutraliseBySubstitutionFromLowerToHigherRank(Army defenderArmy, int substitutableBattalionIndex,
			int intendedBattalionIndex, List<Map.Entry<String, Battalion>> defenderBattalions,
			List<Map.Entry<String, Battalion>> offenderBattalions) {
		defenderArmy.convertLowerToHigherRankedAndViceVersa(substitutableBattalionIndex, intendedBattalionIndex);
		neutralise(defenderBattalions, offenderBattalions, intendedBattalionIndex);
		defenderArmy.convertLowerToHigherRankedAndViceVersa(intendedBattalionIndex, substitutableBattalionIndex);
	}

	private void neutralise(Map.Entry<String, Battalion> defenderMapEntry,
			Map.Entry<String, Battalion> offenderMapEntry) {

		Battalion defenderBattalion = defenderMapEntry.getValue();
		Battalion offenderBattalion = offenderMapEntry.getValue();

		int defenderBattalionUnits = defenderBattalion.getSize();
		int strengthPerDefenderBattalionUnit = defenderBattalion.getStrengthPerUnit();

		int offenderBattalionUnits = offenderBattalion.getSize();
		int strengthPerOffenderBattalionUnit = offenderBattalion.getStrengthPerUnit();

		if (defenderBattalionUnits * strengthPerDefenderBattalionUnit >= offenderBattalionUnits
				* strengthPerOffenderBattalionUnit) {

			defenderBattalionUnits = (defenderBattalionUnits * strengthPerDefenderBattalionUnit
					- offenderBattalionUnits * strengthPerOffenderBattalionUnit) / strengthPerDefenderBattalionUnit;

			defenderBattalion.setSize(defenderBattalionUnits);
			offenderBattalion.setSize(0);
		} else {
			offenderBattalionUnits = (offenderBattalionUnits * strengthPerOffenderBattalionUnit
					- defenderBattalionUnits * strengthPerDefenderBattalionUnit) / strengthPerOffenderBattalionUnit;

			offenderBattalion.setSize(offenderBattalionUnits);
			defenderBattalion.setSize(0);
		}
	}

	private void neutralise(List<Map.Entry<String, Battalion>> defenderBattalions,
			List<Map.Entry<String, Battalion>> offenderBattalions, int indexOfBattalion) {

		Map.Entry<String, Battalion> currentDefenderMapEntry = defenderBattalions.get(indexOfBattalion);
		Map.Entry<String, Battalion> currentOffenderMapEntry = offenderBattalions.get(indexOfBattalion);

		Battalion currentDefenderBattalion = currentDefenderMapEntry.getValue();
		Battalion currentOffenderBattalion = currentOffenderMapEntry.getValue();
		int defenderBattalionUnits = currentDefenderBattalion.getSize();
		int strengthPerDefenderBattalionUnit = currentDefenderBattalion.getStrengthPerUnit();

		int offenderBattalionUnits = currentOffenderBattalion.getSize();
		int strengthPerOffenderBattalionUnit = currentOffenderBattalion.getStrengthPerUnit();

		if (defenderBattalionUnits * strengthPerDefenderBattalionUnit >= offenderBattalionUnits
				* strengthPerOffenderBattalionUnit) {

			defenderBattalionUnits = (defenderBattalionUnits * strengthPerDefenderBattalionUnit
					- offenderBattalionUnits * strengthPerOffenderBattalionUnit) / strengthPerDefenderBattalionUnit;

			currentDefenderBattalion.setSize(defenderBattalionUnits);
			currentOffenderBattalion.setSize(0);
		} else {
			offenderBattalionUnits = (offenderBattalionUnits * strengthPerOffenderBattalionUnit
					- defenderBattalionUnits * strengthPerDefenderBattalionUnit) / strengthPerOffenderBattalionUnit;

			currentOffenderBattalion.setSize(offenderBattalionUnits);
			currentDefenderBattalion.setSize(0);
		}
	}

}
