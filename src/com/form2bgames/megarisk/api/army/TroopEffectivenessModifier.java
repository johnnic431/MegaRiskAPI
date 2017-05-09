package com.form2bgames.megarisk.api.army;

public interface TroopEffectivenessModifier {

	//because this is all that a modifier should do
	public float getModifier(Battalion attacker, Battalion defender);
	
	
}
