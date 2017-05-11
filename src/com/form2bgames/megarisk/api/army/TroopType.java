package com.form2bgames.megarisk.api.army;

import java.util.ArrayList;

public class TroopType {
	
	private String name;
	private ArrayList<TroopEffectivenessModifier> modifiers;
	private ArrayList<RangeEffectivenessModifier> rangemodifiers;
	
	public TroopType(String namee, ArrayList<TroopEffectivenessModifier> modifierz, ArrayList<RangeEffectivenessModifier> rmodifierz) {
		//basically just a container for the modifiers, to make things more convenient
		modifiers = modifierz;
		name = namee;
		rangemodifiers = rmodifierz;
	}
	
	public ArrayList<TroopEffectivenessModifier> getModifiers() {
		
		return modifiers;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<RangeEffectivenessModifier> getRangeModifiers() {
		return rangemodifiers;
	}
	
}
