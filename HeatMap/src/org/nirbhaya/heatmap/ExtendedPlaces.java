package org.nirbhaya.heatmap;

public class ExtendedPlaces 
{
	String place;
	String state;
	String areaType;
	String population;


	public String getPlace() 
	{
		return place;
	}
	public String getState() 
	{
		return state;
	}
	public String getareaType() 
	{
		return areaType;
	}
	public String getPopulation() 
	{
		return population;
	}

	public void setPlace(String place) 
	{
		this.place = place;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public void setAreaType(String areaType) 
	{
		this.areaType = areaType;
	}

	public void setPopulation(String population) 
	{
		this.population = population;
	}


	public ExtendedPlaces(String place, String state, String areaType, String population) 
	{
		// super();
		this.place = place;
		this.state = state;
		this.areaType = areaType;
		this.population = population;

	}

}
