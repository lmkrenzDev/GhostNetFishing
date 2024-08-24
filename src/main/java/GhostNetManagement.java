import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.persistence.PersistenceUnit;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Named("ghostNetManagement")
@ApplicationScoped
public class GhostNetManagement {
	
//    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetmanagement");
	private EntityManagerFactory emf;
	private EntityManager entityManager;

	private final List<Ghostnet> ghostnets = new ArrayList<Ghostnet>();

	private List<User> users = new ArrayList<>();

	public GhostNetManagement() {
		Ghostnet ghostNet1 = new Ghostnet(53.387088, 4.693777, 100, "Gemeldet",
				(new GregorianCalendar(2022, 6, 23).getTime()), (new GregorianCalendar(2023, 1, 15).getTime()));
		ghostnets.add(ghostNet1);

		Ghostnet ghostNet2 = new Ghostnet(69.075299, 12.621785, 50, "Gemeldet",
				(new GregorianCalendar(2012, 11, 23).getTime()), (new GregorianCalendar(2014, 5, 13).getTime()));
		ghostnets.add(ghostNet2);

		Ghostnet ghostNet3 = new Ghostnet(69.075299, 12.621785, 50, "Gemeldet",
				(new GregorianCalendar(2012, 11, 23).getTime()), (new GregorianCalendar(2014, 5, 13).getTime()));
		ghostnets.add(ghostNet3);

		Ghostnet ghostNet4 = new Ghostnet(69.075299, 12.621785, 50, "Gemeldet",
				(new GregorianCalendar(2012, 11, 23).getTime()), (new GregorianCalendar(2014, 5, 13).getTime()));
		ghostnets.add(ghostNet4);
		
		try {
            emf = Persistence.createEntityManagerFactory("ghostnetmanagement");
            entityManager = emf.createEntityManager();
            System.out.println("EntityManager created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        	System.out.print("test");
        }
		

	}

	public List<Ghostnet> getGhostnets() {
		
//		EntityManager em = emf.createEntityManager();
//		Query query = em.createQuery("SELECT * from Ghostnet");
//		List<Ghostnet> ghostnets = query.getResultList();
//		System.out.println(ghostnets.size());
//		return ghostnets;
		
		return ghostnets;
		
	}

	public void addGhostnet(Ghostnet ghostnet) {
		ghostnets.add(ghostnet);
	}

	public int getcurrentSize() {
		return ghostnets.size();
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		users.add(user);
	}

	public int getUserCount() {
		return users.size();
	}

	public User validateUserName(String username) {
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
