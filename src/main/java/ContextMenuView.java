import java.io.Serializable;
import java.util.List;

import org.primefaces.PrimeFaces;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("dtContextMenuView")
@ViewScoped
public class ContextMenuView implements Serializable {

	private List<Ghostnet> ghostnets;

	private Ghostnet selectedGhostNet;
	
	private User selectedPerson;


	@Inject
	private GhostNetManagement ghostNetManagement;
	
	@Inject
	private LoginController loginController;

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
	
	public User getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(User selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public void setGhostNetStatus(String status) {
		if(selectedGhostNet.getStatus().equals("Gemeldet") && status.equals("Bergung bevorstehend")) {
			selectedGhostNet.setStatus(status);		
			selectedGhostNet.setSavingPerson(loginController.getLoggedInUser());
		}
		else if(selectedGhostNet.getStatus().equals("Bergung bevorstehend") && status.equals("Geborgen")) {
			selectedGhostNet.setStatus(status);	
		}
		else if(selectedGhostNet.getStatus().equals("Bergung bevorstehend") && status.equals("Verschollen")) {
			selectedGhostNet.setStatus(status);	
		}
		else if(selectedGhostNet.getStatus().equals("Gemeldet") && status.equals("Verschollen")) {
			selectedGhostNet.setStatus(status);	
		}
		else{
			PrimeFaces.current().executeScript("PF('ErrorDialogStatus').show();");
		}
	}
	
    public void onPersonNameClick(User person) {
    	System.out.println(person.getName());
        this.selectedPerson = person;
    }

}
