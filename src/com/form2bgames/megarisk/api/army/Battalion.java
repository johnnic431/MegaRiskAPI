package com.form2bgames.megarisk.api.army;

public class Battalion {

	private Troop troop;
	private String owner;
	private int amount;
	private Position position;
	
	public Battalion(Troop troopp, String ownerr, Position positionn, int amountt) {
		/*
		this is a battalion, or some amount of a troop at some location with some owner
		*/
		amount = amountt;
		owner = ownerr;
		position = positionn;
		troop = troopp;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public Troop getTroop() {
		return troop;
	}
	
	public Position getPosition() {
		return position;
	}
	
	
}
