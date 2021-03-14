package com.application.backend.dto;

import com.application.backend.domains.defenses.Army;

public class ParticipatingArmies {
	private Army defenderArmy;
	private Army offenderArmy;

	public ParticipatingArmies(Army defenderArmy, Army offenderArmy) {
		this.defenderArmy = defenderArmy;
		this.offenderArmy = offenderArmy;
	}

	public Army getDefenderArmy() {
		return defenderArmy;
	}

	public Army getOffenderArmy() {
		return offenderArmy;
	}
}
