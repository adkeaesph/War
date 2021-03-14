package com.application.backend.services;

import com.application.backend.dao.GalaxyDAO;
import com.application.backend.dao.GalaxyDAOImpl;
import com.application.backend.domains.Galaxy;

public class GalaxyServiceImpl implements GalaxyService {

	private GalaxyDAO galaxyDAO;

	@Override
	public Galaxy createOrEditGalaxyByName(String galaxyName) {
		galaxyDAO = new GalaxyDAOImpl();
		return galaxyDAO.createOrEditGalaxyByName(galaxyName);
	}

}
