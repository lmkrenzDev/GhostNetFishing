import java.io.Serializable;
import java.util.Date;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ReportController implements Serializable {

	private int nr;

	private double longitude;
	private double latitude;
	private double estimatedSize;

	@Inject
	private GhostNetManagement ghostNetManagement;

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

	public String saveReport() {
		try {
			int nr = ghostNetManagement.getcurrentSize() + 1;
			Date now = new Date();
			String status = "Gemeldet"; // Standardstatus

			Ghostnet newGhostnet = new Ghostnet(nr, longitude, latitude, estimatedSize, status, now, now);
			ghostNetManagement.addGhostnet(newGhostnet);
			
			return "index.xhtml";

//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Ghostnet created successfully"));
		} catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to create Ghostnet"));
			
			return "";
		}
	}

	public int getNextNumber() {
		return ghostNetManagement.getcurrentSize() + 1;

	}

}
