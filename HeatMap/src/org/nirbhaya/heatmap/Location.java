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
	long latitude;
	long longitude;


	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
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
