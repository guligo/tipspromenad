package co.vrings.entities;

/**
 * Represents general sports activity. For example, gym, swimming, footbal, etc.
 * 
 * @author guligo
 */
public interface Sport extends Entity {
	
	public String getName();
	
	public void setName(String name);
	
	public String getImage();
	
	public void setImage(String image);
	
}
