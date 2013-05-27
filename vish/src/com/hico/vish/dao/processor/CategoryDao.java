package com.hico.vish.dao.processor;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.hico.vish.dao.table.Category;


public class CategoryDao {

	private PersistenceManagerFactory persistenceManagerFactory;
	
	
	public Category getById(Long id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Category category=persistenceManager.getObjectById(Category.class,id);
			return category;
		}finally{
			persistenceManager.close();
		}
	}
	
	public void saveCategory(Category category) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			persistenceManager.makePersistent(category);
			transaction.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			if(transaction.isActive()) {
				transaction.rollback();
			}
		}finally{
			persistenceManager.close();
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
