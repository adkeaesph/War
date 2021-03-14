package com.application.backend.services;

import java.io.IOException;

import com.application.backend.dto.ParticipatingArmies;
import com.application.backend.exceptions.InvalidInputException;
import com.application.backend.exceptions.InvalidQuantityException;

public interface DefaultWarSituationService {

	ParticipatingArmies getDefaultOpponentArmies();

	ParticipatingArmies initiateDefaultWarSetup(ParticipatingArmies participatingArmies)
			throws IOException, InvalidInputException, InvalidQuantityException;

	ParticipatingArmies initiateDefaultWarSetup(ParticipatingArmies participatingArmies, String filePath)
			throws IOException, InvalidInputException, InvalidQuantityException;

	String getDefaultWarOutcome(ParticipatingArmies participatingArmies) throws IOException;

}
