package com.form2bgames.megarisk.api.army;

import java.util.ArrayList;

public class TroopType {
	
	private String name;
	private ArrayList<TroopEffectivenessModifier> modifiers;
	
	public TroopType(String namee, ArrayList<TroopEffectivenessModifier> modifierz) {
		//basically just a container
		modifiers = modifierz;
		name = namee;
	}
	
	public ArrayList<TroopEffectivenessModifier> getModifiers() {
		
		return modifiers;
	}
	
	public String getName() {
		return name;
	}
	
	
}
