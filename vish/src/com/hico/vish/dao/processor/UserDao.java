package com.hico.vish.dao.processor;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Blog;
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

	public UserEntity addBlog(Blog blog) {
		UserEntity user=null;
		Key userKey=blog.getBlogger().getKey();
		blog.setBlogger(null);
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			user=persistenceManager.getObjectById(UserEntity.class,userKey);
			blog.setBlogger(user);
			user.addBlog(blog);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
		return user;
	}
	
}
