package com.application.backend.dao;

import com.application.backend.datastore.GalaxyStore;
import com.application.backend.domains.Galaxy;

public class GalaxyDAOImpl implements GalaxyDAO {

	@Override
	public void createOrEditGalaxy(Galaxy galaxy) {
		GalaxyStore.addGalaxy(galaxy);
	}

	@Override
	public Galaxy createOrEditGalaxyByName(String galaxyName) {
		return GalaxyStore.addGalaxy(new Galaxy(galaxyName));
	}

}
