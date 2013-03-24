package se.tipspromenad.entities;

/**
 * Represents single point on the geographical map.
 * 
 * @author guligo
 */
public interface Point {

	public Float getLatitude();

	public void setLatitude(Float latitude);

	public Float getLongitude();

	public void setLongitude(Float longitude);
	
}
