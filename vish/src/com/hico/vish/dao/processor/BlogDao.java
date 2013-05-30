package com.hico.vish.dao.processor;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.UserEntity;

public class BlogDao extends BaseDao<Blog>{

	public Blog get(Object id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Blog blog=persistenceManager.getObjectById(Blog.class, id);
			return blog;
		}finally{
			persistenceManager.close();
		}
	}
	
	public List<Blog> getUserAllBlogs(UserEntity user) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Blog.class);
			query.setFilter("owner == paramOwner");
			query.declareParameters(Key.class.getSimpleName()+" paramOwner");
			List<Blog> blogs=(List<Blog>) query.execute(user.getKey());
			return blogs;
		}finally{
			persistenceManager.close();
		}
	}
	
	public Blog getByName(String name) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Blog.class);
			query.setFilter("name == paramName");
			query.declareParameters("String paramName");
			Blog blog=(Blog) query.execute(name);
			return blog;
		}finally{
			persistenceManager.close();
		}
	}
	
	public List<Blog> getBlogList(){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Blog.class);
			List<Blog> blogs=(List<Blog>) query.execute();
			return blogs;
		}finally{
			persistenceManager.close();
		}
	}
	
	
	public List<Blog> getBlogList(UserEntity user){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Blog.class);
			query.setFilter("owner == paramOwner && isDelete == false && isValid == true");
			query.declareParameters(Key.class.getName()+" paramOwner");
			List<Blog> blogs=(List<Blog>) query.execute(user.getKey());
			return blogs;
		}finally{
			persistenceManager.close();
		}
	}
	
}
