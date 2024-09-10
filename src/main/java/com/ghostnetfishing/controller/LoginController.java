package com.ghostnetfishing.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.ghostnetfishing.bean.User;
import com.ghostnetfishing.dao.UserDAO;

/**
 * Klasse für die Verwaltung der signin.xhtml
 * Hierüber kann sich der Benutzer mit Benutzername und Passwort anmelden
 * SessionScoped damit loggedInUser weiter verwendet werden kann
 */

@Named
@SessionScoped
public class LoginController implements Serializable {
	private String username;
	private String password;
	private User currentUser;
	private User loggedInUser;

	private UserDAO userDAO = new UserDAO();
	

	public String login() {
		// keine weitere Überprüfung notwendig, da Validierung durch Post Process
		loggedInUser = currentUser;
		return "index?faces-redirect=true";
	}

	public void postValidateUser(ComponentSystemEvent ev) {
		UIInput temp = (UIInput) ev.getComponent();
		this.username = (String) temp.getValue();
	}

	public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String) value;
		currentUser = findUserByName(username);
		if (currentUser == null) {
			throw new ValidatorException(new FacesMessage("User wurde nicht gefunden"));
		}

	}

	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password = (String) value;
		boolean passwordValid = validateUsernameAndPassword(currentUser, username, password);
		if (!passwordValid) {
			throw new ValidatorException(new FacesMessage("Passwort falsch!"));
		}
	}

	public String logout() {
		loggedInUser = null;
		currentUser = null;
		return "index?faces-redirect=true";
	}

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

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	

	/**
	 * Methode für die Validierung des Benutzernamens, prüft ob der Benutzername in
	 * der Datenbank vorhanden ist bzw. ob dieser bereits registriert wurde
	 * 
	 * @param username: Benutzername, der geprüft werden soll
	 * @return Benutzer, wenn dieser in der Datenbank gefunden wurde, ansonsten null
	 */
	public User findUserByName(String username) {
		List<User> users = userDAO.findAll();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Überprüft, ob das eingegenene Passwort zu dem vorher gesuchten Benutzer
	 * passt. Es werden jeweils die Hashes der Passwörter verglichen
	 * 
	 * @param currentUser: vorher gesuchter Benutzer anhand des Benutezrnamens
	 * @param username:    Benutzername des gesuchten Benutzers
	 * @param password:    Passwort, welches überprüft werden soll
	 * @return true, wenn Benutzername und Passwort zusammen passen
	 */
	public boolean validateUsernameAndPassword(User currentUser, String username, String password) {

		if (currentUser == null) {
			return false;
		}

		if (currentUser.getPasswordHash().equals(User.hashPassword(username, password, currentUser.getSalt()))) {
			return true;
		} else {
			return false;
		}

	}
}
