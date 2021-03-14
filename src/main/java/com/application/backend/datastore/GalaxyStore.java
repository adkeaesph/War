package com.application.backend.datastore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.application.backend.domains.Galaxy;

public class GalaxyStore {
	private static Set<Galaxy> setOfGalaxies;

	private GalaxyStore() {
		setOfGalaxies = new HashSet<>();
	}

	public synchronized static Set<Galaxy> getInstance() {
		if (setOfGalaxies == null)
			setOfGalaxies = new HashSet<>();
		return setOfGalaxies;
	}

	public synchronized static Galaxy addGalaxy(Galaxy galaxy) {
		if (galaxy != null) {
			if (!getInstance().contains(galaxy))
				getInstance().add(galaxy);
			else {
				getInstance().remove(galaxy);
				getInstance().add(galaxy);
			}
			return findByGalaxyID(galaxy.getGalaxyID());
		}
		return null;
	}

	public synchronized static Galaxy findByGalaxyName(String galaxyName) {
		if (!getInstance().contains(new Galaxy(galaxyName)))
			return null;
		Iterator<Galaxy> iterator = getInstance().iterator();
		Galaxy currentGalaxy = null;
		while (iterator.hasNext()) {
			currentGalaxy = iterator.next();
			if (currentGalaxy.getName().equals(galaxyName))
				break;
		}
		return currentGalaxy;
	}

	public synchronized static Galaxy findByGalaxyID(String galaxyID) {
		Iterator<Galaxy> iterator = getInstance().iterator();
		Galaxy currentGalaxy = null;
		while (iterator.hasNext()) {
			currentGalaxy = iterator.next();
			if (currentGalaxy.getGalaxyID().equals(galaxyID))
				break;
		}
		return currentGalaxy;
	}
}
