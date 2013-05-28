package com.hico.vish.dao.processor;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;


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
	
	public List<Category> getUserCategory(UserEntity owner){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Category.class);
			query.setFilter("owner == paramOwner");
			query.declareParameters(Key.class.getName()+" paramOwner");
			Object result=query.execute(owner.getKey());
			return result!=null?(List<Category>)result:null;
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
