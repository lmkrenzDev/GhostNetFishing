package com.ghostnetfishing.controller;

import com.ghostnetfishing.bean.Ghostnet;
import com.ghostnetfishing.dao.GhostNetDAO;

import java.io.Serializable;
import java.util.List;

import org.primefaces.PrimeFaces;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Klasse für die Verwaltung der index.xhtml
 */
@Named("ghostnetManagementController")
@ViewScoped
public class GhostnetManagementController implements Serializable {

	private List<Ghostnet> ghostnets;

	private Ghostnet selectedGhostNet;
		
	@Inject
	private LoginController loginController;
	
	private GhostNetDAO ghostNetDAO = new GhostNetDAO();
	

	@PostConstruct
	public void init() {
		ghostnets = ghostNetDAO.findAll();
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
		//das Setzen des Status ist abhängig von dem aktuellen Status
		// ist eine Statuswechsel nicht möglich, erhält der Nutzer eine Fehlermeldung in Form eines Popup-Dialogs
		if(selectedGhostNet.getStatus().equals("Gemeldet") && status.equals("Bergung bevorstehend")) {
			selectedGhostNet.setStatus(status);		
			selectedGhostNet.setSavingPerson(loginController.getLoggedInUser());
			ghostNetDAO.update(selectedGhostNet);
		}
		else if(selectedGhostNet.getStatus().equals("Bergung bevorstehend") && status.equals("Geborgen")) {
			selectedGhostNet.setStatus(status);	
			ghostNetDAO.update(selectedGhostNet);
		}
		else if(selectedGhostNet.getStatus().equals("Bergung bevorstehend") && status.equals("Verschollen")) {
			selectedGhostNet.setStatus(status);	
			selectedGhostNet.setSavingPerson(null);
			ghostNetDAO.update(selectedGhostNet);
		}
		else if(selectedGhostNet.getStatus().equals("Gemeldet") && status.equals("Verschollen")) {
			selectedGhostNet.setStatus(status);
			selectedGhostNet.setSavingPerson(null);
			ghostNetDAO.update(selectedGhostNet);
		}
		else{
			PrimeFaces.current().executeScript("PF('ErrorDialogStatus').show();");
		}
	}
	
}
