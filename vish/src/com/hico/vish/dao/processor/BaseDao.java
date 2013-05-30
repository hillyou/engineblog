package com.hico.vish.dao.processor;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.hico.vish.dao.table.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> {

	protected PersistenceManagerFactory persistenceManagerFactory;
	protected Class clazz;
	
	
	public void save(T t) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			t.setCreateDate(new Date());
			persistenceManager.makePersistent(t);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
	}
	
	public void update(T t) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			persistenceManager.makePersistent(t);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
	}
	
	
	public void delete(T t){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try{
			persistenceManager.deletePersistent(t);
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
	}

	public T get(Object id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			T t=(T) persistenceManager.getObjectById(clazz, id);
			return t;
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

	/**
	 * @return the clazz
	 */
	public Class getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	
	
}
