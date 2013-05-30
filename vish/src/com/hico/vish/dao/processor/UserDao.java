package com.hico.vish.dao.processor;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.hico.vish.dao.table.UserEntity;

public class UserDao extends BaseDao<UserEntity>{
	
	public UserEntity getUserByEmail(String email) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(UserEntity.class);
			query.setFilter("email== useremail");
			query.declareParameters("String useremail");
			query.setUnique(true);
			UserEntity user=(UserEntity) query.execute(email);
			return user;
		}finally{
			persistenceManager.close();
		}
	}
	
}
