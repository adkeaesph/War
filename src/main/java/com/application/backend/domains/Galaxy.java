package com.application.backend.domains;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.application.backend.constants.IdPrefix;

public class Galaxy {
	private String galaxyID;
	private String name;
	private Set<String> planetIDs;

	public Galaxy(String name) {
		galaxyID = IdPrefix.GALAXY_ID_PREFIX + UUID.randomUUID().toString();
		planetIDs = new HashSet<>();
		this.name = name;
	}

	public Galaxy(String name, Set<String> planetIDs) {
		this(name);
		this.planetIDs = planetIDs;
	}

	public String getGalaxyID() {
		return galaxyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getPlanetIDs() {
		return planetIDs;
	}

	public void setPlanetIDs(Set<String> planetIDs) {
		this.planetIDs = planetIDs;
	}

	public void addPlanetID(String planetID) {
		planetIDs.add(planetID);
	}

	public void removePlanetID(String planetID) {
		planetIDs.remove(planetID);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Galaxy other = (Galaxy) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String description = "Galaxy [galaxyID=" + galaxyID + ", name=" + name + "]\n";
		description += "List of planet IDs of the planets of this galaxy : \n";
		for (String planetID : planetIDs)
			description += planetID + " ";
		return description;
	}

}
