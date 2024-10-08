package com.ghostnetfishing.bean;

import java.util.Date;

import jakarta.persistence.*;

/**
 * Bean für ein Geisternetz
 */
@Entity
public class Ghostnet {

	@Id
	private int nr;

	private double longitude;
	private double latitude;
	private double estimatedSize;
	private String status;
	private Date recordingDate;
	// n:1-Beziehung zu einer User-Bean
	@ManyToOne
	private User savingPerson;



	public Ghostnet(int nr,double longitude, double latitude, double estimatedSize, String status, Date recordingDate) {
		this.nr = nr;
		this.longitude = longitude;
		this.latitude = latitude;
		this.status = status;
		this.recordingDate = recordingDate;
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
	
	public User getSavingPerson() {
		return savingPerson;
	}

	public void setSavingPerson(User savingPerson) {
		this.savingPerson = savingPerson;
	}

}
