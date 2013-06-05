package com.hico.vish.dao.processor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> {

	protected PersistenceManagerFactory persistenceManagerFactory;
	
	
	public void save(T t) {
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
	
	public void delete(Collection<T> t){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try{
			persistenceManager.deletePersistentAll(t);
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
			Class<T> clazz=getGenericClass();
			return persistenceManager.getObjectById(clazz,id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
		return null;
	}
	
	public Collection<T> get(Collection<Key> ids,Class<T> clazz) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		List newIds=new ArrayList();
		try{
			for(Key key : ids){
				newIds.add(persistenceManager.newObjectIdInstance(clazz, key));
			}
			return persistenceManager.getObjectsById(newIds);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
		return null;
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
	
	private Class<T> getGenericClass(){
        Class<T> result =null;
        Type type =this.getClass().getGenericSuperclass();

        if(type instanceof ParameterizedType){
             ParameterizedType pt =(ParameterizedType) type;
             Type[] fieldArgTypes = pt.getActualTypeArguments();
             result =(Class<T>) fieldArgTypes[0];
       }
       return result;
	}
}
