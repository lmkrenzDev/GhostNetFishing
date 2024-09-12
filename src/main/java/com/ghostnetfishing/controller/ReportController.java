package com.ghostnetfishing.controller;

import com.ghostnetfishing.bean.Ghostnet;
import com.ghostnetfishing.dao.*;

import java.io.Serializable;
import java.util.Date;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * Klasse für die Verwaltung der Report.xhtml in der ein neues Geisternetz angelegt werden kann
 */
@Named
@ViewScoped
public class ReportController implements Serializable {

	private int nr;

	private double longitude;
	private double latitude;
	private double estimatedSize;


	private GhostNetDAO ghostNetDAO = new GhostNetDAO();

	// Getter und Setter
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

	/**
	 * Methode zum Speichern eines neuen Geisternetzes mithilfe der GhostnetDAO
	 * @return
	 */
	public String saveReport() {
		try {

			int nr = ghostNetDAO.findMaxId() + 1;
			Date now = new Date();
			String status = "Gemeldet";

			Ghostnet newGhostnet = new Ghostnet(nr,longitude, latitude, estimatedSize, status, now);
			ghostNetDAO.save(newGhostnet);

			//Weiterleitung zur Startseite
			return "index.xhtml?faces-redirect=true";

		} catch (Exception e) {
			return "";
		}
	}

	public int getNextNumber() {
		return ghostNetDAO.findMaxId() + 1;
	}

}
