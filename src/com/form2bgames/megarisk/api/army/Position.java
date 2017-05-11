package com.form2bgames.megarisk.api.army;

public class Position {
	private float latitude,longitude,altitude;
	public Position(float latitude,float longitude,float altitude) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
		
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getAltitude() {
		return altitude;
	}
	
	
}
