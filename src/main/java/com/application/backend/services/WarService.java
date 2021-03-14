package com.application.backend.services;

import com.application.backend.domains.defenses.Army;
import com.application.backend.dto.ParticipatingArmies;

public interface WarService {
	ParticipatingArmies startWar(Army defenderArmy, Army offenderArmy);
}
