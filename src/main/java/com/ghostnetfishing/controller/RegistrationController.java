package com.ghostnetfishing.controller;

import java.io.Serializable;

import com.ghostnetfishing.bean.User;
import com.ghostnetfishing.dao.UserDAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 * Klasse für die Verwaltung der signup.xhtml
 */
@Named
@RequestScoped
public class RegistrationController implements Serializable {
	private String username;
	private String password;
	private String surname;
	private String name;
	private String phoneNumber;

	
	private UserDAO userDAO = new UserDAO();

	/**
	 * Speichern eines neuen Users in der Datenbank mithilfe der UserDAO
	 * @return
	 */
	public String signup() {

		FacesContext context = FacesContext.getCurrentInstance();

		User user = new User(username, password, surname, name, phoneNumber);
		userDAO.save(user);

		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Registrierung erfolgreich."));
		context.getPartialViewContext().getRenderIds().add("messages");

		return "index.xhtml?faces-redirect=true";

	}

	// Getter und Setter für Benutzername, Passwort, Telefonnummer, Name und Vorname

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}