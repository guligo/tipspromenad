package co.vrings.entities.enums;

/**
 * Represents intensity of sports activity.
 * 
 * @author guligo
 */
public enum Intensity {
	
	NOT_SPECIFIED(0, "Not specified"),
	VERY_LOW     (1, "Very low"),
	LOW          (2, "Low"),
	NORMAL       (3, "Normal"),
	HIGH         (4, "High"),
	VERY_HIGH    (5, "Very high");
	
	private int value;
	private String displayName;
	
	Intensity(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public static Intensity getInstance(int value) {
		for (Intensity intensity : Intensity.values()) {
			if (value == intensity.getValue()) {
				return intensity;
			}
		}
		return null;
	}
	
}
