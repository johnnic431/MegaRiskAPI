package com.form2bgames.megarisk.api.army;

import java.util.ArrayList;

public class Troop {
	
	/*
	This class is the basis of ALL troops that behave like a standard unit (e.g. cannot use magic or anything like that)
	*/
	private ArrayList<TroopType> types;
	private String name;
	private float healthperunit;
	private float attackperunit;
	private int cutoffnumber;
	private float cuttoffscaleing;
	private int cutoffgradient;
	
		
	public Troop(ArrayList<TroopType> typess, String namee, float healthperunitt, float attackperunitt, int cutoffnumberr, float cuttoffscaleingg, int cutoffgradientt) {
		/*a lot to explain here. basically pass me your poor, your tired, and your hungry, and ill make it into a troop
		but really here goes
		types - basically a list of all types your troop has
		name - i hope you can tell what this means
		healthperunit - also hope this is obvious
		attackperunit - ditto
		cutoffnumber - number at which troops ATTACK (NOT defence!!!) will begin scaling based on...
		cuttoffscaleing - % troop number is muliplied by when deciding attack strength
		cutoffgradient - number at which minimum troop efficiency is reached
		*/
		types = typess;
		healthperunit = healthperunitt;
		attackperunit = attackperunitt;
		cutoffnumber = cutoffnumberr;
		cuttoffscaleing = cuttoffscaleingg;
		cutoffgradient = cutoffgradientt;
	}

	public ArrayList<TroopType> getTypes() {
		return types;
	}
	
	public float getHealth(int numberofunits) {
		return numberofunits*healthperunit;
	}
	
	public String getName() {
		return name;
	}
	
	public float getUnmodifiedAttack(int numberofunits) {
		if (numberofunits <= cutoffnumber) {
			return numberofunits*attackperunit;
		}
		else {
			int scalednumber = numberofunits-cutoffnumber;
			float attack = cutoffnumber*attackperunit;
			float scaleing = 1-((1-cuttoffscaleing)*(scalednumber/(cutoffgradient-cutoffnumber)));
			if (scaleing < cuttoffscaleing) {
				attack += cuttoffscaleing*scalednumber;
			}
			else {
				attack += scaleing*scalednumber;
			}
			return attack;
		}
	}
	
}
