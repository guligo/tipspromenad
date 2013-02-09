package co.vrings.entities.enums;

/**
 * Represents user's mood while doing sports activities.
 * 
 * @author guligo
 */
public enum Mood {
	
	NOT_SPECIFIED(0, "Not specified"),
	VERY_BAD     (1, "Very bad"),
	BAD          (2, "Bad"),
	NEUTRAL      (3, "Neutral"),
	GOOD         (4, "Good"),
	VERY_GOOD    (5, "Very good");
	
	private int value;
	private String displayName;
	
	Mood(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public static Mood getInstance(int value) {
		for (Mood mood : Mood.values()) {
			if (value == mood.getValue()) {
				return mood;
			}
		}
		return null;
	}
	
}
