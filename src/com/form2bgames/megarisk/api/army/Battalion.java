package com.form2bgames.megarisk.api.army;

import com.form2bgames.megarisk.api.game.PlayableEntity;

public class Battalion {

	private Troop troop;
	private PlayableEntity owner;
	private int amount;
	private Position position;
	
	public Battalion(Troop troopp, PlayableEntity ownerr, Position positionn, int amountt) {
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
	
	public PlayableEntity getOwner() {
		return owner;
	}
	
	public Troop getTroop() {
		return troop;
	}
	
	public Position getPosition() {
		return position;
	}
	
	
}
