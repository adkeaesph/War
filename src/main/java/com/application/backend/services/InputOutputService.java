package com.application.backend.services;

import java.io.IOException;
import java.util.List;

import com.application.backend.domains.defenses.Army;

public interface InputOutputService {

	String[] extractIndividualBattalionStrengthsFromInputFile() throws IOException;

	String[] extractIndividualBattalionStrengthsFromInputFile(String filePath) throws IOException;

	String getOutcomeWithStats(List<Integer> initialStrengthRankwiseOfArmy, List<Integer> finalStrengthRankwiseOfArmy,
			List<String> categories, Army offenderArmy);

}
