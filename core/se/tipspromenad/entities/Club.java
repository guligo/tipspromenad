package se.tipspromenad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents club.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Entity
@Table(name = "clubs")
public class Club extends se.tipspromenad.entities.Entity {

	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = true, unique = false)
	private String url;
	@Column(nullable = false)
	private Double latitude;
	@Column(nullable = false)
	private Double longitude;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
