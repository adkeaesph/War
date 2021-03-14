package com.application.backend.domains.defenses;

public class Battalion {
	private int size;
	private int strengthPerUnit;
	private int ranking;

	public Battalion(int size, int strengthPerUnit, int ranking) {
		super();
		this.size = size;
		this.strengthPerUnit = strengthPerUnit;
		this.ranking = ranking;
	}

	public int getSize() {
		return size;
	}

	public int getStrengthPerUnit() {
		return strengthPerUnit;
	}

	public int getRanking() {
		return ranking;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setStrengthPerUnit(int strengthPerUnit) {
		this.strengthPerUnit = strengthPerUnit;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
}
