package com.form2bgames.megarisk.api.game;

import com.form2bgames.megarisk.api.army.Position;

public class GameWorld {
	
	private int R=6371;//Radius of thing
	
	/**
	 * @param lat1 latitude 1
	 * @param lat2 latitude 2
	 * @param lon1 longitude 1
	 * @param lon2 longitude 2
	 * @param el1 height 1 (km)
	 * @param el2 height 2 (km)
	 * @return the distance in kilometers between points
	 */
	public float distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

		el1 = el1 / 1.609344;
		el2 = el2 / 1.609344;
		
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c;

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return (float)(Math.sqrt(distance) * 1.609344);
	}
	
	public float distance(Position p1,Position p2){
		return distance(p1.getLatitude(),p2.getLatitude(),p1.getLongitude(),p2.getLongitude(),p1.getAltitude(),p2.getAltitude());
	}
}
