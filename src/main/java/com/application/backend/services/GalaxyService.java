package com.application.backend.services;

import com.application.backend.domains.Galaxy;

public interface GalaxyService {

	Galaxy createOrEditGalaxyByName(String galaxyName);

}
