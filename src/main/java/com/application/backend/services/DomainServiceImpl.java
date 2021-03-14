package com.application.backend.services;

import com.application.backend.domains.Galaxy;
import com.application.backend.domains.Planet;
import com.application.backend.domains.defenses.Army;

public class DomainServiceImpl implements DomainService {

	private GalaxyService galaxyService;
	private PlanetService planetService;

	@Override
	public Army initialisePlanetForWar(String galaxyName, String planetName, String rulerName) {
		galaxyService = new GalaxyServiceImpl();
		planetService = new PlanetServiceImpl();

		Galaxy galaxy = galaxyService.createOrEditGalaxyByName(galaxyName);
		Planet planet = planetService.createOrEditPlanetByNameAndGalaxyID(planetName, galaxy.getGalaxyID());
		planetService.appointRuler(planet.getPlanetID(), rulerName);
		return new Army(planet.getPlanetID());
	}

}
