package com.application.backend.dao;

import com.application.backend.datastore.RulerStore;
import com.application.backend.domains.governments.Ruler;

public class RulerDAOImpl implements RulerDAO {

	@Override
	public Ruler createBeingByName(String rulerName) {
		return RulerStore.addRuler(new Ruler(rulerName));
	}

}
