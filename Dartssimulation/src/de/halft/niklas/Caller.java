package de.halft.niklas;

public abstract class Caller {
	String name;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public abstract void call();
}
