import java.util.List;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named("ghostNetManagement")
@ApplicationScoped
public class GhostNetManagement {

    @Inject
    private GhostNetDAO ghostNetDAO;
    
    @Inject UserDAO userDAO;

    private List<Ghostnet> ghostnets;

	public GhostNetManagement() {

	}

	public List<Ghostnet> getGhostnets() {
		
		ghostnets = ghostNetDAO.findAll();
		return ghostnets;
	}


	public User validateUserName(String username) {
		List<User> users = userDAO.findAll();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

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
