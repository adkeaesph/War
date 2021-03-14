package com.application.backend.datastore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.application.backend.domains.governments.Ruler;

public class RulerStore {

	private static Set<Ruler> setOfRulers;

	private RulerStore() {
		setOfRulers = new HashSet<>();
	}

	public synchronized static Set<Ruler> getInstance() {
		if (setOfRulers == null)
			setOfRulers = new HashSet<>();
		return setOfRulers;
	}

	public synchronized static Ruler addRuler(Ruler ruler) {
		if (ruler != null) {
			if (!getInstance().contains(ruler))
				getInstance().add(ruler);
			else {
				getInstance().remove(ruler);
				getInstance().add(ruler);
			}
			return findByRulerID(ruler.getUniversalCitizenID());
		}
		return null;
	}

	private synchronized static Ruler findByRulerID(String universalCitizenID) {
		Iterator<Ruler> iterator = getInstance().iterator();
		Ruler currentRuler = null;
		while (iterator.hasNext()) {
			currentRuler = iterator.next();
			if (currentRuler.getUniversalCitizenID().equals(universalCitizenID))
				break;
		}
		return currentRuler;
	}
}
