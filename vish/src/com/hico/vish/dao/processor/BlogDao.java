package com.hico.vish.dao.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.util.CategoryUtil;

public class BlogDao extends BaseDao<Blog>{

	public void addArticle(Article article) {
		Key blogKey=article.getBlog().getKey();
		article.setBlog(null);
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			Blog blog=persistenceManager.getObjectById(Blog.class,blogKey);
			article.setBlog(blog);
			blog.getArticles().add(article);
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
	
	
	public void addCategory(Category category) {
		Key blogKey=category.getBlog().getKey();
		category.setBlog(null);
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			Blog blog=persistenceManager.getObjectById(Blog.class,blogKey);
			category.setBlog(blog);
			blog.getCategories().add(category);
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
	
	
	public void deleteCategory(Key categoryKey,boolean isDeleteArticle) {
		Key blogKey=categoryKey.getParent();
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			Blog blog=persistenceManager.getObjectById(Blog.class,blogKey);
			List<Category> allCategories=blog.getCategories();
			List<Article> allArticles=blog.getArticles();
			List<Category> subsAndSelf=CategoryUtil.getSubCategoriesAndSelf(allCategories, blogKey);
			List<Article> articlesUnderCategory=CategoryUtil.getArticlesUnderCategory(allArticles, subsAndSelf);
			allArticles.removeAll(articlesUnderCategory);
			allCategories.removeAll(subsAndSelf);
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
	
	
	public Blog fetchBlogArticle(Object id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			persistenceManager.getFetchPlan().addGroup("articleGroup");
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
	
	@SuppressWarnings("unchecked")
	public Blog getByNameWithArticles(String name) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			persistenceManager.getFetchPlan().addGroup("articleGroup");
			Query query=persistenceManager.newQuery(Blog.class);
			query.setFilter("name == paramName");
			query.declareParameters("String paramName");
			List<Blog> blogs=(List<Blog>)query.execute(name);
			return (blogs==null||blogs.isEmpty())?null:blogs.get(0);
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
