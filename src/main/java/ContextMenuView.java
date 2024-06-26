import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("dtContextMenuView")
@ViewScoped
public class ContextMenuView implements Serializable {

	private List<Ghostnet> ghostnets;

	private Ghostnet selectedGhostNet;

	@Inject
	private GhostNetManagement ghostNetManagement;

	@PostConstruct
	public void init() {
		ghostnets = ghostNetManagement.getGhostnets();
	}

	// Getter/ Setter
	public List<Ghostnet> getGhostnets() {
		return ghostnets;
	}

	public void setGhostnets(List<Ghostnet> ghostnets) {
		this.ghostnets = ghostnets;
	}

	public Ghostnet getSelectedGhostNet() {
		return selectedGhostNet;
	}

	public void setSelectedGhostNet(Ghostnet selectedGhostNet) {
		this.selectedGhostNet = selectedGhostNet;
	}

	public void setGhostNetStatus(String status) {
		selectedGhostNet.setStatus(status);
	}

	public boolean renderMenuItem(String statusTransition) {
		System.out.println("Test1");
		if(selectedGhostNet!= null) {
			System.out.println("Test2");
			if(statusTransition == "Bergen" && selectedGhostNet.getStatus() == "Gemeldet") {
				System.out.println("Test3");
				return true;
			}
		}
		
		return false;
	}

}
