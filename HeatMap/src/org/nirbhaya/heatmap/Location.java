package org.nirbhaya.heatmap;
/**
 * 
 */

/**
 * @author romil
 *
 */
public class Location {
	String locationName;
	double latitude;
	double longitude;


	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Location(String locationName, long latitude, long longitude) {
		super();
		this.locationName = locationName;
		this.latitude = latitude;
		this.longitude = longitude;
	} 
	
	public Location(){
		
	}
	
	@Override
	public String toString() {
		return locationName;
	}

}
