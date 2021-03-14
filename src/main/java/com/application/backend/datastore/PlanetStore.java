package com.application.backend.datastore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.application.backend.domains.Planet;

public class PlanetStore {
	private static Set<Planet> setOfPlanets;

	private PlanetStore() {
		setOfPlanets = new HashSet<>();
	}

	public synchronized static Set<Planet> getInstance() {
		if (setOfPlanets == null)
			setOfPlanets = new HashSet<>();
		return setOfPlanets;
	}

	public synchronized static Planet addPlanet(Planet planet) {
		if (planet != null) {
			if (!getInstance().contains(planet)) {
				getInstance().add(planet);

			} else {
				getInstance().remove(planet);
				getInstance().add(planet);
			}
			return findByPlanetID(planet.getPlanetID());
		}
		return null;
	}

	public synchronized static Planet findByPlanetID(String planetID) {
		Iterator<Planet> iterator = getInstance().iterator();
		Planet currentPlanet = null;
		while (iterator.hasNext()) {
			currentPlanet = iterator.next();
			if (currentPlanet.getPlanetID().equals(planetID))
				break;
		}
		return currentPlanet;
	}

}
