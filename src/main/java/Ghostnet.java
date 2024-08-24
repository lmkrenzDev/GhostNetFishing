import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ghostnet {

	@Id
	@GeneratedValue
	private int nr;

	private double longitude;
	private double latitude;
	private double estimatedSize;
	private String status;
	private Date recordingDate;
	private Date lastUpDate;
	private User savingPerson;



	public Ghostnet(double longitude, double latitude, double estimatedSize, String status, Date recordingDate,
			Date lastUpate) {
//		this.nr = nr;
		this.longitude = longitude;
		this.latitude = latitude;
		this.status = status;
		this.recordingDate = recordingDate;
		this.lastUpDate = lastUpate;
		this.estimatedSize = estimatedSize;
	}
	
	public Ghostnet() {
		
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getEstimatedSize() {
		return estimatedSize;
	}

	public void setEstimatedSize(double estimatedSize) {
		this.estimatedSize = estimatedSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	public Date getLastUpDate() {
		return lastUpDate;
	}

	public void setLastUpDate(Date lastUpDate) {
		this.lastUpDate = lastUpDate;
	}
	
	public User getSavingPerson() {
		return savingPerson;
	}

	public void setSavingPerson(User savingPerson) {
		this.savingPerson = savingPerson;
	}

}
