package com.application.backend.services;

import com.application.backend.dao.PlanetDAO;
import com.application.backend.dao.PlanetDAOImpl;
import com.application.backend.dao.RulerDAO;
import com.application.backend.dao.RulerDAOImpl;
import com.application.backend.domains.Planet;
import com.application.backend.domains.governments.Ruler;

public class PlanetServiceImpl implements PlanetService {

	private PlanetDAO planetDAO;
	private RulerDAO rulerDAO;

	@Override
	public Planet createOrEditPlanetByNameAndGalaxyID(String planetName, String galaxyID) {
		planetDAO = new PlanetDAOImpl();
		return planetDAO.createOrEditPlanetByNameAndGalaxyID(new Planet(planetName, galaxyID));
	}

	@Override
	public Ruler appointRuler(String planetID, String rulerName) {
		rulerDAO = new RulerDAOImpl();
		Ruler ruler = rulerDAO.createBeingByName(rulerName);
		planetDAO.appointRuler(ruler, planetID);
		return ruler;
	}

}
