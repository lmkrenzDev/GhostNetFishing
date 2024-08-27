import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import java.util.List;

@Named
@ApplicationScoped
public class UserDAO {

	private EntityManager entityManager;

	public UserDAO() {
		try {
			entityManager = Persistence.createEntityManagerFactory("ghostnetmanagement").createEntityManager();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
		return query.getResultList();
	}

	public void save(User user) {

		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			entityManager.persist(user);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public void update(User user) {
		
		
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			entityManager.merge(user);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	
}
