package com.ghostnetfishing.dao;

import jakarta.persistence.*;
import java.util.List;

import com.ghostnetfishing.bean.User;

/**
 * Data Access Object für einen User, welches registriert wurde
 */
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

	/**
	 * Abfrage aller in der Datenbank gespeicherten User
	 * @return
	 */
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
		return query.getResultList();
	}

	/**
	 * Speichern eines neuen Users bei der Registrierung
	 * @param user
	 */
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
