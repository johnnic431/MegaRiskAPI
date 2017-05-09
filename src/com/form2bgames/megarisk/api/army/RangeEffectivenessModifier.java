package com.form2bgames.megarisk.api.army;

public interface RangeEffectivenessModifier {
	
	//this is in similar spirit to the troop effectiveness modifiers. obviously, the position system is needed to do anything with this
	
	public float getRange(Battalion attacker, Battalion defender);
	
	
}
