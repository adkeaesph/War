package com.application.backend.services;

import com.application.backend.domains.defenses.Army;

public interface DomainService {
	public Army initialisePlanetForWar(String galaxyName, String planetName, String rulerName);
}
