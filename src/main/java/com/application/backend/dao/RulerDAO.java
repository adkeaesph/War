package com.application.backend.dao;

import com.application.backend.domains.governments.Ruler;

public interface RulerDAO {

	Ruler createBeingByName(String rulerName);

}
