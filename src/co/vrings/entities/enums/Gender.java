package co.vrings.entities.enums;

/**
 * Represents user's gender.
 * 
 * @author guligo
 */
public enum Gender {
	
	MALE  ("Male"),
	FEMALE("Female");
	
	private String displayName;
	
	Gender(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
