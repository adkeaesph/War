package com.application.backend.domains.defenses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.application.backend.constants.CommonConstants;
import com.application.backend.constants.DefenceConstants;
import com.application.backend.constants.IdPrefix;
import com.application.backend.miniservices.BasicService;
import com.application.backend.miniservices.BasicServiceImpl;

public class Army {
	private String armyID;
	private String planetID;
	private Map<String, Battalion> battalions;
	private int totalStrength;

	public Army(String planetID) {
		armyID = IdPrefix.ARMY_ID_PREFIX + UUID.randomUUID().toString();
		this.planetID = planetID;
		battalions = new HashMap<>();
		totalStrength = 0;
	}

	public String getArmyID() {
		return armyID;
	}

	public String getPlanetID() {
		return planetID;
	}

	public Map<String, Battalion> getBattalions() {
		return battalions;
	}

	public Set<Map.Entry<String, Battalion>> getBattalionSets() {
		return battalions.entrySet();
	}

	public int getTotalStrength() {
		return totalStrength;
	}

	public void setPlanetID(String planetID) {
		this.planetID = planetID;
	}

	public void addBattalion(String category, Battalion battalion) {
		if (battalion == null)
			return;

		totalStrength += battalion.getStrengthPerUnit() * battalion.getSize();
		if (battalions.containsKey(category)) {
			Battalion categoricalBattalion = battalions.get(category);
			battalions.remove(category);
			categoricalBattalion.setSize(categoricalBattalion.getSize() + battalion.getSize());
			battalions.put(category, categoricalBattalion);
		} else
			battalions.put(category, battalion);
	}

	public void sortBattalionsByRanking() {
		List<Map.Entry<String, Battalion>> listOfBattalionEntries = new ArrayList<>(getBattalionSets());
		Collections.sort(listOfBattalionEntries, new RankingComparator());
		battalions = new LinkedHashMap<>();
		for (Map.Entry<String, Battalion> battalionEntry : listOfBattalionEntries)
			battalions.put(battalionEntry.getKey(), battalionEntry.getValue());
	}

	public void convertHigherToLowerRankedAndViceVersa(int indexOfSubstitutableBattalion,
			int indexOfIntendedBattalion) {
		BasicService basicService = new BasicServiceImpl();
		List<Map.Entry<String, Battalion>> listOfBattalionEntries = new ArrayList<>(getBattalionSets());
		int substitutionFactor = indexOfSubstitutableBattalion - indexOfIntendedBattalion;
		Map.Entry<String, Battalion> substitutableBattalionEntry = listOfBattalionEntries
				.get(indexOfSubstitutableBattalion);
		Battalion substitutableBattalion = substitutableBattalionEntry.getValue();
		int sizeOfIntendedBattalion = substitutableBattalion.getSize();
		if (basicService.isNumberPositive(substitutionFactor)) {
			sizeOfIntendedBattalion *= DefenceConstants.DEFAULT_CONVERSION_FACTOR_BETWEEN_ADJACENT_UNITS;
		} else if (basicService.isNumberNegative(substitutionFactor)) {
			substitutionFactor *= CommonConstants.NEGATIVE_TO_POSITIVE_MULTIPLIER;
			sizeOfIntendedBattalion /= DefenceConstants.DEFAULT_CONVERSION_FACTOR_BETWEEN_ADJACENT_UNITS;
		}
		substitutableBattalion.setSize(0);
		updateSizeOfIntendedBattalion(listOfBattalionEntries, indexOfIntendedBattalion, sizeOfIntendedBattalion);
	}

	public void convertLowerToHigherRankedAndViceVersa(int indexOfSubstitutableBattalion,
			int indexOfIntendedBattalion) {
		BasicService basicService = new BasicServiceImpl();
		List<Map.Entry<String, Battalion>> listOfBattalionEntries = new ArrayList<>(getBattalionSets());
		int substitutionFactor = indexOfSubstitutableBattalion - indexOfIntendedBattalion;
		Map.Entry<String, Battalion> substitutableBattalionEntry = listOfBattalionEntries
				.get(indexOfSubstitutableBattalion);
		Battalion substitutableBattalion = substitutableBattalionEntry.getValue();
		int sizeOfIntendedBattalion = substitutableBattalion.getSize();
		if (basicService.isNumberPositive(substitutionFactor)) {
			sizeOfIntendedBattalion *= DefenceConstants.DEFAULT_CONVERSION_FACTOR_BETWEEN_ADJACENT_UNITS;
			substitutableBattalion.setSize(0);
		} else if (basicService.isNumberNegative(substitutionFactor)) {
			substitutionFactor *= CommonConstants.NEGATIVE_TO_POSITIVE_MULTIPLIER;
			substitutableBattalion.setSize(
					sizeOfIntendedBattalion % DefenceConstants.DEFAULT_CONVERSION_FACTOR_BETWEEN_ADJACENT_UNITS);
			sizeOfIntendedBattalion /= DefenceConstants.DEFAULT_CONVERSION_FACTOR_BETWEEN_ADJACENT_UNITS;
		}
		updateSizeOfIntendedBattalion(listOfBattalionEntries, indexOfIntendedBattalion, sizeOfIntendedBattalion);
	}

	private void updateSizeOfIntendedBattalion(List<Map.Entry<String, Battalion>> listOfBattalionEntries, int index,
			int extraUnits) {
		Map.Entry<String, Battalion> intendedBattalionEntry = listOfBattalionEntries.get(index);
		Battalion intendedBattalion = intendedBattalionEntry.getValue();
		intendedBattalion.setSize(intendedBattalion.getSize() + extraUnits);
	}

	private class RankingComparator implements Comparator<Map.Entry<String, Battalion>> {
		@Override
		public int compare(Map.Entry<String, Battalion> entry1, Map.Entry<String, Battalion> entry2) {
			if (entry1.getValue().getRanking() < entry2.getValue().getRanking())
				return -1;
			if (entry1.getValue().getRanking() > entry2.getValue().getRanking())
				return 1;
			return 0;
		}
	}
}
