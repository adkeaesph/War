package com.application.backend.domains;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.application.backend.constants.IdPrefix;

public class Planet {
	private String planetID;
	private String name;
	private String rulerUID; // universal ID of the ruler of the planet
	private String galaxyID;

	public Planet(String name, String galaxyID) {
		planetID = IdPrefix.PLANET_ID_PREFIX + UUID.randomUUID().toString();
		this.name = name;
		this.galaxyID = galaxyID;
	}

	public String getPlanetID() {
		return planetID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRulerUID() {
		return rulerUID;
	}

	public void setRulerUID(String rulerUID) {
		this.rulerUID = rulerUID;
	}

	public String getGalaxyID() {
		return galaxyID;
	}

	public void setGalaxyID(String galaxyID) {
		this.galaxyID = galaxyID;
	}

	public Galaxy getGalaxy(Set<Galaxy> galaxies) {
		Iterator<Galaxy> iterator = galaxies.iterator();
		Galaxy currentGalaxy;
		while (iterator.hasNext()) {
			currentGalaxy = iterator.next();
			if (currentGalaxy.getGalaxyID().equals(galaxyID))
				return currentGalaxy;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Planet [planetID=" + planetID + ", name=" + name + ", rulerUID=" + rulerUID + "]";
	}

}
