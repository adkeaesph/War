package com.application.backend.services;

import com.application.backend.domains.Planet;
import com.application.backend.domains.governments.Ruler;

public interface PlanetService {

	Planet createOrEditPlanetByNameAndGalaxyID(String planetName, String galaxyID);

	Ruler appointRuler(String planetID, String rulerName);

}
