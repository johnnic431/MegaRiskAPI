package com.form2bgames.megarisk.api.army;

import java.util.ArrayList;

public class Troop {
	
	/*
	This class is the basis of ALL troops that behave like a standard unit (e.g. cannot use magic or anything like that)
	*/
		
	public Troop(ArrayList<String> types, String name, float healthperunit, float attackperunit, int cutoffnumber, float cutoffscaling, int cutoffgradient) {
		/*a lot to explain here. basically pass me your poor, your tired, and your hungry, and ill make it into a troop
		but really here goes
		types - basically a list of all types your troop has
		name - i hope you can tell what this means
		healthperunit - also hope this is obvious
		attackperunit - ditto
		cutoffnumber - number at which troops ATTACK (NOT defence!!!) will begin scaling based on...
		cuttoffscaling - % troop number is muliplied by when deciding attack strength
		cuttoffgradient - number at which minimum troop efficiency is reached
		*/
	}
	
	
}
