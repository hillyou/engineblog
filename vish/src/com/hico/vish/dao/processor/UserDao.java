package com.hico.vish.dao.processor;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.hico.vish.dao.table.UserEntity;

public class UserDao {

	private PersistenceManagerFactory persistenceManagerFactory;
	
	public UserEntity getUserByEmail(String email) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Query query=persistenceManager.newQuery(UserEntity.class);
		query.setFilter("email== useremail");
		query.declareParameters("String useremail");
		query.setUnique(true);
		UserEntity user=(UserEntity) query.execute(email);
		return user;
	}
	
	public UserEntity getUserById(String id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Query query=persistenceManager.newQuery(UserEntity.class);
		query.setFilter("userId== userIdValue");
		query.declareParameters("String userIdValue");
		query.setUnique(true);
		UserEntity user=(UserEntity) query.execute(id);
		return user;
	}
	
	public void saveUser(UserEntity user) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			persistenceManager.makePersistent(user);
			transaction.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			if(transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	/**
	 * @return the persistenceManagerFactory
	 */
	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	/**
	 * @param persistenceManagerFactory the persistenceManagerFactory to set
	 */
	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}
}
