package com.application.backend.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.application.backend.constants.ExceptionMessages;
import com.application.backend.constants.InputOutputConstants;
import com.application.backend.constants.CommonConstants;
import com.application.backend.domains.defenses.Army;

public class InputOutputServiceImpl implements InputOutputService {

	@Override
	public String[] extractIndividualBattalionStrengthsFromInputFile() throws IOException {
		BufferedReader bufferedReader = getBufferedReader(
				getCurrentDirectory() + InputOutputConstants.INPUT_FILE_RELATIVE_PATH);
		String[] words = getInputStringthroughReader(bufferedReader);
		bufferedReader.close();
		return words;
	}

	@Override
	public String[] extractIndividualBattalionStrengthsFromInputFile(String filePath) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = getBufferedReader(getCurrentDirectory() + filePath);
		} catch (FileNotFoundException exception) {
			try {
				bufferedReader = getBufferedReader(filePath);
			} catch (FileNotFoundException exception2) {
				throw new FileNotFoundException(ExceptionMessages.INVALID_RELATIVE_FILEPATH_MESSAGE);
			}
		}
		String[] words = getInputStringthroughReader(bufferedReader);
		bufferedReader.close();
		return words;
	}

	@Override
	public String getOutcomeWithStats(List<Integer> initialStrengthRankwiseOfArmy,
			List<Integer> finalStrengthRankwiseOfArmy, List<String> categories, Army offenderArmy) {
		String battalionStats = CommonConstants.EMPTY_STRING;
		for (int strengthIndex = 0; strengthIndex < initialStrengthRankwiseOfArmy.size(); strengthIndex++) {
			battalionStats += (initialStrengthRankwiseOfArmy.get(strengthIndex)
					- finalStrengthRankwiseOfArmy.get(strengthIndex)) + categories.get(strengthIndex)
					+ CommonConstants.SINGLE_SPACE_DELIMETER;
		}

		return getOutcome(battalionStats, offenderArmy);
	}

	private String getOutcome(String battalionStats, Army army) {
		ArmyService armyService = new ArmyServiceImpl();
		if (armyService.isTheWholeArmyNeutralised(army))
			battalionStats = InputOutputConstants.OUTCOME_VICTORY + battalionStats;
		else
			battalionStats = InputOutputConstants.OUTCOME_LOSS + battalionStats;
		return battalionStats;
	}

	private BufferedReader getBufferedReader(String filePath) throws FileNotFoundException {
		return new BufferedReader(new FileReader(filePath));
	}

	private String getCurrentDirectory() {
		return System.getProperty(InputOutputConstants.USER_DIR);
	}

	private String[] getInputStringthroughReader(BufferedReader bufferedReader) throws IOException {
		String inputString;
		String[] words = null;
		while ((inputString = bufferedReader.readLine()) != null) {
			words = inputString.split(CommonConstants.SPACES_DELIMETER);
		}
		return words;
	}
}
