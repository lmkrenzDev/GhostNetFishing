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
	private GhostNetDAO ghostNetDAO;

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

			int nr = ghostNetDAO.findMaxId() + 1;
			Date now = new Date();
			String status = "Gemeldet";

			Ghostnet newGhostnet = new Ghostnet(nr,longitude, latitude, estimatedSize, status, now, now);
			ghostNetDAO.save(newGhostnet);

			return "index.xhtml";

		} catch (Exception e) {
			return "";
		}
	}

	public int getNextNumber() {
		return ghostNetDAO.findMaxId() + 1;
	}

}
