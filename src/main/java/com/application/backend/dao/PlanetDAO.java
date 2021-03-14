package com.application.backend.dao;

import com.application.backend.domains.Planet;
import com.application.backend.domains.governments.Ruler;

public interface PlanetDAO {

	public Planet createOrEditPlanetByNameAndGalaxyID(Planet planet);

	public void appointRuler(Ruler ruler, String planetID);
}
