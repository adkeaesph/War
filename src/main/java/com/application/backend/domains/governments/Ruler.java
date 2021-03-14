package com.application.backend.domains.governments;

import java.util.UUID;

import com.application.backend.constants.IdPrefix;

public class Ruler {

	private String universalCitizenID;
	private String name;

	public Ruler(String name) {
		universalCitizenID = IdPrefix.UNIVERSAL_CITIZEN_ID_PREFIX + UUID.randomUUID().toString();
		this.name = name;
	}

	public String getUniversalCitizenID() {
		return universalCitizenID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
