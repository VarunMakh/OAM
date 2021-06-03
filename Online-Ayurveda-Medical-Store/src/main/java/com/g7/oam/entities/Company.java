package com.g7.oam.entities;

public enum Company {

	DIVIS("Divis Laboratories Ltd."), SUN("Sun Pharmaceutical Industries Ltd."), REDDY("Dr. Reddys Laboratories Ltd."),
	CIPLA("Cipla Ltd."), BIOCON("Biocon Ltd.");

	String name;

	Company(String name) {
		this.name = name;
	}

}
