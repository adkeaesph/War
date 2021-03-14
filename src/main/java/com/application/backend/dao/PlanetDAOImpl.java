package com.application.backend.dao;

import com.application.backend.datastore.GalaxyStore;
import com.application.backend.datastore.PlanetStore;
import com.application.backend.domains.Galaxy;
import com.application.backend.domains.Planet;
import com.application.backend.domains.governments.Ruler;

public class PlanetDAOImpl implements PlanetDAO {

	@Override
	public Planet createOrEditPlanetByNameAndGalaxyID(Planet planet) {
		planet = PlanetStore.addPlanet(planet);
		Galaxy galaxy = GalaxyStore.findByGalaxyID(planet.getGalaxyID());
		galaxy.addPlanetID(planet.getPlanetID());
		return planet;
	}

	@Override
	public void appointRuler(Ruler ruler, String planetID) {
		Planet planet = PlanetStore.findByPlanetID(planetID);
		planet.setRulerUID(ruler.getUniversalCitizenID());
	}

}
