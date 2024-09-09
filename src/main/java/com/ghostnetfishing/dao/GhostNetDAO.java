package com.ghostnetfishing.dao;

import jakarta.persistence.*;
import java.util.List;

import com.ghostnetfishing.bean.Ghostnet;

/**
 * Data Access Object für ein Geisternetz
 * Klasse für den gekappselten Zugriff auf die Geisternetze in der Datenbank
 */
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

	/**
	 * Abfrage aller Geisternetze, die in der Datenbank vorhanden sind
	 * @return
	 */
	public List<Ghostnet> findAll() {
		TypedQuery<Ghostnet> query = entityManager.createQuery("select g from Ghostnet g", Ghostnet.class);
		return query.getResultList();
	}

	/**
	 * speichern eines neunen Geisternetzes
	 * @param ghostnet: zu speicherndes Geisternetz
	 */
	public void save(Ghostnet ghostnet) {

		EntityTransaction transaction = null;

		try {
			//Beginn eines Transaktion
			transaction = entityManager.getTransaction();
			transaction.begin();

			//Persistierung
			entityManager.persist(ghostnet);

			// Bestätigen, dass Transkation vollendet
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * Speichern der Änderungen an einem Geisternetzes
	 * wird vor allem für die Änderung des Status eines Geisternetzes benötigt
	 * @param ghostnet: zu veränderndes Geisternetz inkl. der Änderungen
	 */
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

	/**
	 * Abfrage der letzten ID eines Geisternetzes, um ein neues Geisternetz mit einer nächst höheren ID gespeichert werden kann
	 * @return: höchste ID eines gespeicherten Geisternetzes, falls kein Geisternetz vorhanden ist, wird 0 zurückgegegben
	 */
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
