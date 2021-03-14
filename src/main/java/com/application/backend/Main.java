package com.application.backend;

import com.application.backend.constants.ExceptionMessages;
import com.application.backend.constants.FileExtensionConstants;
import com.application.backend.constants.InputOutputConstants;
import com.application.backend.dto.ParticipatingArmies;
import com.application.backend.exceptions.InvalidCommandFormatException;
import com.application.backend.exceptions.InvalidFileExtensionException;
import com.application.backend.services.DefaultWarSituationService;
import com.application.backend.services.DefaultWarSituationServiceImpl;

public class Main {

	public static void main(String[] args) throws Exception {
		DefaultWarSituationService defaultWarSituationService = new DefaultWarSituationServiceImpl();
		ParticipatingArmies participatingArmies = defaultWarSituationService.getDefaultOpponentArmies();
		if (args.length == InputOutputConstants.ARGUMENT_LENGTH_ZER0)
			participatingArmies = defaultWarSituationService.initiateDefaultWarSetup(participatingArmies);
		else if (args.length == InputOutputConstants.ARGUMENT_LENGTH_ONE) {
			if (args[InputOutputConstants.ARGUMENT_LENGTH_ZER0].endsWith(FileExtensionConstants.EXTENSION_TXT))
				participatingArmies = defaultWarSituationService.initiateDefaultWarSetup(participatingArmies,
						args[InputOutputConstants.ARGUMENT_LENGTH_ZER0]);
			else
				throw new InvalidFileExtensionException(ExceptionMessages.INVALID_FILE_EXTENSION_MESSAGE);
		} else
			throw new InvalidCommandFormatException(ExceptionMessages.INVALID_COMMAND_FORMAT_MESSAGE);
		System.out.println(defaultWarSituationService.getDefaultWarOutcome(participatingArmies));
	}

}
