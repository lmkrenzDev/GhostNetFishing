import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import java.util.List;

@Named
@ApplicationScoped
public class GhostNetDAO {

	private EntityManager entityManager;

	public GhostNetDAO() {
		try {
			entityManager = Persistence.createEntityManagerFactory("ghostnetmanagement").createEntityManager();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Ghostnet> findAll() {
		TypedQuery<Ghostnet> query = entityManager.createQuery("select g from Ghostnet g", Ghostnet.class);
		return query.getResultList();
	}

	public void save(Ghostnet ghostnet) {

		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			entityManager.persist(ghostnet);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public void update(Ghostnet ghostnet) {
		
		
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			entityManager.merge(ghostnet);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
	public int findMaxId() {
	    try {
	        TypedQuery<Integer> query = entityManager.createQuery("SELECT MAX(g.id) FROM Ghostnet g", Integer.class);
	        
	        Integer maxId = query.getSingleResult();
	        System.out.print(maxId);
	        
	        return (maxId != null) ? maxId : 0;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}
}
