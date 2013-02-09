package co.vrings.entities;

import java.util.Date;

/**
 * Represents measurement of an attribute.
 * 
 * @author guligo
 */
public interface Measurement extends Entity {
	
	public Attribute getAttribute();
	
	public void setAttribute(Attribute attribute);
	
	public Entry getEntry();
	
	public void setEntry(Entry entry);
	
	public Date getDate();
	
	public void setDate(Date date);
	
	public Double getValue();
	
	public void setValue(Double value);
	
}
