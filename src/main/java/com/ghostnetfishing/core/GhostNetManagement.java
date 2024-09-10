package com.ghostnetfishing.core;

import java.util.ArrayList;
import java.util.List;

import com.ghostnetfishing.bean.Ghostnet;
import com.ghostnetfishing.bean.User;
import com.ghostnetfishing.dao.GhostNetDAO;
import com.ghostnetfishing.dao.UserDAO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/*
 * Die Klasse GhostNetManagement dient als oberste Verwaltungsklasse für die Webseite
 * 
 */
@Named("ghostNetManagement")
@ApplicationScoped
public class GhostNetManagement {

	private GhostNetDAO ghostNetDAO = new GhostNetDAO();

	private UserDAO userDAO = new UserDAO();

	private List<Ghostnet> ghostnets;

	public GhostNetManagement() {

	}

	/**
	 * Methode zum Abfragen aller Geisternetze mithilfe des Data Access Objects für
	 * die Geisternetze
	 * 
	 * @return Liste aller Geisternetze aus der Datenbank
	 */
	public List<Ghostnet> getGhostnets() {

		ghostnets = ghostNetDAO.findAll();
		return ghostnets;
	}

	/**
	 * Methode für die Validierung des Benutzernamens, prüfz ob der benutzername in
	 * der Datenbank vorhanden ist bzw. ob dieser bereits registriert wurde
	 * 
	 * @param username: Benutzername, der geprüft werden soll
	 * @return Benutzer, wenn dieser in der Datenbank gefunden wurde, ansonsten null
	 */
	public User validateUserName(String username) {
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
