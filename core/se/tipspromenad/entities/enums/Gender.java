package se.tipspromenad.entities.enums;

/**
 * Represents user's gender.
 * 
 * @author guligo
 * @author pavelefimov
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
