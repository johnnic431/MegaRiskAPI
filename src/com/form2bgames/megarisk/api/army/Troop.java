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
	private float range;
		
	/**
	 * @param typess basically a list of all types your troop has
	 * @param namee i hope you can tell what this means
	 * @param healthperunitt also hope this is obvious
	 * @param attackperunitt ditto
	 * @param rangee range in km
	 * @param cutoffnumberr number at which troops ATTACK (NOT defence!!!) will begin scaling based on...
	 * @param cuttoffscaleingg % troop number is muliplied by when deciding attack strength
	 * @param cutoffgradientt number at which minimum troop efficiency is reached
	 */
	public Troop(ArrayList<TroopType> typess, String namee, float healthperunitt, float attackperunitt,float rangee, int cutoffnumberr, float cuttoffscaleingg, int cutoffgradientt) {
		types = typess;
		healthperunit = healthperunitt;
		attackperunit = attackperunitt;
		cutoffnumber = cutoffnumberr;
		cuttoffscaleing = cuttoffscaleingg;
		cutoffgradient = cutoffgradientt;
		range = rangee;
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
	
	/*
	|------------|--------------|-------------
	0           cut            grad
	 linear         psuedo        linear
	 				quadratic
	*/
	
	public float getUnmodifiedAttack(int numberofunits) {
		if (numberofunits <= cutoffnumber) {
			return numberofunits*attackperunit;
		}
		else {
			int scalednumber = numberofunits-cutoffnumber;
			float attack = cutoffnumber*attackperunit;
			float scaleing = 1-
					((1-cuttoffscaleing)
					*(scalednumber/(cutoffgradient-cutoffnumber))	);
			if (scaleing < cuttoffscaleing) {
				attack += cuttoffscaleing*scalednumber;
			}
			else {
				attack += scaleing*scalednumber;
			}
			return attack;
		}
	}

	public float getRange() {
		return range;
	}
	
	//public getRange
	
}
