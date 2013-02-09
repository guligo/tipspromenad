package co.vrings.entities;

/**
 * Represents measurement unit like kilogram, meter, etc.
 * 
 * @author guligo
 */
public interface MeasurementUnit {
	
	public void setName(String name);
	
	public String getName();
	
	public void setAbbreviation(String abbreviation);
	
	public String getAbbreviation();
	
}
