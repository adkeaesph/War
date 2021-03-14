package com.application.backend.dao;

import com.application.backend.domains.Galaxy;

public interface GalaxyDAO {
	public void createOrEditGalaxy(Galaxy galaxy);

	public Galaxy createOrEditGalaxyByName(String galaxyName);
}
